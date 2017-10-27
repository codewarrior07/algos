import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TwoSumImpl implements TwoSum{
    private Set<Integer> set;
    public TwoSumImpl() {
        set = new HashSet<Integer>();
    }
    
    @Override
    public void store(int input) {
        set.add(input);
    }
    
    @Override
    public boolean test(int val) {
        for(Integer i:set) {
            if(set.contains(val-i))
                return true;
        }
        return false;
    }
    
    public int[] twoSum(int[] numbers, int target) {

        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < numbers.length; i++){
            int diff = target - numbers[i];
            if(hash.containsKey(diff)){
                int toReturn[] = {hash.get(diff)+1, i+1};
                return toReturn;
            }

            hash.put(numbers[i], i);

        }

        return null;

    }
    
    public static void main(String[] args) {
    	TwoSumImpl obj = new TwoSumImpl();
    	obj.store(1);
    	obj.store(-2);
    	obj.store(3);
    	obj.store(-16);
    	obj.store(6);
    	System.out.println(obj.test(0));
    }
}
interface TwoSum {
    /**
     * Stores @param input in an internal data structure.
     */
    void store(int input);
 
    /**
     * Returns true if there is any pair of numbers in the internal data structure which
     * have sum @param val, and false otherwise.
     * For example, if the numbers 1, -2, 3, and 6 had been stored,
     * the method should return true for 4, -1, and 9, but false for 10, 5, and 0
     */
    boolean test(int val);
}
