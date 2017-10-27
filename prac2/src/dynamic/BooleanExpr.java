package dynamic;

import java.util.HashMap;
import java.util.Map;

public class BooleanExpr {
	public static void main(String[] args){
		BooleanExpr expr = new BooleanExpr();
		System.out.println(expr.getWays("1^0|0|1",true));
	}
	public int getWays(String expr,boolean res){
		return getWaysHelper(expr,res,0,expr.length()-1,new HashMap<String,Integer>());
	}
	public int getWaysHelper(String expr,boolean res,int start,int end,Map<String,Integer> mem){
		String key = ""+res+start+end;
		if(mem.containsKey(key))
			return mem.get(key);
		if(start==end){
			if(expr.charAt(start)=='1'&&res)
				return 1;
			if(expr.charAt(start)=='0'&&!res)
				return 1;
			return 0;
		}
		int sum=0;
		if(res){
			for(int i=start+1;i<=end;++i){
				if(expr.charAt(i)=='&')
					sum += getWaysHelper(expr,true,start,i-1,mem)*getWaysHelper(expr,true,i+1,end,mem);
				else if(expr.charAt(i)=='|')
					sum += getWaysHelper(expr,true,start,i-1,mem)*getWaysHelper(expr,true,i+1,end,mem) +
							getWaysHelper(expr,false,start,i-1,mem)*getWaysHelper(expr,true,i+1,end,mem) +
							getWaysHelper(expr,true,start,i-1,mem)*getWaysHelper(expr,false,i+1,end,mem);
				else if(expr.charAt(i)=='^')
					sum+= getWaysHelper(expr,true,start,i-1,mem)*getWaysHelper(expr,false,i+1,end,mem) +
							getWaysHelper(expr,false,start,i-1,mem)*getWaysHelper(expr,true,i+1,end,mem);
			}
		} else {
			for(int i=start+1;i<=end;++i){
				if(expr.charAt(i)=='&')
					sum += getWaysHelper(expr,false,start,i-1,mem)*getWaysHelper(expr,false,i+1,end,mem) +
					getWaysHelper(expr,false,start,i-1,mem)*getWaysHelper(expr,true,i+1,end,mem) +
					getWaysHelper(expr,true,start,i-1,mem)*getWaysHelper(expr,false,i+1,end,mem);
				else if(expr.charAt(i)=='|')
					sum += getWaysHelper(expr,false,start,i-1,mem)*getWaysHelper(expr,false,i+1,end,mem);
				else if(expr.charAt(i)=='^')
					sum+= getWaysHelper(expr,true,start,i-1,mem)*getWaysHelper(expr,true,i+1,end,mem) +
					getWaysHelper(expr,false,start,i-1,mem)*getWaysHelper(expr,false,i+1,end,mem);
			}
		}
		mem.put(key,sum);
		return sum;
	}
}


