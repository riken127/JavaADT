package list.unordered;

import interfaces.UnorderedListADT;
import list.CircularLinkedList;
import nodes.LinearNode;

import java.util.NoSuchElementException;

public class UnorderedCircularLinkedList<T> extends CircularLinkedList<T> implements UnorderedListADT<T> {
    @Override
    public void addToFront(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);

        if(isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.setNext(front);
            front = newNode;
        }
        count++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
            rear.setNext(front);
        }
        modCount++;
        count++;
    }

    @Override
    public void addAfter(T element, T target) {
        LinearNode<T> newNode = new LinearNode<>(element);

        if (isEmpty()) {
            throw new NoSuchElementException("No elements.");
        } else {
            LinearNode<T> current = front;
            boolean found = false;

            for (int i = 0; i < size() && !found; i++) {
                if (current.getCurrent().equals(target)) {
                    newNode.setNext(current.getNext());
                    current.setNext(newNode);
                    if (current == rear) {
                        rear = newNode;
                        rear.setNext(front);// Update rear if adding after the last node
                    }
                    found = true;
                } else {
                    current = current.getNext();
                }
            }

            if (!found) {
                throw new NoSuchElementException("Target not found.");
            }

            count++;
            modCount++;
        }
    }

}
