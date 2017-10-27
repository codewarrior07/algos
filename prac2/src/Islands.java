
public class Islands {
	public static void main(String[] args){

	}

	/*
	 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water 
	 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

		Example 1:

		11110
		11010
		11000
		00000
		Answer: 1

		Example 2:

		11000
		11000
		00100
		00011
		Answer: 3
	 */
	public int numIslands(char[][] grid) {
		if(grid==null || grid.length==0)
			return 0;
		int n = grid.length;
		int m = grid[0].length;
		boolean[][] st = new boolean[n][m];
		int count=0;
		for(int i=0;i<n;++i){
			for(int j=0;j<m;++j){
				if(grid[i][j]=='1' && !st[i][j]) {
					numHelper(grid,st,i,j,n,m);
					++count;
				}
			}
		}
		return count;
	}

	private void numHelper(char[][] grid,boolean[][] st,int i,int j,int n,int m) {
		if(i<0 || j<0 || i>=n || j>=m || grid[i][j]=='0' || st[i][j])
			return;
		st[i][j] = true;
		numHelper(grid,st,i,j-1,n,m);
		numHelper(grid,st,i,j+1,n,m);
		numHelper(grid,st,i-1,j,n,m);
		numHelper(grid,st,i+1,j,n,m);
	}
	
}
