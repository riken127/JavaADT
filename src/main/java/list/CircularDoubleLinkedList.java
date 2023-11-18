package list;

import interfaces.ListADT;
import nodes.DoubleNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class CircularDoubleLinkedList<T> implements ListADT<T> {

    protected DoubleNode<T> front, rear;
    protected int count;
    protected int modCount;

    public CircularDoubleLinkedList() {
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
        modCount++;
        T removedElement = front.getCurrent();
        front = front.getNext();
        rear.setNext(front);
        front.setPrevious(rear);
        count--;
        return removedElement;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        modCount++;
        T removedElement = rear.getCurrent();
        rear = rear.getPrevious();
        rear.setNext(front);
        front.setPrevious(rear);
        count--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            return null;
        }

        T removedElement = null;

        DoubleNode<T> loopedElement = front;

        for (int i = 0; i < count && loopedElement != rear; i++) {
            if (loopedElement.getCurrent().equals(element)) {
                removedElement = loopedElement.getCurrent();
                if (loopedElement.getNext() == loopedElement) {
                    front = null;
                    rear = null;
                } else {
                    loopedElement.getPrevious().setNext(loopedElement.getNext());
                    loopedElement.getNext().setPrevious(loopedElement.getPrevious());
                }
                count--;
                break;
            }
            loopedElement = loopedElement.getNext();
        }
        modCount++;
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
        DoubleNode<T> loopedElement = front;

        for (int i = 0; i < count && loopedElement != rear; i++) {
            if (loopedElement.getCurrent().equals(target)) {
                return true;
            }
            loopedElement = loopedElement.getNext();
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
        return new CircularDoubleLinkedListIterator(modCount);
    }

    private class CircularDoubleLinkedListIterator<T> implements Iterator<T> {

        private DoubleNode<T> current;
        private int expectedModCount;

        public CircularDoubleLinkedListIterator(int modCount) {
            current = (DoubleNode<T>) front;
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
                throw new RuntimeException("Modification Counter.");
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
