package col106.assignment4.WeakAVLMap;
import java.util.Vector;

public class WeakAVLMap<K extends Comparable,V> implements WeakAVLMapInterface<K,V> {

	public Node<K,V> root;
	int size = 0;
	int rot_count = 0;
	public void insert_rec(Node<K,V> _root,K key,V value,Node<K,V> _r,int flag)
	{
		if(_root == null)
		{
			if(flag==0) {
				_r.left = new Node<>(key, value);
				_r.left.parent = _r;
			}
			else {
				_r.right = new Node<>(key, value);
				_r.right.parent = _r;
			}
		}
		else if(key.compareTo(_root.key)<0)
		{
			insert_rec(_root.left,key,value,_root,0);
		}
		else
		{
			insert_rec(_root.right,key,value,_root,1);
		}
	}
	public void insert(K key, V value)
	{
		//write your code here
		if(size==0)
		{
			root = new Node<K,V>(key,value);
		}
		else
		{
			if(key.compareTo(root.key)<0)
				insert_rec(root, key, value,null,0);
			else
				insert_rec(root, key, value,null,1);
		}
		size++;
	}
	public Node<K,V> getNode(Node<K,V> r,K key)
	{
		if(r==null)
			return null;
		if(r.key.compareTo(key)==0)
			return r;
		if(r.key.compareTo(key)>0)
		{
			return getNode(r.left,key);
		}
		else
			return getNode(r.right,key);
//		Queue<K,V> Q = new Queue<>();
//		Q.enqueue(root);
//		Node<K,V> check = Q.front();
//		int flag= 0;
//		while(!Q.isEmpty())
//		{
//			check = Q.front();
//			if(check.key.equals(key)){flag =  1;break;}
//			if(check.left!=null)
//			{
//				Q.enqueue(check.left);
//			}
//			if(check.right!=null)
//			{
//				Q.enqueue(check.right);
//			}
//			Q.dequeue();
//		}
//		if(flag==1)
//			return check;
//		else
//			return null;
	}
	public WeakAVLMap()
	{
		// write your code here
		root = null;
	}
	public int get_rank(Node<K,V> n)
	{
		if(n==null)
		{
			return 0;
		}
		else
		{
			return n.rank;
		}
	}
	public int get_rank_diff(Node<K,V> n,Node<K,V> p)
	{
		return get_rank(p)-get_rank(n);
	}
	public Node<K,V> get_sibling(Node<K,V> n,Node<K,V> p)
	{
		if(p.left==n)
		{
			return p.right;
		}
		else
		{
			return p.left;
		}
	}
	public Node<K,V> get_t(Node<K,V> n)
	{
		Node<K,V> l = n.left;
		Node<K,V> r = n.right;
		int lr = get_rank_diff(l,n);
		int rr = get_rank_diff(r,n);
		if(rr==1)
			return r;
		else
			return l;
	}
	public void left_rot(Node<K,V> p,Node<K,V> q)
	{
		p.left = q.right;
		if(q.right!=null)
		{ q.right.parent =  p; }
		if(p.parent==null)
		{
			q.parent = null;
			root = q;
		}
		else {
			if (p.parent.left == p) {
				p.parent.left = q;
				q.parent = p.parent;
			} else {
				p.parent.right = q;
				q.parent = p.parent;
			}
		}
		q.right = p;
		p.parent = q;
		rot_count++;
	}
	public void right_rot(Node<K,V> p,Node<K,V> q)
	{
		p.right = q.left;
		if(q.left!=null)
		{ q.left.parent =  p; }
		if(p.parent==null)
		{
			q.parent = null;
			root = q;
		}
		else {
			if (p.parent.left == p) {
				p.parent.left = q;
				q.parent = p.parent;
			} else {
				p.parent.right = q;
				q.parent = p.parent;
			}
		}
		q.left = p;
		p.parent = q;
		rot_count++;
	}
	public void left_right_rot(Node<K,V> p,Node<K,V> q,Node<K,V> t)
	{

		if(p.parent==null)
		{
			t.parent = null;
			root = t;
		}
		else {
			if (p.parent.left == p) {
				p.parent.left = t;
				t.parent = p.parent;
			} else {
				p.parent.right = t;
				t.parent = p.parent;
			}
		}
		q.right = t.left;
		if(t.left!=null)
			t.left.parent =  q;
		p.left = t.right;
		if(t.right!=null)
			t.right.parent = p;
		t.left = q;
		q.parent = t;
		t.right = p;
		p.parent = t;
		rot_count = rot_count+2;
	}
	public void right_left_rot(Node<K,V> p,Node<K,V> q,Node<K,V> t)
	{
		if(p.parent==null)
		{
			t.parent = null;
			root = t;
		}
		else {
			if (p.parent.left == p) {
				p.parent.left = t;
				t.parent = p.parent;
			} else {
				p.parent.right = t;
				t.parent = p.parent;
			}
		}
		q.left = t.right;
		if(t.right!=null)
			t.right.parent =  q;
		p.right = t.left;
		if(t.left!=null)
			t.left.parent = p;
		t.right = q;
		q.parent = t;
		t.left = p;
		p.parent = t;
		rot_count = rot_count+2;
	}
	public V put(K key, V value)
	{
		V ans;
		Node<K,V> temp = getNode(root,key);
		if(temp!=null) {
			ans = temp.value;
			temp.value = value;
		}
		else {
			ans = null;
			insert(key, value);
			Node<K, V> q = getNode(root, key);
			q.rank++;
			while (true) {
				Node<K, V> p = q.parent;
				if (p == null) {
					break;
				}
				if (get_rank_diff(q, p) == 1) {
					break;
				}
				if (get_rank_diff(q, p) == 0)
				{
					Node<K, V> s = get_sibling(q, p);
					if (get_rank_diff(s, p) == 1)
					{
						p.rank++;
						q = p;
						continue;
					}
					else
						{
						Node<K, V> t = get_t(q);
						if (p.left == q && q.left == t) {
							p.rank--;
							left_rot(p, q);
							break;
						}
						if (p.right == q && q.right == t) {
							p.rank--;
							right_rot(p, q);
							break;
						}
						if (p.left == q && q.right == t) {
							p.rank--;
							q.rank--;
							t.rank++;
							left_right_rot(p, q, t);
							break;
						}
						if (p.right == q && q.left == t) {
							p.rank--;
							q.rank--;
							t.rank++;
							right_left_rot(p, q, t);
							break;
						}
					}
				}
			}
		}
		return ans;
	}
	public int relation(Node<K,V> p, Node<K,V> c)
	{
		if(p.left==c){return -1;}
		else return 1;
	}
	public void delete(K key)
	{
		//write your code here
		Node<K,V> node = getNode(root,key);
		if(node == root)
		{
			if(node.left==null && node.right==null)
			{
				root = null;
			}
			else if(node.right==null)
			{
				node.left.parent = null;
				//node.left.rank = node.rank;
				root = node.left;
			}
			else if(node.left==null)
			{
				node.right.parent = null;
				//node.right.rank = node.rank;
				root = node.right;
			}
			else
			{
				int flag = 0;
				Node<K,V> check = node.right;
				while(check.left!=null)
				{
					check = check.left;
					flag = 1;
				}
				check.rank = node.rank;
				Node<K,V> q = check.right;
				Node<K,V> p;
				if(flag==1)
				{
					p = check.parent;
					if (relation(check.parent, check) == -1)
						check.parent.left = check.right;
					else
						check.parent.right = check.right;

					if (check.right != null)
						check.right.parent = check.parent;
					check.left = node.left;
					root = check;
					check.parent = node.parent;
					check.right = node.right;
					if(node.left!=null)
						node.left.parent = check;
					if(node.right!=null)
						node.right.parent = check;
				}
				else
				{
					p = check;
					check.left = node.left;
					check.parent = node.parent;
					if(node.left!=null)
						node.left.parent = check;
					root = check;
				}
				Node<K,V> s1 = get_sibling(q,p);
				int point = 0;
				if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s1,p)==2))
				{
					p.rank--;
					q = p;
					p = q.parent;
					if(p==null)
						point = 1;
					else
						s1 = get_sibling(q,p);
				}
				if(get_rank_diff(q,p)!=2&&point == 0)
				{
					while(true)
					{
						if(q==root || get_rank_diff(q,p)<=2)
						{
							break;
						}
						Node<K,V> s = get_sibling(q,p);
						if(get_rank_diff(s,p)==2)
						{
							p.rank--;
							q = p;
							p = q.parent;
							//s = get_sibling(q,p);
							continue;
						}
						else
						{
							if(get_rank_diff(s.left,s) ==2 && get_rank_diff(s.right,s)==2)
							{
								p.rank--;
								s.rank--;
								q = p;
								p = q.parent;
								//s = get_sibling(q,p);
								continue;
							}
							else
							{
								Node<K,V> t;
								if(get_rank_diff(s.left,s) == get_rank_diff(s.right,s))
								{
									if(p.left==s)
										t = s.left;
									else
										t = s.right;
								}
								else
								{
									t = get_t(s);
								}
								if(p.left==s && s.left==t)
								{
									p.rank--;
									s.rank++;
									left_rot(p,s);
									Node<K,V> s2 = get_sibling(q,p);
									if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2))
									{
										p.rank--;
									}
									break;
								}
								if(p.right==s && s.right==t)
								{
									p.rank--;
									s.rank++;
									right_rot(p,s);
									Node<K,V> s2 = get_sibling(q,p);
									if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2))
									{
										p.rank--;
									}
									break;
								}
								if(p.left==s && s.right==t)
								{
									s.rank--;
									p.rank = p.rank-2;
									t.rank = t.rank+2;
									left_right_rot(p,s,t);
									break;
								}
								if(p.right==s && s.left==t)
								{
									s.rank--;
									p.rank = p.rank-2;
									t.rank = t.rank+2;
									right_left_rot(p,s,t);
									break;
								}
							}
						}
					}
				}
			}
		}
		else if(node.left==null && node.right==null)
		{
			Node<K,V> p = node.parent;
			Node<K,V> q = null;
			if(relation(node.parent,node)==-1)
				node.parent.left = null;
			else
				node.parent.right = null;
			int point = 0;
			Node<K,V> s1 = get_sibling(q,p);
			if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s1,p)==2))
			{
				p.rank--;
				q = p;
				p = q.parent;
				if(p==null)
					point = 1;
				else
					s1 = get_sibling(q,p);
			}
			if(get_rank_diff(q,p)!=2&&point == 0)
			{
				while(true)
				{
					if(q==root || get_rank_diff(q,p)<=2)
					{
						break;
					}
					Node<K,V> s = get_sibling(q,p);
					if(get_rank_diff(s,p)==2)
					{
						p.rank--;
						q = p;
						p = q.parent;
						//s = get_sibling(q,p);
						continue;
					}
					else
					{
						if(get_rank_diff(s.left,s) ==2 && get_rank_diff(s.right,s)==2)
						{
							p.rank--;
							s.rank--;
							q = p;
							p = q.parent;
							//s = get_sibling(q,p);
							continue;
						}
						else
						{
							Node<K,V> t;
							if(get_rank_diff(s.left,s) == get_rank_diff(s.right,s))
							{
								if(p.left==s)
									t = s.left;
								else
									t = s.right;
							}
							else
							{
								t = get_t(s);
							}
							if(p.left==s && s.left==t)
							{
								p.rank--;
								s.rank++;
								left_rot(p,s);
								Node<K,V> s2 = get_sibling(q,p);
								if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2)) {
									p.rank--;
								}
								break;
							}
							if(p.right==s && s.right==t)
							{
								p.rank--;
								s.rank++;
								right_rot(p,s);
								Node<K,V> s2 = get_sibling(q,p);
								if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2)) {
									p.rank--;
								}
								break;
							}
							if(p.left==s && s.right==t)
							{
								s.rank--;
								p.rank = p.rank-2;
								t.rank = t.rank+2;
								left_right_rot(p,s,t);
								break;
							}
							if(p.right==s && s.left==t)
							{
								s.rank--;
								p.rank = p.rank-2;
								t.rank = t.rank+2;
								right_left_rot(p,s,t);
								break;
							}
						}
					}
				}
			}
		}
		else if(node.right==null)
		{
			if(relation(node.parent,node)==-1)
				node.parent.left = node.left;
			else
				node.parent.right = node.left;
			if(node.left!=null)
				node.left.parent = node.parent;
//			if(node.left!=null)
//				node.left.rank = node.rank;
			Node<K,V> q = node.left;
			Node<K,V> p = node.parent;
			Node<K,V> s1 = get_sibling(q,p);
			int point = 0;
			if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s1,p)==2))
			{
				p.rank--;
				q = p;
				p = q.parent;
				if(p==null)
					point = 1;
				else
					s1 = get_sibling(q,p);
			}
			if(get_rank_diff(q,p)!=2&&point == 0)
			{
				while(true)
				{
					if(q==root || get_rank_diff(q,p)<=2)
					{
						break;
					}
					Node<K,V> s = get_sibling(q,p);
					if(get_rank_diff(s,p)==2)
					{
						p.rank--;
						q = p;
						p = q.parent;
						//s = get_sibling(q,p);
						continue;
					}
					else
					{
						if(get_rank_diff(s.left,s) ==2 && get_rank_diff(s.right,s)==2)
						{
							p.rank--;
							s.rank--;
							q = p;
							p = q.parent;
							//s = get_sibling(q,p);
							continue;
						}
						else
						{
							Node<K,V> t;
							if(get_rank_diff(s.left,s) == get_rank_diff(s.right,s))
							{
								if(p.left==s)
									t = s.left;
								else
									t = s.right;
							}
							else
							{
								t = get_t(s);
							}
							if(p.left==s && s.left==t)
							{
								p.rank--;
								s.rank++;
								left_rot(p,s);
								Node<K,V> s2 = get_sibling(q,p);
								if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2)) {
									p.rank--;
								}
								break;
							}
							if(p.right==s && s.right==t)
							{
								p.rank--;
								s.rank++;
								right_rot(p,s);
								Node<K,V> s2 = get_sibling(q,p);
								if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2)) {
									p.rank--;
								}
								break;
							}
							if(p.left==s && s.right==t)
							{
								s.rank--;
								p.rank = p.rank-2;
								t.rank = t.rank+2;
								left_right_rot(p,s,t);
								break;
							}
							if(p.right==s && s.left==t)
							{
								s.rank--;
								p.rank = p.rank-2;
								t.rank = t.rank+2;
								right_left_rot(p,s,t);
								break;
							}
						}
					}
				}
			}
		}
		else if(node.left==null)
		{
			if(relation(node.parent,node)==-1)
				node.parent.left = node.right;
			else
				node.parent.right = node.right;
			if(node.right!=null)
				node.right.parent = node.parent;
//			if(node.right!=null)
//				node.right.rank = node.rank;
			Node<K,V> q = node.right;
			Node<K,V> p = node.parent;
			Node<K,V> s1 = get_sibling(q,p);
			int point = 0;
			if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s1,p)==2))
			{
				p.rank--;
				q = p;
				p = q.parent;
				if(p==null)
					point = 1;
				else
					s1 = get_sibling(q,p);
			}
			if(get_rank_diff(q,p)!=2&&point == 0)
			{
				while(true)
				{
					if(q==root || get_rank_diff(q,p)<=2)
					{
						break;
					}
					Node<K,V> s = get_sibling(q,p);
					if(get_rank_diff(s,p)==2)
					{
						p.rank--;
						q = p;
						p = q.parent;
						//s = get_sibling(q,p);
						continue;
					}
					else
					{
						if(get_rank_diff(s.left,s) ==2 && get_rank_diff(s.right,s)==2)
						{
							p.rank--;
							s.rank--;
							q = p;
							p = q.parent;
							//s = get_sibling(q,p);
							continue;
						}
						else
						{
							Node<K,V> t;
							if(get_rank_diff(s.left,s) == get_rank_diff(s.right,s))
							{
								if(p.left==s)
									t = s.left;
								else
									t = s.right;
							}
							else
							{
								t = get_t(s);
							}
							if(p.left==s && s.left==t)
							{
								p.rank--;
								s.rank++;
								left_rot(p,s);
								Node<K,V> s2 = get_sibling(q,p);
								if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2)) {
									p.rank--;
								}
								break;
							}
							if(p.right==s && s.right==t)
							{
								p.rank--;
								s.rank++;
								right_rot(p,s);
								Node<K,V> s2 = get_sibling(q,p);
								if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2)) {
									p.rank--;
								}
								break;
							}
							if(p.left==s && s.right==t)
							{
								s.rank--;
								p.rank = p.rank-2;
								t.rank = t.rank+2;
								left_right_rot(p,s,t);
								break;
							}
							if(p.right==s && s.left==t)
							{
								s.rank--;
								p.rank = p.rank-2;
								t.rank = t.rank+2;
								right_left_rot(p,s,t);
								break;
							}
						}
					}
				}
			}
		}
		else
		{
			Node<K,V> check = node.right;
			int flag = 0;
			while(check.left!=null)
			{
				check = check.left;
				flag = 1;
			}
			check.rank = node.rank;
			Node<K,V> q = check.right;
			Node<K,V> p;
			if(flag==1)
			{
				p = check.parent;
				if (relation(check.parent, check) == -1)
					check.parent.left = check.right;
				else
					check.parent.right = check.right;

				if (check.right != null)
					check.right.parent = check.parent;
				check.right = node.right;
				if (node.right != null)
					node.right.parent = check;
				check.left = node.left;
				if (node.left != null)
					node.left.parent = check;

				check.parent = node.parent;

				if (relation(node.parent, node) == -1)
					node.parent.left = check;
				else
					node.parent.right = check;
			}
			else
			{
				p = check;
				check.left = node.left;
				if(node.left!=null)
				{
					node.left.parent = check;
				}
				check.parent = node.parent;
				if (relation(node.parent, node) == -1)
					node.parent.left = check;
				else
					node.parent.right = check;
			}
			int point = 0;
			Node<K,V> s1 = get_sibling(q,p);
			if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s1,p)==2))
			{
				p.rank--;
				q = p;
				p = q.parent;
				if(p==null)
					point = 1;
				else
					s1 = get_sibling(q,p);
			}
			if(get_rank_diff(q,p)!=2&&point == 0)
			{
				while(true)
				{
					if(q==root || get_rank_diff(q,p)<=2)
					{
						break;
					}
					Node<K,V> s = get_sibling(q,p);
					if(get_rank_diff(s,p)==2)
					{
						p.rank--;
						q = p;
						p = q.parent;
						//s = get_sibling(q,p);
						continue;
					}
					else
					{
						if(get_rank_diff(s.left,s) ==2 && get_rank_diff(s.right,s)==2)
						{
							p.rank--;
							s.rank--;
							q = p;
							p = q.parent;
							//s = get_sibling(q,p);
							continue;
						}
						else
						{
							Node<K,V> t;
							if(get_rank_diff(s.left,s) == get_rank_diff(s.right,s))
							{
								if(p.left==s)
									t = s.left;
								else
									t = s.right;
							}
							else
							{
								t = get_t(s);
							}
							if(p.left==s && s.left==t)
							{
								p.rank--;
								s.rank++;
								left_rot(p,s);
								Node<K,V> s2 = get_sibling(q,p);
								if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2)) {
									p.rank--;
								}
								break;
							}
							if(p.right==s && s.right==t)
							{
								p.rank--;
								s.rank++;
								right_rot(p,s);
								Node<K,V> s2 = get_sibling(q,p);
								if(isExternal(p)&&(get_rank_diff(q,p)==2 && get_rank_diff(s2,p)==2)) {
									p.rank--;
								}
								break;
							}
							if(p.left==s && s.right==t)
							{
								s.rank--;
								p.rank = p.rank-2;
								t.rank = t.rank+2;
								left_right_rot(p,s,t);
								break;
							}
							if(p.right==s && s.left==t)
							{
								s.rank--;
								p.rank = p.rank-2;
								t.rank = t.rank+2;
								right_left_rot(p,s,t);
								break;
							}
						}
					}
				}
			}
		}
	}
	public boolean isExternal(Node<K,V> n)
	{
		if(n.left==null && n.right==null)
			return true;
		else
			return false;
	}
	public V remove(K key)
	{
		// write your code her
		Node<K,V> temp = getNode(root,key);
		V ans;
		if(temp==null)
			return null;
		else
			ans = temp.value;
		delete(key);
		size--;
		return ans;
	}
	public V get(K key)
	{
		// write your code her
		Node<K,V> n = getNode(root,key);
		if(n==null)
			return null;
		else
			return n.value;
	}
	public void in_order(Node<K,V> n, K key1, K key2, Vector<V> search)
	{
		if(n==null)
			return;
		in_order(n.left,key1,key2,search);
		if(n.key.compareTo(key1)>=0 && n.key.compareTo(key2)<=0)
		{
			search.add(n.value);
		}
		in_order(n.right,key1,key2,search);
	}
	public Vector<V> searchRange(K key1, K key2)
	{
		Vector<V> search = new Vector<>();
		in_order(root,key1,key2,search);
		return search;
	}
	public int rotateCount()
	{ 
		return rot_count;
	}
	public int maxDepth(Node<K,V> node)
	{
		if (node==null)
			return 0;
		else
		{
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);
			if (lDepth > rDepth)
				return(lDepth+1);
			else
				return(rDepth+1);
		}
	}
	public int getHeight()
	{
		return maxDepth(root);
	}
	public Vector<K> BFS()
	{
		Vector<K> ans = new Vector<>();
		Queue<K,V> Q = new Queue<>();
		Q.enqueue(root);
		Node<K,V> check;
		while(!Q.isEmpty())
		{
			check = Q.front();
			if(check!=null) {
				ans.add(check.key);
				if (check.left != null) {
					Q.enqueue(check.left);
				}
				if (check.right != null) {
					Q.enqueue(check.right);
				}
				Q.dequeue();
			}
			else
				Q.dequeue();
		}
		return ans;
	}
//	public static void main(String[] args)
//	{
//		WeakAVLMap<Integer,Integer> A = new WeakAVLMap<>();
//		A.put(10,1);
//		A.put(12,2);
//		A.put(15,3);
//		A.put(11,1);
//		A.put(13,2);
//		A.put(9,3);
//		A.put(0,1);
//		A.put(2,2);
//		A.put(5,3);
//		A.remove(5);
//		A.remove(0);
//		A.remove(2);
//		A.put(17,1);
//		A.put(16,2);
//		A.put(18,3);
//		A.remove(10);
//		A.remove(12);
//		A.remove(11);
//		A.remove(13);
//		A.remove(9);
//		A.remove(18);
//		A.remove(15);
//		A.remove(17);
//		A.remove(16);
////		A.put(10,1);
////		A.put(9,2);
////		A.put(17,1);
////		A.put(16,2);
////		A.remove(17);
////		A.remove(16);
//		System.out.println(A.getHeight()+ " "+ A.rotateCount());
//		Vector<Integer> k = A.BFS();
//		Vector<Integer> k1 = A.searchRange(12,20);
//		System.out.println(A.get(9));
//		for(int i = 0;i<k.size();i++)
//		{
//			System.out.println(k.get(i));
//		}
//		System.out.println();
//		for(int i = 0;i<k1.size();i++)
//		{
//			System.out.println(k1.get(i));
//		}
//	}
}
