package nodes;

public class DoubleNode<T> {
    private DoubleNode<T> next;
    private T current;
    private DoubleNode<T> previous;

    public DoubleNode() {
        this.next = null;
        this.previous = null;
        this.current = null;
    }

    public DoubleNode(T element) {
        this.next = null;
        this.current = element;
        this.previous = null;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public DoubleNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
}
