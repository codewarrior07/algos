package BST;

public class BTNode<T extends Comparable<T>>{
	public T data;
	public BTNode<T> left;
	public BTNode<T> right;
	
	public BTNode(T data) {
		this.data = data;
		left = right = null;
	}
	
	public String toString() {
		return String.valueOf(data);
	}

}

