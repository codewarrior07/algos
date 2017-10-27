package rbtree;

import BST.BST;
import Constants.Constants;

public class RBTree<T extends Comparable<T>> extends BST<T>{
	public RBNode<T> root;
	public int blackHeight;
	public RBTree() {
		root = null;
		blackHeight = 0;
	}
	@Override
	public void insert(T data) {
		RBNode<T> nn = new RBNode<T>(data);
		if(root == null) {
			root = nn;
			root.color = Constants.BLACK;
			return;
		}
		RBNode<T> curr = root;
		RBNode<T> parent = root;
		while(curr != null) {
			parent = curr;
			if(data.compareTo(curr.data) < 0) 
				curr = (RBNode<T>) curr.left;
			else 
				curr = (RBNode<T>) curr.right;
		}
		if(data.compareTo(parent.data) < 0 )
			parent.left = nn;
		else
			parent.right = nn;
		nn.parent = parent;
		checkViolation(nn,parent);
	}
	public void checkViolation(RBNode<T> curr, RBNode<T> parent) {
		if(curr == null) // validated till root
			return;
		if(curr == root) {
			if(curr.color == Constants.RED)
				curr.color = Constants.BLACK; 
			return;
		}
		if(curr.color==Constants.RED && curr.color==parent.color) { // violation
			// check uncle
			RBNode<T> gp = (RBNode<T>)parent.parent;
			RBNode<T> uncle = gp.left == parent ? (RBNode<T>)gp.right: (RBNode<T>)gp.left;
			if(uncle == null || uncle.color==Constants.BLACK) { // black uncle 
				if(gp.left == parent) {
					if(parent.left == curr) { // case 3
						rotateRight(gp,parent);
						gp.color = !gp.color;
						parent.color = !parent.color;
					}
					else if(parent.right == curr) {// case 2
						doubleRotate(parent,curr);
						//parent.color = !parent.color;
						curr.color = !curr.color;
						if(parent == curr.left && curr.right != null)
							((RBNode<T>)curr.right).color = !((RBNode<T>)curr.right).color;
						else if(parent == curr.right && curr.left != null)
							((RBNode<T>)curr.left).color = !((RBNode<T>)curr.left).color;
					}
				}
				else if(gp.right == parent) {
					if(parent.right == curr) {// case 3
						rotateLeft(gp,parent);
						gp.color = !gp.color;
						parent.color = !parent.color;
					}
					else if(parent.left == curr){ // case 2
						doubleRotate(parent,curr);
						//parent.color = !parent.color;
						curr.color = !curr.color;
						if(parent == curr.left && curr.right != null)
							((RBNode<T>)curr.right).color = !((RBNode<T>)curr.right).color;
						else if(parent == curr.right && curr.left != null)
							((RBNode<T>)curr.left).color = !((RBNode<T>)curr.left).color;
					}
				}
			}
			else if(uncle.color == Constants.RED) // flip colors of gp and its children
				flipColor(gp);
		}
		checkViolation((RBNode<T>)curr.parent,(RBNode<T>)parent.parent); // here curr != root, so parent != null
	}
	public void flipColor(RBNode<T> node) {
		node.color = !node.color;
		((RBNode<T>)node.left).color = !((RBNode<T>)node.left).color;
		((RBNode<T>)node.right).color = !((RBNode<T>)node.right).color;
	}
	public void rotateLeft(RBNode<T> parent, RBNode<T> child) { // child is right child of curr
		if(child.left != null)
			((RBNode<T>)child.left).parent = parent;
		parent.right = child.left;
		
		RBNode<T> gp = (RBNode<T>)parent.parent; // save parent's parent
		
		child.left = parent;
		parent.parent = child;
		if(parent == root)
			root = child;
		
		if(gp!=null && gp.left == parent)
			gp.left = child;
		else if(gp!=null && gp.right == parent)
			gp.right = child;
		child.parent = gp;
	}
	public void rotateRight(RBNode<T> parent, RBNode<T> child) { // child is left child of curr
		if(child.right != null)
			((RBNode<T>)child.right).parent = parent;
		parent.left = child.right;
		
		RBNode<T> gp = (RBNode<T>)parent.parent; // save parent's parent
		
		child.right = parent;
		parent.parent = child;
		if(parent == root)
			root = child;
		
		if(gp!=null && gp.left == parent)
			gp.left = child;
		else if(gp!=null && gp.right == parent)
			gp.right = child;
		child.parent = gp;
	}
	public void doubleRotate(RBNode<T> parent,RBNode<T> child) {
		// rotate by parent
		if(parent.right == child){
			// child is right child of curr, so do left rotate by parent
			rotateLeft(parent,child);
			//rotate right by grand parent
			rotateRight((RBNode<T>)child.parent,(RBNode<T>)parent.parent);
			//color change TODO
			
		}
		else {
			// child is left  child of curr, so do right rotate by parent
			rotateRight(parent,child);
			//rotate left by grand parent
			rotateLeft((RBNode<T>)child.parent,(RBNode<T>)parent.parent);
			//color change TODO
		}	
	}
	public void inOrderWithColor(RBNode<T> curr) {
		if(curr!=null) {
			inOrderWithColor((RBNode<T>)curr.left);
			String colour = curr.color ? "red":"black"; 
			if(curr.parent != null)
				System.out.println(curr.data + " "+colour+" parent "+curr.parent.data);
			else
				System.out.println(curr.data + " "+colour+" parent null");
			inOrderWithColor((RBNode<T>)curr.right);
		}
	}
	public void insertTopDown(T data) {
		if(root == null) {
			root = new RBNode<T>(data);
			root.color = Constants.BLACK;
			return;
		}
		RBNode<T> curr = root;
		RBNode<T> parent = root;
		while(curr != null) {
			parent = curr;
			// flip colors if black node has 2 red children
			if(curr.color == Constants.BLACK) {
				if(curr.left != null && ((RBNode<T>)curr.left).color == Constants.RED
						&& curr.right != null && ((RBNode<T>)curr.right).color == Constants.RED) {
					flipColor(curr);
					checkViolation(curr,parent);
				}
			}
			if(data.compareTo(curr.data) < 0)
				curr = (RBNode<T>)curr.left;
			else
				curr = (RBNode<T>)curr.right;
		}
		RBNode<T> nn = new RBNode<T>(data);
		if(data.compareTo(parent.data) < 0)
			parent.left = nn;
		else
			parent.right = nn;
		nn.parent = parent;
		checkViolation(nn,parent);
	}
	public static void main(String[] args)  {
		RBTree<Integer> rbt = new RBTree<Integer>();
		/*rbt.insert(25);
		rbt.insert(50);
		rbt.insert(75);
		rbt.insert(100);
		rbt.insert(120);
		rbt.insert(35);
		rbt.insert(40);
		rbt.insert(1);
		rbt.insert(45);
		rbt.insert(48);
		rbt.insert(37);*/
		/*rbt.insertTopDown(25);
		rbt.insertTopDown(50);
		rbt.insertTopDown(75);
		rbt.insertTopDown(100);
		rbt.insertTopDown(120);
		rbt.insertTopDown(35);
		rbt.insertTopDown(40);
		rbt.insertTopDown(1);
		rbt.insertTopDown(45);
		rbt.insertTopDown(48);
		rbt.insertTopDown(37);*/
		rbt.inOrderWithColor(rbt.root);
		
	}
}
