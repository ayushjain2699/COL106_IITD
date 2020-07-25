package col106.assignment3.Heap;
import java.util.ArrayList;
public class Heap<T extends Comparable, E extends Comparable> implements HeapInterface <T, E> {
	/*
	 * Do not touch the code inside the upcoming block
	 * If anything tempered your marks

import java.util.ArrayList;
 will be directly cut to zero
	*/
	public static void main() {
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	}
	/*
	 * end code
	 */
	// write your code here

	public ArrayList<HeapNode<T,E>> L = new ArrayList<>();
	public int length = 0;
	int largest;
	HeapNode<T,E> temp;
	public Heap()

	{
		L.add(null);
	}
	public void max_heapify(ArrayList<HeapNode<T,E>> List, int i, int N)
	{

		int left = 2*i;                   //left child
		int right = 2*i +1;         //right child
		if(left<= N && L.get(left).getValue().compareTo(L.get(i).getValue())>0 )
		largest = left;
        else
		largest = i;
		if(right <= N && L.get(right).getValue().compareTo(L.get(largest).getValue())>0 )
		largest = right;
		if(largest != i )
		{
			temp = L.get(i);
			L.set(i,L.get(largest));
			L.set(largest,temp);
			max_heapify(L, largest,N);
		}
	}
	public void insert(T key, E value)
	{
		//write your code here
		length = length + 1;
		HeapNode<T,E> node = new HeapNode<>(key,value);
		if(length>=L.size())
			L.add(node);
		else
			L.set(length,node);  //assuming all the numbers greater than 0 are to be inserted in queue.
		increaseKey(key, value);
	}
	public E extractMax()
	{
		//write your code here
		HeapNode<T,E> max = L.get(1);
		L.set(1,L.get(length));
		length = length -1;
		max_heapify(L,1,length);
		return max.getValue();
	}
	public HeapNode<T,E> extractMaxNode()
	{
		//write your code here
		HeapNode<T,E> max = L.get(1);
		L.set(1,L.get(length));
		length = length -1;
		max_heapify(L,1,length);
		return max;
	}
	public void delete(T key)
	{
		//write your code here
	    int i = get_index(key);
		L.set(i,L.get(length));
		length = length -1;
		max_heapify(L,i,length);
		HeapNode<T,E> temp = L.get(i);
		increaseKey(temp.getKey(),temp.getValue());
	}
	public int get_index(T key)
	{
		int i = 1;
		for(i = 1;i<=length;i++)
		{
			if(L.get(i).getKey().equals(key))
				break;
		}
		return i;
	}
	public void increaseKey(T key, E value)
	{
		//write your code here
		int i = get_index(key);
		HeapNode<T,E> node = new HeapNode<>(key,value);
		L.set(i,node);
		while( i > 1 && L.get(i/2).getValue().compareTo(L.get(i).getValue())<0 )
		{
			temp = L.get(i/2);
			L.set(i/2,L.get(i));
			L.set(i,temp);
			i = i/2;
		}
	}
	public void printHeap()
	{
		//write your code here
		for(int i = 1;i<=length;i++)
		{
			System.out.println(L.get(i).getKey() + ", " + L.get(i).getValue());
		}
	}	
}
