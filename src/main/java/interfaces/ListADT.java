package interfaces;

import exceptions.EmptyCollectionException;

import java.util.Iterator;

public interface ListADT<T> extends Iterable<T>{
    T removeFirst() throws EmptyCollectionException;
    T removeLast() throws EmptyCollectionException;
    T remove(T element) throws EmptyCollectionException;
    T first();
    T last();
    boolean contains(T target) throws EmptyCollectionException;
    boolean isEmpty();
    int size();
    Iterator<T> iterator();
    @Override
    String toString();
}