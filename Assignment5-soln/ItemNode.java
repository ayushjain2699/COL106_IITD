package col106.assignment5;

import java.util.Comparator;
import java.util.Date;

import static java.lang.StrictMath.abs;

public class ItemNode extends myComparable<ItemNode> implements ItemInterface {

	int itemId;
	String itemName;
	int stock;
	public DateNode last = null;
	LinkedList<PurchaseNode> transaction_list = new LinkedList<>();
//	private PurchaseNode temp = new PurchaseNode(0,1,8,1970);
	public ItemNode(int itemId, String itemName, int stock){
		this.itemId = itemId;
		this.itemName = itemName;
		this.stock = stock;
//		transaction_list.add(temp);
	}
	public int getItemId(){
		//Enter your code here
		return this.itemId;
	}

	public  String getItemName(){
		//Enter your code here
		return this.itemName;
	}

	public int getStockLeft(){
		//Enter your code here
		return this.stock;
	}
	public int get_first(DateNode d1, DateNode d2)
	{
		Node<PurchaseNode> temp = this.transaction_list.getHead();
		int min = 100000;
		while(temp!=null)
		{
			if(temp.getData().dateobj.compareTo(d1)>=0&&temp.getData().dateobj.compareTo(d2)<=0)
			{
				if(temp.getData().getDate().getYear()<min)
				{
					min = temp.getData().dateobj.getYear();
				}
			}
			temp = temp.next;
		}
		return min;
	}
	public int get_last(DateNode d1, DateNode d2)
	{
		Node<PurchaseNode> temp = this.transaction_list.getHead();
		int max = 0;
		while(temp!=null)
		{
			if(temp.getData().dateobj.compareTo(d1)>=0&&temp.getData().dateobj.compareTo(d2)<=0)
			{
				if(temp.getData().getDate().getYear()>max)
				{
					max = temp.getData().dateobj.getYear();
				}
			}
			temp = temp.next;
		}
		return max;
	}
	public void addTransaction(PurchaseNode purchaseT){
		transaction_list.add(purchaseT);
		if(last==null)	last = purchaseT.dateobj;
		else
		{
			if(last.compareTo(purchaseT.dateobj)<0)
			{
				last = purchaseT.dateobj;
			}
		}
		stock = stock-purchaseT.numItemPurchased;
		//Enter your code here
	}
	public int get_no_of_items(DateNode d1, DateNode d2)
	{
		//LinkedList<PurchaseNode> list = this.transaction_list;
		int ans = 0;
		Node<PurchaseNode> temp = this.transaction_list.getHead();
		while(temp!=null)
		{
			if(temp.getData().dateobj.compareTo(d1)>=0&&temp.getData().dateobj.compareTo(d2)<=0)
			{
				ans += temp.getData().numItemPurchased;
			}
			temp = temp.next;
		}
		return ans;
	}
	public Node<PurchaseNode> getPurchaseHead(){
		//Enter your code here
		return transaction_list.getHead();
	}

	@Override
	public int compareTo_q2(ItemNode n, DateNode d1, DateNode d2)
	{
		int deno1 = abs(this.get_first(d1,d2)-this.get_last(d1,d2))+1;
		int deno2 = abs(n.get_first(d1,d2)-n.get_last(d1,d2))+1;
		int num1 = get_no_of_items(d1,d2);
		int num2 = n.get_no_of_items(d1,d2);
		float final1 = (float)num1/deno1;
		float final2 = (float)num2/deno2;
		if(final1>final2)	return 1;
		else if(final1<final2)	return -1;
		else {
			if (this.getItemName().compareTo(n.getItemName()) < 0) {
				return 1;
			} else return -1;
		}
	}
	@Override
	public int compareTo_q3(ItemNode n)
	{
		if(this.getStockLeft()<n.getStockLeft())	return 1;
		else if(this.getStockLeft()>n.getStockLeft())	return -1;
		else {
			if (this.getItemName().compareTo(n.getItemName()) < 0) {
				return 1;
			} else return -1;
		}
	}
	@Override
	public int compareTo_q1(ItemNode n) {
		DateNode temp = new DateNode(1,8,1970);
		DateNode d1 = this.last;
		DateNode d2 = n.last;
		if(this.last == null)	d1 = temp;
		if(n.last == null)	d2 = temp;
		if(d1.compareTo(d2)>0)	return 1;
		else if(d1.compareTo(d2)<0)	return -1;
		else {
			if (this.getItemName().compareTo(n.getItemName()) < 0) {
				return 1;
			} else return -1;
		}
	}
	@Override
	public int compareTo_q4(ItemNode n) {
		DateNode temp = new DateNode(1,8,1970);
		DateNode d1 = this.last;
		DateNode d2 = n.last;
		if(this.last == null)	d1 = temp;
		if(n.last == null)	d2 = temp;
		if(d1.compareTo(d2)>0)	return -1;
		else if(d1.compareTo(d2)<0)	return 1;
		else {
			if (this.getItemName().compareTo(n.getItemName()) < 0) {
				return 1;
			} else return -1;
		}
	}
}
