package dynamic;

public class LongestPalindromeSubstring {
	public static void main(String[] args){
		System.out.println(getLongestPal2("abcxxcbaba"));
	}
	
	//use this
	public static String getLongestPal2(String inp){
		int start=0,end=0;
		int maxLen = 0;
		for(int i=0;i<inp.length();++i){
			// check for i,i centers
			int l=i,r=i;
			while(l>=0 && r<inp.length() && inp.charAt(l) == inp.charAt(r)) {
				--l;
				++r;
			}
			if(r-l-1 > maxLen) {
				maxLen = r-l-1;
				start = l+1;
				end=r;
			}
			
			// check for i,i+1 centers
			l=i;
			r=i+1;
			while(l>=0 && r<inp.length() && inp.charAt(l) == inp.charAt(r)) {
				--l;
				++r;
			}
			if(r-l-1 > maxLen) {
				maxLen = r-l-1;
				start = l+1;
				end=r;
			}
		}
		System.out.println(maxLen);
		return inp.substring(start,end);
	}
	
	public static String getLongestPal(String inp){
		String temp = inp.substring(0,1);
		String out = inp.substring(0,1);
		int maxLen = 0;
		for(int i=0;i<inp.length();++i){
			temp = expand(inp,i,i);
			if(temp.length() > maxLen) {
				maxLen = temp.length();
				out = temp;
			}
			temp = expand(inp,i,i+1);
			if(temp.length() > maxLen) {
				maxLen = temp.length();
				out = temp;
			}
		}
		return out;
	}
	
	private static String expand(String inp,int l,int r){
		while(l>=0 && r<inp.length() && inp.charAt(l) == inp.charAt(r)) {
			--l;
			++r;
		}
		return inp.substring(l+1,r);
	}
	
	
}
