package isp.lab8.airways;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class AirwaysTest {

    private Waypoint cluj;
    private Waypoint tasod;
    private Waypoint sopav;
    private Waypoint birgu;
    private Waypoint lrop;

    @Before
    public void setUp() {
        // Sample route coordinates provided in lab specification
        cluj = new Waypoint("LRCL", 46.7852, 23.6862, 415);
        tasod = new Waypoint("TASOD", 47.0548, 23.9212, 10460);
        sopav = new Waypoint("SOPAV", 46.9804, 24.7365, 10900);
        birgu = new Waypoint("BIRGU", 45.9467, 26.0217, 10200);
        lrop = new Waypoint("LROP", 44.5711, 26.0858, 106);
    }

    @Test
    public void testIndividualDistanceCalculation() {
        // Calculate the distance between Cluj and Tasod
        double distance = WaypointDistanceCalculator.calculateDistance(cluj, tasod);

        // Check if distance is calculated correctly (greater than zero)
        assertTrue("Distance should be greater than 0", distance > 0);

        // Exact expected geometric distance is approximately 34.6 km
        assertEquals(34.6, distance, 1.5);
    }

    @Test
    public void testTotalRouteDistance() {
        List<Waypoint> route = new ArrayList<>();
        route.add(cluj);
        route.add(tasod);
        route.add(sopav);
        route.add(birgu);
        route.add(lrop);

        double totalDistance = WaypointDistanceCalculator.calculateTotalRouteDistance(route);

        // Verify total flight plan mileage sums up appropriately
        assertTrue("Total distance should be greater than single segments", totalDistance > 300);
        assertEquals(392.5, totalDistance, 10.0);
    }

    @Test
    public void testEmptyOrSingleWaypointRoute() {
        List<Waypoint> emptyRoute = new ArrayList<>();
        List<Waypoint> singleRoute = new ArrayList<>();
        singleRoute.add(cluj);

        assertEquals(0.0, WaypointDistanceCalculator.calculateTotalRouteDistance(emptyRoute), 0.001);
        assertEquals(0.0, WaypointDistanceCalculator.calculateTotalRouteDistance(singleRoute), 0.001);
        assertEquals(0.0, WaypointDistanceCalculator.calculateTotalRouteDistance(null), 0.001);
    }
}