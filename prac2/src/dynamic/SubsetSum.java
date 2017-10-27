/*
Given a set of non-negative integers, and a value sum, determine if there is a subset of 
the given set with sum equal to given sum.
Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output:  True  //There is a subset (4, 5) with sum 9.
 */
package dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetSum {
	public static void main(String[] args) {
		//subsetSum(new int[]{7,1,2,6},8);
		//subsetSumIntegers(new int[]{1,-3,2,4},0);
		for(int i:subsetsum(new int[]{2,3,4,12,5,2},10))
			System.out.println(i);
	}
	// only for positive integers
	public static void subsetSum(int[] arr, int sum) {
		boolean[][] truth = new boolean[arr.length+1][sum+1];
		for(int i=0;i<sum+1;++i) {
			truth[0][i] = false;
		}
		for(int i=0;i<arr.length+1;++i) {
			truth[i][0] = true;
		}
		for(int currItem=1;currItem<arr.length+1;++currItem) {
			for(int currSum=1;currSum<sum+1;++currSum) {
				truth[currItem][currSum] = truth[currItem-1][currSum];
				if(currSum >= arr[currItem-1])
					truth[currItem][currSum] = truth[currItem][currSum] || truth[currItem-1][currSum-arr[currItem-1]]; 
			}
		}
		if(truth[arr.length][sum]) {			
			System.out.println("Sum exists:");
			int currItem = arr.length, currSum = sum;
			while(currSum != 0) {
				if(truth[currItem-1][currSum])
					--currItem;
				else {
					System.out.print(arr[currItem-1]+ " ");
					currSum -= arr[currItem-1];
				}
			}
		} else
			System.out.println("Sum does not exist");
	}
	
	//use this
	public static List<Integer> subsetsum(int[] arr,int sum){
		boolean[][] sub = new boolean[arr.length+1][sum+1];
		for(int i=0;i<=arr.length;++i)
			sub[i][0] = true;
		int i=1,j=1;
		loops:for(i=1;i<=arr.length;++i){
			for(j=1;j<=sum;++j){
				sub[i][j] = sub[i-1][j] || (j>=arr[i-1] && sub[i-1][j-arr[i-1]]);
				if(sub[i][j] && j==sum)
					break loops;
			}
		}
		if(sub[i][j]){
			List<Integer> subset = new ArrayList<Integer>();
			while(j>0){
				if(!sub[i-1][j]){
					subset.add(arr[i-1]);
					j=j-arr[i-1];
				}
				i=i-1;
			}
			return subset;
		}
		return null;
	}
	
	public static void subsetSumIntegers(int[] arr, int sum) {
		int maxSum = 0, leastNum = arr[0],maxNum = arr[0];
		for(int i=0;i<arr.length;++i){
			if(arr[i] > 0)
				maxSum += arr[i];
			else
				maxNum = arr[i] > maxNum ? arr[i] : maxNum;
			leastNum = arr[i] < leastNum ? arr[i] : leastNum;
		}
		if(maxSum == 0)
			maxSum = maxNum;
		if(sum > maxSum)
			return;
		int allSumsSize = maxSum-leastNum+1;
		int[] allSums = new int[allSumsSize];
		int tempNum = leastNum;
		for(int i = 0; i<allSumsSize;++i)
			allSums[i] = tempNum++;
		
		boolean[][] truth = new boolean[arr.length+1][allSumsSize];
		
		for(int i=0;i<allSumsSize;++i)
			truth[0][i] = false;
		for(int i=1;i<arr.length+1;++i){
			int currItem = arr[i-1];
			for(int j=0;j<allSumsSize;++j){
				int currSum = allSums[j];
				truth[i][j] = truth[i-1][j];
				int diff = currSum-currItem;
				if(diff <= maxSum && diff >= leastNum)
					truth[i][j] = diff==0? true : truth[i][j] || truth[i-1][diff-leastNum];
			}
		}
		// print subset
		int currCol = sum-leastNum;
		if(truth[arr.length][currCol]) {
			System.out.println("Sum found");
			for(int i=arr.length;i>0;--i) {
				if(!truth[i-1][currCol]) {
					System.out.print(arr[i-1]+ " ");
					currCol -= arr[i-1];
				}
			}
		} else
			System.out.println("Sum not found");
	}
}
