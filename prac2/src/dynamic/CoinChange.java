/*Given a list of N coins, their values (V1, V2, � , VN), and the total sum S. 
Find the minimum number of coins the sum of which is S 
(we can use as many coins of one type as we want), or report that it�s not possible to select coins in such a way that they sum up to S.
For a better understanding let�s take this example:
Given coins with values 1, 3, and 5.
And the sum S is set to be 11. Solution is 3 coins - 5,5,1.
*/
package dynamic;

public class CoinChange {
	public static void main(String[] args) {
		//findChange(new int[]{2,3,6,1},11);
		//findChangeUpdate(new int[]{2,3,6,1},11);
		System.out.println(getNumWays(new int[]{1,3,5},11));
	}
	public static int[][] findChange(int[] coins, int sum) {
		int[][] soln = new int[sum+1][3];
		soln[0][0] = 0; 
		for(int i=1;i<=sum;++i) {
			soln[i][0] = Integer.MAX_VALUE;
		}
		for(int currSum=1;currSum<=sum;++currSum) {
			for(int currCoin=0;currCoin<coins.length;++currCoin) {
				if(currSum>=coins[currCoin]) {
					if(soln[currSum-coins[currCoin]][0]+1 < soln[currSum][0]) {
						soln[currSum][0] = 1+soln[currSum-coins[currCoin]][0];
						soln[currSum][1] = coins[currCoin];
						soln[currSum][2] = currSum-coins[currCoin];
					}
				}
			}
		}
		System.out.println("Min number of coints required to get "+sum+" :"+soln[sum][0]);
		System.out.println("Denominations:");
		int currCoin = sum;
		while(true) {
			if(currCoin == 0)
				break;
			System.out.print(soln[currCoin][1]+" ");
			currCoin = soln[currCoin][2];
		}
		return soln;
	}
	
	/*public static int[][] findChangeUpdate(int[] coins, int sum) {
		int[][] soln = new int[3][sum+1];
		soln[0][0] = 0;
		for(int i=1;i<sum+1;++i)
			soln[0][i] = Integer.MAX_VALUE;
		for(int currSum=1;currSum<sum+1;++currSum) {
			for(int currCoin=0;currCoin<coins.length;++currCoin) {
				int tempSum = 0, prevSum = 0;
				while(tempSum+coins[currCoin]<=sum) {
					tempSum = coins[currCoin] + prevSum;
					if(1+soln[0][prevSum] < soln[0][tempSum]) {
						soln[0][tempSum] = 1 + soln[0][prevSum];
						soln[1][tempSum] = coins[currCoin];
						soln[2][tempSum] = prevSum;
					}
					prevSum = tempSum;
				}
			}
		}
		System.out.println("Min number of coints required to get "+sum+" :"+soln[0][sum]);
		System.out.println("Denominations:");
		int currCoin = sum;
		while(true) {
			if(currCoin == 0)
				break;
			System.out.print(soln[1][currCoin]+" ");
			currCoin = soln[2][currCoin];
		}
		return soln;
	}*/
	
	public static int getNumWays(int[] s,int n) {
		int[][] arr = new int[s.length+1][n+1];
		for(int i=0;i<n+1;++i)
			arr[0][i] = 0;
		for(int i=0;i<s.length+1;++i)
			arr[i][0] = 1;
		for(int i=1;i<s.length+1;++i){
			for(int j=1;j<n+1;++j) {
				if(j>=s[i-1])
					arr[i][j] = arr[i-1][j]+arr[i][j-s[i-1]];
				else
					arr[i][j]=arr[i-1][j];
			}
		}
		return arr[s.length][n];
	}
	
	//use this to get total number of ways to generate sum using coins
	public static int getNumWays2(int[] s,int n) {
		int[] arr = new int[n+1];
		for(int i=0;i<n+1;++i)
			arr[i] = 0;
		arr[0]=1;
		for(int i=0;i<s.length;++i){
			for(int j=s[i];j<=n;++j){
				arr[j] = arr[j] + arr[j-s[i]];
			}
		}
		return arr[n];
	}
	
	// get min number of coins
	public static int getMinNumberOfCoins(int[] coins,int s){
		int[] min = new int[s+1];
		for(int i=0;i<min.length;++i)
			min[i]=Integer.MAX_VALUE;
		min[0] = 0;
		for(int i=1;i<=s;++i){
			for(int j=0;j<coins.length;++j){
				if(coins[j]<=i && 1+min[i-coins[j]]<min[i])
					min[i] = 1+min[i-coins[j]];
			}
		}
		return (min[s]>0)?min[s]:0;
	}
}
