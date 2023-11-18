package queue;

import exceptions.EmptyCollectionException;
import interfaces.QueueADT;
import nodes.LinearNode;

public class LinkedQueue<T> implements QueueADT<T> {
    private LinearNode<T> front;
    private LinearNode<T> rear;
    private int counter;
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.counter = 0;
    }
    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if (isEmpty()) {
            this.front = newNode;
        }else {
            rear.setNext(newNode);
        }
        this.rear = newNode;

        this.counter++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedQueue");
        }

        T removed = this.front.getCurrent();
        this.front = this.front.getNext();
        this.counter--;
        if (isEmpty()) {
            this.rear = null;
        }
        return removed;
    }

    @Override
    public T first() {
        return this.front.getCurrent();
    }

    @Override
    public boolean isEmpty() {
        return (this.front == null || this.rear == null);
    }

    @Override
    public int size() {
        return this.counter;
    }

    @Override
    public String toString() {
        LinearNode<T> currentNode;
        currentNode = this.front;
        String string = "List:\n";
        for(int i = 0; i < this.counter && currentNode != null; i++) {
            string += currentNode.getCurrent().toString() + "\n";
            currentNode = currentNode.getNext();
        }
        return string;
    }
}
