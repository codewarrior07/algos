/*
 Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given array such that the intgers in 
 the subsequence are sorted in increasing order. For example, if input is {1, 101, 2, 3, 100, 4, 5}, 
 then output should be 106 (1 + 2 + 3 + 100), if the input array is {3, 4, 5, 10}, 
 then output should be 22 (3 + 4 + 5 + 10) and if the input array is {10, 5, 4, 3}, then output should be 10
 */
package dynamic;

import java.util.Arrays;

public class MaximumIncreasingSubsetSum {
	public static void main(String[] args) {
		maxSubsetSum(new int[]{10, 5, 4, 3});
	}
	
	public static int maxSubsetSum(int[] arr){
		int[] ms = new int[arr.length];
		int[] prev = new int[arr.length];
		Arrays.fill(prev,-1);
		int max=arr[0],best=0;
		ms[0]=arr[0];
		for(int i=1;i<arr.length;++i){
			ms[i]=arr[i];
			for(int j=i-1;j>=0;--j){ //1, 101, 2, 3, 100, 4, 5
				if(arr[i]>arr[j] && ms[i]<(ms[j]+arr[i])) {
					ms[i] = ms[j]+arr[i];
					prev[i]=j;
				}
				if(ms[i]>max){
					max=ms[i];
					best=i;
				}
			}
		}
		System.out.println("max sum is:"+max);
		System.out.println("Max array is");
		while(best>=0){
			System.out.println(arr[best]);
			best=prev[best];
		}
		return max;
	}
}
