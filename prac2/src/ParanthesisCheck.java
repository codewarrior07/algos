import java.util.Stack;


public class ParanthesisCheck {
	public static void main(String[] args){
		ParanthesisCheck p = new ParanthesisCheck();
		System.out.println(p.checkParan2("(()((()))))"));
		// ())(
	}
	
	//use this - O(n) O(1)
		public boolean checkParan2(String inp){
			if(inp==null || inp.length()==0)
				return true;
			int open=0,close=0;
			for(int i=0;i<inp.length();++i){
				if(inp.charAt(i)=='(')
					++open;
				else if(inp.charAt(i)==')')
					++close;
				if(close>open)
					return false;
			}
			if(open!=close)
				return false;
			return true;
		}
	
	public boolean checkParan(String inp){
		if(inp==null || inp.length()==0)
			return true;
		Stack<Character> st = new Stack<Character>();
		for(int i=0;i<inp.length();++i){
			if(inp.charAt(i)=='(')
				st.push(inp.charAt(i));
			else if(inp.charAt(i)==')'){
				if(st.isEmpty())
					return false;
				if(st.pop() != '(')
					return false;
			}
		}
		if(!st.isEmpty())
			return false;
		return true;
	}
	
}
