package ru.bashkeev.test;

import ru.bashkeev.annotation.Validate;

@Validate({String.class, Integer.class, Double.class})
class DirectValidateClass {
    private String name = "John";
    private Integer age = 25;
    private Double salary = 50000.0;
    private Boolean active = true;

    // Геттеры
    public String getName() { return name; }
    public Integer getAge() { return age; }
    public Double getSalary() { return salary; }
    public Boolean getActive() { return active; }
}