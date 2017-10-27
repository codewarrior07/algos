/* Given a rod of length n inches and an array of prices that contains prices of all pieces 
   of size smaller than n. 
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces. 
 * For example, if length of the rod is 8 and the values of different pieces are given as following, 
 * then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6

length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 1   5   8   9  10  17  17  20

And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)

length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 3   5   8   9  10  17  17  20
 */

package dynamic;

public class RodCutter {
	public static void main(String[] args) {
		rodCut(new int[]{0,3,5,8,9,10,17,17,20});
	}
	public static void rodCut(int[] prices) {
		int[] soln = new int[prices.length];
		int[] prev = new int[prices.length];
		soln[0] = 0;
		prev[0] = -1;
		for(int i=1;i<prices.length;++i) {
			soln[i] = prices[i];
			prev[i] = -1;
			for(int j=1;j<=(i/2);++j) {
				if(soln[i-j]+soln[j] > soln[i]) {
					soln[i] = soln[i-j]+soln[j];
					prev[i] = i-j;
				}
			}
		}
		System.out.println("Max $$$ is "+soln[prices.length-1]);
		int totLen = prices.length-1;
		int curr = prices.length-1;
		while(totLen > 0) {
			if(prev[curr] == -1) {
				System.out.print(curr+" ");
				totLen -= curr;
				curr = totLen;
			} else
				curr = prev[curr];
		}
	}
}
