package ru.bashkeev.test;

import ru.bashkeev.annotation.Default;

@Default(type = String.class)
public class DefaultReseterTestClass {

    @Default(type = String.class)
    private String annotatedString;

    @Default(type = Integer.class)
    private Integer annotatedInteger;

    @Default(type = Boolean.class)
    private Boolean annotatedBoolean;

    @Default(type = Double.class)
    private Double annotatedDouble;

    private int primitiveInt;
    private double primitiveDouble;
    private boolean primitiveBoolean;
    private long primitiveLong;
    private float primitiveFloat;
    private char primitiveChar;
    private byte primitiveByte;
    private short primitiveShort;

    private String stringField;
    private Integer integerField;
    private Boolean booleanField;
    private Object objectField;
    private java.util.List<String> listField;

    private String initializedString = "initial";
    private int initializedInt = 100;
    private Integer initializedInteger = 200;

    public DefaultReseterTestClass() {
        this.annotatedString = "original annotated";
        this.annotatedInteger = 999;
        this.stringField = "original string";
        this.integerField = 500;
        this.booleanField = true;
        this.objectField = new Object();
        this.listField = java.util.Arrays.asList("item1", "item2");
    }

    public String getAnnotatedString() { return annotatedString; }
    public Integer getAnnotatedInteger() { return annotatedInteger; }
    public Boolean getAnnotatedBoolean() { return annotatedBoolean; }
    public Double getAnnotatedDouble() { return annotatedDouble; }

    public int getPrimitiveInt() { return primitiveInt; }
    public double getPrimitiveDouble() { return primitiveDouble; }
    public boolean isPrimitiveBoolean() { return primitiveBoolean; }
    public long getPrimitiveLong() { return primitiveLong; }
    public float getPrimitiveFloat() { return primitiveFloat; }
    public char getPrimitiveChar() { return primitiveChar; }
    public byte getPrimitiveByte() { return primitiveByte; }
    public short getPrimitiveShort() { return primitiveShort; }

    public String getStringField() { return stringField; }
    public Integer getIntegerField() { return integerField; }
    public Boolean getBooleanField() { return booleanField; }
    public Object getObjectField() { return objectField; }
    public java.util.List<String> getListField() { return listField; }

    public String getInitializedString() { return initializedString; }
    public int getInitializedInt() { return initializedInt; }
    public Integer getInitializedInteger() { return initializedInteger; }
}
