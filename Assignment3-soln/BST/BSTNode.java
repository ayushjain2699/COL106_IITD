package col106.assignment3.BST;


public class BSTNode<T extends Comparable, E extends Comparable> {

    private E value;
    private T key;
    public E getValue() {
        return value;
    }
    public T getKey()
    {
        return key;
    }
    public BSTNode<T,E> left;
    public BSTNode<T,E> right;
    public BSTNode<T,E> parent;
    BSTNode(T k,E val)
    {
        value = val;
        key = k;
        left = null;
        right = null;
        parent = null;
    }
}
