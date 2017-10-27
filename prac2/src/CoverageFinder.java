import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoverageFinder implements Intervals{
	private List<Interval> list;
	public CoverageFinder() {
		list = new ArrayList<Interval>();
	}
	private class Interval implements Comparable<Interval>{
		int x,y;
		Interval(int x,int y) {
			if(x > y)
				throw new IllegalArgumentException("Invalid interval");
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Interval o) {
			return o == null ? 0 : (this.x-o.x);
		}

		@Override
		public String toString(){
			return "["+x+","+y+"]";
		}
	}
	public void addInterval(int x,int y) {
		list.add(new Interval(x,y));
	}
	public int getTotalCoveredLength() {
		Collections.sort(list);
		int coverage= 0;
		Interval last = new Interval(Integer.MIN_VALUE,Integer.MIN_VALUE);
		for(Interval curr:list) {
			if(curr.x > last.y)
				coverage += curr.y - curr.x + 1;
			else if(curr.x == last.x && curr.y > last.y)
				coverage += curr.y - last.y;
			else if(curr.x < last.y &&  curr.y > last.y)
				coverage += curr.y - last.y;
			else 
				continue;
			last = curr;
		}
		return coverage;
	}
	//use any, both work, this is more intuitive
	public int getTotalCoverage2(){
		Collections.sort(list);
		int sum=0;
		int lastEnd=Integer.MIN_VALUE;
		for(Interval i:list){
			if(i.y>lastEnd){
				if(i.x>lastEnd)
					sum+=i.y-i.x+1;
				else
					sum+=i.y-lastEnd;
				lastEnd=i.y;
			}
		}
		return sum;
	}

	public void insertInterval(Interval inter){
		int k=-1,j=-1;// inter.x = -5 inter.y = 1
		int max=Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for(int i=0;i<list.size();++i){
			if(inter.x<=list.get(i).y && list.get(i).y<=min){
				k=i;
				min = list.get(i).y;
			}
			if(inter.y>=list.get(i).x && list.get(i).x>=max){
				j=i;
				max=list.get(i).x;
			}
		}

		if(k==-1 || j==-1){
			list.add(inter);
			return;
		}
		list.get(Math.max(k,j)).x = Math.min(list.get(k).x,inter.x);
		list.get(Math.max(k,j)).y = Math.max(list.get(j).y,inter.y);
		if(k!=j){
			for(int v = Math.max(k,j),i=Math.min(k,j);v>i;--v)
				list.remove(i);
		}
	}

	public static void main(String[] args) {
		CoverageFinder c = new CoverageFinder();
		c.addInterval(-5,-1);
    	c.addInterval(1,3);
    	c.addInterval(4,10);
    	c.insertInterval(c.new Interval(-2,12));
		for(Interval i:c.list)
			System.out.println(i);
	}
}

interface Intervals {

	/**
	 * Adds an interval [from, to] into internal structure.
	 */
	void addInterval(int from, int to);


	/**
	 * Returns a total length covered by intervals.
	 * If several intervals intersect, intersection should be counted only once.
	 * Example:
	 *
	 * addInterval(3, 6)
	 * addInterval(8, 9)
	 * addInterval(1, 5)
	 *
	 * getTotalCoveredLength() -> 6
	 * i.e. [1,5] and [3,6] intersect and give a total covered interval [1,6]
	 * [1,6] and [8,9] don't intersect so total covered length is a sum for both intervals, that is 6.
	 *
	 *                   _________
	 *                                               ___
	 *     ____________
	 *
	 * 0  1    2    3    4    5   6   7    8    9    10
	 *
	 */
	int getTotalCoveredLength();
}