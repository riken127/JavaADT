package list;

import list.unordered.DoubleLinkedUnorderedList;
import nodes.DoubleNode;
import nodes.LinearNode;

public class SortingAndSearching<T> {
    public static <T extends Comparable<? super T>> boolean linearSearch(T[] data, int min, int max, T target) {
        int index = min;
        boolean found = false;
        while (!found && index <= max) {
            if (data[index].compareTo(target) == 0)
                found = true;
            index++;
        }
        return found;
    }

    public static <T extends Comparable<? super T>> boolean linearSearch(LinkedList<T> list, T target) {
        boolean found = false;
        LinearNode<T> node = list.front;
        int i = 0;
        while (!found && list.front != list.rear && i < list.size()) {
            if (node.getCurrent().compareTo(target) == 0)
                found = true;
            node = node.getNext();
            i++;
        }
        return found;
    }

    public static <T extends Comparable<? super T>> boolean binarySearch(T[] data, int min, int max, T target) {
        boolean found = false;
        int midPoint = (min + max) / 2;

        if (data[midPoint].compareTo(target) == 0) {
            found = true;
        } else if (data[midPoint].compareTo(target) > 0) {
            if (min <= midPoint - 1)
                found = binarySearch(data, min, midPoint - 1, target);
        } else if (midPoint + 1 <= max) {
            found = binarySearch(data, midPoint + 1, max, target);
        }

        return found;
    }

    public static <T extends Comparable<? super T>> void selectionSort(T[] data) {
        int min;
        T temp;
        for (int index = 0; index < data.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < data.length; scan++) {
                if (data[scan].compareTo(data[min]) < 0)
                    min = scan;
            }
            temp = data[min];
            data[min] = data[index];
            data[index] = temp;
        }
    }

    public static <T extends Comparable<? super T>> void selectionSort(LinkedList<T> list) {
        if (list.size() <= 1)
            return;

        LinearNode<T> sortedEnd = null;
        LinearNode<T> newFront = null;
        LinearNode<T> newRear = null;
        for (int i = 0; i < list.size(); i++) {
            LinearNode<T> smallestPrev = null;
            LinearNode<T> smallest = list.front;
            LinearNode<T> prev = null;
            LinearNode<T> node = list.front;

            while (node != null && node.getNext() != null) {
                if (node.getNext().getCurrent().compareTo(smallest.getCurrent()) < 0) {
                    smallestPrev = node;
                    smallest = node.getNext();
                }
                node = node.getNext();
            }

            if (smallestPrev != null) {
                smallestPrev.setNext(smallest.getNext());
            } else {
                list.front = smallest.getNext();
            }

            smallest.setNext(null);
            if (sortedEnd == null) {
                newFront = smallest;
                newRear = smallest;
            } else {
                sortedEnd.setNext(smallest);
                newRear = smallest;
            }

            sortedEnd = smallest;
        }

        list.front = newFront;
        list.rear = newRear;
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] data) {
        for (int index = 1; index < data.length; index++) {
            T key = data[index];
            int position = index;

            while (position > 0 && data[position - 1].compareTo(key) > 0) {
                data[position] = data[position - 1];
                position--;
            }
            data[position] = key;
        }
    }

    public static <T extends Comparable<? super T>> void insertionSort(LinkedList<T> list) {
        if (list.size() <= 1)
            return;

        LinearNode<T> sorted = null;
        LinearNode<T> lastSorted = null;
        LinearNode<T> current = list.front;
        LinearNode<T> next;

        while (current != null) {
            next = current.getNext();

            if (sorted == null || sorted.getCurrent().compareTo(current.getCurrent()) > 0) {
                current.setNext(sorted);
                sorted = current;
                if (lastSorted == null) {
                    lastSorted = current; // Update lastSorted for the first node in the sorted list
                }
            } else {
                LinearNode<T> temp = sorted;
                while (temp.getNext() != null && temp.getNext().getCurrent().compareTo(current.getCurrent()) < 0) {
                    temp = temp.getNext();
                }
                current.setNext(temp.getNext());
                temp.setNext(current);
                if (temp == lastSorted) {
                    lastSorted = current; // Update lastSorted when the current node is inserted at the end
                }
            }

            current = next;
        }

        list.front = sorted;
        list.rear = lastSorted; // Set the last node as the rear
    }

    public static <T extends Comparable<? super T>> void bubbleSort(T[] data) {
        int position, scan;
        T temp;

        for (position = data.length - 1; position >= 0; position--) {
            for (scan = 0; scan <= position - 1; scan++) {
                if (data[scan].compareTo(data[scan + 1]) > 0) {
                    temp = data[scan];
                    data[scan] = data[scan + 1];
                    data[scan + 1] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<? super T>> void bubbleSort(LinkedList<T> list) {
        if (list.count <= 1)
            return;

        boolean swapped;

        LinearNode<T> right;
        LinearNode<T> left = null;

        do {
            swapped = false;
            right = list.front;

            while (right.getNext() != left) {
                if (right.getCurrent().compareTo(right.getNext().getCurrent()) > 0) {
                    T temporaryElement = right.getCurrent();
                    right.setCurrent(right.getNext().getCurrent());
                    right.getNext().setCurrent(temporaryElement);
                    swapped = true;
                }
                right = right.getNext();
            }
            left = right;
        } while (swapped);
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] data, int min, int max) {
        T[] temp;
        int index, left, right;

        if (min == max)
            return;

        int size = max - min + 1;
        int pivot = (min + max) / 2;
        temp = (T[]) (new Comparable[size]);

        mergeSort(data, min, pivot);
        mergeSort(data, pivot + 1, max);

        for (index = 0; index < size; index++)
            temp[index] = data[min + index];

        left = 0;

        right = pivot - min + 1;
        for (index = 0; index < size; index++) {
            if (right <= max - min) {
                if (left <= pivot - min) {
                    if (temp[left].compareTo(temp[right]) > 0) {
                        data[index + min] = temp[right++];
                    } else {
                        data[index + min] = temp[left++];
                    }
                } else {
                    data[index + min] = temp[left++];
                }
            } else {
                data[index + min] = temp[left++];
            }
        }
    }

    public static <T extends Comparable<? super T>> void mergeSort(LinkedList<T> list) {
        list.front = sortList(list, list.front, list.size());
    }

    private static <T extends Comparable<? super T>> LinearNode<T> sortList(LinkedList<T> list, LinearNode<T> front,
            int count) {
        if (count <= 1 || front == null || front.getNext() == null) {
            return front;
        }

        LinearNode<T> turtle = front;

        // Move hare by two and turtle by one
        for (int i = 0; i < count / 2 - 1; i++) {
            turtle = turtle.getNext();
        }

        LinearNode<T> right = sortList(list, turtle.getNext(), count - count / 2);
        turtle.setNext(null); // Break the list into two halves
        LinearNode<T> left = sortList(list, front, count / 2);

        return merge(list, left, right);
    }

    private static <T extends Comparable<? super T>> LinearNode<T> merge(LinkedList<T> list, LinearNode<T> firstList,
            LinearNode<T> secondList) {
        LinearNode<T> newList = new LinearNode<>();
        LinearNode<T> p = newList;

        while (firstList != null && secondList != null) {
            if (firstList.getCurrent().compareTo(secondList.getCurrent()) < 0) {
                p.setNext(firstList);
                firstList = firstList.getNext();
            } else {
                p.setNext(secondList);
                secondList = secondList.getNext();
            }
            p = p.getNext();
        }

        if (firstList == null && secondList == null)
            list.rear = p;

        if (firstList != null) {
            list.rear = firstList;
            p.setNext(firstList);
        } else if (secondList != null) {
            list.rear = secondList;
            p.setNext(secondList);
        }

        return newList.getNext();
    }

    public static <T extends Comparable<? super T>> void quickSort(LinkedList<T> list) {
        quickSort(list.front, list.rear);
    }

    private static <T extends Comparable<? super T>> void quickSort(LinearNode<T> start, LinearNode<T> end) {
        if (start != null && end != null && start != end && start.getNext() != end) {
            LinearNode<T> pivot = partition(start, end);

            quickSort(start, pivot);
            quickSort(pivot.getNext(), end);
        }
    }

    private static <T extends Comparable<? super T>> LinearNode<T> partition(LinearNode<T> start, LinearNode<T> end) {
        LinearNode<T> pivot = start;
        LinearNode<T> current = start.getNext();
        LinearNode<T> tail = start;

        while (current != end.getNext()) {
            if (current.getCurrent().compareTo(pivot.getCurrent()) < 0) {
                swap(tail.getNext(), current);
                tail = tail.getNext();
            }
            current = current.getNext();
        }

        swap(start, tail);

        return tail;
    }

    // Implement the swap method appropriately for a singly linked list.
    private static <T extends Comparable<? super T>> void swap(LinearNode<T> node1, LinearNode<T> node2) {
        T temp = node1.getCurrent();
        node1.setCurrent(node2.getCurrent());
        node2.setCurrent(temp);
    }


    public static <T extends Comparable<? super T>> void quickSort(T[] data, int min, int max) {
        int indexOfPartition;

        if (max - min > 0) {
            indexOfPartition = findPartition(data, min, max);

            quickSort(data, min, indexOfPartition - 1);
            quickSort(data, indexOfPartition + 1, max);
        }
    }



    private static <T extends Comparable<? super T>> int findPartition (T[] data, int min, int max) {
        int left, right;
        T temp, partitionElement;
        int middle = (min + max) / 2;

        partitionElement = data[middle];
        left = min;
        right = max;

        while (left < right) {
            while(data[left].compareTo(partitionElement) < 0) {
                left++;
            }
            while (data[right].compareTo(partitionElement) > 0) {
                right--;
            }
            if (left < right) {
                temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }

        temp = data[right];
        data[right] = data[left];
        data[left] = temp;
        return right;
    }


}
