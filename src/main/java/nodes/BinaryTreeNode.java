package nodes;

public class BinaryTreeNode<T> {
    protected T element;
    protected BinaryTreeNode<T> left, right;

    public BinaryTreeNode (T element) {
        this.element = element;
        left = null;
        right = null;
    }

    public int numChildren() {
        int children = 0;

        if (left != null)
            children = 1 + left.numChildren();
        if (right != null)
            children += 1 + right.numChildren();
        return children;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }
}
