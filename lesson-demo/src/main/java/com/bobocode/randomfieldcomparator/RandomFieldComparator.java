package com.bobocode.randomfieldcomparator;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * By default it compares only accessible fields, but this can be configured via a constructor property. If no field is
 * available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    Field fieldForComparing;
    Class<T> targetType;

    public RandomFieldComparator(Class<T> targetType) {
        this(targetType, false);
    }

    /**
     * A constructor that accepts a class and a property indicating which fields can be used for comparison. If property
     * value is true, then only public fields or fields with public getters can be used.
     *
     * @param targetType                  a type of objects that may be compared
     * @param compareOnlyAccessibleFields config property indicating if only publicly accessible fields can be used
     */
    @SneakyThrows
    public RandomFieldComparator(Class<T> targetType, boolean compareOnlyAccessibleFields) {
        Objects.requireNonNull(targetType);
        this.targetType = targetType;
        Field[] fields = targetType.getDeclaredFields();

        List<Field> sortedFields = new ArrayList<>();

        for (var field : fields) {
            if (field.getType().isPrimitive() || Comparable.class.isAssignableFrom(field.getType())){
                if (compareOnlyAccessibleFields) {
                    if (field.canAccess(targetType.getDeclaredConstructor().newInstance())) {
                        sortedFields.add(field);
                    }
                } else {
                    sortedFields.add(field);
                }
            }
        }

        if (sortedFields.size() == 0) throw new IllegalArgumentException("No field is available to compare");
        fieldForComparing = sortedFields.get(ThreadLocalRandom.current().nextInt(0, sortedFields.size()));
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value (nulls last).
     */
    @SneakyThrows
    @Override
    public int compare(T o1, T o2) {
        Objects.requireNonNull(o1);
        Objects.requireNonNull(o2);

        fieldForComparing.setAccessible(true);
        Object fieldObj1 = fieldForComparing.get(o1);
        Object fieldObj2 = fieldForComparing.get(o2);
        fieldForComparing.setAccessible(false);

        if (fieldObj1 == null && fieldObj1 == null) return 0;
        if (fieldObj1 == null) return 1;
        if (fieldObj1 == null) return -1;

        if (fieldForComparing.getType().isPrimitive()) {
            if (boolean.class.isAssignableFrom(fieldForComparing.getType())){
                return ((Boolean) fieldObj1).compareTo((Boolean) fieldObj2);
            }
            if (byte.class.isAssignableFrom(fieldForComparing.getType())){
                return ((Byte) fieldObj1).compareTo((Byte) fieldObj2);
            }
            if (short.class.isAssignableFrom(fieldForComparing.getType())){
                return ((Short) fieldObj1).compareTo((Short) fieldObj2);
            }
            if (char.class.isAssignableFrom(fieldForComparing.getType())){
                return ((Character) fieldObj1).compareTo((Character) fieldObj2);
            }
            if (int.class.isAssignableFrom(fieldForComparing.getType())){
                return ((Integer) fieldObj1).compareTo((Integer) fieldObj2);
            }
            if (long.class.isAssignableFrom(fieldForComparing.getType())){
                return ((Long) fieldObj1).compareTo((Long) fieldObj2);
            }
            if (float.class.isAssignableFrom(fieldForComparing.getType())){
                return ((Float) fieldObj1).compareTo((Float) fieldObj2);
            }
            if (double.class.isAssignableFrom(fieldForComparing.getType())){
                return ((Double) fieldObj1).compareTo((Double) fieldObj2);
            }
        }
        return ((Comparable) fieldObj1).compareTo(fieldObj2);
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return "Random field comparator of class " + targetType.getTypeName() + " is comparing " + fieldForComparing.getName();
    }
}