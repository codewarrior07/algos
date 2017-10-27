// Doubly linked double ended List
package LinkedLists;

import java.util.Iterator;

public class DLL<T> implements Iterable<T>{
	DNode<T> first, last;
	public DLL() {
		first = last = null;
	}
	
	public boolean isEmpty() {
		if (this.first == null)
			return true;
		return false;
	}
	
	public void insertFirst(T dat) {
		DNode<T> nn = new DNode<T>(dat);
		if(isEmpty()) 
			last = nn;
		else
			first.prev = nn;
		nn.next = first;
		first = nn;
	}
	
	public void insertLast(T dat) {
		DNode<T> nn = new DNode<T>(dat);
		if(isEmpty())
			first = nn;
		else 
			last.next = nn;
		nn.prev = last;
		last = nn;
	}
	
	public T deleteFind(T dat) {
		if(isEmpty())
			return null;
		else if(dat.equals(last.data)) {
			DNode<T> temp = last;
			last = last.prev;
			last.next = null;
			return temp.data;
		}
		else {
			DNode<T> curr = first;
			while(curr != null && !curr.data.equals(dat))
				curr = curr.next;
			if(curr == null)
				return null;
			if(curr == first) {// curr is first but not last
				DNode<T> temp = first;
				first = first.next;
				first.prev = null;
				return temp.data;
			}
			DNode<T> temp = curr;
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
			return temp.data;
		}
	}
	
	public DNode<T> deleteFirst() {
		if(isEmpty())
			return null;
		DNode<T> temp = first;
		if(first == last)
			last = null;
		else
			first.next.prev = null;
		first = first.next;
		return temp;
	}
	
	public DNode<T> deleteLast() {
		DNode<T> temp = last;
		if(isEmpty())
			return null;
		if(first == last)
			first = null;
		else 
			last.prev.next = null;
		last = last.prev;
		return temp;
	}
	
	public T find(T dat) {
		if(isEmpty())
			return null;
		if(dat == last.data)
			return last.data;
		DNode<T> curr = first;
		while(curr != null && !curr.data.equals(dat)) 
			curr = curr.next;
		if(curr == null)
			return null;
		return curr.data;
	}
	
	public void displayForward() {
		DNode<T> curr = first;
		while(curr != null) {
			System.out.println(curr.data);
			curr = curr.next;
		}
	}
	
	public void displayReverse() {
		DNode<T> curr = last;
		while(curr != null) {
			System.out.println(curr.data);
			curr = curr.prev;
		}
	}
	@Override
	public Iterator<T> iterator() {
		Iterator<T> itr = new Iterator<T>() {
			DNode<T> curr = first;
			@Override
			public boolean hasNext() {
				return curr != null && curr.data != null;
			}
			
			@Override
			public T next() {
				DNode<T> temp = curr;
				curr = curr.next;
				return temp.data;
			}
			
			public T prev() {
				DNode<T> temp = curr;
				curr = curr.prev;
				return temp.data;
			}
		};
		return itr;
	}
	public static void main(String[] args) {
		DLL<Integer> dll = new DLL<Integer>();
		dll.insertFirst(10);
		dll.insertFirst(20);
		dll.insertFirst(1);
		dll.insertFirst(123);
		dll.insertFirst(-12);
		dll.insertFirst(0);
		//dll.displayForward();
		//dll.deleteLast();
		//dll.displayForward();
		
		/*for(Integer i:dll) {
			System.out.println(i);
		}
		Iterator<Integer> itr = dll.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}*/

	}
	
}
