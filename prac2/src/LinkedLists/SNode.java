package LinkedLists;


public class SNode<T> {
	public T data;
	public SNode<T> next;
	public SNode(T data) {
		this.data = data;
		next = null;
	}
	public String toString() {
		return String.valueOf(data);
	}
}
