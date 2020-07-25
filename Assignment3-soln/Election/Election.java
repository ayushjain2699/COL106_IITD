package col106.assignment3.Election;

import col106.assignment3.BST.BST;
import col106.assignment3.BST.BSTNode;
import col106.assignment3.BST.Queue;
import col106.assignment3.Heap.Heap;
import col106.assignment3.Heap.HeapNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;

public class Election implements ElectionInterface {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		ElectionDriverCode EDC = new ElectionDriverCode();
		System.setOut(EDC.fileout());
	}
	/*
	 * end code
	 */
	//write your code here
	BST<String,Integer> Tree = new BST<>(); //ID as key,Votes as Values, for printing
	ArrayList<Candidate> Candidates = new ArrayList<>(); //lists of all the candidates
	HeapElection LPS = new HeapElection(); //key -> String[](party,state), value -> int
	HeapElection temp = new HeapElection();
	Heap<String,Integer> cand = new Heap<>();// key -> String(ID), value -> int
	Heap<String,Integer> GLP = new Heap<>(); // key -> String(party), value -> int
	public Candidate find_C(String candID)
	{
		int i;
		for(i = 0;i<Candidates.size();i++)
		{
			if(Candidates.get(i).candID.equals(candID))
			{
				break;
			}
		}
		return Candidates.get(i);
	}
	public boolean check_LPS(String[] key)
	{
		for(int i = 1;i<=LPS.length;i++)
		{
			if(Arrays.equals(LPS.L.get(i).getKey(),key))
			{
				return true;
			}
		}
		return false;
	}
	public boolean check_GLP(String key)
	{
		for(int i = 1;i<=GLP.length;i++)
		{
			if(GLP.L.get(i).getKey().equals(key))
			{
				return true;
			}
		}
		return false;
	}
	public int get_votes_LPS(String[] key)
	{
		int v = 0;
		for(int i = 1;i<=LPS.length;i++)
		{
			if(Arrays.equals(LPS.L.get(i).getKey(),key))
			{
				v = LPS.L.get(i).getValue();
			}
		}
		return v;
	}
	public int get_votes_GLP(String key)
	{
		int v = 0;
		for(int i = 1;i<=GLP.length;i++)
		{
			if(GLP.L.get(i).getKey().equals(key))
			{
				v = GLP.L.get(i).getValue();
			}
		}
		return v;
	}
	public void insert_LPS(String party, String state, String votes)
	{
		String[] key = {party,state};
		int value = Integer.valueOf(votes);
		if(check_LPS(key))
		{
			int v = get_votes_LPS(key);
			LPS.increaseKey(key,v+value);
		}
		else
		{
			LPS.insert(key,value);
		}
	}
	public void insert_GLP(String party,String votes)
	{
		String key = party;
		int value = Integer.valueOf(votes);
		if(check_GLP(key))
		{
			int v = get_votes_GLP(key);
			GLP.increaseKey(key,v+value);
		}
		else
		{
			GLP.insert(key,value);
		}
	}
	public ArrayList<Candidate> get_delete_cand(String constituency)
	{
		ArrayList<Candidate> out = new ArrayList<>();
		for(int i = 0;i<Candidates.size();i++)
		{
			if(Candidates.get(i).constituency.equals(constituency))
			{
				out.add(Candidates.get(i));
			}
		}
		return out;
	}
    public void insert(String name, String candID, String state, String district, String constituency, String party, String votes)
	{
		//write your code here
		Candidate C = new Candidate(name,candID,state,district,constituency,party,votes);
		insert_LPS(party,state,votes);
		insert_GLP(party,votes);
		cand.insert(candID,Integer.valueOf(votes));
		Candidates.add(C);
		int value = Integer.valueOf(C.votes);
		Tree.insert(candID, value);
	}
	public void updateVote(String name, String candID, String votes)
	{
		//write your code here
		int value = Integer.valueOf(votes);
		Candidate C = find_C(candID);
		int temp = Integer.valueOf(C.votes);
		String[] key_LPS = {C.party,C.state};
		String key_GLP = C.party;
		int v_LPS = get_votes_LPS(key_LPS);
		int v_GLP = get_votes_GLP(key_GLP);
		v_LPS = v_LPS - temp + Integer.valueOf(votes);
		v_GLP = v_GLP - temp + Integer.valueOf(votes);
		LPS.increaseKey(key_LPS,v_LPS);
		LPS.max_heapify(LPS.L,LPS.get_index(key_LPS),LPS.length);
		GLP.increaseKey(key_GLP,v_GLP);
		GLP.max_heapify(GLP.L,GLP.get_index(key_GLP),GLP.length);
		C.votes = votes;
		Tree.update(candID,value);
		cand.increaseKey(candID,value);
		cand.max_heapify(cand.L,cand.get_index(candID),cand.length);
	}
	public void topkInConstituency(String constituency, String k)
	{
		//write your code here
		Candidate ans;
		HeapNode<String,Integer> n;
		int count = Integer.valueOf(k);
		n = cand.extractMaxNode();
		Heap<String,Integer> temp = new Heap<>();
		temp.insert(n.getKey(),n.getValue());
		while(count>0&&cand.length>=0)
		{
			ans = find_C(n.getKey());
			if(ans.constituency.equals(constituency))
			{
				System.out.println(ans.name + ", " + ans.candID + ", " + ans.party);
				count--;
			}
			if(cand.length==0)
				break;
			n = cand.extractMaxNode();
			temp.insert(n.getKey(),n.getValue());
		}
		while(temp.length!=0)
		{
			n = temp.extractMaxNode();
			cand.insert(n.getKey(),n.getValue());
		}
	}
	public void leadingPartyInState(String state)
	{
		//write your code here
		HeapNode_Election n = LPS.extractMax();
		temp.insert(n.getKey(),n.getValue());
		while(!(state.equals(n.getKey()[1])))
		{
			n = LPS.extractMax();
			temp.insert(n.getKey(),n.getValue());
		}
		int temp_votes = n.getValue();
		ArrayList<String> ans = new ArrayList<>();
		while(temp_votes==n.getValue())
		{
			if(state.equals(n.getKey()[1]))
			{
				ans.add(n.getKey()[0]);
			}
			if(LPS.length==0)
				break;
			n = LPS.extractMax();
			temp.insert(n.getKey(),n.getValue());
		}
		sort_list(ans);
		for(int i = 0;i<ans.size();i++)
		{
			System.out.println(ans.get(i));
		}
		while(temp.length!=0)
		{
			n = temp.extractMax();
			LPS.insert(n.getKey(),n.getValue());
		}
	}
	public boolean check_delete_cand(ArrayList<Candidate> L, Candidate C)
	{
		for(int i = 0;i<L.size();i++)
		{
			if(L.get(i).candID.equals(C.candID))
			{
				return true;
			}
		}
		return false;
	}
	public void remove_from_Candidates(ArrayList<Candidate> L)
	{
		for(int i = 0;i<Candidates.size();i++)
		{
			if(check_delete_cand(L,Candidates.get(i)))
			{
				Candidates.remove(i);
				i--;
			}
		}
	}
	public void remove_from_cand(ArrayList<Candidate> L)
	{
		for(int i = 0;i<L.size();i++)
		{
			cand.delete(L.get(i).candID);
		}
	}
	public void remove_from_GLP(ArrayList<Candidate> L)
	{
		int v;
		for(int i = 0;i<L.size();i++)
		{
			v = get_votes_GLP(L.get(i).party);
			v = v - Integer.valueOf(L.get(i).votes);
			GLP.delete(L.get(i).party);
			if(v!=0)
			GLP.insert(L.get(i).party,v);
		}
	}
	public void remove_from_LPS(ArrayList<Candidate> L)
	{

		int v;
		for(int i = 0;i<L.size();i++)
		{
			String[] key = new String[2];
			key[0] = L.get(i).party;
			key[1] = L.get(i).state;
			v = get_votes_LPS(key);
			v = v - Integer.valueOf(L.get(i).votes);
			LPS.delete(key);
			if(v!=0)
			LPS.insert(key,v);
		}
	}
	public void cancelVoteConstituency(String constituency)
	{
		//write your code here
		ArrayList<Candidate> delete_cand = get_delete_cand(constituency);
		remove_from_BST(delete_cand);
		remove_from_cand(delete_cand);
		remove_from_GLP(delete_cand);
		remove_from_LPS(delete_cand);
		remove_from_Candidates(delete_cand);
	}
	public void sort_list(ArrayList<String> l)
	{
		String temp;
		//int a,b;
		for(int i = 0; i < l.size()-1; ++i)
		{
			for (int j = i + 1; j < l.size(); ++j)
			{
				if(l.get(i).compareTo(l.get(j))>0)
				{
					temp = l.get(i);
					l.set(i,l.get(j));
					l.set(j,temp);
				}
			}
		}
	}
	public void sort_list_int(ArrayList<String> l)
	{
		String temp;
		for(int i = 0; i < l.size()-1; ++i)
		{
			for (int j = i + 1; j < l.size(); ++j)
			{
				if (Integer.valueOf(l.get(i))>Integer.valueOf(l.get(j)))
				{
					temp = l.get(i);
					l.set(i,l.get(j));
					l.set(j,temp);
				}
			}
		}
	}
	public void remove_from_BST(ArrayList<Candidate> L)
	{
		ArrayList<String> t = new ArrayList<>();
		for(int i = 0;i<L.size();i++)
		{
			t.add((L.get(i).candID));
		}
		sort_list(t);
		for(int i = 0;i<t.size();i++)
		{
			Tree.delete(t.get(i));
		}
	}
	public void leadingPartyOverall()
	{
		//write your code here
		ArrayList<String> print_list = new ArrayList<>();
		Heap<String,Integer> temp_list = new Heap<>();
		HeapNode<String,Integer> n = GLP.extractMaxNode();
		int temp_votes = n.getValue();
		temp_list.insert(n.getKey(),n.getValue());
		while(temp_votes==n.getValue())
		{
			print_list.add(n.getKey());
			if(GLP.length==0)
				break;
			n = GLP.extractMaxNode();
			temp_list.insert(n.getKey(),n.getValue());
		}
		while(temp_list.length!=0)
		{
			n = temp_list.extractMaxNode();
			GLP.insert(n.getKey(),n.getValue());
		}
		sort_list(print_list);
		for(int i = 0;i<print_list.size();i++)
		{
			System.out.println(print_list.get(i));
		}
	}
	public void voteShareInState(String party,String state)
	{
		//write your code here
		Candidate n;
		int deno = 0;
		int num = 0;
		for(int i = 0;i<Candidates.size();i++)
		{
			n = Candidates.get(i);
			if(n.state.equals(state))
			{
				deno += Integer.valueOf(n.votes);
				if(n.party.equals(party))
				{
					num += Integer.valueOf(n.votes);
				}
			}
		}
		//DecimalFormat format1dec = new DecimalFormat("0.0");
		int ans = num*100/deno;
		//System.out.format("%.1f",ans);
		System.out.println(ans);
	}
	public void printElectionLevelOrder()
	{

		Queue<String,Integer> Q = new Queue<>();
		Q.enqueue(Tree.root);
		BSTNode<String,Integer> check;
		String out;
		Candidate C;
		while(!Q.isEmpty())
		{
			check = Q.front();
			out = check.getKey();
			C = find_C(out);
			System.out.println(C.name + ", " + C.candID + ", "+C.state+ ", "+C.district + ", "+C.constituency + ", "+C.party + ", "+C.votes);
			if(check.left!=null)
			{
				Q.enqueue(check.left);
			}
			if(check.right!=null)
			{
				Q.enqueue(check.right);
			}
			Q.dequeue();
		}
//		LPS.printHeap();
//		//System.out.println(LPS.length);
//		System.out.println();
//		GLP.printHeap();
//		//System.out.println(GLP.length);
//		System.out.println();
//		cand.printHeap();
//		//System.out.println(cand.length);
	}
}