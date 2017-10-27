package dynamic;

import java.util.ArrayList;
import java.util.List;

public class Robot {
	class Point {
		int x,y;
		Point(int x,int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object p){
			if(p instanceof Point)
				return ((Point)p).x == this.x && ((Point)p).y == this.y;
			return false;
		}
		
		@Override
		public String toString(){
			return this.x + "," + this.y;
		}
	}
	public static void main(String[] args) {
		int[][] arr = {{0,0,0},{0,0,0},{0,0,0}};
		Robot r = new Robot();
		/*List<List<Point>> list = r.getPaths(arr,2,2,new ArrayList<Point>(), new ArrayList<List<Point>>());
		System.out.println(r.getNumPaths(arr,2,2));
		for(List<Point> l : list) {
			for(Point p:l)
				System.out.println(p);
			System.out.println("----------");
		}*/
		/*for(List<Point> l : r.getPaths(arr)) {
			for(Point p:l)
				System.out.print(p+ " ");
			System.out.println("");
		}*/
		System.out.println(r.getNumPaths(arr,arr.length-1,arr.length-1));
	}
	public List<List<Point>> getPaths(int[][] arr, int x,int y, List<Point> curr, List<List<Point>> paths) {
		Point p = new Point(x,y);
		if(x == 0 && y == 0) {
			curr.add(p);
			paths.add(new ArrayList<Point>(curr));
			curr.remove(p);
			return paths;
		}
		if(x<0 || y<0 || arr[x][y] == -1)
			return null;
		curr.add(p);
		getPaths(arr,x-1,y,curr,paths);
		getPaths(arr,x,y-1,curr,paths);
		curr.remove(p);
		return paths;
	}
	
	// USE THIS
	public List<List<Point>> getPaths(int[][] arr) {
		List<List<Point>> paths = new ArrayList<List<Point>>();
		getPathsHelper(arr,0,0,new ArrayList<Point>(),paths);
		return paths;
	}
	
	public void getPathsHelper(int[][] arr,int x,int y,List<Point> curr, List<List<Point>> paths) {
		if(x>=arr.length || y>=arr.length || arr[x][y] == -1)
			return;
		Point p = new Point(x,y);
		curr.add(p);
		if(x == arr.length-1 && y == x)
			paths.add(new ArrayList<Point>(curr));
		else {
			getPathsHelper(arr,x+1,y,curr,paths);
			getPathsHelper(arr,x,y+1,curr,paths);
		}
		curr.remove(p);
	}
	
	public int getNumPaths(int[][] arr,int x, int y) {
		if(x == 0 && y == 0)
			return 1;
		if(x<0 || y<0 || arr[x][y]==-1)
			return 0;
		return getNumPaths(arr,x-1,y)+ getNumPaths(arr,x,y-1);
	}
	
}
