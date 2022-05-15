package com.bobocode.sort;

public class BubbleSortAlgorithm {

    public static void main(String[] args) {
        int[] unsortedArray = {1, 3, 13, 5, 8, 4, 56, 10};

        int[] sortedArray = bubbleSort(unsortedArray);

        System.out.println(sortedArray);

    }

    public static int[] bubbleSort(int[] arrayToSort) {

        boolean elementsSwaped = true;
        while (elementsSwaped) {
            elementsSwaped = false;
            for (int i = 1; i < arrayToSort.length; i++) {
                if (arrayToSort[i - 1] > arrayToSort[i]) {
                    int temp = arrayToSort[i-1];
                    arrayToSort[i-1] = arrayToSort[i];
                    arrayToSort[i] = temp;
                    elementsSwaped = true;
                }
            }
        }

        return arrayToSort;
    }
}
