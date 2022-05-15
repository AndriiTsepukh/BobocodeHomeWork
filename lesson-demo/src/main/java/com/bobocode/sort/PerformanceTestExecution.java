package com.bobocode.sort;

import java.util.concurrent.ThreadLocalRandom;

import static com.bobocode.sort.InsertionSortAlgorithm.insertionSort;

public class PerformanceTestExecution {

    public static void main(String[] args) {
        var n = 2_000;
        var attempts = 30;
        var totalTime = 0;

        for (int i = 1; i < attempts+1; i++){
            var arraySize = n * i;
            var arr = generateArray(arraySize);
            var start = System.nanoTime();
//            bubbleSort(arr);
//            mergeSort(arr);
            insertionSort(arr);
            var executionTime = (System.nanoTime() - start)/1000_000;
            System.out.printf("%12d %12d%n", arraySize, executionTime);
            totalTime += executionTime;
        }
    }

    private static int[] generateArray(int n) {
        var arr = new int[n];

        for (int i = 0; i < n; i++){
            arr[i] = ThreadLocalRandom.current().nextInt(n);
        }

        return arr;
    }

}
