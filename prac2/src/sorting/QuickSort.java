package sorting;

import java.util.concurrent.DelayQueue;

public class QuickSort {
	static int[] arr = {1,2,3,4,5};
	public static void main(String[] args) {
		//partition(0,4,4);
		//quickSort(0,arr.length-1);
		quickSortWithMedian(0,4);
		for(Integer i:arr)
			System.out.println(i);
	}
	public static void quickSort(int start,int end) {
		if(start < end) {
			int pivotIndex = partition(start,end);
			quickSort(start,pivotIndex-1);
			quickSort(pivotIndex+1,end);
		}
	}
	public static int partition(int start, int end) {
		int pivot = arr[end];
		int i = start-1;
		for(int j=start;j<end;++j) {
			if(arr[j] <= pivot) {
				int temp = arr[j];
				arr[j] = arr[++i];
				arr[i] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = pivot;
		arr[end] = temp;
		return i+1;
	}
	public static int partitionIt(int start, int end,int pivotIndex) {
		int pivot = arr[pivotIndex];
		end = end-1;
		int i = start-1;
		for(int j=start;j<end;++j) {
			if(arr[j] <= pivot) {
				int temp = arr[j];
				arr[j] = arr[++i];
				arr[i] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = pivot;
		arr[end] = temp;
		return i+1;
	}
	public static void quickSortWithMedian(int start, int end) {
		if(start<end){
			int median = findMedian(start,end);
			int pivot = partitionIt(start,end,median);
			quickSortWithMedian(start,pivot-1);
			quickSortWithMedian(pivot+1,end);
		}
	}
	public static int findMedian(int start, int end) {
		int center = (start+end)/2;
		if(arr[start] > arr[center])
			swap(start,center);
		if(arr[center] > arr[end])
			swap(center,end);
		if(arr[start] > arr[end])
			swap(start,end);
		swap(center, end-1);
		return end-1;
	}
	public static void swap(int ind1, int ind2) {
		int temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}
}

