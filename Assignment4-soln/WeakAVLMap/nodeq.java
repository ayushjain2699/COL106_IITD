package col106.assignment4.WeakAVLMap;

public class nodeq<K extends Comparable,V>
{
        nodeq<K,V> next = null;
        nodeq<K,V> previous = null;
        Node<K,V> value;
        nodeq(Node<K,V> node) {
            value = node;
        }
}
