package ru.bashkeev.city;

import java.util.Objects;

public class Route {
    City destination;
    int  cost;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Route other = (Route) obj;
        return cost == other.cost && Objects.equals(destination, other.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, cost);
    }

    @Override
    public String toString() {
        return destination.getName() + ": " + cost;
    }
}