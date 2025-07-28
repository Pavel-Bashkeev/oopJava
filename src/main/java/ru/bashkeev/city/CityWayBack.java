package ru.bashkeev.city;

import java.util.ArrayList;
import java.util.List;

public class CityWayBack extends City {

    public CityWayBack(String name) {
        super(name);
    }

    public CityWayBack(String name, List<Route> routes) {
        super(name);
        setRoutes(new ArrayList<>(routes));
    }

    @Override
    public void addRoute(City destination, int cost) {
        if (!hasRouteTo(this, destination)) {
            super.addRoute(destination, cost);

            if (!hasRouteTo(destination, this)) {
                destination.addRoute(this, cost);
            }
        }
    }

    @Override
    public void removeRoute(City city) {
        super.removeRoute(city);

        if (hasRouteTo(city, this)) {
            city.removeRoute(this);
        }
    }

    @Override
    public void setRoutes(List<Route> routes) {
        List<Route> currentRoutes = new ArrayList<>(getRoutes());
        for (Route route : currentRoutes) {
            this.removeRoute(route.getDestination());
        }

        for (Route route : routes) {
            this.addRoute(route.getDestination(), route.getCost());
        }
    }

    private boolean hasRouteTo(City from, City to) {
        return from.getRoutes().stream()
                .anyMatch(r -> r.getDestination().equals(to));
    }
}