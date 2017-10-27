package graphs;

public class Islands {
	public static void main(String[] args){
		char[][] grid = {{'1','1','0','0','0'},
						{'1','1','0','0','0'},
						{'0','0','1','0','0'},
						{'0','0','0','1','1'}};
		System.out.println(numIslands(grid));
	}
    public static int numIslands(char[][] grid) {
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
    
    public static void numHelper(char[][] grid,boolean[][] st,int i,int j,int n,int m) {
    	if(i<0 || j<0 || i>=n || j>=m || grid[i][j]=='0' || st[i][j])
    		return;
    	st[i][j] = true;
        numHelper(grid,st,i,j-1,n,m);
        numHelper(grid,st,i,j+1,n,m);
        numHelper(grid,st,i-1,j,n,m);
        numHelper(grid,st,i+1,j,n,m);
    }
}
