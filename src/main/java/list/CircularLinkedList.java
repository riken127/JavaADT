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

    public T remove(T element) {
        if (isEmpty()) {
            return null;
        }

        LinearNode<T> loopNode = front;
        T removedElement = null;

        while (loopNode != rear && loopNode.getNext() != front) {
            if (loopNode.getNext().getCurrent().equals(element)) {
                LinearNode<T> newNext = loopNode.getNext().getNext();
                removedElement = loopNode.getNext().getCurrent();

                if (loopNode.getNext() == rear) {
                    rear = loopNode;
                }

                loopNode.setNext(newNext);
                count--;
                break;
            }
            loopNode = loopNode.getNext();
        }

        // Update front if the first node is removed
        if (front != null && front.getCurrent().equals(element)) {
            front = front.getNext();
            count--;
        }

        // Update rear if the last node is removed
        if (isEmpty()) {
            rear = null;
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
        LinearNode<T> loopNode = rear;
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
        private final int expectedModCount;
        private int iteratorCounter;

        public CircularLinkedListIterator(int modCount) {
            current = (LinearNode<T>) front;
            expectedModCount = modCount;
            iteratorCounter = 0;
        }

        @Override
        public boolean hasNext() {
            return (iteratorCounter < size());
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
            iteratorCounter++;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        LinearNode<T> node = front;
        String s = "";
        for(int i = 0; i < size(); i++) {
            if(node == front) {
                s += "#FRONT#";
            }else if (node == rear) {
                s += "#REAR#";
            }
            s += "[&" + node + "\t*" +node.getCurrent() + "\t->\t" + node.getNext() + "]\n";
            node = node.getNext();
        }
        return s;
    }
}
