package list;

import interfaces.ListADT;
import nodes.LinearNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class CircularLinkedList<T> implements ListADT<T> {

    protected LinearNode<T> front;
    protected LinearNode<T> rear;
    protected int count;
    protected int modCount;

    public CircularLinkedList() {
        front = null;
        rear = null;
        count = 0;
        modCount = 0;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removedElement = front.getCurrent();
        LinearNode<T> loopNode = rear;
        for (int i = 0; i < count && loopNode.getNext() != null; i++) {
            loopNode = loopNode.getNext();
        }
        count--;
        loopNode.setNext(null);
        front = loopNode;
        return removedElement;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        count--;
        T removedElement = rear.getCurrent();
        LinearNode<T> oldRear = rear;
        rear = rear.getNext();
        oldRear.setNext(null);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        LinearNode<T> loopNode = rear;
        T removedElement = null;
        if (rear == null) {
            throw new NullPointerException("The list is empty");
        }
        for (int i = 0; i < count && loopNode.getNext() != front; i++) {
            if (loopNode.getNext().getCurrent().equals(element)) {
                LinearNode<T> newNext = loopNode.getNext().getNext();
                removedElement = loopNode.getNext().getCurrent();
                loopNode.getNext().setNext(null);
                loopNode.setNext(newNext);
                count--;
                break;
            }
        }
        return removedElement;
    }

    @Override
    public T first() {
        return front.getCurrent();
    }

    @Override
    public T last() {
        return rear.getCurrent();
    }

    @Override
    public boolean contains(T target) {
        LinearNode loopNode = rear;
        for (int i = 0; i < count && loopNode.getNext() != front; i++) {
            if (loopNode.getCurrent().equals(target)) {
                return true;
            }
            loopNode = loopNode.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (front == null);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularLinkedListIterator<>(modCount);
    }

    private class CircularLinkedListIterator<T> implements Iterator<T> {

        private LinearNode<T> current;
        private int expectedModCount;

        public CircularLinkedListIterator(int modCount) {
            current = (LinearNode<T>) front;
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return current != rear;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new RuntimeException("ModCounter.");
            }
            T result = current.getCurrent();
            current = current.getNext();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
