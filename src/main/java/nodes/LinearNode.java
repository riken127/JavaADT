package nodes;

public class LinearNode<T> {
    private T current;
    private LinearNode<T> next;

    public LinearNode(T current) {
        this.current = current;
    }

    public LinearNode() {
        this.current = null;
        this.next = null;
    }
    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public LinearNode<T> getNext() {
        return next;
    }

    public void setNext(LinearNode<T> next) {
        this.next = next;
    }
}
