package col106.assignment3.BST;

public class BST<T extends Comparable, E extends Comparable> implements BSTInterface<T, E>  {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		BSTDriverCode BDC = new BSTDriverCode();
		System.setOut(BDC.fileout());
	}
	/*
	 * end code
	 * start writing your code from here
	 */
	
	//write your code here 
	public BSTNode<T,E> root = null;
	int size = 0;
	//write your code here
	public void insert_rec(BSTNode<T,E> _root,T key,E value,BSTNode<T,E> _r,int flag)
	{
		if(_root == null)
		{
			if(flag==0) {
				_r.left = new BSTNode<>(key, value);
				_r.left.parent = _r;
			}
			else {
				_r.right = new BSTNode<>(key, value);
				_r.right.parent = _r;
			}
		}
		else if(value.compareTo(_root.getValue())<0)
		{
			insert_rec(_root.left,key,value,_root,0);
		}
		else
		{
			insert_rec(_root.right,key,value,_root,1);
		}
	}
    public void insert(T key, E value) {
		//write your code here
		if(size==0)
		{
			root = new BSTNode<T,E>(key,value);
		}
		else
		{
			if(value.compareTo(root.getValue())<0)
				insert_rec(root, key, value,null,0);
			else
				insert_rec(root, key, value,null,1);
		}
		size++;
	}

    public void update(T key, E value) {
		//write your code here
		delete(key);
		insert(key,value);
    }
	public int relation(BSTNode<T,E> p, BSTNode<T,E> c)
	{
		if(p.left==c){return -1;}
		else return 1;
	}
    public void delete(T key) {
		//write your code here
		BSTNode<T,E> node = getNode(key);
		if(node == root)
		{
			if(node.right==null)
			{
				if(node.left!=null)
					node.left.parent = null;
				root = node.left;
			}
			else if(node.left==null)
			{
				if(node.right!=null)
					node.right.parent = null;
				root = node.right;
			}
			else
			{
				int flag = 0;
				BSTNode<T,E> check = node.right;
				while(check.left!=null)
				{
					check = check.left;
					flag = 1;
				}
				if(flag==1) {
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
					check.left = node.left;
					if(node.left!=null)
						node.left.parent = check;
					root = check;
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
		}
		else if(node.left==null)
		{
			if(relation(node.parent,node)==-1)
				node.parent.left = node.right;
			else
				node.parent.right = node.right;
			if(node.right!=null)
				node.right.parent = node.parent;
		}
		else
		{
			BSTNode<T,E> check = node.right;
			int flag = 0;
			while(check.left!=null)
			{
				check = check.left;
				flag = 1;
			}
			if(flag==1) {
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
		}
    }
	public BSTNode<T,E> getNode(T key)
	{
		Queue<T,E> Q = new Queue<>();
		Q.enqueue(root);
		BSTNode<T,E> check = Q.front();
		while(!Q.isEmpty())
		{
			check = Q.front();
			if(check.getKey().equals(key)){break;}
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
		return check;
	}
    public void printBST () {
		//write your code here
		Queue<T,E> Q = new Queue<>();
		Q.enqueue(root);
		BSTNode<T,E> check;
		while(!Q.isEmpty())
		{
			check = Q.front();
			System.out.println(check.getKey() + ", " + check.getValue());
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
    }
}