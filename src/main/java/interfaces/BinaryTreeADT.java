package interfaces;

import exceptions.ElementNotFoundException;

import java.util.Iterator;

public interface BinaryTreeADT<T> {
    T getRoot();
    boolean isEmpty();
    int size();
    boolean contains(T targetElement);
    T find(T targetElement) throws ElementNotFoundException;
    String toString();
    Iterator<T> iteratorInOrder();
    Iterator<T> iteratorPreOrder();
    Iterator<T> iteratorPostOrder();
    Iterator<T> iteratorLevelOrder();
}
