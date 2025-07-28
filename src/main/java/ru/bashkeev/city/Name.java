package ru.bashkeev.city;

public class Name {
    private String lastName;
    private final String firstName;
    private String middleName;

    public Name(String lastName, String firstName, String middleName) {
        if (allFieldsEmpty(lastName, firstName, middleName)) {
            throw new IllegalArgumentException("Хотя бы одно из полей (фамилия, имя или отчество) должно быть заполнено");
        }
        this.lastName = isNullOrEmpty(lastName) ? null : lastName;
        this.firstName = isNullOrEmpty(firstName) ? null : firstName;
        this.middleName = isNullOrEmpty(middleName) ? null : middleName;
    }

    public Name(String lastName, String firstName) {
        this(lastName, firstName, null);
    }

    public Name(String firstName) {
        this(null, firstName, null);
    }

    private boolean allFieldsEmpty(String... fields) {
        for (String field : fields) {
            if (field != null && !field.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
