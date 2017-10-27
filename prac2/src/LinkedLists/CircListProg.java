package LinkedLists;

public class CircListProg {
	public static void main(String[] args){
		CircLinkedList<Integer> cll = new CircLinkedList<Integer>();
		cll.insert(10);
		cll.insert(2);
		cll.insert(3);
		cll.insert(4);
		cll.display();
		cll.remove(cll.find(3));
		cll.display();
	}
}

class CircLinkedList<T> {
	SNode last;
	public CircLinkedList() {
		last = null;
	}
	
	public boolean isEmpty(){
		return last == null;
	}
	
	public void insert(T dat) {
		SNode nn = new SNode(dat);
		if(isEmpty()){
			last = nn;
			last.next = last;
		} else {
			nn.next = last.next;
			last.next = nn;
			last = nn;
		}
	}
	
	public void remove(SNode rem){
		if(isEmpty())
			return;
		if(last == rem && last.next == last) {
			last = null;
			return;
		}
		SNode curr = last.next;
		SNode prev = last;
		while(curr != last && curr != rem){
			prev = curr;
			curr = curr.next;
		}
		if(curr == last && curr == rem){
			prev.next = last.next;
			last = prev;
		}
		else
			prev.next = curr.next;
	}
	
	public SNode find(T dat) {
		if (isEmpty()) 
			return null;
		if(last.data == dat)
			return last;
		SNode curr = last.next;
		while(curr != last && curr.data != dat)
			curr = curr.next;
		if(curr == last) {
			if(curr.data == dat)
				return last;
			else 
				return null;
		}
		return curr;
		//return (curr==last?(curr.data==dat?last:null):curr);
	}
	
	public void display() {
		SNode curr = last.next;
		do {
			System.out.println(curr.data);
			curr = curr.next;
		} while(curr != last.next);
		System.out.println("-------");
	}
}




