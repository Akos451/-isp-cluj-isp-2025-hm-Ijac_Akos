package isp.lab8.airways;

import java.io.*;
import java.util.*;

public class Exercise {
    private static final String BASE_DIR = "routes";

    public static void main(String[] args) {
        // Ensure base directory exists
        File baseFolder = new File(BASE_DIR);
        if (!baseFolder.exists()) {
            baseFolder.mkdir();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== AIRPLANE ROUTE MANAGEMENT =====");
            System.out.println("1. Create New Route");
            System.out.println("2. Add Waypoint to Route");
            System.out.println("3. Load & View Route Summary");
            System.out.println("4. Delete Route");
            System.out.println("5. List All Available Routes");
            System.out.println("6. Inject Demo Data (LRCL-LROP)");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter unique route name: ");
                    String name = scanner.nextLine();
                    createRoute(name);
                    break;
                case 2:
                    System.out.print("Enter target route name: ");
                    String rTarget = scanner.nextLine();
                    System.out.print("Waypoint Name: ");
                    String wpName = scanner.nextLine();
                    System.out.print("Latitude: ");
                    double lat = scanner.nextDouble();
                    System.out.print("Longitude: ");
                    double lon = scanner.nextDouble();
                    System.out.print("Altitude (meters): ");
                    int alt = scanner.nextInt();

                    addWaypointToRoute(rTarget, new Waypoint(wpName, lat, lon, alt));
                    break;
                case 3:
                    System.out.print("Enter route name to load: ");
                    String rLoad = scanner.nextLine();
                    List<Waypoint> wps = loadRoute(rLoad);
                    if (wps != null) {
                        System.out.println("\n--- Waypoints in " + rLoad + " ---");
                        for (Waypoint wp : wps) {
                            System.out.println(wp);
                        }
                        double totalKm = WaypointDistanceCalculator.calculateTotalRouteDistance(wps);
                        System.out.printf("Total Route Distance: %.2f km\n", totalKm);
                    }
                    break;
                case 4:
                    System.out.print("Enter route name to delete: ");
                    String rDelete = scanner.nextLine();
                    deleteRoute(rDelete);
                    break;
                case 5:
                    listAllRoutes();
                    break;
                case 6:
                    String demoRoute = "LRCL-LROP";
                    createRoute(demoRoute);
                    addWaypointToRoute(demoRoute, new Waypoint("LRCL", 46.7852, 23.6862, 415));
                    addWaypointToRoute(demoRoute, new Waypoint("TASOD", 47.0548, 23.9212, 10460));
                    addWaypointToRoute(demoRoute, new Waypoint("SOPAV", 46.9804, 24.7365, 10900));
                    addWaypointToRoute(demoRoute, new Waypoint("BIRGU", 45.9467, 26.0217, 10200));
                    addWaypointToRoute(demoRoute, new Waypoint("LROP", 44.5711, 26.0858, 106));
                    System.out.println("Demo route 'LRCL-LROP' loaded successfully!");
                    break;
                case 7:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }

    private static void createRoute(String routeName) {
        File routeFolder = new File(BASE_DIR, routeName);
        if (routeFolder.exists()) {
            System.out.println("Route '" + routeName + "' already exists.");
            return;
        }
        if (routeFolder.mkdir()) {
            System.out.println("Route folder '" + routeName + "' initialized.");
        } else {
            System.out.println("Failed to create route directory.");
        }
    }

    private static void addWaypointToRoute(String routeName, Waypoint waypoint) {
        File routeFolder = new File(BASE_DIR, routeName);
        if (!routeFolder.exists()) {
            System.out.println("Route '" + routeName + "' does not exist!");
            return;
        }

        File waypointFile = new File(routeFolder, waypoint.getName() + ".ser");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(waypointFile));
            oos.writeObject(waypoint);
            System.out.println("Waypoint '" + waypoint.getName() + "' serialized.");
        } catch (IOException e) {
            System.out.println("Error saving waypoint: " + e.getMessage());
        } finally {
            if (oos != null) {
                try { oos.close(); } catch (IOException ignored) {}
            }
        }
    }

    private static List<Waypoint> loadRoute(String routeName) {
        File routeFolder = new File(BASE_DIR, routeName);
        List<Waypoint> waypoints = new ArrayList<>();

        if (!routeFolder.exists() || !routeFolder.isDirectory()) {
            System.out.println("Route '" + routeName + "' not found.");
            return null;
        }

        File[] files = routeFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".ser")) {
                    ObjectInputStream ois = null;
                    try {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        waypoints.add((Waypoint) ois.readObject());
                    } catch (Exception e) {
                        System.out.println("Error reading waypoint file: " + file.getName());
                    } finally {
                        if (ois != null) {
                            try { ois.close(); } catch (IOException ignored) {}
                        }
                    }
                }
            }
        }
        return waypoints;
    }

    private static void deleteRoute(String routeName) {
        File routeFolder = new File(BASE_DIR, routeName);
        if (!routeFolder.exists()) {
            System.out.println("Route '" + routeName + "' does not exist.");
            return;
        }

        File[] files = routeFolder.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }

        if (routeFolder.delete()) {
            System.out.println("Route '" + routeName + "' deleted completely.");
        } else {
            System.out.println("Deletion failed.");
        }
    }

    private static void listAllRoutes() {
        File baseFolder = new File(BASE_DIR);
        File[] files = baseFolder.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No routes found.");
            return;
        }

        System.out.println("\n--- Available Tracked Folders ---");
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(" -> " + file.getName());
            }
        }
    }
}