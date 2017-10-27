package PriorityQueue;

import java.lang.reflect.Array;

public class PriorityQueueProg {
	public static void main(String[] args){
		PQCircQueueArray pq = new PQCircQueueArray(Integer.class,5);
		pq.insert(5);
		pq.insert(1);
		pq.display();
	}
}

class PQCircQueueArray<T extends Comparable> {
	T[] arr;
	int head,tail=0;
	int currSize=0,maxSize=0;
	@SuppressWarnings("unchecked")
	public PQCircQueueArray(Class<T> c,int maxSize) {
		arr = (T[]) Array.newInstance(c, maxSize);
		this.maxSize = maxSize;
	}
	public boolean isEmpty() {
		return currSize==0;
	}
	public boolean isFull() {
		return currSize==maxSize;
	}
	public T remove() {
		if(isEmpty())
			return null;
		return arr[head++];
	}
	public T getMax() {
		if(isEmpty())
			return null;
		if(tail == 0)
			return arr[maxSize-1];
		return arr[tail];
	}
	public T getMin() {
		if(isEmpty())
			return null;
		return arr[head];
	}
	public void display() {
		int itr = currSize-1;
		for(int start = head;itr>=0;--itr) {
			System.out.println(arr[start]);
			if(start == maxSize-1)
				start = 0;
			else
				++start;
		}
		System.out.println("------");
		System.out.println("head " + head);
		System.out.println("tail " + tail);
		System.out.println("currSize " + currSize);
	}
	public void insert(T data) {
		if(isFull())
			return;
		if(isEmpty())
			arr[tail++] = data;
		else {
			int itr = currSize-1;
			int curr = tail==0 ? maxSize-1 : tail-1;
			while(itr>=0){
				//System.out.println(data.compareTo(arr[curr]) + " comp");
				if(data.compareTo(arr[curr]) < 0) {
					if(curr == maxSize-1) {
						arr[0] = arr[curr--];
					} else {
						arr[curr+1] = arr[curr];
						if(curr == 0)
							curr = maxSize-1;
						else
							--curr;
					}
					--itr;
				} else {
					arr[curr+1] = data;
					break;
				}
			}
		}
		++currSize;
	}
}
