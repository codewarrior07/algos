import java.util.List;
/** 
* Given a nested list of integers, returns the sum of all integers in the list weighted by their depth 
* For example, given the list {{1,1},2,{1,1}} the function should return 10 (four 1's at depth 2, one 2 at depth 1) 
* Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3) 
*/ 
public class DepthSum{
	public int depthSum (List<NestedInteger> input) {
	    if(input == null)
	        return 0;
	    return depthSumHelper(input,1);
	}

	private int depthSumHelper(List<NestedInteger> inp, int depth) {
	    int sum = 0;
	    for(int i=0;i<inp.size();++i) {
	        if(inp.get(i).isInteger())
	            sum += inp.get(i).getInteger()*depth;
	        else {
	            sum += depthSumHelper(inp.get(i).getList(),depth+1);
	        }
	    }
	    return sum;
	}
	
	public int reverseDepthSum (List<NestedInteger> input)
	{
	    if(input == null)
	        return 0;
	    return reverseDepthSumHelper(input, 1, findDepth(input));    
	}
	//{1,1}, 2, {1,1},depth=1 maxDepth = 2
	// {1,1}, 2, 2 -> sum = 2
	// 2 -? 2*(2) -> sum = 4
	// {1,1}, 2, 2 -> sum = 2

	private int reverseDepthSumHelper(List<NestedInteger> input, int depth,int maxDepth) {
	    int sum = 0;
	    for(int i=0;i<input.size();++i) {
	        if(input.get(i).isInteger()) 
	            sum += input.get(i).getInteger() * (maxDepth - depth+1);
	        else 
	            sum += reverseDepthSumHelper(input.get(i).getList(), depth+1, maxDepth);
	    }
	    return sum;
	}

	// {1,1}, 2, {1,1}
	// {1,1} - 1 max = 1
	// 2 
	// {1,1} - 1 
	// findDepth(null); 
	// findDepth({1,1}); depth = 1
	// findDepth({{1},2,2,{0,{3,4}}) depth = 3
	// {{{{{1}}}},{1}}
	// max = 4
	// nextDepth = 1
	private int findDepth(List<NestedInteger> input) {
	    int max = 0;
	    for(int i=0;i<input.size();++i) {
	       if(!input.get(i).isInteger()) {
	           int nextDepth = findDepth(input.get(i).getList());
	           if(nextDepth > max)
	               max = nextDepth;
	       }
	    }
	    return 1 + max;
	}
}

interface NestedInteger 
{ 
	/** @return true if this NestedInteger holds a single integer, rather than a nested list */ 
	boolean isInteger(); 

	/** @return the single integer that this NestedInteger holds, if it holds a single integer 
	 * Return null if this NestedInteger holds a nested list */ 
	Integer getInteger(); 

	/** @return the nested list that this NestedInteger holds, if it holds a nested list 
	 * Return null if this NestedInteger holds a single integer */ 
	List<NestedInteger> getList(); 
}
