package ru.bashkeev.city;

import java.util.*;

public class City {
    String name;
    List<Route> routes;

    public City(String name, List<Route> routes) {
        this.name = name;
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
        routes.removeIf(route -> route.destination.equals(city));
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
        return getDestinationNames().equals(other.getDestinationNames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDestinationNames());
    }

    private Set<String> getDestinationNames() {
        Set<String> names = new HashSet<>();
        for (Route route : routes) {
            City destination = route.getDestination();
            if (destination != null) {
                names.add(destination.getName());
            }
        }
        return names;
    }
}
