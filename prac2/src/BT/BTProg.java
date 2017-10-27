package BT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class BTProg {
	public static void main(String[] args){
		/*BT<String> bt = new BT<String>();
		bt.insert("a");
		bt.insert("b");
		bt.insert("g");
		bt.insert("c");
		bt.insert("e");
		bt.insert("h");
		bt.insert("i");
		bt.insert("d");
		bt.insert("f");
		BTNode<String> s =  bt.find("a");
		bt.delete(s);
		bt.serialize(s);*/
		
		BT<Integer> bt = new BT<Integer>();
		bt.insert(0);
		bt.insert(1);
		bt.insert(2);
		BTNode<Integer> root = bt.find(0);
		bt.serialize(root);
		BTNode<Integer> node = bt.deserialize("C:\\Users\\Sriram\\workspace\\prac2\\src\\BT\\bt.ser");
		bt.BFS();
	}
}

class BTNode<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	T data;
	BTNode<T> left;
	BTNode<T> right;
	BTNode<T> parent; // not used
	public BTNode(T data) {
		this.data = data;
	}
	public String toString() {
		return String.valueOf(this.data);
	}
}

class BT<T> {
	BTNode<T> root;
	private String MARKER = "NULL";
	public BT(){
		root=null;
	}
	public boolean isEmpty() {
		return root==null;
	}
	public void insert(T data){
		BTNode<T> nn = new BTNode<T>(data);
		if(root == null) {
			root = nn;
			return;
		}
		Queue<BTNode<T>> q = new LinkedList<BTNode<T>>();
		q.add(root);
		while(true) {
			BTNode<T> temp = q.remove();
			if(temp.left == null){
				temp.left = nn;
				nn.parent = temp;
				break;
			} else if(temp.right == null) {
				temp.right = nn;
				nn.parent = temp;
				break;
			} else {
				q.add(temp.left);
				q.add(temp.right);
			}
		}
	}
	public BTNode<T> find(T data) {
		Queue<BTNode<T>> q = new LinkedList<BTNode<T>>();
		q.add(root);
		while(true) {
			BTNode<T> temp = q.remove();
			if(temp.data == data)
				return temp;
			if(temp.left != null)
				q.add(temp.left);
			if(temp.right != null)
				q.add(temp.right);
		}
	}
	public BTNode<T> findRec(BTNode<T> curr,BTNode<T> node) {
		if(curr == null)
			return null;
		if(curr.data == node.data)
			return curr;
		BTNode<T> l = findRec(curr.left,node);
		return l==null ? findRec(curr.right,node) : l;
	}
	public void delete(BTNode<T> del) {
		Queue<BTNode<T>> q = new LinkedList<BTNode<T>>();
		BTNode<T> parent = root;
		q.add(root);
		while(!q.isEmpty()) {
			BTNode<T> temp = q.remove();
			if(temp.data == del.data)
				break;
			if(temp.left != null) {
				if(temp.left.data == del.data) {
					parent = temp;
					break;
				} else
					q.add(temp.left);
			}
			if(temp.right != null) {
				if(temp.right.data == del.data) {
					parent = temp;
					break;
				} else
					q.add(temp.right);
			}
		}
		if(del.left == null && del.right == null) {
			if(del == root)
				root = null;
			else if(parent.left == del)
				parent.left = null;
			else 
				parent.right = null;
		}
		else if(del.left == null) {
			if(del == root)
				root = root.right;
			else if(parent.left == del)
				parent.left = del.right;
			else
				parent.right = del.right;
		}
		else if(del.right == null) {
			if(del == root)
				root = root.left;
			else if(parent.left == del)
				parent.left = del.left;
			else
				parent.right = del.left;
		}
		else { // replace del node with its child that has only one subtree
			Stack<BTNode<T>> st = new Stack<BTNode<T>>();
			BTNode<T> rep = del;
			BTNode<T> repParent = del;
			st.add(del);
			while(!st.isEmpty()) {
				BTNode<T> temp = st.pop();
				if(temp.right == null || temp.left == null) {
					rep = temp;
					break;
				} else if(temp.left != null)
					st.push(temp.left);
				else if(temp.right != null)
					st.push(temp.right);
				repParent = temp;
			}
			if(del == root)
				root = rep;
			if(del == parent.left)
				parent.left = rep;
			else if(del == parent.right)
				parent.right = rep;
			// put rep's left or right on its parent's left or right
			boolean isLeftChild = (repParent.left == rep); 
			if(rep.left == null){
				if(isLeftChild)
					repParent.left = rep.right;
				else 
					repParent.right = rep.right;
			}
			else if(rep.right == null){
				if(isLeftChild)
					repParent.left = rep.left;
				else 
					repParent.right = rep.left;
			}
			rep.right = del.right;
			rep.left = del.left;
		}
	}
	public void display() {
		this.BFS();
	}
	public void BFS(){
		Queue<BTNode<T>> q = new LinkedList<BTNode<T>>();
		q.add(root);
		while(!q.isEmpty()) {
			BTNode<T> temp = q.remove();
			if(temp.left != null)
				q.add(temp.left);
			if(temp.right != null)
				q.add(temp.right);
			System.out.println(temp.data);
		}
	}
	
	
	public void serialize(BTNode<T> node){
		if(node == null)
			return;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("C:\\Users\\Sriram\\workspace\\prac2\\src\\BT\\bt.ser");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			serialize(node,os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void serialize(BTNode<T> node,ObjectOutputStream os) throws IOException{
		if(node == null){
			os.writeObject(MARKER);
			return;
		}
		os.writeObject(node.data);
		serialize(node.left,os);
		serialize(node.right,os);
	}
	
	public BTNode<T> deserialize(String path){
		if(path==null)
			return null;
		BTNode<T> root=null;
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream is = new ObjectInputStream(fis);
			root=deserialize(root,is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return root;
	}
	@SuppressWarnings("unchecked")
	public BTNode<T> deserialize(BTNode<T> root, ObjectInputStream is) throws ClassNotFoundException, IOException{
		T data = (T)is.readObject();
		if(data.equals(MARKER))
			return null;
		root = new BTNode<T>(data);
		root.left = deserialize(root.left,is);
		root.right = deserialize(root.right,is);
		return root;
	}
}
