package LinkedLists;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T>{
	public SNode<T> first;
	public SinglyLinkedList(){
		first = null;
	}
	public boolean isEmpty() {
		return first==null;
	}
	public void insertFirst(T data) {
		SNode<T> nn = new SNode<T>(data);
		nn.next = first;
		first = nn;
	}
	public SNode<T> deleteFirst() {
		if(isEmpty())
			return null;
		SNode<T> temp = first;
		first = first.next;
		return temp;
	}
	
	public void display() {
		SNode<T> curr = first;
		while(curr != null) {
			System.out.println(curr.data);
			curr = curr.next;
		}
	}
	public SNode<T> find(T data) {
		SNode<T> curr = first;
		while(curr!=null) {
			if(curr.data == data)
				return curr;
			curr = curr.next;
		}
		return null;
	}
	
	@Override
	public Iterator<T> iterator(){
		Iterator<T> itr = new Iterator<T>(){
			SNode<T> curr = first;
			@Override
			public boolean hasNext(){
				return curr != null;
			}
			
			@Override
			public T next() {
				T dat = curr.data;
				curr = curr.next;
				return dat;
			}
		};
		return itr;
	}
}