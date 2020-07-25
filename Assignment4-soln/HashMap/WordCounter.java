package col106.assignment4.HashMap;

import java.util.Vector;

public class WordCounter {

	public WordCounter()
	{
		// write your code here
	}
	public Vector<String> make_array(String str, int l)
	{
		Vector<String> ans = new Vector<>();
		for(int i = 0;i<str.length()-l+1;i++)
		{
			ans.add(str.substring(i,i+l));
		}
		return ans;
	}
	public int count(String str, String word)
	{
		// write your code here
		int l_ss = word.length();
		Vector<String> words = make_array(str,l_ss);
		int l = words.size();
		HashMap<Integer> H = new HashMap<>(l);
		for(int i = 0;i<l;i++)
		{
			if(H.contains(words.get(i)))
			{
				Hash_Entry<Integer> temp = H.get_entry(words.get(i));
				H.put(words.get(i),temp.value+1);
			}
			else
			{
				H.put(words.get(i),1);
			}
		}
		if(H.get(word)!=null)
			return H.get(word);
		else
			return 0;
	}
//	public static void main(String args[])
//	{
//		WordCounter W = new WordCounter();
//		String str = "eeetr etrrettetr";
//		String word = "etr";
//		System.out.print(W.count(str,word));
//	}
}
