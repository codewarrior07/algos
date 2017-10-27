package naryTree;

import java.util.ArrayList;
import java.util.List;

public class NaryNode {
	int value;
	List<NaryNode> children;
 
		public NaryNode(int val){
		value = val;
		children = new ArrayList<NaryNode>();
	}
 
	static boolean isUnival(NaryNode tree, Counter counter){
		if (tree == null) return false;
		boolean totalUnivalence = true;
		boolean localUnivalence = true;
 
		if (tree.children.isEmpty()){
			return true;
		}
 
		for ( NaryNode child : tree.children) {
			boolean uniVal = isUnival(child, counter);
			if (uniVal) {
				if(!child.children.isEmpty()){
					counter.add(1);
				}
			}
			totalUnivalence = totalUnivalence && uniVal ;
			if (child.value != tree.value) {
				localUnivalence = false;
			}
		}
		return totalUnivalence && localUnivalence;
	}
	public Counter getCounter() {
		return new Counter();
	}
 
 
	class Counter {
		int subtrees;
		public  Counter() {
			subtrees = 0;
		}
		public void add(int val) {
			subtrees+=val;
		}
	}
	public static void main(String[] args) {
 
		/*NaryNode treeRoot = new NaryNode(7);
 
		treeRoot.children.add(new NaryNode(7));
		NaryNode firstChild = treeRoot.children.get(0);
		firstChild.children.add(new NaryNode(7));
		firstChild.children.add(new NaryNode(7));
		firstChild.children.add(new NaryNode(7));
		firstChild.children.add(new NaryNode(7));
 
 
		treeRoot.children.add(new NaryNode(6));
		treeRoot.children.add(new NaryNode(7));
		NaryNode thirdChild = treeRoot.children.get(2);
		thirdChild.children.add(new NaryNode(7));
		thirdChild.children.add(new NaryNode(7));
		thirdChild.children.get(0).children.add(new NaryNode(7));*/
		 
		
		NaryNode treeRoot = new NaryNode(7);
		treeRoot.children.add(new NaryNode(7));
		treeRoot.children.add(new NaryNode(7));
		treeRoot.children.add(new NaryNode(7));
		NaryNode firstChild = treeRoot.children.get(0);
		firstChild.children.add(new NaryNode(7));
		Counter counter = treeRoot.getCounter();
		System.out.println(isUnival(treeRoot, counter));
 
		System.out.println(counter.subtrees);
	}
}
