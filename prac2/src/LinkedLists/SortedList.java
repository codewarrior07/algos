package LinkedLists;

import java.util.Iterator;

/*public class SortedListProg {
	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i=0;i<10;++i){
			arr[i] = (int)(Math.random()*100);
			System.out.println(arr[i]);
		}
		SortedList sl = new SortedList();
		for(int i=0;i<10;++i) {
			sl.insert(arr[i]);
		}
		for(int i=0;i<10;++i) {
			arr[i] = sl.removeFirst().data;
		}
		System.out.println("---------");
		for(int i=0;i<10;++i)
			System.out.println(arr[i]);
	}
}*/

public class SortedList<T extends Comparable<T>> {
	DNode<T> first,last;
	public SortedList() {
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void insert(T dat) {
		DNode<T> nn = new DNode<T>(dat);
		DNode<T> curr = first;
		if(isEmpty()){
			first = nn;
			last = nn;
			return;
		}
		if(dat.compareTo(last.data) > 0) {
			nn.prev = last;
			last.next = nn;
			last = nn;
			return;
		}
		while(curr != null && curr.data.compareTo(dat) < 0) {
			curr = curr.next;
		}
		if(curr == first) 
			first = nn;
		else
			curr.prev.next = nn;
		nn.prev = curr.prev;
		nn.next = curr;
		curr.prev = nn;
	}
	
	public DNode<T> removeFirst() {
		if(isEmpty())
			return null;
		DNode<T> temp = first;
		if(first == last)
			last = null;
		first = first.next;
		return temp;
	}
	
	public void display() {
		 for(DNode<T> curr = first;curr != null;curr=curr.next)
			 System.out.println(curr.data);
		 System.out.println("---------");
	}
	
	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i=0;i<10;++i){
			arr[i] = (int)(Math.random()*100);
			System.out.println(arr[i]);
		}
		SortedList<Integer> sl = new SortedList<Integer>();
		for(int i=0;i<10;++i) {
			sl.insert(arr[i]);
		}
		for(int i=0;i<10;++i) {
			arr[i] = sl.removeFirst().data;
		}
		System.out.println("---------");
		for(int i=0;i<10;++i)
			System.out.println(arr[i]);
	}
}
