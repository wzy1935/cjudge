package com.wzebra.cjudge.algorithm.base;


public class TextComparator {
    String standard = null;
    String test = null;
    boolean isDifferent = false;

    public TextComparator(String standard, String test) {
        this.standard = chomp(standard);
        this.test = chomp(test);
    }

    public boolean compare() {
        isDifferent = standard.compareTo(test) == 0;
        return isDifferent;
    }

    public static String chomp(String str) {
        if (str == null || str.equals("")) {
            return "";
        }

        if (str.length() == 1) {
            char ch = str.charAt(0);
            if ((ch == '\r') || (ch == '\n')) {
                return "";
            }
            return str;
        }

        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);

        if (last == '\n') {
            if (str.charAt(lastIdx - 1) == '\r') {
                lastIdx--;
            }
        } else if (last != '\r') {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }

}
