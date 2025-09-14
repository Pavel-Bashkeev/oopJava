package ru.bashkeev.stocks;

import java.util.ArrayList;
import java.util.List;

public class Stock implements Observable {
    private final String         name;
    private       int            price;
    private final List<Observer> observers = new ArrayList<>();

    public Stock(String name, int initialPrice) {
        this.name  = name;
        this.price = initialPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price != this.price) {
            int oldPrice = this.price;
            this.price = price;
            notifyObservers(oldPrice);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int oldPrice) {
        for (Observer observer : observers) {
            observer.update(this, oldPrice);
        }
    }

    @Override
    public String toString() {
        return "Акция " + name + " c ценой  " + price;
    }
}
