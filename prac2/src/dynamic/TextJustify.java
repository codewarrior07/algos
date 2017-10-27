package dynamic;

import java.util.ArrayList;
import java.util.List;

public class TextJustify {
	public static void main(String[] args){
		//for(String s:justifyGreedy(new String[]{"This", "is", "an", "example", "of", "text", "justification."},16))
		//	System.out.println(s);
		System.out.println(justify(new String[]{"abcdef","likes","xy","pqrs"},10));
	}
	/*
	 Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
	You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
	Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, 
	the empty slots on the left will be assigned more spaces than the slots on the right.
	For the last line of text, it should be left justified and no extra space is inserted between words.
	
	For example,
	words: ["This", "is", "an", "example", "of", "text", "justification."]
	L: 16.
	Return the formatted lines as:
	[
   	"This    is    an",
   	"example  of text",
   	"justification.  "
	]
	 */
	
	public static List<String> justifyGreedy(String[] words,int L){
		List<String> out = new ArrayList<String>();
		int n = words.length;
		for(int i=0,w;i<n;i=w){
			int len = -1;
			for(w=i;w<n && len+words[w].length()+1 <=L;++w)
				len += words[w].length()+1;
			StringBuilder str = new StringBuilder(words[i]);
			int space=1,extra=0;
			if(w!=i+1 && w!=n){
				space = (L-len)/(w-i-1) +1;
				extra = (L-len)%(w-i-1);
			}
			for(int j=i+1;j<w;++j){
				for(int s=space;s>0;--s)
					str.append('#');
				if(extra-->0) 
					str.append('#');
				str.append(words[j]);
			}
			int strLen = L-str.length();
			while(strLen-->0)
				str.append('#');
			out.add(str.toString());
		}
		return out;
	}
	
	/*
	 Word Wrap / String Justification algorithm. 
	Given a set of words and a length. 
	You are required to print the words such that the words on each line end almost on the same column and the number of 
	trailing spaces at the end is minimized. 
	
	Given aaa bb cc ddddd and length is 5 print the following output. 
	aaa 
	bb cc 
	ddddd
	 */
	public static String justify(String[] inp,int width){
		int[][] cost = new int[inp.length][inp.length];
		for(int i=0;i<inp.length;++i){
			cost[i][i] = width-inp[i].length();
			for(int j=i+1;j<inp.length;++j)
				cost[i][j] = cost[i][j-1] - inp[j].length()-1;
		}
		for(int i=0;i<inp.length;++i){
			for(int j=i;j<inp.length;++j){
				if(cost[i][j] < 0)
					cost[i][j] = Integer.MAX_VALUE;
				else
					cost[i][j] = cost[i][j]*cost[i][j];
			}
		}
		
		for(int i=0;i<cost.length;++i){
			for(int j=0;j<cost.length;++j){
				if(cost[i][j]==Integer.MAX_VALUE)
					System.out.print("I"+ " ");
				else
					System.out.print(cost[i][j]+ " ");
			}
			System.out.println();
		}
		
		int[] minCost = new int[inp.length];
		int[] result = new int[inp.length];
		for(int i=inp.length-1;i>=0;--i){
			minCost[i] = cost[i][inp.length-1];
			result[i] = inp.length;
			for(int j=inp.length-1;j>i;--j){
				if(cost[i][j-1] != Integer.MAX_VALUE){
					if(cost[i][j-1]+minCost[j] < minCost[i]){
						minCost[i] = cost[i][j-1]+minCost[j];
						result[i] = j;
					}
				}
			}
		}
		
		StringBuilder str = new StringBuilder();
		for(int i=0;i<inp.length;){
			int curr = result[i];
			for(int j=i;j<curr;++j){
				str.append(inp[j]);
				if(j != curr-1)
					str.append("#");
			}
			str.append("\n");
			i=curr;
		}
		return str.toString();
	}
}
