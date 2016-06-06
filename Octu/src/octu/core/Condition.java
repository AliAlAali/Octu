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
public class Condition {

    public static final String OPERATOR_EQUAL = "==";
    public static final String OPERATOR_EQUAL_BIGGER = ">=";
    public static final String OPERATOR_EQUAL_SMALLER = "<=";
    public static final String OPERATOR_SMALLER = "<";
    public static final String OPERATOR_BIGGER = ">";
    public static final String OPERATOR_NOT_EQUAL = "!=";

    private Variable var;
    private String operator;

    public void updateVariable(Variable v) {
        var = v;
    }

    public boolean compare(Variable v, String operator) {
        return var.compareTo(v, operator);
    }

    @Override
    public String toString() {
        return " " + operator + " " + var.toString();
    }

}
