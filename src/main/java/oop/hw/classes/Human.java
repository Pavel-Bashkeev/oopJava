package oop.hw.classes;

public class Human {
    static final String DEFAULT_PATRON_SUFFIX = "ович";
    Name name;
    int height;

    private Human(Name name, int height, Human parent) {
        this.name = name;
        this.height = height;
        initFromParent(parent);
    }

    public Human(String name, int height) {
        this(new Name(name), height, null);
    }

    public Human(Name name, int height) {
        this(name, height, null);
    }

    public Human(Name name, Human parent) {
        this(name, 0, parent);
    }

    public Human(String name, Human parent) {
        this(new Name(name), 0, parent);
    }

    public Human(Name name) {
        this(name, 0, null);
    }

    @Override
    public String toString() {
        String result = "Человек с именем " + name.toString();
        if (this.height > 0) {
            result += " и ростом " + height;
        }

        return result;
    }

    private void initFromParent(Human parent) {
        if (parent == null) return;

        if (this.name.getLastName() == null && parent.name.getLastName() != null) {
            String lastNameParent = parent.name.getLastName();
            this.name.setLastName(lastNameParent);
        }

        if (this.name.getMiddleName() == null && parent.name.getFirstName() != null) {
            String middleNameParent = parent.name.getFirstName() + DEFAULT_PATRON_SUFFIX;
            this.name.setMiddleName(middleNameParent);
        }
    }
}
