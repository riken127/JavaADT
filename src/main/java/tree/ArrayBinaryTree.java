package tree;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import interfaces.BinaryTreeADT;
import list.unordered.ArrayUnorderedList;
import queue.LinkedQueue;

import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[CAPACITY];
    }

    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }
    @Override
    public T getRoot() {
        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        return (tree[0] == null);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(T targetElement) {
        try {
            if (find(targetElement) != null)
                return true;
        } catch (ElementNotFoundException e) {}
        return false;
    }
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++) {
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }
        }

        if (!found)
            throw new ElementNotFoundException("binary tree");

        return temp;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(0, tempList);
        return tempList.iterator();
    }

    protected void inorder(int node, ArrayUnorderedList<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder (node*2 + 1, tempList);
                tempList.addToRear(tree[node]);
                inorder((node + 1) * 2, tempList);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preorder(0, tempList);
        return tempList.iterator();
    }

    protected void preorder(int node, ArrayUnorderedList<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                tempList.addToRear(tree[node]);
                preorder(node * 2 + 1, tempList);
                preorder((node + 1) * 2, tempList);
            }
        }
    }
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postorder(0, tempList);

        return tempList.iterator();
    }

    protected void postorder(int node, ArrayUnorderedList<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                postorder(node * 2 + 1, tempList);
                postorder((node + 1) * 2, tempList);
                tempList.addToRear(tree[node]);
            }
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        LinkedQueue<Integer> nodes = new LinkedQueue<>();
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>();
        nodes.enqueue(0);
        levelorder(nodes, results);

        return results.iterator();
    }

    protected void levelorder(LinkedQueue<Integer> nodes, ArrayUnorderedList<T> results) {
        while(!nodes.isEmpty()) {
            try {
                Integer currentPosition = nodes.dequeue();

                if (tree[currentPosition] != null) {
                    results.addToRear(tree[currentPosition]);
                    nodes.enqueue(currentPosition * 2 + 1);
                    nodes.enqueue((currentPosition + 1) * 2);
                }else {
                    results.addToRear(null);
                }
            }catch (EmptyCollectionException e) {
                e.getStackTrace();
            }
        }
    }
}
