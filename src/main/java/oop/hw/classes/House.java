package oop.hw.classes;

import static oop.hw.helpers.DeclensionWords.getDeclensionWord;

public class House {
    final int numberOfFloors;
    private final String[] FLOOR_FORMS = {"этажом", "этажами", "этажами"};

    public  House (int numberOfFloors) {
        if (numberOfFloors <= 0) {
            throw new IllegalArgumentException("Количество этажей должно быть положительным числом");
        }
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public String toString() {
        String word = getDeclensionWord(this.numberOfFloors,  this.FLOOR_FORMS);
        return "Дом с " + this.numberOfFloors + " " + word;
    }
}
