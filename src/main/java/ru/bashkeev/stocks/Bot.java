package ru.bashkeev.stocks;

public class Bot implements Observer{
    private static final int BUY_THRESHOLD = 70;
    private static final String TARGET_STOCK = "ORCL";

    @Override
    public void update(Stock stock, int oldPrice) {
        if (TARGET_STOCK.equals(stock.getName()) && stock.getPrice() < BUY_THRESHOLD) {
            System.out.println("надо покупать " + TARGET_STOCK + "! Текущая цена: " + stock.getPrice());
        }
    }
}
