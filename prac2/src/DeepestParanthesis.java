/*
  We are given a string having parenthesis like below
     “( ((X)) (((Y))) )”
	We need to find the maximum depth of balanced parenthesis, like 4 in above example. Since ‘Y’ is surrounded by 4 balanced parenthesis.
	If parenthesis are unbalanced then return -1.
	http://www.geeksforgeeks.org/find-maximum-depth-nested-parenthesis-string/
 */
public class DeepestParanthesis {
	public static void main(String[] args){
		System.out.println(deepest("( p((q)) ((s)t) )"));
	}
	public static int deepest(String inp){
		int curr=0,max=0;
		for(int i=0;i<inp.length();++i){
			if(inp.charAt(i)=='('){
				++curr;
				max = curr>max?curr:max;
			}
			else if(inp.charAt(i)==')'){
				--curr;
				if(curr<0)
					return -1;
			}
		}
		if(curr>0)
			return -1;
		return max;
	}
}
