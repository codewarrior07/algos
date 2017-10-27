package BT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTree {
	/** 
	* Represents a pair relation between one parent node and one child node inside a binary tree 
	* If the _parent is null, it represents the ROOT node 
	*/ 
	class Relation { 
	    public Integer _parent; 
	    public Integer _child; 
	    public boolean _isLeft; 
	    public Relation(Integer c, Integer p, boolean left) {
	    	this._parent = p;
	    	this._child = c;
	    	this._isLeft = left;
	    }
	} 


	/** 
	* Represents a single Node inside a binary tree 
	*/ 
	class Node { 
	    public Integer _id; 
	    public Node _left; 
	    public Node _right;
	    
	    @Override
	    public boolean equals(Object o) {
	        if(o != null && o instanceof Node)
	            return ((Node)o)._id == this._id;
	        return false;
	    } 
	} 

	/** 
	* Implement a method to build a tree from a list of parent-child relationships 
	* And return the root Node of the tree 
	*/ 
	// using hasmap, O(n)
	public Node buildTree (List<Relation> data) 
	{
	     Map<Integer,Node> map= new HashMap<Integer,Node>();
	     Node root = new Node();
	     for(Relation r: data) {
	         Node curr = null;
	         if(r._parent != null) {
	             if(map.containsKey(r._parent))
	                 curr = map.get(r._parent);
	             else {
	                 curr = new Node();
	                 curr._id = r._parent;
	                 map.put(r._parent,curr);
	             }
	         }
	         Node child;
	         if(map.containsKey(r._child))
	             child = map.get(r._child);
	         else {
	             child = new Node();
	             child._id = r._child;
	             map.put(r._child,child);
	         }
	         if(r._parent != null) {
	             if(r._isLeft)
	                 curr._left = child;
	             else
	                 curr._right = child;
	         } else
	            root = child;
	     }
	     return root;
	}
	
	public void inorder(Node root) {
		if(root != null) {
			inorder(root._left);
			System.out.println(root._id);
			inorder(root._right);
		}
	}
	
	// Recursive, O(n2)
	public Node buildTreeHelper(List<Relation> list,Node curr){
		for(Relation r:list){
			if(curr != null && r._parent==curr._id){
				Node n = new Node();
				n._id = r._child;
				if(r._isLeft)
					curr._left = n;
				else
					curr._right = n;
				buildTreeHelper(list,n);
			} else if(curr == null && r._parent == null) {
				Node n = new Node();
				n._id = r._child;
				curr = n;
				buildTreeHelper(list,n);
			}
		}
		return curr;
	}
	
	public Node buildTreeRec (List<Relation> data) {
		return buildTreeHelper(data,null);
	}
	
	public static void main(String[] args) {
		BuildTree t = new BuildTree();
		List<Relation> list = new ArrayList<Relation>();
		list.add(t.new Relation(15,20,true));
		list.add(t.new Relation(19,80,true));
		list.add(t.new Relation(17,20,false));
		list.add(t.new Relation(16,80,false));
		list.add(t.new Relation(80,50,false));
		list.add(t.new Relation(50,null,false));
		list.add(t.new Relation(20,50,true));
		Node root = t.buildTreeRec(list);
		t.inorder(root);
	}
}
