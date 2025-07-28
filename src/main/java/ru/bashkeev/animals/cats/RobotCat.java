package ru.bashkeev.animals.cats;

public class RobotCat implements Meowable {
    private String model;

    public RobotCat(String model) {
        this.model = model;
    }

    @Override
    public void meow() {
        System.out.println(model + ": БИП-БИП-МЯУ!");
    }

    @Override
    public String toString() {
        return "RobotCat " + model;
    }
}