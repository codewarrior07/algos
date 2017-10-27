import java.util.EmptyStackException;
import java.util.Stack;

public class PostFixExpr {
	public static void main(String[] args){
		PostFixExpr expr = new PostFixExpr();
		System.out.println(expr.evalPostFix(new String[]{"12","3","/","5","-"}));
	}
	public double evalPostFix(String[] inp) {
	    Stack<Double> st = new Stack<Double>();
	    for(int i=0;i<inp.length;++i) {
	        if(checkNum(inp[i])) 
	            st.push(Double.parseDouble(inp[i]));
	        else {
	        	try {
	        		double op2 = st.pop();
	        		double op1 = st.pop();
	        		if(inp[i].equals("+"))
		                st.push(op1 + op2);
		            else if(inp[i].equals("-"))
		                st.push(op1-op2);
		            else if(inp[i].equals("*"))
		                st.push(op1*op2);
		            else if(inp[i].equals("/"))
		                st.push(op1/op2);
		            else
		            	throw new IllegalArgumentException("Not well formed RPN");
	        	} catch (EmptyStackException e) {
	        		throw new IllegalArgumentException("Not well formed RPN");
	        	}
	        }    
	    } // end for loop
	    double res= st.pop();
	    if(!st.isEmpty())
	    	throw new IllegalArgumentException("Not well formed RPN");
	    return res;
	}
	
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
}
