package com.bobocode.mergesortforlist;

import java.util.ArrayList;
import java.util.List;

public class DemoApp {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(8);
        list.add(12);
        list.add(4);

        mergesort(list);

        System.out.println(list);
    }

    private static <T extends Comparable<T>> void mergesort  (List<T> list) {
        mergeSortPartial(list, 0, list.size());
    }

    protected static <T extends Comparable<T>> void mergeSortPartial(List<T> list, int begin, int end) {
        int length = end - begin;
        int middle = begin + length/2;

        if(length > 1) {
            mergeSortPartial(list, begin, middle);
            mergeSortPartial(list, middle, end);
        }

        int i = begin;
        int j = middle;

        List<T> temp = new ArrayList<>();
        while((i < middle) || (j < end)) {
            if (i == middle) {
                temp.add(list.get(j));
                j++;
                continue;
            };
            if (j == end) {
                temp.add(list.get(i));
                i++;
                continue;
            }

            if(list.get(i).compareTo(list.get(j)) > 0) {
                temp.add(list.get(j));
                j++;
                continue;
            }

            if(list.get(i).compareTo(list.get(j)) < 0) {
                temp.add(list.get(i));
                i++;
                continue;
            }
            temp.add(list.get(i));
            temp.add(list.get(j));
            i++;
            j++;
        }

        for (i = 0; i < temp.size(); i++) {
            list.set(begin + i, temp.get(i));
        }
    }
}
