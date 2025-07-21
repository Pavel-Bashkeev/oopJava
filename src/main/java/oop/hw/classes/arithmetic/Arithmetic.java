package oop.hw.classes.arithmetic;

public class Arithmetic {
    public static double sum(Number... numbers) {
        double total = 0.0;
        for (Number num : numbers) {
            total += num.doubleValue();
        }
        return total;
    }
}
