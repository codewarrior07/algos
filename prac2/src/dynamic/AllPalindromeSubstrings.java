package dynamic;

import java.util.ArrayList;
import java.util.List;

public class AllPalindromeSubstrings {
	public static void main(String[] args){
		//System.out.println("abc".substring(0,3));
		for(String s:new AllPalindromeSubstrings().allPal("abcxxcbaba"))
			System.out.println(s);
	}
	
	public List<String> allPal(String inp){
		List<String> out = new ArrayList<String>();
		for(int i=0;i<inp.length();++i){
			// check for i,i centers
			int l=i,r=i;
			while(l>=0 && r<inp.length() && inp.charAt(l) == inp.charAt(r)) {
			
				--l;
				++r;
				if(r-l-1 > 1)
					out.add(inp.substring(l+1,r));
			}
			// check for i,i+1 centers
			l=i;
			r=i+1;
			while(l>=0 && r<inp.length() && inp.charAt(l) == inp.charAt(r)) {
				
				--l;
				++r;
				if(r-l-1 > 1)
					out.add(inp.substring(l+1,r));
			}
		}
		return out;
		
	}
}
