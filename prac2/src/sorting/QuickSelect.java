/*
 * Find the kth largest/smallest element in an unsorted array. 
	Note that it is the kth largest/smallest element in the sorted order, not the kth distinct element.
	For example, given [3,2,1,5,6,4] and k = 2, return 5.
 */

package sorting;

import java.util.PriorityQueue;
import java.util.Random;

public class QuickSelect {
	public static void main(String[] args){
		int[] arr = new int[]{3,2,1,5,6,4};
		System.out.println(kthLargest(arr,4));
		//System.out.println(kthSmallest(arr,4));
		System.out.println(findKthLargest(arr,4));
	}

	// quick select algo, without shuffling worst case O(n2), after shuffling worst case - O(n)
	//kth largest
	public static int kthLargest(int[] arr,int k){
		if(arr == null || arr.length==0 || k>arr.length || k<1)
			return Integer.MIN_VALUE;
		shuffle(arr);
		int ind = kthLargestHelper(arr,0,arr.length-1,k);
		return (ind<0)?Integer.MIN_VALUE:arr[ind];
	}
	public static int kthLargestHelper(int[] arr, int s, int e,int k){
		if(s>e)
			return -1;
		int pivot = partition(arr,s,e);
		if(pivot==arr.length-k)
			return pivot;
		else if(pivot>arr.length-k)
			return kthLargestHelper(arr,s,pivot-1,k);
		else
			return kthLargestHelper(arr,pivot+1,e,k);
	}

	//kth smallest
	public static int kthSmallest(int[] arr,int k){
		if(arr == null || arr.length==0 || k>arr.length || k<1)
			return Integer.MIN_VALUE;
		shuffle(arr);
		int ind = kthSmallestHelper(arr,0,arr.length-1,k);
		return (ind<0)?Integer.MIN_VALUE:arr[ind];
	}
	public static int kthSmallestHelper(int[] arr, int s, int e,int k){
		if(s>e)
			return -1;
		int pivot = partition(arr,s,e);
		if(pivot==k-1)
			return pivot;
		else if(pivot>k-1)
			return kthSmallestHelper(arr,s,pivot-1,k);
		else
			return kthSmallestHelper(arr,pivot+1,e,k);
	}

	//helpers

	//partition smaller<pivot<greater
	public static int partition(int[] arr,int s,int e){
		int pivot = e;
		int j=s;
		for(int i=s;i<=e;++i){
			if(arr[i] < arr[pivot]){
				swap(arr,i,j);
				++j;
			}
		}
		swap(arr,pivot,j);
		return j;
	}
	public static void swap(int[] arr, int n1, int n2){
		int temp = arr[n1];
		arr[n1] = arr[n2];
		arr[n2] = temp;
	}
	public static void shuffle(int[] arr){
		Random rand = new Random();
		for(int i=arr.length-1;i>1;--i){
			int randIndex = rand.nextInt(i);
			swap(arr,randIndex,i);
		}
	}


	// min heap solution
	public static int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int val : nums) {
			pq.offer(val);

			if(pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}
}
