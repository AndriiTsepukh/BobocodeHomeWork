package com.bobocode.linkedlist;

import java.util.Objects;
import java.util.Stack;

public class DemoAppLinkedList {
    public static void main(String[] args) {
        var head = createLinkedList(4, 3, 9, 1);
        printReversedRecursively(head);
        System.out.println("");
        printReversedUsingStack(head);

        var reversed = reverseLinkedList(head);
        print(reversed);
    }

    /**
     * Creates a list of linked {@link Node} objects based on the given array of elements and returns a head of the list.
     *
     * @param elements an array of elements that should be added to the list
     * @param <T>      elements type
     * @return head of the list
     */
    public static <T> Node<T> createLinkedList(T... elements) {
        Node<T> node = null;
        for (T element : elements) {
            if (node == null) {
                node = new Node<>(element);
            } else {
                addElement(node, element);
            }
        }
        return node;
    }

    private static <T> void addElement(Node<T> node, T element) {
        if (node.next == null) {
            node.next = new Node<>(element);
        } else {
            addElement(node.next, element);
        }
    }

    /**
     * Prints a list in a reserved order using a recursion technique. Please note that it should not change the list,
     * just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedRecursively(Node<T> head) {
        Objects.requireNonNull(head);

        if (head.next == null) {
            System.out.printf(head.element.toString());
        } else {
            printReversedRecursively(head.next);
            System.out.printf(" -> ");
            System.out.printf(head.element.toString());
        }
    }

    public static <T> void print(Node<T> head) {
        Objects.requireNonNull(head);
        System.out.printf(head.element.toString());
        if (head.next != null) printRecursively(head.next);
    }

    public static <T> void printRecursively(Node<T> node) {
        System.out.printf(" -> " + node.element.toString());
        if (node.next != null) printRecursively(node.next);
    }

    /**
     * Prints a list in a reserved order using a {@link java.util.Stack} instance. Please note that it should not change
     * the list, just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedUsingStack(Node<T> head) {
        Objects.requireNonNull(head);

        Stack<T> stack = new Stack<>();

        while (head != null) {
            stack.add(head.element);
            head = head.next;
        }

        System.out.printf(stack.pop().toString());
        while (!stack.empty()){
            System.out.printf(" -> ");
            System.out.printf(stack.pop().toString());
        }
    }

    /**
     * Accepts a linked list head, reverses all elements and returns a new head (the last element).
     * PLEASE NOTE that it should not create new nodes, only change the next references of the existing ones.
     * E.g. you have a like "head:5 -> 7 -> 1 -> 4" should this method will return "head:4 -> 1 -> 7 -> 5"
     *
     * @param head the first element of the list
     * @param <T>  element type
     * @return new head
     */
    public static <T> Node<T> reverseLinkedList(Node<T> head) {
        Stack<Node<T>> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        Node<T> newHead = stack.pop();
        Node<T> node = newHead;
        while (!stack.empty()) {
            Node<T> temp = stack.pop();
            node.next = temp;
            node = temp;
            temp.next = null;
        }

        return newHead;
    }
}