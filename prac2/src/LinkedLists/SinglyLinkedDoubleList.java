package LinkedLists;

public class SinglyLinkedDoubleList<T> {
	public SNode<T> first,last;
	public SinglyLinkedDoubleList() {
		first = null;
		last = null;
	}
	public boolean isEmpty() {
		return first==null;
	}
	public SNode<T> deleteFirst(){
		if(isEmpty())
			return null;
		if(first == last)
			last = null;
		SNode<T> temp = first;
		first = first.next;
		return temp;
	}
	public void insertFirst(T data) {
		SNode<T> nn = new SNode<T>(data);
		if(isEmpty())
			last = nn;
		nn.next = first;
		first = nn;
	}
	public void insertLast(T data) {
		SNode<T> nn = new SNode<T>(data);
		if(isEmpty())
			first = nn;
		else
			last.next = nn;
		last = nn;
	}
	public void display() {
		SNode<T> curr = first;
		while(curr!=null) {
			System.out.println(curr);
			curr = curr.next;
		}
	}
}