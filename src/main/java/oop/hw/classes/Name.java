package oop.hw.classes;

public class Name {
    String lastName;
    String firstName;
    String middleName;

    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public Name(String lastName, String firstName) {
        this(lastName, firstName, null);
    }

    public Name(String firstName) {
        this(null, firstName, null);
    }

    public Name() {
        this(null, null, null);
    }

    public Name onlyLastName(String lastName) {
        return new Name(lastName, null, null);
    }


    public Name onlyMiddleName(String middleName) {
        return new Name(null, null, middleName);
    }

    @Override
    public String toString() {
        String rsString = "";

        if (lastName != null && !lastName.isEmpty()) {
            rsString += lastName;
        }
        if (firstName != null) {
            if (!rsString.isEmpty()) rsString += " ";
            rsString += firstName;
        }

        if (middleName != null) {
            if (!rsString.isEmpty()) rsString += " ";
            rsString += middleName;
        }

        return rsString;
    }
}
