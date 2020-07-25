package col106.assignment4.WeakAVLMap;

public class Node<K extends Comparable,V>
{
    public K key;
    public V value;
    public int rank;
    public Node<K,V> left;
    public Node<K,V> right;
    public Node<K,V> parent;
    public Node(K key_, V value_)
    {
        key = key_;
        value = value_;
        rank = 0;
        left = null;
        right = null;
        parent = null;
    }

}
