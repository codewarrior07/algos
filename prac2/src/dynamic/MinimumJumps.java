/*
 Given an array of integers where each element represents the max number of steps that can be made forward from that element. 
 Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
 If an element is 0, then cannot move through that element.

Example:

Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
Output: 3 (1-> 3 -> 8 ->9)
First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps eg to 5 or 8 or 9.
 */

package dynamic;

public class MinimumJumps {
	public static void main(String[] args) {
		minJumps(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9});
	}
	public static int[] minJumps(int[] arr) {
		int[] jumps = new int[arr.length];
		int[] prev = new int[arr.length];
		jumps[0] = 0;
		prev[0] = -1;
		for(int i=1;i<arr.length;++i) {
			jumps[i] = Integer.MAX_VALUE;
			for(int j=0;j<i;++j) {
				if(j+arr[j]>=i && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], jumps[j]+1);
					prev[i] = j;
					break;
				} else
					prev[i] = -1;
			}
		}
		System.out.println("number of jumps needed "+jumps[arr.length-1]);
		int curr = arr.length-1;
		while(true) {
			if(curr == -1)
				break;
			System.out.print(arr[curr]+"<-");
			curr = prev[curr];
		}
		return jumps;
	}
}
