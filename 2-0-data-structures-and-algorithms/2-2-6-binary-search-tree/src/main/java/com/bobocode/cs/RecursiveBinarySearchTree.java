package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    Node<T> rootNode;
    int size = 0;
    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> recursiveBinarySearchTree = new RecursiveBinarySearchTree<>();
        for(T element : elements){
            recursiveBinarySearchTree.insert(element);
        }
        return recursiveBinarySearchTree;
    }

    @Override
    public boolean insert(T element) {
        if (rootNode == null) {
            rootNode = new Node<>(element);
            size++;
            return true;
        }
        return insert(rootNode, element);
    }

    private boolean insert(Node<T> node, T element) {
        if (node.element.compareTo(element) > 0) {
            if (node.left == null) {
                node.left = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(node.left, element);
            }
        } else if (node.element.compareTo(element) < 0) {
            if (node.right == null) {
                node.right = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(node.right, element);
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) throw new NullPointerException();
        if(rootNode == null) return false;
        return contains(rootNode, element);
    }

    private boolean contains(Node<T> node, T element) {
        if (node.element.compareTo(element) == 0) return true;
        if (node.element.compareTo(element) > 0 && node.left != null) return contains(node.left, element);
        if (node.element.compareTo(element) < 0 && node.right != null) return  contains(node.right, element);
        return false;
    }

    @Override
    public int size() {
        return getSize(rootNode);
    }

    private int getSize(Node<T> node) {
        if (node == null) return 0;
        return getSize(node.left) + getSize(node.right) + 1;
    }

    @Override
    public int depth() {
        if (rootNode == null) return 0;
        return getDepth(rootNode) - 1;
    }

    private int getDepth(Node<T> node) {
        if (node == null) return 0;
        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);
        return (leftDepth > rightDepth) ? leftDepth + 1 : rightDepth + 1;
    }


    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        Stack<Node<T>> stack = new Stack<>();

        Node<T> node = rootNode;

        while (node != null || !stack.empty()) {
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            consumer.accept(node.element);
            node = node.right;
        }
    }

//    @Override
//    public void inOrderTraversal(Consumer<T> consumer) {
//        inOrderTraversal(rootNode, consumer);
//    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer){
        if (node == null) return;
        inOrderTraversal(node.left, consumer);
        consumer.accept(node.element);
        inOrderTraversal(node.right, consumer);
    }

    static class Node <T> {
        T element;
        Node<T> left;
        Node<T> right;
        public Node (T element) {
            this.element = element;
        }
    }
}
