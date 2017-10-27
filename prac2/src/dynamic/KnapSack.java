/*
 A thief robbing a store and can carry a maximal weight of W into their knapsack. 
 There are n items and ith item weigh wi and is worth vi dollars. What items should thief take?
 For example:
 weights = [3,2,1]
 values = [5,3,4]
 W = 5
 has a solution Max value = 9, with 5 + 4
 */
package dynamic;

public class KnapSack {
	public static void main(String[] args) {
		knapSack01(new int[]{5,3,4},new int[]{3,2,1},5);
	}
	public static void knapSack01(int[] val,int[] weights,int W) {
		int[][] v = new int[weights.length+1][W+1];
		int[][] keep = new int[weights.length+1][W+1];
		for(int i=1;i<=weights.length;++i) {
			for(int j=1;j<=W;++j) {
				int totVal = 0;
				if(weights[i-1] <= j){
					totVal = val[i-1] + v[i-1][j-weights[i-1]];
				}				
				v[i][j] = Math.max(v[i-1][j], totVal);
				keep[i][j] = v[i-1][j] > totVal ? 0 : 1;
			}
		}
		System.out.println("Highest weight :"+v[weights.length][W]);
		int w = W, i = weights.length;
		while(i>0) {
			if(keep[i][w]==1) {
				System.out.print("weight "+weights[i-1]+" value "+val[i-1]+ " ,");
				w -= weights[i-1];
			}
			--i;
		}
	}
}
