package interfaces;


import exceptions.EmptyCollectionException;

public interface QueueADT<T> {
    void enqueue(T element);

    T dequeue() throws EmptyCollectionException;

    T first();

    boolean isEmpty();

    int size();

    String toString();
}
