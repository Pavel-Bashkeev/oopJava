package ru.bashkeev.test;

import ru.bashkeev.annotation.AnyObjectValidate;

@AnyObjectValidate
class AliasAnyObjectClass {
    private String text = "Anything";
    private Integer number = 123;
    private Boolean flag = true;
    private Object obj = new Object();

    // Геттеры
    public String getText() { return text; }
    public Integer getNumber() { return number; }
    public Boolean getFlag() { return flag; }
    public Object getObj() { return obj; }
}