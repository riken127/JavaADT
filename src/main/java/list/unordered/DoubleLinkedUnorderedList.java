package list.unordered;

import interfaces.UnorderedListADT;
import nodes.DoubleNode;
import list.DoubleList;

import javax.management.AttributeNotFoundException;
import java.util.NoSuchElementException;

public class DoubleLinkedUnorderedList<T> extends DoubleList<T> implements UnorderedListADT<T> {
    public DoubleLinkedUnorderedList() {
        super();
    }

    @Override
    public void addToFront(T element) {
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setCurrent(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.setNext(front);
            front.setPrevious(newNode);
            front = newNode;
        }
        count++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setCurrent(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.setPrevious(rear);
            rear.setNext(newNode);
            rear = newNode;
        }
        modCount++;
        count++;
    }

    @Override
    public void addAfter(T element, T target) {
        DoubleNode<T> traverse = front;
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setCurrent(element);

        if (isEmpty()) {
            throw new NoSuchElementException("No elements.");
        }else {
            while(traverse != null) {
                if (traverse.getCurrent().equals(target)) {
                    newNode.setNext(traverse.getNext());
                    newNode.setPrevious(traverse);
                    if(traverse.getNext() != null) {
                        traverse.getNext().setPrevious(newNode);
                    }
                    traverse.setNext(newNode);
                    modCount++;
                    count++;
                    return;
                }
                traverse = traverse.getNext();
            }
        }
        throw new NoSuchElementException("No element equals target");
    }
}
