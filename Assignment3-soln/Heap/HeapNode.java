package col106.assignment3.Heap;


public class HeapNode<T extends Comparable, E extends Comparable> {

    private E value;
    private T key;
    public E getValue() {
        return value;
    }
    public T getKey()
    {
        return key;
    }
    public HeapNode(T k,E val)
    {
        value = val;
        key = k;
    }
}
