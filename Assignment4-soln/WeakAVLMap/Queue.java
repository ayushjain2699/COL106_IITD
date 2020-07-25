package col106.assignment4.WeakAVLMap;
public class Queue<K extends Comparable,V>
{
    public nodeq<K,V> start = null;
    public nodeq<K,V> end = null;
    public int size = 0;
    public void enqueue(Node<K,V> bstnode)
    {
        nodeq<K,V> node = new nodeq<>(bstnode);
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
    public Node<K,V> front()
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