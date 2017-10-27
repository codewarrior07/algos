package rbtree;

import BST.BTNode;
import Constants.Constants;
public class RBNode<T extends Comparable<T>> extends BTNode<T>{
	public boolean color;
	public BTNode<T> parent;
	public RBNode(T data) {
		super(data);
		color = Constants.RED;
		parent = null;
	}
}
