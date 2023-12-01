package org.example;

import list.LinkedList;
import list.SortingAndSearching;
import list.unordered.UnorderedCircularDoubleLinkedList;
import list.unordered.UnorderedLinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        UnorderedLinkedList<Integer> integers = new UnorderedLinkedList<>();
        integers.addToRear(1);
        integers.addToRear(2);
        integers.addToRear(100);
        integers.addToRear(20);
        integers.addToFront(412);
        integers.addToFront(9999);
        integers.addToRear(5);

        System.out.println(integers);try {
            SortingAndSearching.quickSort(integers);
        }catch(Exception e) {
            e.getMessage();
        }
        System.out.println(integers);
    }
}