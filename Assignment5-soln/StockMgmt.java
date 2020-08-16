package col106.assignment5;

import javax.xml.crypto.Data;
import java.io.DataInput;

public class StockMgmt implements StockMgmtInterface {
	//!!!!!!!*********DON'T CREATE YOUR OWN OBJECTS OF LLMergeSort*********!!!!!!!
	//use these only this object to sort
	LLMergeSort mergeSortobj;

	LinkedList<CategoryNode> categories;

	//DO NOT MODIFY THIS CONSTRUCTOR
	public StockMgmt() {

		mergeSortobj = new LLMergeSort();
		categories = new LinkedList<CategoryNode>();

		categories.add(new CategoryNode(1, "mobile"));
		categories.add(new CategoryNode(2, "utensils"));
		categories.add(new CategoryNode(3, "sanitary"));
		categories.add(new CategoryNode(4, "medicalEquipment"));
		categories.add(new CategoryNode(5, "clothes"));

	}

	public void addItem(int categoryId, int itemId, String itemName, int stock) {
		//Your code goes here
		ItemNode item = null;
		item = new ItemNode(itemId = itemId, itemName = itemName, stock = stock);
		Node<CategoryNode> temp = categories.getHead();
		while(temp.getData().categoryId!=categoryId)
		{
			temp = temp.next;
		}
		LinkedList<ItemNode> ll = temp.getData().getLinkedListOfCategory();
		ll.add(item);
	}

	public void addItemTransaction(int categoryId, int itemId, String itemName, int numItemPurchased, int day, int month, int year) {
		//Your code goes here
		Node<CategoryNode> temp = categories.getHead();
		while (temp.getData().categoryId != categoryId) {
			temp = temp.next;
		}
		LinkedList<ItemNode> ll = temp.getData().getLinkedListOfCategory();
		Node<ItemNode> temp2 = ll.getHead();
		while (temp2.getData().itemId != itemId) {
			temp2 = temp2.next;
		}
		PurchaseNode pnode = new PurchaseNode(numItemPurchased, day, month, year);
		temp2.getData().addTransaction(pnode);
	}
	//Query 1
	public LinkedList<ItemNode> sortByLastDate() {
		LinkedList<ItemNode> L = new LinkedList<>();
		Node<CategoryNode> pt1 = categories.getHead();
		while(pt1!=null)
		{
			Node<ItemNode> pt2 = pt1.getData().getLinkedListOfCategory().getHead();
			while(pt2!=null)
			{
				L.add(pt2.getData());
				pt2 = pt2.next;
			}
			pt1 = pt1.next;
		}
		return mergeSortobj.MergeSort_q1(L);
	}

	//Query 2
	public LinkedList<ItemNode> sortByPurchasePeriod(int day1, int month1, int year1, int day2, int month2, int year2) {
		LinkedList<ItemNode> L = new LinkedList<>();
		Node<CategoryNode> pt1 = categories.getHead();
		while(pt1!=null)
		{
			Node<ItemNode> pt2 = pt1.getData().getLinkedListOfCategory().getHead();
			while(pt2!=null)
			{
				L.add(pt2.getData());
				pt2 = pt2.next;
			}
			pt1 = pt1.next;
		}
		DateNode d1 = new DateNode(day1,month1,year1);
		DateNode d2 = new DateNode(day2,month2,year2);
		return mergeSortobj.MergeSort_q2(L,d1,d2);
	}

	//Query 3
	public LinkedList<ItemNode> sortByStockForCategory(int category) {
		//Your code goes here
		Node<CategoryNode> pt1 = categories.getHead();
		while(pt1.getData().categoryId!=category)
		{
			pt1 = pt1.next;
		}
		LinkedList<ItemNode> L = pt1.getData().getLinkedListOfCategory();
		return mergeSortobj.MergeSort_q3(L);
	}

	//Query 4
	public LinkedList<ItemNode> filterByCategorySortByDate(int category) {
		//Your code goes here
		Node<CategoryNode> pt1 = categories.getHead();
		while(pt1.getData().categoryId!=category)
		{
			pt1 = pt1.next;
		}
		LinkedList<ItemNode> L = pt1.getData().getLinkedListOfCategory();
		return mergeSortobj.MergeSort_q4(L);
	}

	//!!!!!*****DO NOT MODIFY THIS METHOD*****!!!!!//
	public LinkedList<ItemNode> checkMergeSort() {
		LinkedList<ItemNode> retLst = mergeSortobj.getGlobalList();
		mergeSortobj.clearGlobalList();
		return retLst;
	}
}
