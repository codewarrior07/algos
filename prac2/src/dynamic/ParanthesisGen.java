package dynamic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParanthesisGen {
	public static void main(String[] args) {
		/*List<String> res = new ParanthesisGen().getParenEfficient(3);
		for(String s:res)
			System.out.println(s);*/
		//System.out.println(new ParanthesisGen().getNParen(3));
		System.out.println(new ParanthesisGen().genParenDynamic(3));
	}
	
	//use this
	public List<String> genParenDynamic(int n) {
		List<String> res = new ArrayList<>();
		genParenDynamic(res,n,n,"");
		return res;
	}
	private void genParenDynamic(List<String> res, int left, int right, String str) {
		if(left>right)
			return;
		if(left==0 && right==0)
			res.add(str);
		if(left>0)
			genParenDynamic(res,left-1,right,str+"(");
		if(right>0)
			genParenDynamic(res,left,right-1,str+")");
	}
	
	//inefficient
	public Set<String> getNParen(int n) {
		Set<String> res = new HashSet<String>();
		if(n==1)
			res.add("()");
		else {
			Set<String> prev = getNParen(n-1);
			for(String s:prev) {
				for(int i=0;i<s.length();++i) {
					String out = s.substring(0,i)+"()"+s.substring(i);
					res.add(out);
				}
			}
		}
		return res;
	}
	
	
	
}
