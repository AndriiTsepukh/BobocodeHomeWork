package com.bobocode.parallelmergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class  ParallelMergeSort <T extends Comparable<? super T>> extends RecursiveTask<T[]> {

    T [] unsortedArray;

    public ParallelMergeSort(T[] unsortedArray) {
        this.unsortedArray = unsortedArray;
    }

    @Override
    protected T[] compute() {
        if (unsortedArray.length == 1) return unsortedArray;
        T[] leftUnsorted = Arrays.copyOfRange(unsortedArray,0, unsortedArray.length/2);
        T[] rightUnsorted = Arrays.copyOfRange(unsortedArray, unsortedArray.length/2, unsortedArray.length);

        var leftTask = new ParallelMergeSort<T>(leftUnsorted);
        var rightTask = new ParallelMergeSort<T>(rightUnsorted);
        var leftFork = leftTask.fork();
        var right = rightTask.compute();
        var left = leftFork.join();

        int i = 0;
        int j = 0;

        T[] mergedArray = Arrays.copyOfRange(unsortedArray, 0, unsortedArray.length);

        while (i < left.length || j < right.length) {
            if (i == left.length) {
                mergedArray[i+j] = right[j];
                j++;
            } else if (j == right.length) {
                mergedArray[i+j] = left[i];
                i++;
            } else if (left[i].compareTo(right[j]) < 0) {
                mergedArray[i+j] = left[i];
                i++;
            } else if (left[i].compareTo(right[j]) > 0) {
                mergedArray[i+j] = right[j];
                j++;
            } else {
                mergedArray[i+j] = left[i];
                i++;
                mergedArray[i+j] = right[j];
                j++;
            }
        }

        return mergedArray;
    }
}
