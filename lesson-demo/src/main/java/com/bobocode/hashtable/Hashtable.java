package com.bobocode.hashtable;

import java.util.Arrays;

public class Hashtable <T>{

    @SuppressWarnings({"unchecked", "rawtype"})
    Node<T>[] index = new Node[5];
    /**
     * Adds an element to the hash table. Does not support duplicate elements.
     *
     * @param element
     * @return true if it was added
     */
    public boolean add(T element) {
        var bucketAddress = Math.abs(element.hashCode() % index.length);
        if (index[bucketAddress] == null) {
            index[bucketAddress] = new Node<>(element);
            return true;
        }

        if (alreadyContainsElement(index[bucketAddress], element)) return false;
        addElement(index[bucketAddress], element);
        return true;
    }

    private void addElement(Node<T> node, T element) {
        if (node.next != null) {
            addElement(node.next, element);
        } else {
            node.next = new Node<>(element);
        }
    }

    private boolean alreadyContainsElement(Node<T> node, T element){
        boolean contains = node.element.equals(element);
        if (!contains && node.next != null) {
            contains = alreadyContainsElement(node.next, element);
        }
        return contains;
    }

    /**
     * Prints a hash table according to the following format
     * 0: Andrii -> Taras
     * 1: Start
     * 2: Serhii
     * ...
     */
    public void printTable() {
        for (int i = 0; i < index.length; i++) {
            System.out.printf(i + ":");
            printLinkedList(index[i]);
            System.out.println("");
        }

    }

    public static void printLinkedList(Node<?> head) {
        if (head != null) {
            System.out.printf(head.element.toString());
            if (head.next != null) {
                System.out.printf(" -> ");
                printLinkedList(head.next);
            }
        }
    }

    /**
     * Creates a new underlying table with a given size and add all elements to the new table.
     *
     * @param newSize
     */
    public void resize(int newSize) {
        Node<T>[] currentIndex = Arrays.copyOfRange(index, 0, index.length);
        index = new Node[newSize];

        for (Node<T> node : currentIndex){
            addIntoIndex(node);
        }
    }

    private void addIntoIndex(Node<T> node) {
        if (node != null && node.element != null) {
            add(node.element);
            if (node.next != null) {
                addIntoIndex(node.next);
            }
        }
    }
}
