package col106.assignment3.BST;
public class Node<T extends Comparable, E extends Comparable>
{
    Node<T,E> next = null;
    Node<T,E> previous = null;
    BSTNode<T,E> value;
    Node(BSTNode<T,E> node)
    {
        value = node;
    }
}