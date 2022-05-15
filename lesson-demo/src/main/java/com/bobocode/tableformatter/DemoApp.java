package com.bobocode.tableformatter;

public class DemoApp {
    public static void main(String[] args) {

        int columns = 5;
        String[] input = new String[]{"1", "2", "3", "x", "5", "6", "a", "porosiatko", "c", "10", "11", "12", "13", "14", "15", "16"};
        int[] fieldMaxLength = new int[columns];

        for (int i = 0; i < input.length; i++){
            int location = i % columns;
            if (input[i].length() > fieldMaxLength[location]) fieldMaxLength[location] = input[i].length();
        }

        for (int i = 0; i < input.length; i++){
            int location = i % columns;
            int spaces = fieldMaxLength[location] + 4;
            System.out.printf("%-"+ spaces + "s", input[i]);
            if (location == columns - 1) System.out.println("");
        }
    }
}
