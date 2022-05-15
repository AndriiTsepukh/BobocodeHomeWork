package com.bobocode.hashtable;

public class DemoAppHashTableRun {
    public static void main(String[] args) {
        Hashtable<String> table = new Hashtable<>();

        table.add("One");
        table.add("Two");
        table.add("BigNumber");
        table.add("Three");
        table.add("Four");
        table.add("Five");
        table.add("Six");
        table.add("Seven");
        table.add("Eight");

        table.printTable();

        System.out.println("------------------------------------------");
        table.resize(10);

        table.printTable();

        System.out.println("------------------------------------------");
        table.resize(23);

        table.printTable();

    }
}
