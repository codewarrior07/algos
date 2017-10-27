package BT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import LinkedLists.SinglyLinkedList;
import StacksnQueues.QueueEmptyException;
import StacksnQueues.QueueLL;
import StacksnQueues.StackEmptyException;

public class BTQuestions {
	public static void main(String[] args) throws StackEmptyException, QueueEmptyException {
		BTQ<Character> btq = new BTQ<Character>();
		btq.bt.insert('a');
		btq.bt.insert('b');
		btq.bt.insert('c');
		btq.bt.insert('d');
		btq.bt.insert('e');
		btq.bt.insert('f');

		/*for(SinglyLinkedList<Character> ll:btq.formLinkedLists()) {
			for(Character c:ll) {
				System.out.print(c+" ");
			}
			System.out.println();
		}*/
		/*for(SinglyLinkedList<BTNode<Character>> ll:btq.formLinkedLists1()) {
			for(BTNode<Character> node:ll) {
				System.out.print(node.data+" ");
			}
			System.out.println();
		}*/
		//System.out.println(btq.bt.root.right.left);

		/*BTNode<Character> n1 = btq.bt.find('d');
		BTNode<Character> n2 = btq.bt.find('e');
		System.out.println(btq.lca3(n1,n2));*/

		/*BTQ<Integer> btq = new BTQ<Integer>();
		btq.bt.insert(10);
		btq.bt.insert(1);
		btq.bt.insert(12);
		btq.bt.insert(10);
		btq.bt.insert(7);
		btq.bt.insert(5);
		btq.bt.insert(500);
		btq.bt.insert(100);
		btq.bt.insert(200);
		btq.bt.insert(4);
		btq.bt.insert(3);
		btq.bt.insert(201);
		btq.bt.insert(6);
		btq.bt.insert(-511);
		List<List<BTNode<Integer>>> fin = new ArrayList<List<BTNode<Integer>>>();
		for(List<BTNode<Integer>> list:btq.findPaths1(11,fin)) {
			for(BTNode<Integer> i:list)
				System.out.print(i.data+ " ");
			System.out.println();
		}*/

		/*BTQ<Character> btq = new BTQ<Character>();
		Character[] in = new Character[]{'D','B', 'E', 'A', 'F', 'C'};
		Character[] pre = new Character[]{'A' ,'B', 'D' ,'E', 'C', 'F'};
		btq.preIndex = in.length-1;
		BTNode<Character> root = btq.constructTreeInPre(in,pre,0,in.length-1);*/

		// btq.printLevel(btq.bt.root);

		/*BTQ<Character> btq1 = new BTQ<Character>();
		btq1.bt.insert('a');
		btq1.bt.insert('b');
		btq1.bt.insert('c');
		btq1.bt.insert('d');
		btq1.bt.insert('e');
		btq1.bt.insert('f');
		System.out.println(btq.isEqual(btq.bt.root,btq1.bt.root));*/

		BTQ<Character> btq1 = new BTQ<Character>();
		btq1.bt.insert('a');
		btq1.bt.insert('b');
		btq1.bt.insert('c');
		btq1.bt.insert('d');
		btq1.bt.insert('e');
		btq1.bt.insert('f');
		btq1.bt.insert('g');
		btq1.bt.insert('h');
		btq1.bt.insert('i');
		/*BTNode<Character> n1 = btq1.bt.find('c');
		BTNode<Character> n2 = btq1.bt.find('x');

		System.out.println(btq1.lcaNoParent(n1,n2));*/

		/*
		 *
		 *        1                1
		 *       / \              / \
		 *      2   3            2   3
		 *     / \
		 *    4   5
		 *   / \
		 *  6   7
		 */
		/*btq1 = new BTQ<Character>();
		btq1.bt.insert('1');
		btq1.bt.insert('2');
		btq1.bt.insert('3');
		btq1.bt.insert('4');
		btq1.bt.insert('5');
		BTNode<Character> n6 = new BTNode<Character>('6');
		BTNode<Character> n7 = new BTNode<Character>('7');
		BTNode<Character> n4 = btq1.bt.find('4');
		n4.left = n6;
		n4.right = n7;
		btq1.printLevel(btq1.flipTree(btq1.bt.root));*/

		/*BTNode<Character> n1 = btq1.bt.find('b');
		BTNode<Character> n2 = btq1.bt.find('h');
		System.out.println(btq1.lca(btq1.bt.root,n1,n2));*/

		/*BTQ<Integer> btq2 = new BTQ<Integer>();
		btq2.bt.insert(50);
		btq2.bt.insert(80);
		btq2.bt.insert(10);
		btq2.bt.insert(60);
		btq2.bt.insert(40);
		BTNode<Integer> n61 = new BTNode<Integer>(61);
		BTNode<Integer> n180 = new BTNode<Integer>(180);
		BTNode<Integer> n21 = new BTNode<Integer>(21);
		n21.left = n61;
		n21.right = n180;
		BTNode<Integer> n10 = btq2.bt.find(10);
		n10.right= n21;

		BTNode<Integer> n26 = new BTNode<Integer>(26);
		BTNode<Integer> n17 = new BTNode<Integer>(17);
		BTNode<Integer> n30 = new BTNode<Integer>(30);
		n17.left = n26;
		n30.right = n17;
		BTNode<Integer> n40 = btq2.bt.find(40);
		n40.right = n30;

		BTNode<Integer> n20 = new BTNode<Integer>(20);
		BTNode<Integer> n90 = new BTNode<Integer>(90);
		BTNode<Integer> n60 = btq2.bt.find(60);
		n60.left = n20;
		n60.right =n90;

		BTNode<Integer> n1 = btq2.bt.find(40);
		BTNode<Integer> n2 = btq2.bt.find(90);
		btq2.bt.BFS();
		//System.out.println(btq2.findMinDist(btq2.bt.root,n1,n2));*/

		//BTQ<Integer> btq2 = new BTQ<Integer>();
		//BTNode<Integer> node = btq2.constructTreeInPre(new Integer[]{4,2,5,1,3,7},new Integer[]{1,2,4,5,3,7});

		BTQ<Integer> btq3 = new BTQ<Integer>();
		btq3.bt.insert(10);
		btq3.bt.insert(8);
		btq3.bt.insert(2);
		btq3.bt.insert(3);
		btq3.bt.insert(5);
		btq3.bt.insert(4);
		//System.out.println(btq3.hasPath(btq3.bt.root,15));
		System.out.println(btq3.findKthSmallest(btq3.bt.root,3));

	}
}

class BTQ<T extends Comparable<T>> {
	BT<T> bt;
	public BTQ(){
		bt = new BT<T>();
	}
	/*
	 Implement a function to check if a binary tree is balanced. For the purposes of this
	 question, a balanced tree is defined to be a tree such that the heights of the two
	 subtrees of any node never differ by more than one.
	 */
	// O(N2) see below
	public boolean checkBalanced(BTNode<T> curr) {
		if(curr == null)
			return true;
		int lh = getHeightRec(curr.left);
		int rh = getHeightRec(curr.right);
		if(Math.abs(lh-rh) > 1)
			return false;
		return checkBalanced(curr.left) && checkBalanced(curr.right);
	}
	public int getHeightRec(BTNode<T> curr) {
		if(curr == null)
			return 0;
		return 1 + Math.max(getHeightRec(curr.left), getHeightRec(curr.right));
	}
	/*
	 Implement a function to check if a binary tree is balanced. For the purposes of this
	 question, a balanced tree is defined to be a tree such that the heights of the two
	 subtrees of any node never differ by more than one.
	 */
	//O(N) use this
	public boolean checkBalanced1(){
		return checkHeight(bt.root) == -1 ? false : true;
	}
	public int checkHeight(BTNode<T> curr){
		if(curr == null)
			return 0;
		int lh = checkHeight(curr.left);
		if(lh == -1)
			return -1;
		int rh = checkHeight(curr.right);
		if(rh == -1)
			return -1;
		if(Math.abs(lh-rh) > 1)
			return -1;
		return 1 + Math.max(lh, rh);
	}
	/*
	 Given a binary tree, design an algorithm which creates a linked list of all the nodes at
	 each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
	 */
	//recursive
	public List<List<T>> formLinkedLists() {
		List<List<T>> list = new ArrayList<List<T>>();
		createLinkedLists(this.bt.root, list, 0);
		return list;
	}
	public void createLinkedLists(BTNode<T> curr,List<List<T>> result,int level){
		if(curr == null)
			return;
		List<T> currList;
		if(level == result.size()) {
			currList = new ArrayList<T>();
			result.add(currList);
		} else
			currList = result.get(level);
		currList.add(curr.data);
		createLinkedLists(curr.left,result,level+1);
		createLinkedLists(curr.right,result,level+1);
	}
	/*
	 Given a binary tree, design an algorithm which creates a linked list of all the nodes at
	 each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
	 */
	//iterative
	public List<SinglyLinkedList<BTNode<T>>> formLinkedLists1() {
		if(this.bt.root == null)
			return null;
		List<SinglyLinkedList<BTNode<T>>> result = new ArrayList<SinglyLinkedList<BTNode<T>>>();
		SinglyLinkedList<BTNode<T>> currLL = new SinglyLinkedList<BTNode<T>>();
		currLL.insertFirst(this.bt.root);
		while(!currLL.isEmpty()) {
			result.add(currLL);
			SinglyLinkedList<BTNode<T>> parents = currLL;
			currLL = new SinglyLinkedList<BTNode<T>>();
			for(BTNode<T> btnode:parents){
				if(btnode.left != null)
					currLL.insertFirst(btnode.left);
				if(btnode.right != null)
					currLL.insertFirst(btnode.right);
			}
		}
		return result;
	}
	/*
	 Design an algorithm and write code to find the first common ancestor of two nodes
	 in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
	 necessarily a binary search tree.
	 */
	//use this if n1,n2 exists in the tree
	public BTNode<T> lca(BTNode<T> curr,BTNode<T>n1,BTNode<T> n2){
		if(curr == null)
			return null;
		if(curr == n1 || curr==n2)
			return curr;
		BTNode<T> left =lca(curr.left,n1,n2);
		BTNode<T> right =lca(curr.right,n1,n2);
		if(left != null && right!=null)
			return curr;
		return (left==null)?right:left;
	}
	/*
	 Design an algorithm and write code to find the first common ancestor of two nodes
	 in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
	 necessarily a binary search tree.
	 */
	// O(n2)
	public BTNode<T> lca3(BTNode<T> n1, BTNode<T> n2) {
		return lca3Helper(this.bt.root,n1,n2);
	}

	public BTNode<T> lca3Helper(BTNode<T> curr, BTNode<T> n1, BTNode<T> n2) {
		if(curr == null)
			return null;
		if(curr == n1 || curr == n2)
			return curr;
		boolean isn1Left = covers(curr.left,n1);
		boolean isn2Left = covers(curr.left,n2);
		if(isn1Left != isn2Left)
			return curr;
		BTNode<T> currChild = isn1Left ? curr.left: curr.right;
		return lca3Helper(currChild,n1,n2);
	}
	public boolean covers(BTNode<T> curr,BTNode<T> node) {
		if(curr == null)
			return false;
		if(curr == node)
			return true;
		return covers(curr.left,node) || covers(curr.right,node);
	}
	/*
	 Design an algorithm and write code to find the first common ancestor of two nodes
	 in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
	 necessarily a binary search tree.
	 */
	//use this if n1,n2 may not exist in the tree
	public BTNode<T> lca2(BTNode<T> curr,BTNode<T> n1, BTNode<T> n2) {
		if(curr == null)
			return null;
		if(curr == n1 && curr == n2)
			return curr;
		BTNode<T> x = lca2(curr.left,n1,n2);
		if(x!=null && x!=n1 && x!=n2)
			return x;
		BTNode<T> y = lca2(curr.right,n1,n2);
		if(y!=null && y!=n1 && y!=n2)
			return y;
		if(x!= null && y!=null)
			return curr;
		if(curr==n1 || curr==n2)
			return curr;
		return x==null ? y: x; 
	}

	/*
	 You are given a binary tree in which each node contains a value. Design an algorithm
	to print all paths which sum to a given value. The path does not need to start
	or end at the root or a leaf.
	 */
	// USE THIS
	@SuppressWarnings("unchecked")
	public List<List<BTNode<Integer>>> findPaths1(int sum,List<List<BTNode<Integer>>> list) {
		if(this.bt.root == null)
			return null;
		pathSums1((BTNode<Integer>)this.bt.root,sum,0,list,new ArrayList<BTNode<Integer>>());
		return list;
	}
	public void pathSums1(BTNode<Integer> curr,int sum,int level,List<List<BTNode<Integer>>> fin,List<BTNode<Integer>> temp) {
		if(curr == null)
			return;
		temp.add(curr);
		int tempSum = 0;
		for(int i=level;i>=0;--i) {
			tempSum += temp.get(i).data;
			if(tempSum == sum) {
				List<BTNode<Integer>> t = new ArrayList<BTNode<Integer>>();
				for(int j=i;j<=level;++j)
					t.add(temp.get(j));
				fin.add(t);
			}
		}
		pathSums1(curr.left,sum,level+1,fin,temp);
		pathSums1(curr.right,sum,level+1,fin,temp);
		temp.remove(level);
	}

	//Given a binary tree and a number, return true if the tree has a root-to-leaf path such that adding up all the values 
	//along the path equals the given number. Return false if no such path can be found.
	public boolean hasPath(BTNode<Integer> node,int sum){
		if(node == null)
			return false;
		return hasPath(node,0,sum);

	}

	public boolean hasPath(BTNode<Integer> node,int currSum,int sum){
		if(node == null)
			return false;
		currSum += node.data;
		if(node.left==null && node.right== null && currSum==sum)
			return true;
		return hasPath(node.left,currSum,sum) || hasPath(node.right,currSum,sum);
	}

	/*
	 Construct Tree from given Inorder and Preorder traversals
	 */
	//see below
	static int preIndex = 0;
	public BTNode<T> constructTreeInPre(T[] in,T[] pre,int start,int end){
		if(start > end)
			return null;
		T dat = pre[preIndex];
		BTNode<T> node = new BTNode<T>(dat);
		if(start == end)
			return node;
		int inIndex = 0;
		for(int i=0;i<in.length;++i){
			if(in[i].equals(dat)){
				inIndex = i;
				break;
			}
		}
		node.left = constructTreeInPre(in,pre,start,inIndex-1);
		node.right = constructTreeInPre(in,pre,inIndex+1,end);
		return node;
	}

	//USE THIS
	public BTNode<T> constructTreeInPre(T[] in,T[] pre){
		if(pre == null || in== null)
			return null;
		return constructTreeInPreHelper(0,0, in.length-1, in, pre);
	}
	public BTNode<T> constructTreeInPreHelper(int preStart,int inStart,int inEnd,T[] in, T[] pre){
		if(preStart > pre.length-1 || inStart>inEnd)
			return null;
		BTNode<T> node = new BTNode<T>(pre[preStart]);
		int inIndex=0;
		for(int i=inStart;i<=inEnd;++i){
			if(in[i]==pre[preStart]){
				inIndex=i;
				break;
			}
		}
		node.left = constructTreeInPreHelper(preStart+1,inStart,inIndex-1,in,pre);
		node.right = constructTreeInPreHelper(preStart+1+inIndex-inStart,inIndex+1,inEnd,in,pre);
		return node;
	}

	//construct BT from in and post
	public BTNode<T> constructTreeInPost(T[] post,T[] in){
		if(post == null || in==null)
			return null;
		return constructTreeInPostHelper(post,in,post.length-1,0,in.length-1);
	}
	public BTNode<T> constructTreeInPostHelper(T[] post,T[] in,int postSt, int inSt,int inEnd){
		if(postSt<0 || inEnd<inSt)
			return null;
		BTNode<T> node = new BTNode<T>(post[postSt]);
		int inInd=0;
		for(int i=inSt;i<=inEnd;++i){
			if(in[i]==post[postSt]){
				inInd=i;
				break;
			}
		}
		node.left = constructTreeInPostHelper(post,in,postSt-1-(inEnd-inInd),inSt,inInd-1);
		node.right = constructTreeInPostHelper(post,in,postSt-1,inInd+1,inEnd);
		return node;
	}

	// Print out the level order traversal of a Binary Tree, with new line after each level.
	public void printLevel(BTNode<T> node) throws QueueEmptyException {
		QueueLL<BTNode<T>> q = new QueueLL<BTNode<T>>();
		int currSize = 1, nextSize = 0;
		q.enqueue(node);
		while(!q.isEmpty()) {
			while(currSize > 0) {
				BTNode<T> curr = q.dequeue();
				--currSize;
				System.out.print(curr.data + " ");
				if(curr.left != null) {
					q.enqueue(curr.left);
					++nextSize;
				}
				if(curr.right != null) {
					q.enqueue(curr.right);
					++nextSize;
				}
			}
			System.out.println();
			currSize = nextSize;
			nextSize = 0;
		}
	}

	//Write an algorithm that determines whether or not two binary trees are equivalent.
	public boolean isEqual(BTNode<T> node1, BTNode<T> node2) {
		if(node1 == null && node2 == null)
			return true;
		if(node1 != null && node2 != null) {
			if(node1.data != node2.data)
				return false;
			return (isEqual(node1.left,node2.left) && isEqual(node1.right,node2.right));
		}
		return false;
	}

	/*
	 * Given a binary tree where all the right nodes are leaf nodes, flip it upside down and turn it into a tree with left leaf nodes. 

	Keep in mind: ALL RIGHT NODES IN ORIGINAL TREE ARE LEAF NODE.

	/* for example, turn these:
	 *
	 *        1                1
	 *       / \              / \
	 *      2   3            2   3
	 *     / \
	 *    4   5
	 *   / \
	 *  6   7
	 *
	 into these:
	 * where 6 is the new root node for the left tree, and 2 for the right tree.
	 * oriented correctly:
	 *
	 *     6                  2
	 *    / \                / \
	 *   7   4              3   1
	 *      / \
	 *     5   2
	 *        / \
	 *       3   1
	 */
	public BTNode<T> flipTree(BTNode<T> root )
	{
		if (root == null)
			return null;

		// Working base condition
		if( root.left == null && root.right == null) 
		{
			return root;
		}

		BTNode<T> newRoot = flipTree(root.left);

		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}

	// find min value in BT
	public int min(BTNode<Integer> node){
		if(node==null)
			return Integer.MAX_VALUE;
		return minHelper(node,Integer.MAX_VALUE);
	}

	public int minHelper(BTNode<Integer> node,int min){
		if(node==null)
			return Integer.MAX_VALUE;
		int currMin = Math.min(node.data,min);
		return Math.min(currMin,Math.min(minHelper(node.left,currMin),minHelper(node.right,currMin)));
	}

	/* Find the distance between two keys in a binary tree, no parent pointers are given. 
	  Distance between two nodes is the minimum number of edges to be traversed to reach one node from other, not including the 2 nodes themselves.
	 */
	private class MyWrapper {
		BTNode<T> lca;
		void setLca(BTNode<T> lca){
			this.lca = lca;
		}
		boolean isNull(){
			return this.lca == null;
		}
	}
	public int findMinDist(BTNode<T> node,BTNode<T> n1,BTNode<T> n2){

		MyWrapper wrap = new MyWrapper();
		return findMinDistHelper(node,n1,n2,wrap);
	}
	public int findMinDistHelper(BTNode<T> node,BTNode<T> n1,BTNode<T> n2,MyWrapper wrap){
		if(node==null)
			return 0;
		if(n1==n2)
			return 0;
		boolean found = false;
		if(node==n1 || node==n2)
			found = true;
		int left = findMinDistHelper(node.left,n1,n2,wrap);
		int right = findMinDistHelper(node.right,n1,n2,wrap);
		if(found){
			if(left>0){
				wrap.setLca(node);
				return left-1;
			}
			if(right>0){
				wrap.setLca(node);
				return right-1;
			}
			return 1;
		} else {
			if(left>0 && right>0){
				wrap.setLca(node);
				return left+right-1;
			}
			if(left>0){
				if(wrap.isNull())
					return left+1;
				else
					return left;
			}
			if(right>0){
				if(wrap.isNull())
					return right+1;
				else
					return right;
			}
			return 0;
		}
	}
	
	//find k-th minimum in BT
	public int findKthSmallest(BTNode<Integer> node,int k){
		if(node==null || k<0)
			return Integer.MIN_VALUE;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		findKthSmallestHelper(node,pq,k);
		return pq.peek();
	}
	private void findKthSmallestHelper(BTNode<Integer> node,PriorityQueue<Integer> pq,int k){
		if(node==null)
			return;
		findKthSmallestHelper(node.left,pq,k);
		pq.offer(node.data);
		if(pq.size()>k)
			pq.remove();
		findKthSmallestHelper(node.right,pq,k);
	}
}

