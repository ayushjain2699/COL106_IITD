package col106.assignment5;

import javax.xml.crypto.Data;
import java.util.Comparator;
import java.util.Date;

/*
Implementation of MergeSort Algorithm :
    1. you get linked list of size <=1 you just return the list as it is already sorted
    2. Find mid node using findSplit method(Don't forget to add mid element to globalList before returning it)
    3. Create two LinkedList lt (with head = head and tail = mid) and rt (with head = mid.next and tail = tail)
    4. Now recursively MergeSort lt and rt Linked lists(Maintain this order)
    5. Now merge these two lists that we got from recursive calls using given criteria for ordering
    6. Return merged Linked list
*/

public class LLMergeSort<T extends myComparable> {

  LinkedList<T>  globalList = new LinkedList<T>();
  //CALL THIS METHOD AFTER EVERY CALL OF findSplit and DO NOT MODIFY THIS METHOD
  public void adjustGlobalPointer(T node){
      globalList.add(node);
  }

  // Utility function to get the middle of the linked list
  public Node<T> findSplit(LinkedList<T>  lst) {
    //find middle node of LL :
    Node<T> middle = lst.getHead();
    int mid;

    if(lst.getSize()%2==0)  mid = lst.getSize()/2;
    else  mid = (lst.getSize()/2)+1;

    for(int i = 1;i<mid;i++)
    {
      middle = middle.next;
    }
    //Enter your code here

    //!!!!!*****DO NOT REMOVE THIS METHOD CALL (change the argument appropriately)*****!!!!!
    adjustGlobalPointer(middle.getData()); //Add object of ItemNode after finding mid in each call
    return middle;
  }
  public LinkedList<T> merge_q1(LinkedList<T> lt,LinkedList<T> rt)
  {
    LinkedList<T> merged = new LinkedList<>();
    Node<T> pt_lt = lt.getHead();
    Node<T> pt_rt = rt.getHead();
    while(pt_lt!=null&&pt_rt!=null)
    {
        if(pt_lt.getData().compareTo_q1(pt_rt.getData())<0)
        {
          merged.add(pt_lt.getData());
          pt_lt = pt_lt.next;
        }
        else
        {
          merged.add(pt_rt.getData());
          pt_rt = pt_rt.next;
        }
    }
    if(pt_lt == null)
    {
        while(pt_rt!=null)
        {
          merged.add(pt_rt.getData());
          pt_rt = pt_rt.next;
        }
    }
    else
    {
      while(pt_lt!=null)
      {
        merged.add(pt_lt.getData());
        pt_lt = pt_lt.next;
      }
    }
    return merged;
  }
  public LinkedList<T> MergeSort_q1(LinkedList<T>  lst) {
    if(lst.getSize()<=1)  return lst;

    Node<T> middle = findSplit(lst);
    int size_lt;
    if(lst.getSize()%2==0)  size_lt = lst.getSize()/2;
    else  size_lt = (lst.getSize()/2)+1;
    int size_rt = lst.getSize()-size_lt;

    LinkedList<T> lt = new LinkedList<>(lst.getHead(),middle,size_lt);
    LinkedList<T> rt = new LinkedList<>(middle.next,lst.getTail(),size_rt);
    rt.getTail().next = null;
    lt.getTail().next = null;

    lt = MergeSort_q1(lt);
    rt = MergeSort_q1(rt);

    return merge_q1(lt,rt);



    //Recursively Apply MergeSort, by calling function findSplit(..) to find middle node to split
    //Enter your code here

  }
  public LinkedList<T> merge_q2(LinkedList<T> lt,LinkedList<T> rt,DateNode d1,DateNode d2)
  {
      LinkedList<T> merged = new LinkedList<>();
      Node<T> pt_lt = lt.getHead();
      Node<T> pt_rt = rt.getHead();
      while(pt_lt!=null&&pt_rt!=null)
      {
          if(pt_lt.getData().compareTo_q2(pt_rt.getData(),d1,d2)<0)
          {
              merged.add(pt_lt.getData());
              pt_lt = pt_lt.next;
          }
          else
          {
              merged.add(pt_rt.getData());
              pt_rt = pt_rt.next;
          }
      }
      if(pt_lt == null)
      {
          while(pt_rt!=null)
          {
              merged.add(pt_rt.getData());
              pt_rt = pt_rt.next;
          }
      }
      else
      {
          while(pt_lt!=null)
          {
              merged.add(pt_lt.getData());
              pt_lt = pt_lt.next;
          }
      }
      return merged;
  }
  public LinkedList<T> MergeSort_q2(LinkedList<T> lst, DateNode d1, DateNode d2)
  {
      if(lst.getSize()<=1)  return lst;

      Node<T> middle = findSplit(lst);
      int size_lt;
      if(lst.getSize()%2==0)  size_lt = lst.getSize()/2;
      else  size_lt = (lst.getSize()/2)+1;
      int size_rt = lst.getSize()-size_lt;

      LinkedList<T> lt = new LinkedList<>(lst.getHead(),middle,size_lt);
      LinkedList<T> rt = new LinkedList<>(middle.next,lst.getTail(),size_rt);
      rt.getTail().next = null;
      lt.getTail().next = null;

      lt = MergeSort_q2(lt,d1,d2);
      rt = MergeSort_q2(rt,d1,d2);

      return merge_q2(lt,rt,d1,d2);
  }
  public LinkedList<T> merge_q3(LinkedList<T> lt,LinkedList<T> rt)
  {
      LinkedList<T> merged = new LinkedList<>();
      Node<T> pt_lt = lt.getHead();
      Node<T> pt_rt = rt.getHead();
      while(pt_lt!=null&&pt_rt!=null)
      {
          if(pt_lt.getData().compareTo_q3(pt_rt.getData())<0)
          {
              merged.add(pt_lt.getData());
              pt_lt = pt_lt.next;
          }
          else
          {
              merged.add(pt_rt.getData());
              pt_rt = pt_rt.next;
          }
      }
      if(pt_lt == null)
      {
          while(pt_rt!=null)
          {
              merged.add(pt_rt.getData());
              pt_rt = pt_rt.next;
          }
      }
      else
      {
          while(pt_lt!=null)
          {
              merged.add(pt_lt.getData());
              pt_lt = pt_lt.next;
          }
      }
      return merged;
  }
  public LinkedList<T> MergeSort_q3(LinkedList<T>  lst)
   {
       if(lst.getSize()<=1)  return lst;

       Node<T> middle = findSplit(lst);
       int size_lt;
       if(lst.getSize()%2==0)  size_lt = lst.getSize()/2;
       else  size_lt = (lst.getSize()/2)+1;
       int size_rt = lst.getSize()-size_lt;

       LinkedList<T> lt = new LinkedList<>(lst.getHead(),middle,size_lt);
       LinkedList<T> rt = new LinkedList<>(middle.next,lst.getTail(),size_rt);
       rt.getTail().next = null;
       lt.getTail().next = null;

       lt = MergeSort_q3(lt);
       rt = MergeSort_q3(rt);

       return merge_q3(lt,rt);
   }
  public LinkedList<T> merge_q4(LinkedList<T> lt,LinkedList<T> rt)
  {
        LinkedList<T> merged = new LinkedList<>();
        Node<T> pt_lt = lt.getHead();
        Node<T> pt_rt = rt.getHead();
        while(pt_lt!=null&&pt_rt!=null)
        {
            if(pt_lt.getData().compareTo_q4(pt_rt.getData())<0)
            {
                merged.add(pt_lt.getData());
                pt_lt = pt_lt.next;
            }
            else
            {
                merged.add(pt_rt.getData());
                pt_rt = pt_rt.next;
            }
        }
        if(pt_lt == null)
        {
            while(pt_rt!=null)
            {
                merged.add(pt_rt.getData());
                pt_rt = pt_rt.next;
            }
        }
        else
        {
            while(pt_lt!=null)
            {
                merged.add(pt_lt.getData());
                pt_lt = pt_lt.next;
            }
        }
        return merged;
    }
  public LinkedList<T> MergeSort_q4(LinkedList<T>  lst) {
        if(lst.getSize()<=1)  return lst;

        Node<T> middle = findSplit(lst);
        int size_lt;
        if(lst.getSize()%2==0)  size_lt = lst.getSize()/2;
        else  size_lt = (lst.getSize()/2)+1;
        int size_rt = lst.getSize()-size_lt;

        LinkedList<T> lt = new LinkedList<>(lst.getHead(),middle,size_lt);
        LinkedList<T> rt = new LinkedList<>(middle.next,lst.getTail(),size_rt);
        rt.getTail().next = null;
        lt.getTail().next = null;

        lt = MergeSort_q4(lt);
        rt = MergeSort_q4(rt);

        return merge_q4(lt,rt);



        //Recursively Apply MergeSort, by calling function findSplit(..) to find middle node to split
        //Enter your code here

    }
  //DO NOT CALL OR MODIFY THESE METHODS IN YOUR CALL THIS IS FOR USE IN DRIVER CODE
  public LinkedList<T> getGlobalList() {
    return this.globalList;
  }

  public void clearGlobalList(){
    globalList  = new LinkedList<>();
  }

}
