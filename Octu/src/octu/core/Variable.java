/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core;

/**
 *
 * @author Ali
 */
public class Variable {

    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_NUMBER = "NUMBER";
    public static final String TYPE_BOOLEAN = "BOOLEAN";

    private long number;
    private String text;
    private String type;
    private String name;
    private boolean value;

    public Variable(String name) {
        this.name = name;
    }

    public Object getValue() {
        switch (type) {
            case TYPE_TEXT:
                return text;
            case TYPE_NUMBER:
                return number;
            case TYPE_BOOLEAN:
                return value;
        }
        return null;
    }

    public void assignValue(Object value) {
        switch (type) {
            case TYPE_BOOLEAN:
                this.value = (boolean) value;
                break;
            case TYPE_TEXT:
                text = (String) value;
                break;
            case TYPE_NUMBER:
                 number = (long) value;
                break;

        }
    }

    public boolean compareTo(Variable v, String operator) {

        if (!v.getType().equals(type)) {
            return false;
        }
        switch (operator) {
            case Condition.OPERATOR_EQUAL:
                if (type.equals(TYPE_NUMBER)) {
                    return number == v.getNumber();
                } else if (type.equals(TYPE_TEXT)) {
                    return text == v.getText();
                } else if (type.equals(TYPE_BOOLEAN)) {
                    return getValue() == v.getValue();
                }
                break;
            case Condition.OPERATOR_BIGGER:
                if (type.equals(TYPE_NUMBER)) {
                    return number > v.getNumber();
                } else if (type.equals(TYPE_TEXT)) {
                    return text.compareTo(v.getText()) > 0;
                } else if (type.equals(TYPE_BOOLEAN)) {
                    return getValue() != v.getValue();
                }
                break;
            case Condition.OPERATOR_SMALLER:
                if (type.equals(TYPE_NUMBER)) {
                    return number < v.getNumber();
                } else if (type.equals(TYPE_TEXT)) {
                    return text.compareTo(v.getText()) < 0;
                } else if (type.equals(TYPE_BOOLEAN)) {
                    return getValue() != v.getValue();
                }
                break;
            case Condition.OPERATOR_NOT_EQUAL:
                if (type.equals(TYPE_NUMBER)) {
                    return number != v.getNumber();
                } else if (type.equals(TYPE_TEXT)) {
                    return text.compareTo(v.getText()) != 0;
                } else if (type.equals(TYPE_BOOLEAN)) {
                    return getValue() != v.getValue();
                }
                break;
            case Condition.OPERATOR_EQUAL_BIGGER:
                if (type.equals(TYPE_NUMBER)) {
                    return number >= v.getNumber();
                } else if (type.equals(TYPE_TEXT)) {
                    return text.compareTo(v.getText()) >= 0;
                } else if (type.equals(TYPE_BOOLEAN)) {
                    return getValue() == v.getValue();
                }
                break;
            case Condition.OPERATOR_EQUAL_SMALLER:
                if (type.equals(TYPE_NUMBER)) {
                    return number <= v.getNumber();
                } else if (type.equals(TYPE_TEXT)) {
                    return text.compareTo(v.getText()) <= 0;
                } else if (type.equals(TYPE_BOOLEAN)) {
                    return getValue() == v.getValue();
                }
                break;
        }

        return false;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
