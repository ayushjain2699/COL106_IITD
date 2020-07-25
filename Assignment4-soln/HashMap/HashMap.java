package col106.assignment4.HashMap;
import java.util.ArrayList;
import java.util.Vector;

public class HashMap<V> implements HashMapInterface<V> {
	public int length;
	public ArrayList<Hash_Entry<V>> l = new ArrayList<>();
	public HashMap(int size)
	{
		// write your code here
		length = size;
		for(int i = 0;i<length;i++)
		{
			l.add(null);
		}
	}
	public int power(int a, int b)
    {
        int ans = 1;
        for(int i = 0;i<b;i++)
        {
            ans = ((ans%length)*(a%length))%length;
        }
        return ans%length;
    }
	public int hash_ftn(String s)
	{
		int n = s.length();
		int a = 41;
		int index = 0;
		for(int i = 0;i<n;i++)
		{
			index = (index%length + ((power(a,n-i-1))%length)* (((int)s.charAt(n-i-1))%length))%length;
		}
		return index%length;
	}
	public V put(String key, V value)
	{
		if(contains(key))
		{
			V temp = get(key);
			Hash_Entry<V> e = get_entry(key);
			e.value = value;
			return temp;
		}
		int index = hash_ftn(key);
		Hash_Entry<V> n = new Hash_Entry<>(key,value);
		n.hash_index = index;
		while(l.get(index)!=null)
		{
			index = (index+1)%length;
		}
		l.set(index,n);
		// write your code here
		return null;
	}
//	public int get_hash_index(String key)
//	{
//		Hash_Entry<V> n = get_entry(key);
//		return n.hash_index;
//	}
	public int get_cur_index(String key)
	{
		int index =  hash_ftn(key);
		int temp = index;
		if(l.get(index)==null)
			return -1;
		if(l.get(index).key.equals(key))
		{
			return index;
		}
		index = (index+1)%length;
		while(index!=temp&&l.get(index)!=null)
		{
			if(l.get(index).key.equals(key))
			{
				return index;
			}
			index = (index+1)%length;
		}
		return -1;

	}
	public Hash_Entry<V> get_entry(String key)
	{
		// write your code here
		int index =  hash_ftn(key);
		int temp = index;
		if(l.get(index)==null)
			return null;
		if(l.get(index).key.equals(key))
		{
			return l.get(index);
		}
		index = (index+1)%length;
		while(index!=temp&&l.get(index)!=null)
		{
			if(l.get(index).key.equals(key))
			{
				return l.get(index);
			}
			index = (index+1)%length;
		}
		return null;
	}
	public V get(String key)
	{
		// write your code here
		int index =  hash_ftn(key);
		int temp = index;
		if(l.get(index)==null)
			return null;
		if(l.get(index).key.equals(key))
		{
			return l.get(index).value;
		}
		index = (index+1)%length;
		while(index!=temp&&l.get(index)!=null)
		{
			if(l.get(index).key.equals(key))
			{
				return l.get(index).value;
			}
			index = (index+1)%length;
		}
		return null;
	}
	public int check_inv(int natural,int current)
	{
//		int natural = get_hash_index(key);
//		int current = get_cur_index(key);
		for(int i = natural;;i=(i+1)%length)
		{
			if(i==current)
			    break;
		    if(l.get(i)==null)
			{	return i;	}
		}
		return -1;
	}
	public boolean remove(String key)
	{
		// write your code here
		if(!contains(key))
		{
			return false;
		}
		int temp = get_cur_index(key);
		l.set(temp,null);
		for(int i = (temp+1)%length;;i=(i+1)%length)
		{
			if(l.get(i)==null)
				break;
			if(check_inv(l.get(i).hash_index,i)!=-1)
			{
				l.set(check_inv(l.get(i).hash_index,i),l.get(i));
				l.set(i,null);
			}
		}
		return true;
	}
	public boolean contains(String key)
	{
		// write your code here
		int index =  hash_ftn(key);
		int temp = index;
		if(l.get(index)==null)
			return false;
		if(l.get(index).key.equals(key))
		{
			return true;
		}
		index = (index+1)%length;
		while(index!=temp&&l.get(index)!=null)
		{
			if(l.get(index).key.equals(key))
			{
				return true;
			}
			index = (index+1)%length;
		}
		return false;
	}
	public Vector<String> getKeysInOrder()
	{
		// write your code here
		Vector<String> ans = new Vector<>();
		for(int i =  0;i<length;i++)
		{
			if(l.get(i)!=null)
			{
				ans.add(l.get(i).key);
			}
		}
		return ans;
	}
	// public static void main(String args[])
 //    {
 //        HashMap<Integer> H = new HashMap<>(1000000);
	// 	System.out.println(H.put("djxrt",87));
	// 	System.out.println(H.put("qocbq",1));
	// 	System.out.println(H.put("afgqj",764));
	// 	System.out.println(H.put("bpgln",83));
	// 	System.out.println(H.put("wvidq", 12));
	// 	Vector<String> ans = H.getKeysInOrder();
	// 	for(int i = 0;i<ans.size();i++)
	// 	{
	// 		System.out.println(ans.get(i));
	// 	}
	// 	System.out.println(H.get("wvidq"));
	// 	System.out.println(H.get("afgqj"));
	// 	System.out.println(H.get("djxrt"));
	// 	System.out.println(H.get("bpgln"));
	// 	System.out.println(H.remove("qocbq"));
	// 	ans = H.getKeysInOrder();
	// 	for(int i = 0;i<ans.size();i++)
	// 	{
	// 		System.out.println(ans.get(i));
	// 	}
	// 	System.out.println(H.contains("qocbq"));
	// 	System.out.println(H.contains("bpgln"));
	// 	System.out.println(H.get("wvidq"));
	// 	System.out.println(H.put("wvidq", 100));
	// 	System.out.println(H.get("wvidq"));
 //    }
}
