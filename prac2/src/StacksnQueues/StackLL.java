package StacksnQueues;

import LinkedLists.SinglyLinkedList;

public class StackLL<T> {
	SinglyLinkedList<T> stack;
	public StackLL(){
		stack = new SinglyLinkedList<T>();
	}
	public T pop() throws StackEmptyException{
		if(stack.isEmpty())
			throw new StackEmptyException("Stack Empty");
		return stack.deleteFirst().data;
	}
	public void push(T data) {
		stack.insertFirst(data);
	}
	public void display() {
		stack.display();
	}
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	public T peek() {
		return stack.first.data;
	}
	public boolean contains(T dat) {
		if(stack.find(dat) != null)
			return true;
		return false;
	}
}