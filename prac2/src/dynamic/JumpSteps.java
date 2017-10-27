/*
 * 	A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or
	3 steps at a time. Implement a method to count how many possible ways the child
	can run up the stairs
 */

package dynamic;

import java.util.HashMap;
import java.util.Map;

public class JumpSteps {
	public static void main(String[] args){
		System.out.println(jumps(36));
	}
	public static int jumps(int n){
		if(n<=0)
			return 0;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(0,1);
		return jumps(n,map);
	}
	public static int jumps(int n,Map<Integer,Integer> map){
		if(n<0)
			return 0;
		if(map.containsKey(n))
			return map.get(n);
		map.put(n,jumps(n-1,map)+jumps(n-2,map)+jumps(n-3,map));
		return map.get(n);
	}
}
