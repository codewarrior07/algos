/*
 Given a sequence of N numbers – A[1] , A[2] , …, A[N] . Find the length of the longest non-decreasing sequence.
 eg. {2 6 3 4 1 2 9 5 8}, LIS = {2 3 4 5 8}, Length of LIS = 5
 */
package dynamic;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		findLIS(new int[]{10,1,11,4,2,5,8});
		/*for(Integer i:lis(new int[]{2,6,3,4,1,2,9,5,8}))
			System.out.println(i);*/ // 1 2 2 3 1 2 3   
	}
	
	public static int[] findLIS(int[] arr) {
		int[] soln = new int[arr.length];
		int[] prev = new int[arr.length];
		int best = 1, maxLen = 0;
		soln[0] = 1; 
		//prev[0] = -1;
		Arrays.fill(prev,-1);
		for(int i=1;i<arr.length;++i) {
			soln[i] = 1;
			for(int j=i-1;j>=0;--j) {
				if(1+soln[j] > soln[i] && arr[i] > arr[j]) {
					soln[i] = 1 + soln[j];
					prev[i] = j;
				}
			}
			if(soln[i] > maxLen) {
				maxLen = soln[i];
				best = i;
			}
		}
		System.out.println("Length of LIS "+maxLen);
		System.out.println("LIS is");
		int[] lis = new int[maxLen];
		lis[0] = arr[best];
		int curr = prev[best], itr = 1;
		while(true) {
			if(curr == -1)
				break;
			lis[itr++] = arr[curr];
			curr = prev[curr];
		}
		for(int i=maxLen-1;i>=0;--i)
			System.out.println(lis[i]);
		return soln;
	}
	
	public static int[] lis(int[] a) {
	    int n = a.length;
	    int[] tail = new int[n];
	    int[] prev = new int[n];

	    int len = 0;
	    for (int i = 0; i < n; i++) {
	      int pos = lower_bound(a, tail, len, a[i]);
	      if (pos == len) {
	        ++len;
	      }
	      prev[i] = pos > 0 ? tail[pos - 1] : -1;
	      tail[pos] = i;
	    }

	    int[] res = new int[len];
	    for (int i = tail[len - 1]; i >= 0; i = prev[i]) {
	      res[--len] = a[i];
	    }
	    return res;
	  }

	  static int lower_bound(int[] a, int[] tail, int len, int key) {
	    int lo = -1;
	    int hi = len;
	    while (hi - lo > 1) {
	      int mid = (lo + hi)/2;
	      if (a[tail[mid]] < key) {
	        lo = mid;
	      } else {
	        hi = mid;
	      }
	    }
	    return hi;
	  }
}
