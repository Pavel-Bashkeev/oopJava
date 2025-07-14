package oop.hw.classes;

public class Human {
    private static final String DEFAULT_PATRON_SUFFIX = "ович";
    private final        Name   name;
    private final        int    height;
    private              Human  parent;

    private Human(Name name, int height, Human parent) {
        this.name   = name;
        this.height = height;
        this.parent = parent;
        initFromParent();
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
        StringBuilder sb = new StringBuilder("Человек с именем ");

        String lastName = getActualLastName();
        if (lastName != null) {
            sb.append(lastName).append(" ");
        }

        sb.append(name.getFirstName());

        String middleName = getActualMiddleName();
        if (middleName != null) {
            sb.append(" ").append(middleName);
        }

        if (height > 0) {
            sb.append(" и ростом ").append(height);
        }

        return sb.toString();
    }

    public Human getParent() {
        return parent;
    }

    public void setParent(Human parent) {
        if (parent == this) {
            throw new IllegalArgumentException("Человек не может быть своим родителем");
        }
        this.parent = parent;
        initFromParent();
    }

    public String getFirstName() {
        return this.name.getFirstName();
    }

    public String getLastName() {
        return this.getActualLastName() != null ? this.name.getLastName() : "";
    }

    public String getMiddleName() {
        return this.getActualMiddleName() != null ? this.name.getMiddleName() : "";
    }

    private String getActualLastName() {
        if (name.getLastName() != null) {
            return name.getLastName();
        }
        if (parent != null) {
            return parent.getActualLastName();
        }
        return null;
    }

    private String getActualMiddleName() {
        if (name.getMiddleName() != null) {
            return name.getMiddleName();
        }
        if (parent != null && parent.name.getFirstName() != null) {
            return parent.name.getFirstName() + DEFAULT_PATRON_SUFFIX;
        }
        return null;
    }

    private void initFromParent() {
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