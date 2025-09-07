package ru.bashkeev.city;

import java.util.*;

public class City {
    private String      name;
    private List<Route> routes;

    public City(String name, List<Route> routes) {
        this.name   = name;
        this.routes = new ArrayList<>(routes);
    }

    public City(String name) {
        this(name, new ArrayList<>());
    }

    public List<Route> getRoutes() {
        return new ArrayList<>(routes);
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRoute(City destination, int cost) {
        if (destination == null) {
            throw new IllegalArgumentException("Город назначения не может быть null");
        }

        for (Route route : routes) {
            if (route.getDestination().equals(destination)) {
                route.setCost(cost);
                return;
            }
        }

        routes.add(new Route(destination, cost));
    }

    public void removeRoute(City city) {
        routes.removeIf(route -> route.getDestination().equals(city));
    }

    @Override
    public String toString() {
        if (routes.isEmpty()) {
            return name;
        }
        return name + " -> " + routes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof City other)) return false;
        return Objects.equals(name, other.name) && routes.equals(other.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, routes);
    }
}