// DOESNT GIVE CORRECT SOLUTION
package LinkedLists;

public class NestedList {
	class Node {
		int data;
		Node next;
		Node child;
		Node(int data){
			this.data = data;
		}
	}
	Node head;
	public static void main(String[] args){
		NestedList list = new NestedList();
		Node n1 = list.new Node(1);
		Node n2 = list.new Node(2);
		Node n3 = list.new Node(3);
		Node n4 = list.new Node(4);
		Node n5 = list.new Node(5);
		Node n6 = list.new Node(6);
		Node n7 = list.new Node(7);
		Node n8 = list.new Node(8);
		Node n9 = list.new Node(9);
		Node n10 = list.new Node(10);
		Node n11 = list.new Node(11);
		Node n12 = list.new Node(12);
		Node n13 = list.new Node(13);
		
		n8.child = n9;
		n8.next = n10;
		n7.next = n8;
		n12.child=n13;
		n5.child=n12;
		n5.next = n11;
		n4.child=n6;
		n4.next=n5;
		n3.child=n4;
		n3.next=n7;
		n2.next=n3;
		n1.next=n2;
		list.head=n1;
		
		Node node= list.flatten(list.head);
		list.print(list.head);
	}
	
	public Node flatten(Node node){
		//Node curr = node;
		if(node == null)
			return null;
		if(node.next == null && node.child == null)
			return node;
		while(node != null){
			if(node.child!=null){
				Node temp = node.next;
				node.next = node.child;
				Node tail = flatten(node.child);
				tail.next = temp;
				node = temp;
			}
			else
				node = node.next;
		}
		return node;
	}
	
	public void print(Node node){
		while(node != null){
			System.out.println(node.data);
			node = node.next;
		}
	}
}
