// Given an array, find if it can be partitioned into equal halves.
// eg. [3,2,5,6] returns true -> [3,5] and [2,6]
// second problem: partition with minimum sums
package dynamic;

import java.util.ArrayList;
import java.util.List;

public class PartitionArray {
	static int major = 0;

	public static void main(String[] args) {
		 //parititionEqHalf(new int[]{3,5,11,5});
		partitionWithMinSums(new int[] { 3,2});

	}

	// use this
	public static boolean parititionEqHalf(int[] arr) {
		int sum = 0;
		for (Integer i : arr)
			sum += i;
		if (sum % 2 != 0)
			return false;
		int len = (sum / 2);
		boolean[][] truth = new boolean[arr.length + 1][len+1];
		for (int i = 0; i < len; ++i)
			truth[0][i] = false;
		for (int i = 1; i < arr.length + 1; ++i)
			truth[i][0] = true;
		for (int i = 1; i < arr.length + 1; ++i) {
			for (int j = 1; j < len; ++j) {
				truth[i][j] = truth[i - 1][j];
				if (j >= arr[i - 1])
					truth[i][j] = truth[i][j] || truth[i - 1][j - arr[i - 1]];
			}
		}
		System.out.println(truth[arr.length][len - 1]);
		return truth[arr.length][len - 1];
	}

	// use this for min sums
	public static void partitionWithMinSums(int[] arr) {
		int sum = 0;
		for (Integer i : arr)
			sum += i;
		int sumSize = sum / 2;
		boolean[][] truth = new boolean[arr.length + 1][sumSize+1];
		for (int i = 0; i < sumSize; ++i)
			truth[0][i] = false;
		for (int i = 0; i < arr.length + 1; ++i)
			truth[i][0] = true;
		for (int i = 1; i < arr.length + 1; ++i) {
			for (int j = 1; j < sumSize; ++j) {
				truth[i][j] = truth[i - 1][j];
				if (j >= arr[i - 1])
					truth[i][j] = truth[i][j] || truth[i - 1][j - arr[i - 1]];
			}
		}
		int min = Integer.MAX_VALUE;
		int minX = 0, minY = 0;
		for (int i = 1; i < arr.length + 1; ++i) {
			for (int j = 1; j < sumSize; ++j) {
				if (truth[i][j] && Math.abs(sum - 2 * j) < min) {
					min = Math.abs(sum - 2 * j);
					minX = i;
					minY = j;
				}
			}
		}
		System.out.println("minimum diff is " + min);
		List<Integer> sub1 = new ArrayList<Integer>();
		List<Integer> sub2 = new ArrayList<Integer>();
		while (minY > 0) {
			if (truth[minX][minY] && !truth[minX - 1][minY]) {
				sub1.add(minX);
				minY -= arr[minX - 1];
			}
			--minX;
		}
		for (int i = 1; i <= arr.length; ++i) {
			if (!sub1.contains(i))
				sub2.add(i);
		}
		for (Integer i : sub1)
			System.out.print(arr[i - 1] + " ");
		System.out.println();
		for (Integer i : sub2)
			System.out.print(arr[i - 1] + " ");
	}

	public static boolean partitionEqual(int[] arr, int s1, int e1, int count) {
		if (count == arr.length)
			return false;
		int s2 = (e1 + 1) % arr.length;
		int e2 = s1 == 0 ? arr.length - 1 : s1 - 1;
		int te1 = e1;
		boolean res = false;
		for (int i = 0; i < arr.length - 1; ++i) {
			int tot1 = 0, tot2 = 0, ts1 = s1, ts2 = s2;
			while (ts1 != te1) {
				tot1 += arr[ts1++];
				ts1 = ts1 % arr.length;
			}
			tot1 += arr[ts1];
			while (ts2 != e2) {
				tot2 += arr[ts2++];
				ts2 = ts2 % arr.length;
			}
			tot2 += arr[ts2];
			if (tot1 == tot2) {
				System.out.println(tot1);
				res = true;
				while (s1 != te1) {
					System.out.print(arr[s1++] + " ");
					s1 = s1 % arr.length;
				}
				System.out.print(arr[s1]);
				System.out.println();
				while (s2 != e2) {
					System.out.print(arr[s2++] + " ");
					s2 = s2 % arr.length;
				}
				System.out.print(arr[s2]);
				return true;
			}
			te1 = (te1 + 1) % arr.length;
			s2 = (s2 + 1) % arr.length;
			++major;
		}
		return res || partitionEqual(arr, ++s1, ++e1, ++count);
	}

}
