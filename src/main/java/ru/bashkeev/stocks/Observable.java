package ru.bashkeev.stocks;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(int oldPrice);
}
