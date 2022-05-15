package com.bobocode.sort;

import java.util.HashMap;
import java.util.Map;

public class HeterogeniousMaxHolder <T extends Comparable<T>> {
    Map<Class<?>, T> maxValues = new HashMap<>();
    public void put (Class<T> classVal, T t) {
        T currentMax = maxValues.get(classVal);
        if ((currentMax == null) || t.compareTo(currentMax) > 0) maxValues.put(classVal, t);
    }
    public T getMax(Class<T> classVal) {
        return maxValues.get(classVal);
    }
}
