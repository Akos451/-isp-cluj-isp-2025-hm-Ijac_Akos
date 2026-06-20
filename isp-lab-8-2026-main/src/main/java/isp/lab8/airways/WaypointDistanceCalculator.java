package isp.lab8.airways;

import java.lang.Math;
import java.util.List;

/**
 * Example how to calculate distance between 2 geographical points. Reuse part of this code in your application.
 */
public class WaypointDistanceCalculator {

    public static void main(String[] args) {
        // Example coordinates for two waypoints
        //Cluj-Napoca
        double lat1 = 46.7712;
        double lon1 = 23.6236;
        //Bucharest
        double lat2 = 44.4268;
        double lon2 = 26.1025;

        // Calculate the distance between the two waypoints
        double distance = calculateDistance(lat1, lon1, lat2, lon2);

        // Print the result
        System.out.println("The distance between the two waypoints is: " + distance + " kilometers");
    }

    // Method to calculate the distance between two waypoints using the haversine formula
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        int earthRadius = 6371; // Radius of the Earth in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return distance;
    }

    // --- Added helper methods for your application ---

    /**
     * Helper method to calculate distance directly using Waypoint objects
     */
    public static double calculateDistance(Waypoint w1, Waypoint w2) {
        return calculateDistance(w1.getLatitude(), w1.getLongitude(), w2.getLatitude(), w2.getLongitude());
    }

    /**
     * Calculates total cumulative path length across an ordered list of waypoints.
     */
    public static double calculateTotalRouteDistance(List<Waypoint> waypoints) {
        if (waypoints == null || waypoints.size() < 2) {
            return 0.0;
        }
        double totalDistance = 0.0;
        for (int i = 0; i < waypoints.size() - 1; i++) {
            totalDistance += calculateDistance(waypoints.get(i), waypoints.get(i + 1));
        }
        return totalDistance;
    }
}