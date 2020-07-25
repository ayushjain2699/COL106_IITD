package col106.assignment3.BST;
public class Queue<T extends Comparable, E extends Comparable>
{
    public Node<T,E> start = null;
    public Node<T,E> end = null;
    public int size = 0;
    public void enqueue(BSTNode<T,E> bstnode)
    {
        Node<T,E> node = new Node<>(bstnode);
        if(size == 0)
        {
            start = node;
            end = node;
        }
        else
        {
            end.next = node;
            node.previous = end;
            end = node;
        }
        size++;
    }
    public BSTNode<T,E> front()
    {
        return start.value;
    }
    public void dequeue()
    {
        start = start.next;
        size--;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }
}