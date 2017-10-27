import java.util.ArrayList;
import java.util.List;


public class PowerSet {

	public static void main(String[] args) {
		List<Integer> set = new ArrayList<>();
		set.add(3);
		set.add(4);
		set.add(5);
		List<List<Integer>> list = powerSet(set,0);
		System.out.println(list);
	}
	
	public static List<List<Integer>> powerSet(List<Integer> set, int index) {
		List<List<Integer>> allSet;
		if(set.size()==index) {
			allSet = new ArrayList<>();
			allSet.add(new ArrayList<Integer>());
			return allSet;
		}
		allSet = powerSet(set, index+1);
		int curr = set.get(index);
		List<List<Integer>> inner = new ArrayList<List<Integer>>();
		for(List<Integer> list:allSet) {
			List<Integer> newList = new ArrayList<>(list);
			newList.add(curr);
			inner.add(newList);
		}
		allSet.addAll(inner);
		return allSet;
	}

}
