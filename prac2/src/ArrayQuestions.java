import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;


public class ArrayQuestions {
	public static void main(String[] args) {
		//System.out.println(compress("abccccc"));
		/*char[][] mat = {{'a','b','c','d'},{'e','f','g','h'},{'i','j','k','l'},{'m','n','o','p'}};
		for(int i=0;i<mat.length;++i){
			for(int j=0;j<mat[i].length;++j)
				System.out.print(mat[i][j]+ " ");
			System.out.println();
		}
		rotateClock(mat);
		System.out.println("--------");
		for(int i=0;i<mat.length;++i){
			for(int j=0;j<mat[i].length;++j)
				System.out.print(mat[i][j]+ " ");
			System.out.println();
		}*/

		/*int[][] mat = {{1,2,3},{0,5,2},{7,6,0}}; 
		for(int i=0;i<mat.length;++i){
			for(int j=0;j<mat[i].length;++j)
				System.out.print(mat[i][j]+ " ");
			System.out.println();
		}
		System.out.println("----------");
		fillZeros1(mat);
		for(int i=0;i<mat.length;++i){
			for(int j=0;j<mat[i].length;++j)
				System.out.print(mat[i][j]+ " ");
			System.out.println();
		}*/

		//rotateRight("water1".toCharArray(),3);

		//System.out.println(hIndexUnsorted(new int[] {4,0,9,10,3,8,7}));
		
		/*char[] str = new char[]{'a','b','c'};
		char x ='d';
		char[] temp = new char[str.length+1];
		for(int j=0;j<temp.length;++j) {
			for(int k=0;k<j;++k){
				temp[k] = str[k];
			}
			temp[j] = x;
			for(int k=j;k<str.length;++k){
				temp[k+1] = str[k];
			}
			System.out.println(temp);
		}*/

		/*int c = 0;
		for(char[] str:permutate("abcdefghijk")){
			//System.out.println(str);
			++c;
		}
		System.out.println(c);*/

		//System.out.println(majElem(new int[]{3,3,4,2,4,4,2,4,4}));

		//movingAvg(new int[]{1,2,3,4,5,5,3,3,2,1},5);

		/*Random rand = new Random();
		System.out.println(rand.nextInt(1));*/

		/*	for(Integer i:shuffle(new int[]{7,2,1,3,4}))
			System.out.println(i);*/
		//permutate(new String[]{"red","fox","super"},0,"");

		//printEmptyIntervals(new int[]{0, 5, 6, 45, 86});

		//System.out.println(checkNum(".11"));
		ArrayQuestions obj = new ArrayQuestions();
		//obj.permString("abcdefghijk");
		/*for(String s: obj.permString("abcdefghi"))
			System.out.println(s);*/

		//System.out.println(obj.findInRotated(new int[]{4,5,6,7,1,2,3},12));

		//System.out.println(obj.replaceZero(-102050000));

		/*for(Integer i:obj.selfExProduct(new int[]{1,2,-3,-4}))
			System.out.println(i);*/

		/*for(Integer i:obj.mergeSorted(new int[]{1,4,100,210},new int[]{50,99}))
			System.out.println(i);*/

		//System.out.println(obj.findLarger(new char[]{'b','c','d','e'},'f'));

		/*for(String s:obj.permString("abc"))
			System.out.println(s);*/

		/*for(int i:obj.reverseGroup(new int[]{1,2,3,4,5},1))
			System.out.println(i);*/
		//System.out.println(obj.finddHighestSmallerValue(new int[]{1,3,5,7,9},0));
	
		//for(Integer i:obj.continuousSubarraySum(new int[]{1,4,0,0,3,9},7))
		//	System.out.println(i);
		
		//for(Integer i:obj.chooseRandomSubset(new int[]{4,1,2,7,8,3,6,7},4))
		//	System.out.println(i);
		
		//System.out.println(obj.maxDiff(new int[]{6,4,5,3,1,2}));
	}
	/*
	 Implement a method to perform basic string compression using the counts of
	repeated characters. For example, the string aabcccccaaa would become
	a2blc5a3. If the "compressed" string would not become smaller than the original
	string, your method should return the original string
	 */
	public static String compress(String inp) {
		if(inp == null)
			return null;
		char[] temp = new char[inp.length()];
		int start = 0,end=0,curr=0;
		for(int i=1;i<inp.length();++i){
			if(inp.charAt(i) != inp.charAt(i-1)){
				int count = end-start+1;
				if(curr+(""+count).length()+1>temp.length)
					return inp;
				temp[curr++] = inp.charAt(i-1);
				for(Character c:(""+count).toCharArray())
					temp[curr++] = c;
				start = end = i;
			} else
				end++;
		}
		int count = end-start+1;
		if(curr+(""+count).length()+1>=temp.length)
			return inp;
		temp[curr++] = inp.charAt(inp.length()-1);
		for(Character c:(""+count).toCharArray())
			temp[curr++] = c;
		return String.valueOf(temp);

	}

	/*
	 Rotate a matrix by 90 degrees clockwise
	 */
	public static void rotateClock(char[][] mat) {
		int n = mat.length;
		for(int first=0;first<n/2;++first){
			//int first = layer;
			int last = n-1-first;
			for(int i=first;i<last;++i){
				int offset = i-first;
				char top = mat[first][i];
				mat[first][i] = mat[last-offset][first];
				mat[last-offset][first] = mat[last][last-offset];
				mat[last][last-offset] = mat[i][last];
				mat[i][last] = top;
			}
		}
	}
	public static void rotateClock1(char[][] mat) {
		for(int j=0;j<mat.length/2;++j){
			int last = mat.length-1-j;
			for(int i=j;i<last;++i){
				int offset = i-j;
				char curr = mat[j][i];
				mat[j][i] = mat[last-offset][j];
				mat[last-offset][i] = mat[last][last-offset];
				mat[last][last-offset] = mat[i][last];
				mat[i][last] = curr;
			}
		}
	}
	/*
	 Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
	 column are set to 0.
	 */
	public static void fillZeros(int[][] mat) {
		boolean[] row = new boolean[mat.length];
		boolean[] col = new boolean[mat.length];
		for(int i=0;i<mat.length;++i){
			for(int j=0;j<mat[i].length;++j){
				if(mat[i][j] == 0) {
					row[i] = true;
					col[j] = true;
				}
			}
		}

		for(int i=0,k=0;i<row.length && k<col.length;++i,++k){
			if(row[i]) {
				for(int j=0;j<mat[i].length;++j)
					mat[i][j] = 0;
			}
			if(col[i]) {
				for(int j=0;j<col.length;++j)
					mat[j][i] = 0;
			}
		}
	}
	public static void fillZeros1(int[][] mat) {
		long row = 0L,col = 0L;
		for(int i=0;i<mat.length;++i){
			for(int j=0;j<mat[i].length;++j){
				if(mat[i][j] == 0) {
					row = row | (1<<i);
					col = col | (1<<j);
				}
			}
		}
		for(int i=0;i<mat.length;++i) {
			for(int j=0;j<mat[i].length;++j) {
				if((row & (1<<i)) >0 || (col & (1<<j))>0)
					mat[i][j] = 0;
			}
		}
	}

	public static void rotateRight(char[] inp,int s) {
		// reverse entire arr
		int n = inp.length;
		for(int i=0;i<n/2;++i) {
			char c = inp[i];
			inp[i] = inp[n-1-i];
			inp[n-1-i] = c;
		}

		// reverse 0..s
		for(int i=0;i<s/2;++i){
			char c = inp[i];
			inp[i] = inp[s-1-i];
			inp[s-1-i] = c;
		}

		//reverse n-s...n
		int limit = n-s;
		for(int i=0;i<(n-s)/2;++i){
			char c = inp[i+s];
			inp[i+s] = inp[n-1-i];
			inp[n-1-i] = c;
		}
		System.out.println(String.valueOf(inp));
	}
	/* find max h-index of a sorted array
	basically if I were to have an array of [ 0 3 4 7 8 9 10 ], my h-index would be 4 since I have 4 numbers bigger than 4
	 */
	public static int hIndexSorted(int[] arr) {
		int max = Integer.MIN_VALUE;
		for(int i=0;i<arr.length;++i){
			if((arr.length-i+1 >= arr[i]) && max < arr[i])
				max = arr[i];
		}
		return max;
	}
	//unsorted h-index 4 0 9 10 3 8 7
	public static int hIndexUnsorted(int[] arr) {
		int[] store = new int[arr.length+1];
		// store stores 0..arr.length-1 items that are lesser than arr.length. store[arr.length] contains items in arr that are > arr.length
		// 1 0 0 1 1 0 0 4
		for(int i=0;i<arr.length;++i) {
			store[Math.min(arr.length,arr[i])]++;
		}
		int sum = store[store.length-1];
		for(int i=store.length-2;i>=0;--i){
			sum += store[i];
			if(store[i]>0 && sum >= i)
				return i;
		}
		return 0;
	}

	/*
	 Write a method to compute all permutations of a string
	 */
	public static List<char[]> permutate(String inp) {
		int count = 0;
		List<char[]> prev = new ArrayList<char[]>();
		List<char[]> curr = new ArrayList<char[]>();
		prev.add(new char[]{inp.charAt(0)});
		for(int i=1;i<inp.length();++i){
			for(char[] str:prev){
				for(int j=0;j<str.length+1;++j) {
					char[] temp = new char[str.length+1];
					for(int k=0;k<j;++k){
						temp[k] = str[k];
					}
					temp[j] = inp.charAt(i);
					for(int k=j;k<str.length;++k){
						temp[k+1] = str[k];
					}
					curr.add(temp);
				}
			}
			prev = curr;
			curr = new ArrayList<char[]>();
		}
		System.out.println(prev.size());
		return prev;
	}
	/*
	 A majority element in an array A[] of size n is an element that appears more than n/2 times (and hence there is at most one such element).

	Write a function which takes an array and emits the majority element (if it exists), otherwise prints NONE as follows:

       I/P : 3 3 4 2 4 4 2 4 4
       O/P : 4 

       I/P : 3 3 4 2 4 4 2 4
       O/P : NONE
	 */
	public static int majElem(int[] arr){
		int count = 1, majIndex = 0;
		for(int i=1;i<arr.length;++i){
			if(arr[majIndex] == arr[i])
				++count;
			else
				--count;
			if(count ==0){
				count=1;
				majIndex = i;
			}
		}
		count = 0;
		for(int i=0;i<arr.length;++i){
			if(arr[i] == arr[majIndex])
				++count;
		}
		if(count > arr.length/2)
			return arr[majIndex];
		return -1;
	}
	/*
	 * Calculuate Moving average
	 */
	public static double movingAvg(int[] arr,int windowSize){
		Queue<Integer> q = new LinkedList<Integer>();
		int sum = 0;
		for(Integer i:arr){
			sum += i;
			q.add(i);
			if(q.size()>windowSize)
				sum -= q.remove();
		}
		System.out.println((double)sum/q.size());
		return sum/q.size();
	}
	/*
	 Randomly shuffle an array
	 */
	public static int[] shuffle(int[] arr) {
		for(int i=arr.length-1;i>0;--i){
			Random rand = new Random();
			int j = rand.nextInt(i+1);
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		return arr;
	}
	/*
	 Permutate a list of string 
	this question is supposed permutate the characters instead of who string, 
	as input example {"red", "fox", "super" }, the expected output is 
	 */
	public static void permutate(String[] words, int depth, String permutation){
		if (depth == words.length){
			System.out.println(permutation);	
		}
		else {
			String w = words[depth];
			for (int i = 0; i < w.length(); i++){
				permutate(words, depth + 1, permutation+w.charAt(i));
			}
		}
	}
	/*
	 Given a sorted array [0-99]
	 With input: [1, 5, 45, 86]
	 Write a function that prints the empty regions, example Output: “0,2-4,6-44,46-85,87-99”  
	 */
	public static void printEmptyIntervals(int[] inp) {
		int small = 0, large;
		for(int i=0;i<inp.length;++i) {
			large = inp[i] - 1;
			if(small == large) 
				System.out.println(small);
			else if(small < large)
				System.out.println(small + " - " + large);
			small = inp[i] + 1;
		}
		if(small < 99)
			System.out.println(small + " - " + 99);
		else if(small == 99)
			System.out.println(99);
	}
	/*
	  Given two (dictionary) words as Strings, determine if they are isomorphic. Two words are called isomorphic if the letters 
	  in one word can be remapped to get the second word. Example: 
	Given "foo", "app"; returns true we can map 'f' -> 'a' and 'o' -> 'p' 
	Given "bar", "foo"; returns false we can't map both 'a' and 'r' to 'o' 
	Given "turtle", "tletur" returns true, we can map 't' -> 't', 'u' -> 'l', 'r' -> 'e', 'l' -> 'u', 'e' -'r'
	Given "ab", "ca"; returns true we can map 'a' -> 'c', 'b'
	 */
	/* SOLUTION:
	 * Hash <char, firstseenindex> for each string. 

		The encoding of first seenindices shud match. 

		E.g. Foo and app both encode to 011 
		Abcd and hole both encode to 0123 

		Hate and hell do not match as encodings are 0123 and 0122 
	 */
	public static boolean isIsomorphic(String inp1, String inp2) {
		Map<Character,Integer> map1 = new HashMap<Character,Integer>();
		Map<Character,Integer> map2 = new HashMap<Character,Integer>();
		if(inp1.length() != inp2.length())
			return false;
		for(int i=0;i<inp1.length();++i) {
			if(!map1.containsKey(inp1.charAt(i))) {
				if(map2.containsKey(inp2.charAt(i)))
					return false;
				map1.put(inp1.charAt(i),i);
				map2.put(inp2.charAt(i),i);
			} else {
				if(!map2.containsKey(inp2.charAt(i)))
					return false;
			}
		}
		return true;
		/*Set<Character> set = new HashSet<Character>();
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<inp1.length();++i) {
			if(!set.contains(inp1.charAt(i)))  {
				set.add(inp1.charAt(i));
				list.add(i);
			}
		}
		set = new HashSet<Character>();
		int currIndex = 0;
		for(int i=0;i<inp2.length();++i) {
			if(!set.contains(inp2.charAt(i))) {
				set.add(inp2.charAt(i));
			} else
			{
				if(list.get(currIndex) != i)
					return false;
			}
			currIndex++;
		}
		return true;*/
	}
	//write a function that takes a string and check if it is a number
	// test cases: 0, -12, 25, -12.23, 23.34, 0.0, 1235172351763.12368712638176381
	// "", null, 12., -, 12..23, 12.23-, 12b,
	public static boolean checkNum(String inp) {
		if(inp == null || inp == "")
			return false;
		boolean decimalSeen = false;
		for(int i=0;i<inp.length();++i) {
			if(inp.charAt(i) == '-' && i == 0){
				if(inp.length() == 1)
					return false;
			}
			else if(inp.charAt(i) == '.') {
				if(decimalSeen || inp.length()==1 || i==0)
					return false;
				decimalSeen = true;
			}
			else if(inp.charAt(i) < 48 || inp.charAt(i) > 57)
				return false;
		}
		return true;
	}

	//Find all the permutations of a string  
	private List<String> permString(String inp) {
		List<String> list = new ArrayList<String>();
		if(inp.length() == 1) {
			list.add(inp);
			return list;
		}
		List<String> out = permString(inp.substring(1));
		for(String s:out) {
			for(String tmp:append(s,inp.charAt(0)))
				list.add(tmp);
		}
		return list;

	}
	private List<String> append(String str, char c) {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<str.length();++i) {
			String tmp = str.substring(0,i) + c + str.substring(i,str.length());
			list.add(tmp);
		}
		list.add(str+c);
		return list;   
	}

	//given a sorted array that has been rotated, find a number in that array
	public int findInRotated(int[] inp,int num) {
		if(inp == null)
			return -1;
		int l = 0;
		int r = inp.length-1;
		while(l<=r) {
			int mid = (l+r)/2;
			if(inp[mid] == num)
				return mid;
			if(inp[l] <= inp[mid]) {
				if(inp[l] <= num && num < inp[mid])
					r = mid-1;
				else
					l = mid+1;
			} else {
				if(inp[mid] < num && num <= inp[r])
					l = mid+1;
				else
					r = mid-1;
			}
		}
		return -1;
	}

	//Write a program to replace 0s with 5 in a given number.
	public int replaceZero(int num) {
		int temp = num;
		int place = 1;
		while(temp != 0) {
			int rem = temp % 10;
			if(rem == 0)
				num = num < 0 ? num-(place*5) : num+(place*5);
				place *= 10;
				temp = temp/10;
		}
		return num;
	}

	/*
	 * given an array of integers A[1..n], make a new array B[1..n] where B[i] is the product of everythimg in A excluding A[i]
	 */
	public int[] selfExProduct(int[] arr) {
		int numZeros = 0, prod = 1;
		int[] out = new int[arr.length];
		for(int i=0;i<arr.length;++i) {
			if(arr[i] == 0)
				numZeros++;
			else
				prod *= arr[i];
			if(numZeros > 1) {
				prod = 0;
				break;
			}
		}
		for(int i=0;i<arr.length;++i) {
			if(arr[i] == 0){
				out[i] = prod;
			}
			else {
				if(numZeros == 1)
					out[i] = 0;
				else
					out[i] = prod/arr[i];
			}
		}
		return out;
	}

	//merge 2 sorted lists
	public int[] mergeSorted(int[] l1,int[] l2) {
		int itr1=0,itr2=0,itr3=0;;
		int[] out = new int[l1.length+l2.length];
		while(itr1<l1.length && itr2<l2.length) {
			if(l1[itr1] <= l2[itr2])
				out[itr3++] = l1[itr1++];
			else
				out[itr3++] = l2[itr2++];
		}
		while(itr1<l1.length)
			out[itr3++] = l1[itr1++];
		while(itr2<l2.length)
			out[itr3++] = l2[itr2++];
		return out;
	}

	/*
	Write a program that takes an integer and prints out all ways to multiply smaller integers that equal the original number, 
	without repeating sets of factors. In other words, if your output contains 4 * 3, 
	you should not print out 3 * 4 again as that would be a repeating set. 
	Note that this is not asking for prime factorization only. 
	Also, you can assume that the input integers are reasonable in size; correctness is more important than efficiency.
	PrintFactors(12)
	12 * 1
	6 * 2
	4 * 3
	3 * 2 * 2
	 */
	public void findFactors(int num) {
		if(num == 0)
			return;
		System.out.println(num+"*"+1);
		findFactorsHelper("", num, num);
	}

	private void findFactorsHelper(String curr,int dividend,int prevFactor) {
		for(int factor=dividend-1;factor>=2;--factor){
			if(dividend%factor==0 && factor<=prevFactor) {
				int nextfactor = dividend/factor;
				if(nextfactor<=factor) {
					//if(nextfactor<=prevFactor)
					System.out.println(curr+factor+"*"+nextfactor);
				}
				findFactorsHelper(curr+factor+"*",nextfactor,factor);
			}
		}
	}

	/*
	 given a target and a sorted array, find the element that is strictly larger than the target.
	i.e. {a,c,d,e} b output: c
	 */
	public char findLarger(char[] arr,char inp) {
		int start = 0, end = arr.length-1;
		int minDist = Integer.MAX_VALUE, nextIndex=-1;
		while(start <= end) {
			int mid = (start+end)/2;
			if(inp > arr[mid])
				start = mid+1;
			else {
				if((arr[mid] - inp) > 0 && (arr[mid] - inp) < minDist) {
					minDist = arr[mid] - inp;
					nextIndex = mid;
				}
				end = mid-1;
			}
		}
		return (nextIndex == -1) ? '\0' : arr[nextIndex];
	}

	// given a target and a sorted array, find the highest value smaller than the target
	public int finddHighestSmallerValue(int[] arr,int target){
		int s=0, e=arr.length-1;
		int minDiff=Integer.MAX_VALUE;
		int max=-1;
		while(s<=e){
			int mid = (s+e)/2;
			if(arr[mid]==target){
				if(mid>0)
					return arr[mid-1];
				else
					return Integer.MIN_VALUE;
			} else if(arr[mid]<target){
				if(target-arr[mid]<minDiff){
					minDiff = target-arr[mid];
					max=mid;
				}
				s=mid+1;
			} else {
				e=mid-1;
			}
		}
		if(max==-1)
			return Integer.MIN_VALUE;
		return arr[max];
	}

	public int[] reverseGroup(int[] arr,int size){
		if(arr==null || arr.length==0 || size==1)
			return arr;
		for(int i=0;i<arr.length;){
			int nextInd = i;
			for(int j=Math.min(i+size-1,arr.length-1);j>i;--j){
				int temp = arr[i];
				arr[i++] = arr[j];
				arr[j] = temp;
			}
			i= nextInd+size;
		}
		return arr;
	}

	//10.	Given an array of positive integers and a target integer, find if there is a consecutive subarray that sums to the target. 
	//E.g, given {5,6,4,12}, findsum(10)=true, findsum(11)=false
	public int[] continuousSubarraySum(int[] arr,int val){
		if(arr==null ||arr.length==0)
			return new int[0];
		int currSum=arr[0];
		int start=0,i=1;
		for(;i<arr.length;++i){
			while(currSum>val && start<i-1){
				currSum -= arr[start];
				++start;
			}
			if(currSum==val){
				int[] out = new int[i-start];
				System.arraycopy(arr,start,out,0,i-start);
				return out;
			}
			if(currSum<val){
				currSum += arr[i];
			}
		}
		return new int[0];
	}
	
	// choose an array of size m from an array of size n randomly
	public int[] chooseRandomSubset(int[] arr, int m) {
		if(arr==null || arr.length==0 || m==0)
			return new int[0];
		if(m>arr.length)
			return null;
		int[] sub = new int[m];
		for(int i=0;i<m;++i)
			sub[i] = arr[i];
		for(int i=m;i<arr.length;++i){
			Random rand = new Random();
			int next = rand.nextInt(i+1);
			if(next<m)
				sub[next] = arr[i];
		}
		return sub;
	}
	
	//Given an array of integer, find the maximum drop between two array elements, given that second element comes after the first one.
	// i.e. maximize a[m]-a[n], where m>n
	public int maxDiff(int[] arr){
		if(arr==null||arr.length==0)
			return 0;
		int max=arr[0];
		int maxDiff=0;
		for(int i=1;i<arr.length;++i){
			if(arr[i]>max)
				max=arr[i];
			else {
				maxDiff=Math.max(max-arr[i],maxDiff);
			}
		}
		return maxDiff;
	}
}
