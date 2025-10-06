package ru.bashkeev.test;

import ru.bashkeev.annotation.StringIntegerValidate;

@StringIntegerValidate
class AliasStringIntegerClass {
    private String text = "Hello";
    private Integer number = 42;
    private Double decimal = 3.14;  // Невалидно - Double не в разрешенных типах

    // Геттеры
    public String getText() { return text; }
    public Integer getNumber() { return number; }
    public Double getDecimal() { return decimal; }
}