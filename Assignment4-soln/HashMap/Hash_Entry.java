package col106.assignment4.HashMap;

public class Hash_Entry<V>
{
    public String key;
    public V value;
    int hash_index;
    public Hash_Entry(String k, V val)
    {
        key = k;
        value = val;
    }
}
