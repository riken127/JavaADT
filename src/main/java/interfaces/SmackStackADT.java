package interfaces;

import exceptions.EmptyCollectionException;

public interface SmackStackADT<T> {
    /**
     * Removes the last element;
     *
     * @return returns the last element
     */
    public T smack() throws EmptyCollectionException;
}
