package BST;

import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable<T>> {
	public BTNode<T> root;
	public BST() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root==null;
	}
	public BTNode<T> find(T inp) {
		if(isEmpty())
			return null;
		BTNode<T> curr = root;
		while(curr != null && curr.data != inp){
			if(inp.compareTo(curr.data) < 0)
				curr = curr.left;
			else 
				curr = curr.right;
		}
		return curr;
		
	}
	
	public void insert(T dat) {
		BTNode<T> nn = new BTNode<T>(dat);
		if(root == null) {
			root = nn;
			return;
		}
		BTNode<T> curr = root;
		BTNode<T> prev = curr;
		while(curr!= null){
			prev = curr;
			if(dat.compareTo(curr.data) < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
		if(dat.compareTo(prev.data) < 0)
			prev.left = nn;
		else
			prev.right = nn;
	}
	
	public void insertRec(BTNode<T> curr, BTNode<T> prev, T dat) {
		if (root == null) {
			root = new BTNode<T>(dat);
			return;
		}
		if(curr == null) {
			if(dat.compareTo(prev.data) <0)
				prev.left = new BTNode<T>(dat);
			else
				prev.right = new BTNode<T>(dat);
			return;
		}
		prev = curr;
		if(dat.compareTo(curr.data) < 0 )
			curr = curr.left;
		else
			curr = curr.right;
		insertRec(curr,prev,dat);
	}
	
	public void inorder(BTNode<T> curr) {
		if(curr == null)
			return;
		inorder(curr.left);
		System.out.println(curr.data);
		inorder(curr.right);
	}
	
	public void preorder(BTNode<T> curr) {
		if(curr != null){
			System.out.println(curr.data);
			preorder(curr.left);
			preorder(curr.right);
		}
	}
	
	public void postorder(BTNode<T> curr) {
		if(curr != null) {
			postorder(curr.left);
			postorder(curr.right);
			System.out.println(curr.data);
		}
	}
	
	public void inorderIter() {
		
	}
	
	public BTNode<T> max(BTNode<T> curr){
		while(curr != null && curr.right != null)
			curr = curr.right;
		return curr;
	}
	
	public BTNode<T> min(BTNode<T> curr){
		while(curr!=null && curr.left!= null)
			curr = curr.left;
		return curr;
	}
	
	public BTNode<T> predecessor(BTNode<T> inp) {
		if(inp == null)
			return null;
		if(inp.left != null)
			return max(inp.left);
		BTNode<T> curr = root, pred = null;
		while(curr != inp) {
			if(inp.data.compareTo(curr.data) < 0)
				curr = curr.left;
			else {
				pred = curr;
				curr = curr.right;
			}
		}
		return pred;
	}
	
	public BTNode<T> successor(BTNode<T> inp) {
		if(inp == null)
			return null;
		if(inp.right != null)
			return min(inp.right);
		BTNode<T> curr = root,succ=null;
		while(curr != inp) {
			if(inp.data.compareTo(curr.data) < 0 ){
				succ = curr;
				curr = curr.left;
			}
			else {
				curr = curr.right;
			}
		}
		return succ;
	}
	
	public void delete(BTNode<T> inp) {
		if(isEmpty())
			return;
		BTNode<T> curr=root,parent=root;
		while(curr != inp) {
			parent = curr;
			if(inp.data.compareTo(curr.data) < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
		if(curr.left == null & curr.right == null) {
			if(root == curr)
				root = null;
			else if(parent.left == curr)
				parent.left = null;
			else
				parent.right = null;
		}
		else if(curr.right == null) {
			if(root == curr)
				root = curr.left;
			else if(parent.left == curr)
				parent.left = curr.left;
			else
				parent.right = curr.left;
		}
		else if(curr.left == null) {
			if(root == curr)
				root = curr.right;
			else if(parent.left == curr)
				parent.left = curr.right;
			else
				parent.right = curr.right;
		}
		else {
			BTNode<T> succ = successor(curr);
			if(succ == curr.right) {
				if(root == curr)
					root = succ;
				else if(parent.left == curr)
					parent.left = succ;
				else
					parent.right = succ;
			}
			else {
				if(root == curr)
					root = succ;
				else if(parent.left == curr)
					parent.left = succ;
				else
					parent.right = succ;
				BTNode<T> succParent = curr.right;
				BTNode<T> temp = curr.right;
				while(temp != succ) {
					succParent = temp;
					temp = temp.left;
				}
				succParent.left = succ.right;
				succ.right = curr.right;
			}
			succ.left = curr.left;
		}
	}
	
	public void BFS(BTNode<T> inp) {
		Queue<BTNode<T>> q = new LinkedList<BTNode<T>>();
		q.add(inp);
		while(!q.isEmpty()) {
			BTNode<T> temp = q.remove();
			if(temp.left != null)
				q.add(temp.left);
			if(temp.right != null)
				q.add(temp.right);
			System.out.println(temp.data);
		}
	}
	
	public static void main(String[] args) {
		BST<Integer> bst = new BST<Integer>();
		bst.insertRec(bst.root,bst.root,14);
		bst.insert(5);
		bst.insertRec(bst.root,bst.root,20);
		bst.insert(2);
		bst.insertRec(bst.root,bst.root,12);
		bst.insert(15);
		bst.insert(30);
		bst.insert(1);
		bst.insert(3);
		bst.insert(13);
		bst.insert(29);
		bst.insert(4);
		bst.insert(6);
		bst.insert(7);
		//bst.inorder(bst.root);
		//System.out.println(bst.find(30));
		//System.out.println(bst.successor(bst.find(30)));
		//bst.delete(bst.find(29));
		//bst.inorder(bst.root);
		bst.BFS(bst.root);
	}
}




