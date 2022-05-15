package com.bobocode.sort;

public class InsertionSortAlgorithm {

    public static void main(String[] args) {
        int [] unsortedArray = {1,3,13,5,8,4,56,10};

        int[] sortedArray = insertionSort(unsortedArray);

        System.out.println(sortedArray);

    }

    public static int[] insertionSort(int[] unsortedArray) {

        for (int i=1; i < unsortedArray.length; i++) {
            for (int j=0; j < i; j++) {
                if (unsortedArray[i-j] < unsortedArray[i-j-1]) {
                    int temp = unsortedArray[i-j];
                    unsortedArray[i-j] = unsortedArray[i-j-1];
                    unsortedArray[i-j-1] = temp;
                } else {
                    break;
                }
            }
        }

        return unsortedArray;
    }
}
