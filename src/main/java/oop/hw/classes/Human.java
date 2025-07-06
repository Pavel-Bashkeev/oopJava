package oop.hw.classes;

public class Human {
    Name name;
    int height;

    public Human(String name, int height) {
        this.name = new Name(name);
        this.height = height;
    }

    public Human(Name name, int height) {
        this.name = name;
        this.height = height;
    }

    public Human(Name name, Human parent) {
        this.name = name;

        if (this.name.getLastName() == null && parent.name.getLastName() != null) {
            String lastNameParent = parent.name.getLastName();
            this.name.setLastName(lastNameParent);
        }

        if (this.name.getMiddleName() == null && parent.name.getFirstName() != null) {
            String middleNameParent = parent.name.getFirstName() + "ович";
            this.name.setMiddleName(middleNameParent);
        }
    }

    public Human(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (this.height != 0) {
            return "Человек с именем " + name.toString() + " и ростом " + height;
        }

        return "Человек с именем " + name.toString();
    }
}
