package list.ordered;

import interfaces.OrderedListADT;
import list.DoubleList;
import nodes.DoubleNode;
import stack.ArrayStack;

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

        DoubleNode<T> traverse = front;
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setCurrent(element);
        boolean found = false;

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        }else if (temp.compareTo(rear.getCurrent()) >= 0) {
            rear.setNext(newNode);
            newNode.setPrevious(rear);
            newNode.setNext(null);
            rear = newNode;
        }else if (temp.compareTo(front.getCurrent()) <= 0) {
            front.setPrevious(newNode);
            newNode.setNext(front);
            newNode.setPrevious(null);
            front = newNode;
        }else {
            while((temp.compareTo(traverse.getCurrent()) > 0))
                traverse = traverse.getNext();
            newNode.setNext(traverse);
            newNode.setPrevious(traverse.getPrevious());
            traverse.getPrevious().setNext(newNode);
            traverse.setPrevious(newNode);
        }
        modCount++;
        count++;
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
