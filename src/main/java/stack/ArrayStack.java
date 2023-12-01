package stack;

import exceptions.EmptyCollectionException;
import interfaces.StackADT;

public class ArrayStack<T> implements StackADT<T> {
    /**
     * constant to represent the default capacity of the array.
     */
    private final int DEFAULT_CAPACITY = 100;
    /**
     * integer that represents both the number of elements and the next
     * available position in the array.
     */
    protected int top;
    /**
     * array of generic elements to represent the stack.
     */
    protected T[] stack;

    /**
     * Creates an empty stack using the default capacity.
     */
    public ArrayStack() {
        this.top = 0;
        this.stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty stack using the specified capacity.
     *
     * @param initialCapacity represents the specified capacity.
     */
    public ArrayStack(int initialCapacity) {
        this.top = 0;
        this.stack = (T[]) (new Object[initialCapacity]);
    }

    private void expandCapacity() {
        T[] temporaryStack = (T[]) (new Object[size()]);
        if (size() >= 0) System.arraycopy(this.stack, 0, temporaryStack, 0, size());
        this.stack = (T[]) (new Object[size() << 2]);
        if (size() >= 0) System.arraycopy(temporaryStack, 0, this.stack, 0, size());
    }

    /**
     * Adds the specified element to the top of this stack,
     * expanding the capacity of the stack array if necessary.
     *
     * @param element generic element to be pushed onto stack.
     */
    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity();
        }

        stack[top++] = element;
    }

    /**
     * Removes the element at the top of this stack and
     * returns a reference to it.
     *
     * @return T element removed from top of stack.
     * @throws EmptyCollectionException if a pop is attempted on an empty stack.
     */
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        T result = stack[--top];
        stack[top] = null;

        return result;
    }

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack.
     * Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element on top of the stack.
     * @throws EmptyCollectionException if a peek is attempted on an empty stack.
     */
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }

        return stack[top - 1];
    }

    public boolean isEmpty() {
        return (top == 0);
    }

    public int size() {
        return top;
    }

    @Override
    public String toString() {
        String s = "Stack Information.\n";
        for (int i = 0; i < size(); i++) {
            s += stack[i] + "\t" + (i + 1) + "º\n";
        }
        return s;
    }
}