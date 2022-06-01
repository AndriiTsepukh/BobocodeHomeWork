package com.bobocode.parallelmergesort;

import java.util.Arrays;

public class DemoApp {
    public static void main(String[] args) {
        Integer[] array = {12,5,23,7,10,134,6};

        var sortedArray = new ParallelMergeSort<Integer>(array).compute();

        Arrays.stream(sortedArray).forEach(System.out::println);
    }
}
