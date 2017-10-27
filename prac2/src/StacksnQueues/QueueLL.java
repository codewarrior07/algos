package StacksnQueues;

import java.lang.reflect.Array;

import LinkedLists.SinglyLinkedDoubleList;

public class QueueLL<T> {
	SinglyLinkedDoubleList<T> queue;
	public QueueLL() {
		queue = new SinglyLinkedDoubleList<T>();
	}
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	public T peek() {
		return queue.first.data;
	}
	public void enqueue(T data) {
		queue.insertLast(data);
	}
	public T dequeue() throws QueueEmptyException {
		if(queue.isEmpty())
			throw new QueueEmptyException();
		return queue.deleteFirst().data;
		
	}
	public static void main(String[] args) throws QueueEmptyException,QueueFullException{
		/*QueueLL<String> q = new QueueLL<String>();
		q.enqueue("asd");
		q.enqueue("a");
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		q.enqueue("a");
		System.out.println(q.dequeue());*/
		
		Queue<Integer> q = new Queue<Integer>(Integer.class,5);
		q.enqueue(10);
		q.enqueue(1);
		q.dequeue();
		q.dequeue();
		q.enqueue(15);
		q.enqueue(25);
		q.enqueue(35);
		q.enqueue(45);
		q.enqueue(55);
		q.dequeue();
		q.enqueue(15);
		q.dequeue();
		q.display();
	}
}

class Queue<T> {
	T[] arr;
	int maxSize;
	int tail,head;
	int currSize;
	@SuppressWarnings("unchecked")
	public Queue(Class<T> c, int maxSize) {
		this.maxSize = maxSize;
		arr = (T[]) Array.newInstance(c, maxSize);
		tail=head=currSize=0;
	}
	public boolean isFull() {
		return currSize==maxSize;
	}
	public boolean isEmpty() {
		return currSize==0;
	}
	public void enqueue(T data) throws QueueFullException{
		if(!isFull()){
			arr[tail]=data;
			++currSize;
			tail = tail==maxSize-1? 0 : ++tail;
		}
		else
			throw new QueueFullException();
	}
	public T dequeue() throws QueueEmptyException{
		if(isEmpty())
			throw new QueueEmptyException();
		T temp = arr[head];
		--currSize;
		head = head==maxSize-1? 0 : ++head;
		return temp; 
	}
	public void display(){
		if(!isEmpty()){
			int iter = head;
			while(true) {
				System.out.println(arr[iter]);
				iter = iter==maxSize-1? 0 : ++iter;
				if(iter == tail)
					break;
			}
			/*for(int i=0;i<maxSize;++i)
				System.out.println(arr[i]);*/
			System.out.println("-----");
			System.out.println("head "+head);
			System.out.println("tail "+tail);
			System.out.println("currSize "+currSize);
		}
	}
}



class QueueFullException extends Exception {
	String msg;
	public QueueFullException(){
		this.msg = "Full queue";
	}
	public QueueFullException(String msg){
		this.msg = msg;
	}
	public String toString() {
		return this.msg;
	}
}