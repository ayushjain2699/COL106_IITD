package col106.assignment3.Election;


public class HeapNode_Election{

    private int value;
    private String[] key;
    public  int getValue() {
        return value;
    }
    public String[] getKey()
    {
        return key;
    }
    HeapNode_Election(String[] k,int val)
    {
        value = val;
        key = k;
    }
}
