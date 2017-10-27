import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;



public class demo {
	public static void main(String[] args) {
		/*String s ="\\\\ms\\lis\\asd\\bhe";
		String[] p = s.substring(2).split("\\\\");
		for(String c:p)
			System.out.println(c);*/
		
		
		/*Map<String,List<String>> map = new HashMap<String,List<String>>();
		List<String> list = new ArrayList<String>();
		list.add("abc");
		map.put("asd",list);
		List<String> l = map.get("asd");
		l.add("x");
		System.out.println(map);*/
		
		/*String s = "xyz.xyz";
		boolean flag=false;
		for(int i=s.indexOf("xyz");i>=0 && i<s.length();){			
			if(i==0||(i>0 && s.charAt(i-1) != '.')){
				flag = true;
				break;
			}
			i=s.indexOf("xyz",i+3);
		}
		System.out.println(flag);*/
		/*LinkedList<Integer> list = new LinkedList<Integer>();
		list.offer(9);*/
		//System.out.println(0^4);
		/*PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(12);
		pq.add(5);
		pq.add(10);
		for(int i:pq){
			if(i==10)
				pq.remove(i);
		}
		for(int i:pq)
			System.out.println(i);*/
		//int[][] a = new int[1000000][1000000];
		//int[] a = new int[1000000];
		
		/*int num=125;
		List<Integer> list = new ArrayList<Integer>();
		int rem;
		while(num>0){
			rem = num%62;
			list.add(rem);
			num=num/62;
		}
		System.out.println(list);*/
		
		
		/*Box b1 = new Box(6,7,13);
		Box b2 = new Box(4,5,12);
		Box b3 = new Box(1,2,6);
		List<Box> list = new ArrayList<Box>();
		list.add(b1);
		list.add(b2);
		list.add(b3);
		Collections.sort(list);
		for(Box b:list)
			System.out.println(b);*/
		//X<String> x = new X<String>("asd");
		/*char i = 'a';
		i = i > 'z' ? 'a' : ++i;
		System.out.println(i);*/
		
		/*int l = (-1+2)>>>1;
		System.out.println(-1|0);*/
		
		/*List<ArrayList<Integer>> l = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> i1 = new ArrayList<Integer>();
		i1.add(1);
		l.add(i1);
		ArrayList<Integer> c = l.get(0);
		c.add(2);
		for(Integer i:l.get(0))
			System.out.println(i);*/
		
		/*List<Integer> list = new ArrayList<Integer>();
		list.add(0,5);
		list.add(1,6);
		list.add(3,8);
		*/
		/*int size = 32;
		int i = 10;
		boolean[] ct = new boolean[32];
		for(int count = 0;count<54;count++) {
			int ind = ((int)((Math.pow(i, 2)+i)/2))%size;
			//System.out.println(ind);
			ct[ind] = true;
			++i;
		}
		for(boolean b:ct)
			System.out.println(b);*/
		/*int[] arr = {6,1,3,5,2,4};
		int i = 0, j = 0, e = arr.length-1;
		while(j<e) {
			if(arr[j]<arr[e]) {
				int t = arr[i];
				arr[i++] = arr[j];
				arr[j] = t;
			}
			++j;
		}
		int t = arr[i];
		arr[i] = arr[e];
		arr[e] = t;
		System.out.println("part "+i);
		for(Integer x:arr)
			System.out.println(x);*/
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.offer(3);
		pq.offer(8);
		pq.offer(5);
		System.out.println(pq.remove());
		System.out.println(pq.remove());
		System.out.println(pq.remove());
	}
}

class X<T> {
	T dat;
	public X(T dat) {
		if(dat instanceof String)
			System.out.println("hi");
		this.dat = dat;
	}
}

class Box implements Comparable<Box>{
	int w,d,h;
	Box(int w,int d,int h) {
		this.w=w;
		this.d=d;
		this.h=h;
	}
	@Override
	public int compareTo(Box b){
		if(b.w>this.w && b.d>this.d && b.h>this.h)
			return 1;
		else if(b.w==this.w && b.d==this.d && b.h==this.h)
			return 0;
		else
			return -1;
	}
	@Override
	public String toString(){
		return this.w + " "+this.d+" "+this.h;
	}
}

abstract class A {
	 abstract void func();
}

class B extends A {
	void func(){
		System.out.println("B");
	}
}

class C extends A {
	void func(){
		System.out.println("C");
	}
}