// for elements in [0,1)
package sorting;


import LinkedLists.SortedList;

public class BucketSort {
	public static void main(String[] args) {
		double[] arr = new double[10];
		for(int i=0;i<10;++i) {
			arr[i] = Math.random();
			System.out.println(arr[i]);
		}
		arr = bucketSort(arr);
		System.out.println("--------");
		for(Double d:arr)
			System.out.println(d);
	}
	public static double[] bucketSort(double[] arr){
		SortedList<Double>[] bucket = new SortedList[10];
		for(int i=0;i<bucket.length;++i)
			bucket[i] = new SortedList<Double>();
		for(Double d:arr){
			bucket[(int)(d*10)].insert(d);
		}
		int itr=0;
		for(int k=0;k<bucket.length;++k) {
			while(!bucket[k].isEmpty())
				arr[itr++]=bucket[k].removeFirst().data;
		}
		return arr;
	}
}
