package ru.bashkeev.test;

import ru.bashkeev.annotation.NumberValidate;

@NumberValidate
class AliasNumberClass {
    private Double price = 99.99;
    private Float discount = 0.1f;
    private Integer quantity = 10;  // Integer extends Number - валидно!
    private String name = "Product";  // Невалидно

    // Геттеры
    public Double getPrice() { return price; }
    public Float getDiscount() { return discount; }
    public Integer getQuantity() { return quantity; }
    public String getName() { return name; }
}
