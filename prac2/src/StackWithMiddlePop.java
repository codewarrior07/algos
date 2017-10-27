public class StackWithMiddlePop<T> {
	Node<T> head;
	Node<T> middle;
	int size = 0;
	private class Node<T> {
		T data;
		Node<T> next;
		Node<T> prev;
		public Node(T data){
			this.data = data;
		} 
	}   
	public void push(T data) {
		Node<T> n = new Node<T>(data);
		if(head == null) {
			head = n;
			middle = head;
			return;
		}
		n.next = head;
		head.prev = n;
		head = n;
		++size;
		if((size & 1) == 0)
			middle = middle.prev;
	}
	public T pop() {
		if(isEmpty())
			return null;
		Node<T> temp = head;
		head = head.next;
		head.prev = null;
		--size;
		if((size & 1) == 1)
			middle = middle.next;
		return temp.data;
	}
	public T popMiddle() {
		if(isEmpty())
			return null;
		Node<T> temp = middle;
		if(middle == head){
			head = null;
		}
		else if(middle.next == null){ // last element
			head.next = null;
			middle = head;
		}
		else {
			Node<T> newMid = middle.next;
			middle.next.prev = middle.prev;
			middle.prev.next = middle.next;
			middle = newMid;
		}
		return temp.data;
	}
	public T peekMiddle() {
		if(isEmpty())
			return null;
		return middle.data;
	}
	public T peekHead() {
		if(isEmpty())
			return null;
		return head.data;
	}
	public boolean isEmpty(){
		return head == null;
	}
	public static void main(String[] args) {
		StackWithMiddlePop<Integer> st = new StackWithMiddlePop<Integer>();
		st.push(1);
		st.push(5);
		st.push(7);
		st.push(8);
		//st.push(11);
		System.out.println(st.pop());
		System.out.println(st.pop());
		//System.out.println(st.pop());
		System.out.println("-------");
		/*System.out.println(st.peekMiddle());
		System.out.println(st.peekHead());*/
		System.out.println(st.popMiddle());
		
		System.out.println(st.peekHead());
	}
}
