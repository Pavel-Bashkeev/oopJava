package oop.hw.classes.chapterTwo;

import oop.hw.classes.City;
import oop.hw.classes.Route;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityWayBackTest {

    @Test
    void testConstructorThrowsForOneWayRoutes() {
        City normalCity = new City("NormalCity");
        CityWayBack cityA = new CityWayBack("A");

        normalCity.addRoute(cityA, 10);

        assertThrows(IllegalArgumentException.class, () ->
                new CityWayBack("B", List.of(new Route(normalCity, 10))));
    }

    @Test
    void testAddRouteCreatesReverseRoute() {
        CityWayBack cityA = new CityWayBack("A");
        CityWayBack cityB = new CityWayBack("B");

        cityA.addRoute(cityB, 10);

        assertEquals(1, cityA.getRoutes().size());
        assertEquals(1, cityB.getRoutes().size());
        assertEquals(cityB, cityA.getRoutes().getFirst().getDestination());
        assertEquals(cityA, cityB.getRoutes().getFirst().getDestination());
        assertEquals(10, cityA.getRoutes().getFirst().getCost());
        assertEquals(10, cityB.getRoutes().getFirst().getCost());
    }

    @Test
    void testAddRouteWithNormalCity() {
        CityWayBack cityA = new CityWayBack("A");
        City normalCity = new City("NormalCity");

        cityA.addRoute(normalCity, 15);

        assertEquals(1, cityA.getRoutes().size());
        assertEquals(1, normalCity.getRoutes().size());
        assertEquals(normalCity, cityA.getRoutes().getFirst().getDestination());
        assertEquals(cityA, normalCity.getRoutes().getFirst().getDestination());
    }

    @Test
    void testCostChangesAreNotAutomaticallySynced() {
        CityWayBack cityA = new CityWayBack("A");
        CityWayBack cityB = new CityWayBack("B");

        cityA.addRoute(cityB, 10);
        cityA.getRoutes().getFirst().setCost(20);

        assertEquals(20, cityA.getRoutes().getFirst().getCost());
        assertEquals(10, cityB.getRoutes().getFirst().getCost());
    }

    @Test
    void testToString() {
        CityWayBack cityA = new CityWayBack("A");
        CityWayBack cityB = new CityWayBack("B");

        cityA.addRoute(cityB, 10);

        String str = cityA.toString();
        String strB = cityB.toString();

        assertTrue(str.contains("B: 10"));
        assertTrue(strB.contains("A: 10"));
    }
}