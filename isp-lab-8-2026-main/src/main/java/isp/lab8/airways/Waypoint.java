package isp.lab8.airways;

import java.io.Serializable;

public class Waypoint implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double latitude;
    private double longitude;
    private int altitude;

    public Waypoint(String name, double latitude, double longitude, int altitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public int getAltitude() { return altitude; }

    @Override
    public String toString() {
        return String.format("Waypoint: %-6s | Lat: %7.4f° | Lon: %7.4f° | Alt: %d m",
                name, latitude, longitude, altitude);
    }
}