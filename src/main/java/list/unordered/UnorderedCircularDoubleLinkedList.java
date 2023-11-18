package list.unordered;

import interfaces.UnorderedListADT;
import list.CircularDoubleLinkedList;
import nodes.DoubleNode;

import java.util.NoSuchElementException;

public class UnorderedCircularDoubleLinkedList<T> extends CircularDoubleLinkedList<T> implements UnorderedListADT<T> {
    @Override
    public void addToFront(T element) {
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setCurrent(element);
        if(isEmpty()) {
            front = newNode;
            rear = newNode;
            rear.setNext(front);
            rear.setPrevious(front);
        }else {
            newNode.setNext(front);
            newNode.setPrevious(rear);
            front = newNode;
            rear.setNext(front);
        }

        modCount++;
        count++;
    }

    @Override
    public void addToRear(T element) {
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setCurrent(element);
        if(isEmpty()) {
            front = newNode;
            rear = newNode;
            rear.setNext(front);
            rear.setPrevious(front);
        }else {
            newNode.setNext(front);
            newNode.setPrevious(rear);
            rear.setNext(newNode);
            rear = newNode;
        }
        count++;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target) {
        DoubleNode<T> loopNode = front;
        DoubleNode<T> newNode = new DoubleNode<>();

        newNode.setCurrent(element);


        if (isEmpty()) {
            throw new NoSuchElementException("No elements found");
        } else {
            for(int i = 0; i < size() && loopNode.getNext() != front; i++){
                if(loopNode.getCurrent().equals(target)) {
                    if(loopNode.getNext() != front ) {
                        newNode.setNext(loopNode.getNext());
                        newNode.setPrevious(loopNode);
                        loopNode.getNext().setPrevious(loopNode);
                    }else {
                        rear = newNode;
                    }
                    loopNode.setNext(newNode);
                    count++;
                    modCount++;
                    return;
                }
                loopNode = loopNode.getNext();
            }
        }
        throw new NoSuchElementException("Not found");
    }
}
