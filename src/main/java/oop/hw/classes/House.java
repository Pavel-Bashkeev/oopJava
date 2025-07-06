package oop.hw.classes;

import static oop.hw.helpers.DeclensionWords.getDeclensionWord;

public class House {
    int numberOfFloors;
    private final String[] FLOOR_FORMS = {"этажом", "этажами", "этажами"};

    public  House (int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public House () {
        this(0);
    }

    @Override
    public String toString() {
        String word = getDeclensionWord(this.numberOfFloors,  this.FLOOR_FORMS);
        return "Дом с " + this.numberOfFloors + " " + word;
    }
}
