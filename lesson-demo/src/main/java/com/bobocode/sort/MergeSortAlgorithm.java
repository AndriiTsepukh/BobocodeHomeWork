package com.bobocode.sort;

import java.util.Arrays;

public class MergeSortAlgorithm {
    public static void main(String[] args) {
        int [] unsortedArray = {1,3,13,5,8,4,56,10};

        int[] sortedArray =  mergeSort(unsortedArray);
        System.out.println(sortedArray);
    }

    public static int[] mergeSort(int[] unsortedArray) {
        if (unsortedArray.length == 1) {
            return unsortedArray;
        }
        int[] firstArray = Arrays.copyOfRange(unsortedArray, 0, unsortedArray.length / 2);
        int[] secondArray = Arrays.copyOfRange(unsortedArray, unsortedArray.length / 2, unsortedArray.length);
        int[] sortedFirstArray = mergeSort(firstArray);
        int[] sortedSecondArray = mergeSort(secondArray);
        int[] mergedArray = mergeArrays(sortedFirstArray, sortedSecondArray);
        return mergedArray;
    }

    private static int[] mergeArrays(int[] sortedFirstArray, int[] sortedSecondArray) {
        int[] mergedArray = new int[sortedFirstArray.length + sortedSecondArray.length];

        int i = 0;
        int j = 0;
        while (i < sortedFirstArray.length || j < sortedSecondArray.length) {
            if (i == sortedFirstArray.length) {
                mergedArray[i + j] = sortedSecondArray[j];
                j++;
            } else if (j == sortedFirstArray.length) {
                mergedArray[i + j] = sortedFirstArray[i];
                i++;
            } else if (sortedFirstArray[i] < sortedSecondArray[j]) {
                mergedArray[i + j] = sortedFirstArray[i];
                i++;
            } else if (sortedFirstArray[i] > sortedSecondArray[j]) {
                mergedArray[i + j] = sortedSecondArray[j];
                j++;
            } else if (sortedFirstArray[i] == sortedSecondArray[j]){
                mergedArray[i+j] = sortedFirstArray[i];
                i++;
            }
        }
        return mergedArray;
    }
}
