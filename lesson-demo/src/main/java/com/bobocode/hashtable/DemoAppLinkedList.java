package com.bobocode.hashtable;

public class DemoAppLinkedList {
    public static void main(String[] args) {
        Node<Integer> elements = createLinkedList(1,3,5,7);
        printLinkedList(elements);

    }

    /**
     * Creates a linked list based on the input array and returns a head
     */
    public static <T> Node<T> createLinkedList(T... elements) {
        Node<T> node = null;
        for (T element : elements) {
            if(node == null) {
                node = new Node<>(element);
            } else {
                Node<T> temp = node;
                node = new Node<>(element);
                node.next = temp;
            }
        }
        return node;
    }

    /**
     * Prints a linked list with arrows like this
     * head:5 -> 7 -> 1 -> 4
     * @param head the first element of the list
     */
    public static void printLinkedList(Node<?> head) {
        System.out.printf(head.element.toString());
        if (head.next != null) {
            System.out.printf(" -> ");
            printLinkedList(head.next);
        }
    }

    public static Node<?> revertLinkedList(Node<?> head) {
        Node<?> next = head.next;
        revertLinkedList(next);
//TODO
        return null;
    }
}