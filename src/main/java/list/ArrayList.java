package list;

import interfaces.ListADT;
import nodes.DoubleNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ArrayList<T> implements ListADT<T> {
    private static final int DEFAULT_CAPACITY = 100;
    private static final int NOT_FOUND = -1;
    private static final int EXPAND_BY = 2;
    protected int rear;
    protected int modCount;
    protected T[] list;
    public ArrayList() {
        this.rear = 0;
        this.modCount = 0;
        this.list = (T[]) new Object[DEFAULT_CAPACITY];
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T element = list[0];

        for(int i = 0; i < rear; i++) {
            list[i] = list[i + 1];
        }

        list[--rear] = null;
        modCount++;
        return element;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T element = list[rear - 1];

        list[--rear] = null;
        modCount++;
        return element;

    }

    private int findIndex(T element) {
        for(int i = 0; i < rear; i++)
            if(list[i].equals(element))
                return i;

        return NOT_FOUND;
    }
    public T remove(T element) {
        if(isEmpty()) {
            return null;
        }

        int elementIndex = findIndex(element);

        if(elementIndex == NOT_FOUND) {
            return null;
        }

        T removedElement = list[elementIndex];

        for(int i = elementIndex; i < rear; i++) {
            list[i] = list[i + 1];
        }

        list[--rear] = null;
        modCount++;
        return removedElement;
    }

    public T first() {
        if(isEmpty()) {
            return null;
        }

        return list[0];
    }

    public T last() {
        if(isEmpty()) {
            return null;
        }

        return list[rear - 1];
    }

    public boolean contains(T target) {
        return (findIndex(target) != NOT_FOUND);
    }

    public boolean isEmpty() {
        return (rear == 0);
    }

    public int size() {
        return rear;
    }
    private class ArrayIterator implements Iterator<T> {
        private final T[] array;
        private int i;
        private final int expectedModCount;
        public ArrayIterator(int modCount) {
            array = list;
            i = 0;
            expectedModCount = modCount;
        }


        @Override
        public boolean hasNext() {
            return i  < rear;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if(expectedModCount != modCount) {
                throw new RuntimeException();
            }
            return array[i++];
        }

        @Override
        public void remove() {
                throw new UnsupportedOperationException();
        }
    }
    public Iterator<T> iterator() {
        return  new ArrayIterator(modCount);
    }

    public void expandCapacity() {
        T[] larger = (T[]) new Object[this.rear * EXPAND_BY];

        if (rear >= 0) System.arraycopy(this.list, 0, larger, 0, rear);

        this.list = larger;
    }

    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < rear; i++) {
            s += list[i].toString();
        }
        return s;
    }
}
