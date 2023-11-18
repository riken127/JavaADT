package stack;

import exceptions.EmptyCollectionException;
import interfaces.StackADT;
import nodes.LinearNode;

public class LinkedStack<T> implements StackADT<T> {
    private LinearNode<T> top;
    private int count;

    public LinkedStack() {
        top = null;
        count = 0;
    }

    public void push(T element) {
        LinearNode<T> node = new LinearNode<>(element);
        if (this.top == null) {
            top = node;
        } else {
            node.setNext(top);
            top = node;
        }

        count++;
    }

    public T pop() throws EmptyCollectionException {
        if (top == null) {
            throw new EmptyCollectionException("Stack");
        }
        T data = top.getCurrent();
        top = top.getNext();

        count--;
        return data;
    }

    public T peek() throws EmptyCollectionException {
        if (top == null) {
            throw new EmptyCollectionException("Stack");
        }

        T data = top.getCurrent();
        return data;
    }

    public boolean isEmpty() {
        return (this.top.getNext() == null);
    }

    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        String s = "Linked Stack Elements\n";
        LinearNode<T> node = top.getNext();
        for (int i = 0; i < count && top.getNext() != null; i++) {
            s += node.toString();
        }

        return s;
    }
}
