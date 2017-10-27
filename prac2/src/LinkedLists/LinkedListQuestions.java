package LinkedLists;

import StacksnQueues.StackEmptyException;
import StacksnQueues.StackLL;

public class LinkedListQuestions {
	public static void main(String[] args) throws StackEmptyException {
		LLQ<Integer> llq = new LLQ<Integer>();
		llq.sll.insertFirst(10);
		llq.sll.insertFirst(40);
		llq.sll.insertFirst(53);
		llq.sll.insertFirst(30);
		llq.sll.insertFirst(67);
		llq.sll.insertFirst(12);
		llq.sll.insertFirst(89);
		//llq.partition(4);
		//llq.sll.display();
		/*SinglyLinkedList<Integer> sl1 = new SinglyLinkedList<Integer>();
		sl1.insertFirst(9);
		sl1.insertFirst(9);
		sl1.insertFirst(9);
		SinglyLinkedList<Integer> sl2 = new SinglyLinkedList<Integer>();
		sl2.insertFirst(2);
		sl2.insertFirst(4);
		for(Integer n:llq.addLists2(sl1,sl2))
			System.out.println(n);*/
		//System.out.println("--------");
		//llq.reverse();
		//System.out.println(llq.lastNode().data);
		//System.out.println(llq.isPalindrome());
		SNode<Integer> node=llq.sortAlternateList(llq.sll.first);
		System.out.println("asd");
	}
	
}

class LLQ<T extends Comparable<T>> {
	public SinglyLinkedList<T> sll;
	public LLQ() {
		this.sll = new SinglyLinkedList<T>();
	}
	/*
	 Write code to partition a linked list around a value x, such that all nodes less than x
	come before alt nodes greater than or equal to x.
	 */
	public void partition(T num) {
		SNode<T> i = sll.first;
		SNode<T> j = sll.first;
		while(j!=null) {
			if(j.data.compareTo(num) <=0) {
				T temp = i.data;
				i.data = j.data;
				j.data = temp;
				i = i.next;
			}
			j = j.next;
		}
	}
	/*
	 You have two numbers represented by a linked list, where each node contains a
	single digit. The digits are stored in reverse order, such that the 1 's digit is at the head
	of the list. Write a function that adds the two numbers and returns the sum as a
	linked list.
	9 6 1
	7 4
	 */
	public SinglyLinkedList<Integer> addLists(SinglyLinkedList<Integer> l1,SinglyLinkedList<Integer> l2) {
		SinglyLinkedList<Integer> sum = new SinglyLinkedList<Integer>();
		int total = 0;
		SNode<Integer> n1 = l1.first;
		SNode<Integer> n2 = l2.first;
		int carry = 0, remain = 0,digit=1;
		while(n1 != null && n2 != null) {
			int tempSum = n1.data + n2.data + carry;
			carry = tempSum / 10;
			remain = tempSum % 10;
			sum.insertFirst(remain);
			n1 = n1.next;
			n2 = n2.next;
		}
		while(n1 != null) {
			int tempSum = carry + n1.data;
			carry = tempSum/10;
			remain = tempSum % 10;
			sum.insertFirst(remain);
			n1 = n1.next;
		}
		while(n2 != null) {
			int tempSum = carry + n2.data;
			carry = tempSum/10;
			remain = tempSum % 10;
			sum.insertFirst(remain);
			n2 = n2.next;
		}
		if(carry > 0)
			sum.insertFirst(carry);
		return sum;
	}
	/*
	 You have two numbers represented by a linked list, where each node contains a
	single digit. The digits are stored in reverse order, such that the 1 's digit is at the head
	of the list. Write a function that adds the two numbers and returns the sum as a
	linked list.
	9 6 1
	7 4
	 */
	public SinglyLinkedList<Integer> addLists1(SinglyLinkedList<Integer> l1,SinglyLinkedList<Integer> l2) {
		SinglyLinkedList<Integer> sum = new SinglyLinkedList<Integer>();
		SNode<Integer> n1 = l1.first;
		SNode<Integer> n2 = l2.first;
		int total = 0,digit=1;
		while(n1 != null && n2 != null) {
			int tempSum = (n1.data + n2.data)*digit;
			total += tempSum;
			digit *= 10;
			n1 = n1.next;
			n2 = n2.next;
		}
		while(n1 != null) {
			int tempSum = (n1.data)*digit;
			total += tempSum;
			digit *= 10;
			n1 = n1.next;
		}
		while(n2 != null) {
			int tempSum = (n2.data)*digit;
			total += tempSum;
			digit *= 10;
			n1 = n1.next;
		}
		digit = digit/10;
		while(total > 0) {
			sum.insertFirst(total/digit);
			total %= digit;
			digit = digit/10;
		}
		return sum;
	}
	/*
	 You have two numbers represented by a linked list, where each node contains a
	single digit. The digits are stored in normal order, such that the 1 's digit is at the last
	of the list. Write a function that adds the two numbers and returns the sum as a
	linked list.
	9 6 1
	7 4
	 */
	public SinglyLinkedList<Integer> addLists2(SinglyLinkedList<Integer> l1,SinglyLinkedList<Integer> l2) {
		SinglyLinkedList<Integer> sum = new SinglyLinkedList<Integer>();
		SNode<Integer> n1 = l1.first;
		SNode<Integer> n2 = l2.first;
		int num1 = 0, num2 = 0;
		int digit = 10;
		while(n1 != null){
			num1 = n1.data + num1*digit;		
			n1 = n1.next;
		}
		while(n2 != null){
			num2 = n2.data + num2*digit;
			n2 = n2.next;
		}
		int total = num1 + num2;
		while(total > 0) {
			sum.insertFirst(total%10);
			total /= 10;
		}
		return sum;
	}
	/*
	Given a circular linked list, implement an algorithm which returns the node at the
	beginning of the loop.
	 */
	public SNode<T> collision(){
		SNode<T> tort = sll.first;
		SNode<T> hare = sll.first;
		while(hare != null && hare.next != null){
			tort = tort.next;
			hare = hare.next.next;
			if(tort == hare)
				break;
		}
		if(hare == null || hare.next == null)
			return null;
		hare = sll.first;
		while(tort != hare) {
			tort = tort.next;
			hare = hare.next;
		}
		return tort;
	}
	/*
	 Reverse a linked list
	 */
	public SinglyLinkedList<T> reverse() {
		SNode<T> curr = sll.first;
		SNode<T> prev = null;
		while(curr != null) {
			SNode<T> temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		sll.first = prev;
		return sll;
	}
	/*
	 * find last node
	 */
	public SNode<T> lastNode(){
		SNode<T> curr = sll.first;
		while(curr != null && curr.next != null) {
			if(curr.next.next == null){
				curr = curr.next;
				break;
			}
			curr = curr.next.next;
		}
		return curr;
	}
	/*
	 Implement a function to check if a linked list is a palindrome
	 */
	public boolean isPalindrome() throws StackEmptyException {
		StackLL<T> st = new StackLL<T>();
		SNode<T> hare = sll.first;
		SNode<T> tort = sll.first;
		while(hare != null && hare.next != null) {
			st.push(tort.data);
			tort = tort.next;
			hare = hare.next.next;
		}
		if(hare != null)
			tort = tort.next;
		boolean flag = true;
		while(tort != null) {
			if(!st.pop().equals(tort.data)) {
				flag = false;
				break;
			}
			tort = tort.next;
		}
		return flag;
	}
	/*
	 Given a singly linked list L: L0→L1→ ... →Ln-1→Ln,
	reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→...
	For example, given {1,2,3,4}, reorder it to {1,4,2,3}. You must do this in-place without altering the nodes' values.
	 */
	public void reorderList(SNode<T> head) {
		 
		if (head != null && head.next != null) {
 
			SNode<T> slow = head;
			SNode<T> fast = head;
 
			//use a fast and slow pointer to break the link to two parts.
			while (fast != null && fast.next != null && fast.next.next!= null) {
				//why need third/second condition?
				System.out.println("pre "+slow.data + " " + fast.data);
				slow = slow.next;
				fast = fast.next.next;
				System.out.println("after " + slow.data + " " + fast.data);
			}
 
			SNode<T> second = slow.next;
			slow.next = null;// need to close first part
 
			// now should have two lists: head and fast
 
			// reverse order for second part
			second = reverseOrder(second);
 
			SNode<T> p1 = head;
			SNode<T> p2 = second;
 
			//merge two lists here
			while (p2 != null) {
				SNode<T> temp1 = p1.next;
				SNode<T> temp2 = p2.next;
 
				p1.next = p2;
				p2.next = temp1;		
 
				p1 = temp1;
				p2 = temp2;
			}
		}
	}
 
	public SNode<T> reverseOrder(SNode<T> head) {
 
		if (head == null || head.next == null) {
			return head;
		}
 
		SNode<T> pre = head;
		SNode<T> curr = head.next;
 
		while (curr != null) {
			SNode<T> temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
		}
 
		// set head node's next
		head.next = null;
 
		return pre;
	}
	
	/*
	 Given a Linked List. The Linked List is in alternating ascending and descending orders. Sort the list efficiently.
	Example:
	Input List:   10->40->53->30->67->12->89->NULL
	Output List:  10->12->30->43->53->67->89->NULL
	 */
	// doesnt work atm
	public SNode<Integer> sortAlternateList(SNode<Integer> node){
		if(node == null)
			return null;
		SNode<Integer> head1=new SNode<Integer>(0);
		SNode<Integer> head2=new SNode<Integer>(0);
		SNode<Integer> node1 = head1;
		SNode<Integer> node2 = head2;
		SNode<Integer> curr = node;
		//separate node1=asc, node2=descending
		while(curr != null) {
			node1.next = curr;
			node1 = node1.next;
			curr = curr.next;
			if(curr != null) {
				node2.next = curr;
				node2 = node2.next;
				curr = curr.next;
			}
		}
		head1=head1.next;
		head2=head2.next;
		
		//reverse node2 list
		SNode<Integer> prev=null;
		while(node2!=null){
			SNode<Integer> temp = node2.next;
			node2.next = prev;
			prev=node2;
			node2=temp;
		}
		node2=prev;
		
		//merge node and node2 in sorted order
		SNode<Integer> fakeHead = new SNode<Integer>(0);
		SNode<Integer> itr = fakeHead;
		while(node1!=null && node2!= null){
			if(node1.data<node2.data){
				itr.next = node1;
				node1=node1.next;
			}else{
				itr.next = node2;
				node2=node2.next;
			}
			itr=itr.next;
		}
		if(node1!=null)
			itr.next=node1;
		if(node2!=null)
			itr.next=node2;
		fakeHead=fakeHead.next;
		return fakeHead;
	}
	
	//pairwise swap of nodes
	// input: 1->2->3->4 o/p: 2->1->4->3
	//recursive
	public SNode<T> pairwiseSwap(SNode<T> node){
		if(node==null || node.next==null)
            return node;
		SNode<T> temp = node.next.next;
		SNode<T> newHead = node.next;
        node.next.next = node;
        node.next = pairwiseSwap(temp);
        return newHead;
	}
	//iterative
	
	
}
