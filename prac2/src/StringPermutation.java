import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StringPermutation {
	public static void main(String[] args){
		for(String s:findPerm("abcd"))
			System.out.println(s);
	}
	// find all possible permutations
	public static List<String> findPerm(String inp){
		return findPerm(inp,inp.length()-1);
	}
	public static List<String> findPerm(String inp,int len){
		if(len == 0){
			List<String> list = new ArrayList<String>();
			list.add(String.valueOf(inp.charAt(len)));
			return list;
		}
		List<String> temp = findPerm(inp,len-1);
		List<String> fin = new ArrayList<String>();
		for(String s:temp){
			char c = inp.charAt(len);
			for(int i=0;i<=s.length();++i){
				String t = s.substring(0,i)+c+s.substring(i);
				fin.add(t);
			}
			//fin.add(s);
		}
		//fin.add(String.valueOf( inp.charAt(len)));
		return fin;
	}

	//Find all the permutations of a string  
	private static List<String> permString(String inp) {
		List<String> list = new ArrayList<String>();
		if(inp.length() == 1) {
			list.add(inp);
			return list;
		}
		List<String> out = permString(inp.substring(1));
		for(String s:out) {
			for(String tmp:append(s,inp.charAt(0)))
				list.add(tmp);
		}
		return list;

	}
	private static List<String> append(String str, char c) {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<str.length();++i) {
			String tmp = str.substring(0,i) + c + str.substring(i,str.length());
			list.add(tmp);
		}
		list.add(str+c);
		return list;   
	}
	
	public static List<String> perm(String str,int ind) {
		if(ind==str.length()-1){
			List<String> list = new ArrayList<>();
			list.add(String.valueOf(str.charAt(ind)));
			return list;
		}
		List<String> list = perm(str,ind+1);
		List<String> currList = new ArrayList<>();
		char c = str.charAt(ind);
		for(String curr:list) {
			for(int i=0;i<=curr.length();++i){
				String s = curr.substring(0,i) + c + curr.substring(i,curr.length());
				currList.add(s);
			}
		}
		return currList;
	}
	
	// Write a method to compute all permutations of a string whose
	// characters are not necessarily unique. The list of permutations should not have duplicates
	// CTC prob 8.8
	public static List<String> permDups(String inp) {
		Map<Character,Integer> hist = new HashMap<>();
		for(Character c:inp.toCharArray()) {
			if(hist.containsKey(c))
				hist.put(c, hist.get(c)+1);
			else
				hist.put(c, 1);
		}
		List<String> result = new ArrayList<>();
		permDups(hist,"",inp.length(),result);
		return result;
	}
	private static void permDups(Map<Character,Integer> hist, String prefix,int rem,List<String> result) {
		if(rem==0) {
			result.add(prefix);
			return;
		}
		for(Character c:hist.keySet()) {
			int count = hist.get(c);
			if(count>0) {
				hist.put(c, count-1);
				permDups(hist,prefix+c,rem-1,result);
				hist.put(c, count);
			}
		}
	}
}
