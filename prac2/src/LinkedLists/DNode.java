package LinkedLists;

public class DNode<T> {
	public T data;
	DNode<T> next;
	DNode<T> prev;
	public DNode(T data) {
		this.data = data;
		next = prev = null;
	}
	
	public String toString() {
		return String.valueOf(this.data);
	}
}