package dynamic;

public class LongestPalindromeSubsequence {
	
	public static void main(String[] args) {
	    //System.out.println(getLongestPalindromicSubSequenceSize("XAYBZA"));
	    System.out.println(getLongestPalindromicSubSequence("BBABCBCAB"));
		//temp("XAYBZA");
	}
	
	public static int getLongestPalindromicSubSequenceSize(String source){
	    int n = source.length();
	    int[][] LP = new int[n][n];
	     
	    //All sub strings with single character will be a plindrome of size 1
	    for(int i=0; i < n; i++){
	        LP[i][i] = 1;
	    }
	    //Here gap represents gap between i and j.
	    for(int gap=1;gap<n;gap++){
	        for(int i=0;i<n-gap;i++ ){
	                    int j=i+gap;
	                    if(source.charAt(i)==source.charAt(j) && gap==1)
	                        LP[i][j]=2;
	                    else if(source.charAt(i)==source.charAt(j))
	                        LP[i][j]=LP[i+1][j-1]+2;
	                    else
	                        LP[i][j]= Math.max(LP[i][j-1], LP[i+1][j]);              
	         }      
	    }       
	    return LP[0][n-1];      
	}
	
	
	/*
	 This can be solved in O(n^2) using dynamic programming. Basically, the problem is about building the longest palindromic subsequence in x[i...j] 
	 using the longest subsequence for x[i+1...j], x[i,...j-1] and x[i+1,...,j-1] (if first and last letters are the same).

	 Firstly, the empty string and a single character string is trivially a palindrome. 
	 Notice that for a substring x[i,...,j], if x[i]==x[j], we can say that the length of the longest palindrome is the longest palindrome over 
	 x[i+1,...,j-1]+2. If they don't match, the longest palindrome is the maximum of that of x[i+1,...,j] and y[i,...,j-1].

	This gives us the function:

	longest(i,j)= 	j-i+1 if j-i<=0,
              		2+longest(i+1,j-1) if x[i]==x[j]
              		max(longest(i+1,j),longest(i,j-1)) otherwise
	 */
	public static String getLongestPalindromicSubSequence(String source){
	    int n = source.length();
	    int[][] LP = new int[n][n];
	     
	    //All sub strings with single character will be a plindrome of size 1
	    for(int i=0; i < n; i++){
	        LP[i][i] = 1;
	    }
	    //Here gap represents gap between i and j.
	    for(int gap=1;gap<n;gap++){
	        for(int i=0;i<n-gap;i++ ){
	                    int j=i+gap;
	                    if(source.charAt(i)==source.charAt(j) && gap==1)
	                        LP[i][j]=2;
	                    else if(source.charAt(i)==source.charAt(j))
	                        LP[i][j]=LP[i+1][j-1]+2;
	                    else
	                        LP[i][j]= Math.max(LP[i][j-1], LP[i+1][j]);              
	         }      
	    }   
	    //Rebuilding string from LP matrix
	    StringBuilder strBuff = new StringBuilder();
	    int x = 0;
	    int y = n-1;
	    while(x < y){
	        if(source.charAt(x) == source.charAt(y)){
	            strBuff.append(source.charAt(x));
	            x++;
	            y--;
	        } else if(LP[x][y-1] > LP[x+1][y]){
	            y--;
	        } else {
	            x++;
	        }
	    }
	    StringBuilder strBuffCopy = new StringBuilder(strBuff);
	    String str = strBuffCopy.reverse().toString();
	    if(x == y){          
	        strBuff.append(source.charAt(x)).append(str);
	    } else {
	        strBuff.append(str);
	    }
	    return strBuff.toString();
	}
}
