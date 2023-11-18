package list.ordered;

import exceptions.ElementAlreadyExistsException;
import interfaces.OrderedListADT;
import list.DoubleList;
import nodes.DoubleNode;
import stack.ArrayStack;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidParameterException;

public class DoubleLinkedOrderedList<T> extends DoubleList<T> implements OrderedListADT<T> {
    public DoubleLinkedOrderedList() {
        super();
    }
    @Override
    public void add(T element) {
        Comparable temp;
        if (element instanceof Comparable)
            temp = (Comparable) element;
        else
            throw new InvalidParameterException("OrderedDoubleList: add(): element is not comparable");


        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setCurrent(element);
        if (front == null) {
            front = newNode;
            rear = newNode;
        } else if (temp.compareTo(front.getCurrent()) <= 0) {
            newNode.setNext(front);
            front.setPrevious(newNode);
            front = newNode;
        } else if (temp.compareTo(rear.getCurrent())>=0) {
            newNode.setPrevious(rear);
            rear.setNext(newNode);
            rear = newNode;
        } else {
            DoubleNode<T> current = front.getNext();

            while(current != null && temp.compareTo(current.getCurrent()) > 0) {
                current = current.getNext();
            }

            if (current != null) {
                newNode.setPrevious(current.getPrevious());
                newNode.setNext(current);
                current.getPrevious().setNext(newNode);
                current.setPrevious(newNode);
            } else {
                newNode.setPrevious(rear);
                rear.setNext(newNode);
                rear = newNode;
            }
        }
        count++;
        modCount++;
    }

    public ArrayStack<T> invertAndStack() {
        ArrayStack<T> stack = new ArrayStack<>();
        DoubleNode<T> traverse = rear;
        while (traverse != null) {
            stack.push(traverse.getCurrent());
            traverse = traverse.getPrevious();
        }
        return stack;
    }
}
