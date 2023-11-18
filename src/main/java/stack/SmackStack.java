package stack;

import exceptions.EmptyCollectionException;
import interfaces.SmackStackADT;

public class SmackStack<T> extends ArrayStack<T> implements SmackStackADT<T> {
    @Override
    public T smack() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("SmackStack");
        }

        T result = stack[0];
        leftShift();
        stack[--top] = null;
        return result;
    }

    private void leftShift() {
        if (size() > 1) {
            for (int i = 0; i < size() - 1; i++) {
                stack[i] = stack[i + 1];
            }
        }
    }
}
