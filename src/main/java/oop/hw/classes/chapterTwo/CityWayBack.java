package oop.hw.classes.chapterTwo;

import oop.hw.classes.City;
import oop.hw.classes.Route;

import java.util.List;

public class CityWayBack extends City {
    public CityWayBack(String name, List<Route> routes) {
        super(name, routes);
        validateTwoWay(routes);
    }

    public CityWayBack(String name) {
        super(name);
    }

    private void validateTwoWay(List<Route> routes) {
        for (Route route : routes) {
            City destination = route.getDestination();
            boolean hasReverseRoute = destination.getRoutes().stream()
                    .anyMatch(r -> r.getDestination().equals(this));

            if (!hasReverseRoute) {
                throw new IllegalArgumentException("Дороги между городами должны быть с обратной дорогой");
            }
        }
    }

    @Override
    public void addRoute(City destination, int cost) {
        super.addRoute(destination, cost);

        boolean hasReverseRoute = false;
        for (Route route : destination.getRoutes()) {
            if (route.getDestination().equals(this)) {
                hasReverseRoute = true;
                break;
            }
        }

        if (!hasReverseRoute) {
            destination.addRoute(this, cost);
        }
    }

    @Override
    public void removeRoute(City city) {
        super.removeRoute(city);
        city.removeRoute(this);
    }

    @Override
    public void setRoutes(List<Route> routes) {
        validateTwoWay(routes);
        super.setRoutes(routes);
    }
}
