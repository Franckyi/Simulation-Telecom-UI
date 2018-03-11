package fr.rtgrenoble.velascof.simtelui;

import javafx.scene.control.TextInputControl;

import java.util.regex.Pattern;

public class Util {

    private static final Pattern DOUBLE = Pattern.compile(
            "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
                    "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
                    "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
                    "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

    private static final Pattern BINARY_STRING = Pattern.compile("[0-1]+");

    public static boolean isInteger(TextInputControl c) {
        return isInteger(c.getText());
    }

    public static boolean isPositiveInteger(TextInputControl c) {
        return isPositiveInteger(c.getText());
    }

    public static boolean isDouble(TextInputControl c) {
        return isDouble(c.getText());
    }

    public static boolean isPositiveDouble(TextInputControl c) {
        return isPositiveDouble(c.getText());
    }

    public static boolean isNotEmpty(TextInputControl c) {
        return isNotEmpty(c.getText());
    }

    public static boolean isBinaryString(TextInputControl c) {
        return isBinaryString(c.getText());
    }

    public static boolean isOrder(TextInputControl c) {
        return isOrder(c.getText());
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

    public static boolean isPositiveInteger(String s) {
        return isInteger(s, 10) && toInteger(s) >= 0;
    }

    public static boolean isDouble(String s) {
        return DOUBLE.matcher(s).matches();
    }

    public static boolean isPositiveDouble(String s) {
        return isDouble(s) && toDouble(s) >= 0;
    }

    public static boolean isNotEmpty(String s) {
        return !s.isEmpty();
    }

    public static boolean isBinaryString(String s) {
        return BINARY_STRING.matcher(s).matches();
    }

    public static boolean isOrder(String s) {
        return isOrder(toInteger(s));
    }

    public static boolean isOrder(int i) {
        return i > 1 && Math.log(i) / Math.log(2) == (int) (Math.log(i) / Math.log(2));
    }

    public static int toInteger(TextInputControl c) {
        return toInteger(c.getText());
    }

    public static double toDouble(TextInputControl c) {
        return toDouble(c.getText());
    }

    public static int toInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double toDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
