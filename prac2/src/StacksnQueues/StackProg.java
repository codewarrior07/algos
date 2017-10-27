package StacksnQueues;

import LinkedLists.SinglyLinkedList;

public class StackProg {
	public static void main(String[] args) throws StackEmptyException,StackFullException {
		/*StackLL<Integer> st = new StackLL<Integer>();
		st.push(12);
		st.push(2);
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());*/
		
		BracketChecker br = new BracketChecker();
		System.out.println(br.isExprValid("a{b(c]d}e"));
	}
}

class BracketChecker {
	StackLL<Character> st;
	public BracketChecker(){
		st = new StackLL<Character>();
	}
	public boolean isExprValid(String expr) throws StackEmptyException {
		for(int i=0;i<expr.length();++i) {
			if(expr.charAt(i) == '{' || expr.charAt(i) == '[' || expr.charAt(i) == '(') {
				st.push(expr.charAt(i));
			}
			else if(expr.charAt(i) == '}' || expr.charAt(i) == ']' || expr.charAt(i) == ')') {
				char temp = st.pop();
				if(!(expr.charAt(i) == counterParen(temp)))
						return false;
			}
		}
		if(!st.isEmpty())
			return false;
		return true;
	}
	public char counterParen(char inp) {
		switch(inp) {
		case '{': 
			return '}';
		case '[': 
			return ']';
		case '(':
			return ')';
		default:
			return '0';
		}
	}
}

class Stack {
	int maxSize;
	int arr[];
	int top;
	public Stack(int maxSize) {
		this.maxSize = maxSize;
		arr = new int[maxSize];
		top = 0;
	}
	
	public boolean isFull() {
		return (top == maxSize -1);
	}
	
	public boolean isEmpty() {
		return top==0;
	}
	
	public int pop() throws StackEmptyException {
		if(isEmpty())
			throw new StackEmptyException("Stack empty");
		return arr[top--];
	}
	
	public void push(int data) throws StackFullException {
		if(isFull())
			throw new StackFullException("Stack Full");
		arr[top++] = data;
	}
}

