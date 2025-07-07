package oop.hw.classes;

public class Route {
    City destination;
    int cost;

    public Route(City destination, int cost) {
        this.destination = destination;
        this.cost = cost;
    }

    public City getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }
}
