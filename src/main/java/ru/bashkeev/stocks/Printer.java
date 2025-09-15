package ru.bashkeev.stocks;

public class Printer implements Observer {
    @Override
    public void update(Stock stock, int oldPrice) {
        System.out.println(stock + ", изменила цену: " + stock.getPrice());
    }
}
