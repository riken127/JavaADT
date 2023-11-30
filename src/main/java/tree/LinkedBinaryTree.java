package tree;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import interfaces.BinaryTreeADT;
import list.unordered.ArrayUnorderedList;
import nodes.BinaryTreeNode;
import queue.LinkedQueue;

import java.lang.reflect.Array;
import java.util.Iterator;
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected BinaryTreeNode<T> root;

    public LinkedBinaryTree(){
        count = 0;
        root = null;
    }

    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<>(element);
    }
    @Override
    public T getRoot() {
        return root.getElement();
    }

    @Override
    public boolean isEmpty() {
        return (root == null);
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
        }catch(ElementNotFoundException e) {}
        return false;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null)
            throw new ElementNotFoundException("Binary Tree");

        return (current.getElement());
    }

    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null)
            return null;
        if (next.getElement().equals(targetElement))
            return next;
        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null)
            temp = findAgain(targetElement, next.getRight());

        return temp;
    }
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(root, tempList);

        return tempList.iterator();
    }

    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preorder(root, tempList);

        return tempList.iterator();
    }

    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preorder(node.getLeft(), tempList);
            preorder(node.getRight(), tempList);
        }
    }
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postorder(root, tempList);

        return tempList.iterator();
    }

    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postorder(node.getLeft(), tempList);
            postorder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        LinkedQueue<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>();
        nodes.enqueue(root);
        levelorder(nodes, results);

        return results.iterator();
    }

    protected void levelorder(LinkedQueue<BinaryTreeNode<T>> nodes, ArrayUnorderedList<T> results) {
        while (!nodes.isEmpty()) {
            try {
                BinaryTreeNode<T> node = nodes.dequeue();

                if (node != null) {
                    results.addToRear(node.getElement());
                    nodes.enqueue(node.getLeft());
                    nodes.enqueue(node.getRight());
                }else {
                    results.addToRear(null);
                }
            }catch(EmptyCollectionException e) {
                e.getStackTrace();
            }

        }
    }
}
