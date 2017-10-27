package sorting;

import java.util.ArrayList;
import java.util.List;

public class RadixSort {
	public static void main(String[] args) {
		int[] arr = {103,102,101,5};
		arr = radixSort(arr);
		for(Integer i:arr) 
			System.out.println(i);
	}
	public static int[] radixSort(int[] arr) {
		final int RADIX = 10;
		List<Integer>[] bucket = new ArrayList[RADIX];
		boolean maxLength = false;
		int placement = 1;
		for(int i=0;i<bucket.length;++i)
			bucket[i] = new ArrayList<Integer>();
		while(!maxLength) { 
			maxLength = true;
			int q = 0;
			for(Integer i:arr) {
				q = i/placement;
				int digitVal = q%10;
				bucket[digitVal++].add(i);
				if(q>0)
					maxLength = false;
			}
			int itr=0;
			for(int j=0;j<bucket.length;++j) {
				for(Integer k:bucket[j]) {
					arr[itr++] = k;
				}
				bucket[j].clear();
			}
			placement *= RADIX;
		}
		return arr;
	}
}
