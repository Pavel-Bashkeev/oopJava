package ru.bashkeev.stocks;

public interface Observer {
    void  update(Stock stock, int oldPrice);
}
