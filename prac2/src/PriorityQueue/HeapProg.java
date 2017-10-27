package PriorityQueue;

import java.util.ArrayList;

public class HeapProg {
	public static void main(String[] args) {
		MaxHeap<Integer> heap = new MaxHeap<Integer>();
		heap.insert(10);
		heap.insert(1);
		heap.insert(20);
		heap.insert(4);
		heap.insert(3);
		heap.insert(2);
		heap.display();
		System.out.println(heapSort(heap));
	}
	public static ArrayList<Integer> heapSort(MaxHeap<Integer> heap) {
		// make strongly ordered max heap
		for(int i=heap.getCurrSize()-1; i>=0;--i) {
			int temp = heap.removeMax();
			heap.insertAt(i, temp);
		}
		return heap.getArray();
	}
}

class MinHeap<T extends Comparable<T>> {
	ArrayList<T> arr;
	int currSize;
	public MinHeap() {
		arr = new ArrayList<T>();
		currSize = 0;
	}
	public boolean isEmpty() {
		return arr.size() == 0;
	}
	public int getCurrSize() {
		return currSize;
	}
	public void insert(T data) {
		arr.add(data);
		trickleUp(getCurrSize()-1);
	}
	public void insertAt(int index, T val) {
		arr.add(index, val);
	}
	public ArrayList<T> getArray() {
		return arr;
	}
	public T removeMin() {
		T temp = arr.get(0);
		arr.set(0, arr.get(getCurrSize()-1));
		trickleDown(0);
		return temp;
	}
	public void change(int index, int newVal) {
		
	}
	
	public void trickleUp(int index) {
		T bottom = arr.get(index);
		while(index>0) {
			int parent = (index-1)/2;
			if(arr.get(parent).compareTo(bottom) < 0)
				break;
			arr.set(index, arr.get(parent));
			index = parent;
		}
		arr.set(index, bottom);
	}
	public void trickleDown(int index) {
		T top = arr.get(index);
		while(index < getCurrSize()/2) {
			int lc = 2*index + 1;
			int rc = 2*index + 2;
			int smallerChild;
			if(rc < getCurrSize() && arr.get(rc).compareTo(arr.get(lc)) < 0) 
				smallerChild = rc;
			else
				smallerChild = lc;
			if(top.compareTo(arr.get(smallerChild)) < 0)
				break;
			arr.set(index, arr.get(smallerChild));
			index = smallerChild;
		}
		arr.set(index, top);
	}
	public void display() {
		for(T i:arr) {
			System.out.println(i);
		}
	}
}

class MaxHeap<T extends Comparable<T>> {
	ArrayList<T> arr;
	int currSize;
	public MaxHeap() {
		arr = new ArrayList<T>();
		currSize = 0;
	}
	public boolean isEmpty() {
		return arr.size() == 0;
	}
	public int getCurrSize() {
		return currSize;
	}
	public void insert(T data) {
		arr.add(data);
		trickleUp(currSize++);
	}
	public void insertAt(int index, T val) {
		arr.set(index, val);
	}
	public ArrayList<T> getArray() {
		return arr;
	}
	public T removeMax() {
		T temp = arr.get(0);
		arr.set(0, arr.get(--currSize));
		trickleDown(0);
		return temp;
	}
	public void change(int index, int newVal) {
		
	}
	
	public void trickleUp(int index) {
		T bottom = arr.get(index);
		while(index>0) {
			int parent = (index-1)/2;
			if(arr.get(parent).compareTo(bottom) > 0)
				break;
			arr.set(index, arr.get(parent));
			index = parent;
		}
		arr.set(index, bottom);
	}
	public void trickleDown(int index) {
		T top = arr.get(index);
		while(index < getCurrSize()/2) {
			int lc = 2*index + 1;
			int rc = 2*index + 2;
			int largerChild;
			if(rc < getCurrSize() && arr.get(rc).compareTo(arr.get(lc)) > 0) 
				largerChild = rc;
			else
				largerChild = lc;
			if(top.compareTo(arr.get(largerChild)) > 0)
				break;
			arr.set(index, arr.get(largerChild));
			index = largerChild;
		}
		arr.set(index, top);
	}
	public void display() {
		for(T i:arr) {
			System.out.println(i);
		}
	}
}

class PriorityQueueHeap<T extends Comparable<T>> {
	MaxHeap<T> heap;
	public PriorityQueueHeap() {
		heap = new MaxHeap<T>();
	}
	public void insert(T data) {
		heap.insert(data);
	}
	public T remove() {
		return heap.removeMax();
	}
}

