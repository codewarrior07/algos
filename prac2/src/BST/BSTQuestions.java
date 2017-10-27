package BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BSTQuestions {
	public static void main(String[] args) {
		/*BSTQ<Integer> ques = new BSTQ<Integer>();
		ques.bst.root = ques.makeArrayAsBST(new Integer[]{1,2,3,4,5,6,7,8},0,7);
		ques.bst.inorder(ques.bst.root);
		/*System.out.println(ques.isBST1(ques.bst.root));
		System.out.println(ques.isBST2(ques.bst.root, Integer.MIN_VALUE));
		System.out.println(ques.isBST3(ques.bst.root,Integer.MIN_VALUE,Integer.MAX_VALUE));
		BTNode<Integer> node = ques.bst.find(6);
		System.out.println(ques.findSuccessor(node));*/
		
		BSTQ<Integer> ques1 = new BSTQ<Integer>();
		int[] arr = new int[]{20,8,22,4,12,10,14};
		for(Integer i:arr)
			ques1.bst.insert(i);
		System.out.println(ques1.findKSmallest(ques1.bst.root,5));
		/*int[] arr = new int[]{10,5,1,7,40,50};
		for(Integer i:arr)
			ques1.bst.insert(i);
		List<BTNode<Integer>> serArr = ques1.serialzizeBST(ques1.bst.root);
		Integer[] serArr2 = new Integer[serArr.size()];
		for(int i=0;i<serArr2.length;++i)
			serArr2[i] = serArr.get(i).data;
		ques1.bst.preorder(ques1.reconstructBSTPreorder(serArr2));*/
	}
}

class BSTQ<T extends Comparable<T>> {
	BST<T> bst;
	public BSTQ(){
		bst = new BST<T>();
	}
	/*
	 Given a sorted (increasing order) array with unique integer elements, write an algorithm
	 to create a binary search tree with minimal height.
	*/
	public BTNode<T> makeArrayAsBST(T[] arr,int s,int e){
		if(s > e)
			return null;
		int mid = (s+e)/2;
		BTNode<T> curr = new BTNode<T>(arr[mid]);
		curr.left = makeArrayAsBST(arr,s,mid-1);
		curr.right = makeArrayAsBST(arr,mid+1,e);
		return curr;
	}
	/*
	 Implement a function to check if a binary tree is a binary search tree.
	 */
	public boolean isBST1(BTNode<T> curr) {
		List<T> list = new ArrayList<T>();
		inOrderWalk(curr,list);
		for(int i=1;i<list.size();++i){
			if(list.get(i).compareTo(list.get(i-1)) < 0)
				return false;
		}
		return true;
	}
	public void inOrderWalk(BTNode<T> curr,List<T> list) {
		if(curr != null) {
			inOrderWalk(curr.left,list);
			list.add(curr.data);
			inOrderWalk(curr.right,list);
		}
	}
	/*
	 Implement a function to check if a binary tree is a binary search tree.
	 */
	public boolean isBST2(BTNode<T> curr,T last) {
		if(curr == null)
			return true;
		if(!isBST2(curr.left,last))
			return false;
		if(last.compareTo(curr.data)<0)
			last = curr.data;
		else
			return false;
		if(!isBST2(curr.right,last))
			return false;
		return true;
	}
	/*
	 Implement a function to check if a binary tree is a binary search tree.
	 */
	public boolean isBST3(BTNode<T> curr,T min, T max) {
		if(curr == null)
			return true;
		if(curr.data.compareTo(min)<0 || curr.data.compareTo(max)>0)
			return false;
		return isBST3(curr.left,min,curr.data) && isBST3(curr.right,curr.data,max);
			
	}
	/*
	 Write an algorithm to find the 'next'node (i.e., in-order successor) of a given node in
	 a binary search tree. You may assume that each node has a link to its parent.
	 */	
	public T findSuccessor(BTNode<T> node){
		if(node == null)
			return null;
		BTNode<T> succ = null;
		if(node.right != null){
			succ = node.right;
			while(succ.left!=null) {
				succ = succ.left;
			}
			return succ.data;
		}
		succ = null;
		BTNode<T> curr = this.bst.root;
		while(curr != node){
			if(curr.data.compareTo(node.data) > 0)
				succ = curr;
			curr = node.data.compareTo(curr.data) < 0 ? curr.left : curr.right;
		}
		return succ.data;
	}
	
	
	//Given root of binary search tree and K as input, find K-th smallest element in BST
	class WrapInt{
		int count=0;
	}
	public T findKSmallest(BTNode<T> node,int n){
		if(node==null || n<0)
			return null;
		
		WrapInt c = new WrapInt();
		return findKSmallestHelper(node,n,c);
	}
	
	private T findKSmallestHelper(BTNode<T> node,int n,WrapInt c){
		if(node==null)
			return null;
		T left = findKSmallestHelper(node.left,n,c);
		if(left != null)
			return left;
		++(c.count);
		if(n==c.count)
			return node.data;
		T right = findKSmallestHelper(node.right,n,c);
		if(right != null)
			return right;
		return null;
	}
	
	//serialize using preorder
	public List<BTNode<T>> serialzizeBST(BTNode<T> root){
		if(root==null)
			return null;
		List<BTNode<T>> list = new ArrayList<BTNode<T>>();
		serializePreorder(root,list);
		return list;
	}
	private void serializePreorder(BTNode<T> node,List<BTNode<T>> list){
		if(node==null)
			return;
		list.add(node);
		serializePreorder(node.left,list);
		serializePreorder(node.right,list);
	}
	
	//reconstruct/deserialize BST from preorder traversal
	public BTNode<T> reconstructBSTPreorder(T[] pre){
		if(pre==null)
			return null;
		Stack<BTNode<T>> st = new Stack<BTNode<T>>();
		BTNode<T> root = new BTNode<T>(pre[0]);
		st.push(root);
		for(int i=0;i<pre.length;++i){
			BTNode<T> temp=null;
			while(!st.isEmpty() && st.peek().data.compareTo(pre[i])<0) // st.peek().data < pre[i]
				temp = st.pop();
			if(temp!=null){
				BTNode<T> node = new BTNode<T>(pre[i]);
				temp.right = node;
				st.push(node);
			}
			if(st.peek().data.compareTo(pre[i])>0){
				BTNode<T> node = new BTNode<T>(pre[i]);
				st.peek().left = node;
				st.push(node);
			}
		}
		return root;
	}
	
}
