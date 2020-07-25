package col106.assignment3.Election;

import col106.assignment3.Heap.HeapNode;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapElection{
    public ArrayList<HeapNode_Election> L = new ArrayList<>();
    int length = 0;
    int largest;
    HeapNode_Election temp;
    public HeapElection()
    {
        L.add(null);
    }
    public void max_heapify(ArrayList<HeapNode_Election> List, int i, int N)
    {

        int left = 2*i;                   //left child
        int right = 2*i +1;         //right child
        if(left<= N && L.get(left).getValue()>(L.get(i).getValue()) )
            largest = left;
        else
            largest = i;
        if(right <= N && L.get(right).getValue()>(L.get(largest).getValue()))
            largest = right;
        if(largest != i )
        {
            temp = L.get(i);
            L.set(i,L.get(largest));
            L.set(largest,temp);
            max_heapify(L, largest,N);
        }
    }
    public void insert(String[] key, int value) {
        //write your code here
        length = length + 1;
        HeapNode_Election node = new HeapNode_Election(key,value);
        if(length>=L.size())
            L.add(node);
        else
            L.set(length,node);  //assuming all the numbers greater than 0 are to be inserted in queue.
        increaseKey(key, value);
    }
    public HeapNode_Election extractMax() {
        //write your code here
        HeapNode_Election max = L.get(1);
        L.set(1,L.get(length));
        length = length -1;
        max_heapify(L,1,length);
        return max;
    }

    public void delete(String[] key) {
        //write your code here
        int i = get_index(key);
        L.set(i,L.get(length));
        length = length -1;
        max_heapify(L,i,length);
        HeapNode_Election temp = L.get(i);
        increaseKey(temp.getKey(),temp.getValue());
    }
    public int get_index(String[] key)
    {
        int i = 1;
        for(i = 1;i<=length;i++)
        {
            if(Arrays.equals(L.get(i).getKey(),(key)))
                break;
        }
        return i;
    }
    public void increaseKey(String[] key, int value) {
        //write your code here
        int i = get_index(key);
        HeapNode_Election node = new HeapNode_Election(key,value);
        L.set(i,node);
        while( i > 1 && L.get(i/2).getValue()<(L.get(i).getValue()))
        {
            temp = L.get(i/2);
            L.set(i/2,L.get(i));
            L.set(i,temp);
            i = i/2;
        }
    }

    public void printHeap() {
        //write your code here
        for(int i = 1;i<=length;i++)
        {
            System.out.println(L.get(i).getKey()[0] + " " + L.get(i).getKey()[1] + ", " + L.get(i).getValue());
        }
    }
}
