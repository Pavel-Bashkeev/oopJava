package oop.hw.classes;

import java.util.ArrayList;
import java.util.List;

public class City {
    String name;
    List<Route> routes;

    public City(String name,  List<Route> routes) {
        this.name = name;
        this.routes = new ArrayList<>(routes);
    }

    public City(String name) {
        this(name , new ArrayList<>());
    }

    public List<Route> getRoutes() {
        return new ArrayList<>(routes);
    }

    public void addRoute(City city, int cost) {
        this.routes.add(new Route(city, cost));
    }

    public void removeRoute(City city) {
        routes.removeIf(route -> route.destination.equals(city));
    }

    @Override
    public String toString() {
        StringBuilder rsString = new StringBuilder(this.name);

        if (!routes.isEmpty()) {
            rsString.append(" -> {");
            routes.forEach((route) -> {
                rsString.append(route.getDestination().getName())
                        .append(": ")
                        .append(route.getCost())
                        .append(", ");
            });
            rsString.delete(rsString.length() - 2, rsString.length());
            rsString.append("};");
        }
        return rsString.toString();
    }

    public String getName(){
        return this.name;
    }
}
