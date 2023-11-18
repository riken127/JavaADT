package list.ordered;

import interfaces.OrderedListADT;
import list.ArrayList;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {
    public ArrayOrderedList() {
        super();
    }

    @Override
    public void add(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        Comparable<T> temporary = (Comparable<T>) element;

        int i = 0;
        while (i < rear && temporary.compareTo(list[i]) > 0)
            i++;
        for(int j = rear; j > i; j--) {
            list[j] = list[j - 1];
        }
        list[i] = element;
        rear++;
        modCount++;
    }
}
