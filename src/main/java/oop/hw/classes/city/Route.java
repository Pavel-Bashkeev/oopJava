package oop.hw.classes.city;

public class Route {
    City destination;
    int cost;

    public Route(City destination, int cost) {
        this.destination = destination;
        setCost(cost);
    }

    public City getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("Стоимость должна быть положительной");
        }
        this.cost = cost;
    }

    @Override
    public String toString() {
        return destination.getName() + ": " + cost;
    }
}
