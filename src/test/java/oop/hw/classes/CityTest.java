package oop.hw.classes;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CityTest {

    @Test
    void testCreateCitiesSimple() {
        City moscow = new City("Москва");
        assertEquals("Москва", moscow.getName());
        assertEquals("Москва", moscow.toString());
    }

    @Test
    void testCreateCitiesWithRoutes() {
        City volgograd = new City("Москва");
        City peter = new City("Санкт-Петербург");

        List<Route> routes = List.of(new Route(volgograd, 1000), new Route(peter, 400));
        City moscow = new City("Москва", routes);

        assertEquals("Москва", moscow.getName());
        assertEquals("Москва -> {Москва: 1000, Санкт-Петербург: 400};", moscow.toString());
    }

    @Test
    void testAddRoute() {
        City moscow = new City("Москва");
        City peter = new City("Санкт-Петербург");

        moscow.addRoute(peter, 500);
        assertEquals(1, moscow.getRoutes().size());
    }

    @Test
    void testMultipleRoutes() {
        City moscow = new City("Москва");
        City peter = new City("Санкт-Петербург");
        City kazan = new City("Казань");

        moscow.addRoute(peter, 500);
        moscow.addRoute(kazan, 700);

        String result = moscow.toString();
        assertTrue(result.contains("Санкт-Петербург: 500"));
        assertTrue(result.contains("Казань: 700"));
    }

    @Test
    void testRemoveRoute() {
        City moscow = new City("Москва");
        City peter = new City("Санкт-Петербург");

        moscow.addRoute(peter, 500);
        moscow.removeRoute(peter);

        assertEquals(0, moscow.getRoutes().size());
        assertEquals("Москва", moscow.toString());
    }

    @Test
    void testPrintSchema() {
        City A = new City("A");
        City B = new City("B");
        City C = new City("C");
        City D = new City("D");
        City E = new City("E");
        City F = new City("F");

        A.addRoute(B, 500);
        A.addRoute(D, 700);
        A.addRoute(C, 300);

        B.addRoute(A, 500);
        B.addRoute(C, 700);

        C.addRoute(B, 300);
        C.addRoute(D, 700);

        D.addRoute(C, 700);
        D.addRoute(E, 700);
        D.addRoute(A, 700);

        E.addRoute(F, 700);

        F.addRoute(B, 700);
        F.addRoute(E, 700);

        assertEquals("A -> {B: 500, D: 700, C: 300};", A.toString());
        assertEquals("B -> {A: 500, C: 700};", B.toString());
        assertEquals("C -> {B: 300, D: 700};", C.toString());
        assertEquals("D -> {C: 700, E: 700, A: 700};", D.toString());
        assertEquals("E -> {F: 700};", E.toString());
        assertEquals("F -> {B: 700, E: 700};", F.toString());
    }
}