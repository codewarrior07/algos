import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*This class will be given a list of words (such as might be tokenized
 * from a paragraph of text), and will provide a method that takes two
 * words and returns the shortest distance (in words) between those two
 * words in the provided text.
 * Example:
 * WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
 * assert(finder.distance("fox","the") == 3);
 * assert(finder.distance("quick", "fox") == 1);
*/
public class WordDistanceFinder {
    Map<String,List<Integer>> map;
    public WordDistanceFinder(List<String> list){
        map = new HashMap<String,List<Integer>>();
        for(int i=0;i<list.size();++i) {
            List<Integer> curr;
            if(map.containsKey(list.get(i)))
                curr = map.get(list.get(i));
            else
                curr = new ArrayList<Integer>();
            curr.add(i);
            map.put(list.get(i),curr);
        }
    }
    
    public int distance(String inp1, String inp2) {
        List<Integer> l1=null,l2=null;
        for(Map.Entry<String,List<Integer>> entr:map.entrySet()) {
            if(entr.getKey().equals(inp1))
                l1 = entr.getValue();
            else if(entr.getKey().equals(inp2)) 
                l2 = entr.getValue();
            if(l1 != null && l2 != null)
                break;
        }
        if(l1 == null || l2 == null)
            return -1;
        int minDist = Integer.MAX_VALUE;
        int itr1 = 0, itr2 = 0;
        while(itr1 < l1.size() && itr2 < l2.size()) {
            if( minDist > Math.abs(l1.get(itr1) - l2.get(itr2))) {
                minDist = Math.abs(l1.get(itr1) - l2.get(itr2));
                if(l1.get(itr1) <= l2.get(itr2)) {
                    itr1++;
                    itr2 = 0;
                } else {
                    itr2++;
                }
            }
        }
        return minDist;
    }
    
    
    public static void main(String[] args) {
    	//WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
    	//assert(finder.distance("fox","the") == 3);
    	//System.out.println(finder.distance("fox", "quick"));
    	Set<Integer> set = new HashSet<Integer>();
    	set.add(1);
    	set.add(-2);
    	set.add(3);
    	set.add(6);
    	for(int i:set){
    		if(set.contains(0-i) && (0-i)!=i)
    			System.out.println(true);
    	}
    }
}