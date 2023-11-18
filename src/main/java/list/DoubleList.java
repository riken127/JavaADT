package list;

import interfaces.ListADT;
import nodes.DoubleNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleList<T> implements ListADT<T> {
    protected DoubleNode<T> front, rear;
    protected int count, modCount;

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T removedElement = front.getCurrent();
        front = front.getNext();
        count--;
        modCount++;
        return removedElement;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T removedElement;

        DoubleNode<T> loopNode = front;

        for(int i = 0; i < count && loopNode.getNext().getNext() != null; i++) {
            loopNode = loopNode.getNext();
        }

        removedElement = loopNode.getNext().getCurrent();

        loopNode.setNext(null);
        count--;
        modCount++;
        return null;
    }

    @Override
    public T remove(T element) {
        if(isEmpty()) {
            return null;
        }

        T removedElement = null;

        DoubleNode<T> loopNode = front;

        for(int i = 0; i < count && loopNode.getNext() != null; i++) {
            if (loopNode.getCurrent().equals(element)) {
                removedElement = loopNode.getCurrent();
                loopNode.getPrevious().setNext(loopNode.getNext());
                loopNode.getNext().setPrevious(loopNode.getPrevious());
                count--;
                modCount++;
                break;
            }
            loopNode = loopNode.getNext();

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
        DoubleNode<T> loopNode = front;
        for(int i = 0; i < count && loopNode.getPrevious() != null; i++) {
            if (loopNode.getCurrent().equals(target)) {
                return true;
            }
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

    private class DoubleListIterator implements Iterator<T> {
        private DoubleNode<T> current;
        private int expectedModCount;
        public DoubleListIterator(int modCount) {
            expectedModCount = modCount;
            current = rear;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if(expectedModCount != modCount) {
                throw new RuntimeException();
            }
            T element = current.getCurrent();
            current = current.getNext();
            return element;
        }

        @Override
        public void remove() {
           throw new UnsupportedOperationException();
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new DoubleListIterator(modCount);
    }

    @Override
    public String toString() {
       String s = new String();
       DoubleNode<T> loopNode = rear;
        for (int i = 0; i < count && loopNode.getNext() != null ; i++) {
            s += loopNode.getCurrent().toString();
            loopNode = loopNode.getNext();
        }
        return s;
    }
}
