import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FindAllAnagrams {
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("xyx");
		list.add("race");
		list.add("acer");
		list.add("rear");
		list.add("yxx");
		for(List<String> l:findAllAnagrams(list))
			System.out.println(l);
	}
	
	public static List<List<String>> findAllAnagrams(List<String> inp){
		class Histogram {
			int[] arr;
			Histogram(String inp){
				arr = new int[256];
				for(int i=0;i<inp.length();++i)
					++(arr[inp.charAt(i)]);
			}
			
			@Override
			public boolean equals(Object o){
				return Arrays.equals(this.arr, ((Histogram)o).arr);
			}
			
			@Override
			public int hashCode(){
				return Arrays.hashCode(this.arr);
			}
			
			@Override
			public String toString(){
				StringBuilder str = new StringBuilder();
				for(int i=0;i<arr.length;++i){
					if(arr[i] > 0){
						str.append(((char)i)+""+arr[i]+" ");
					}
				}
				return str.toString();
			}
		}
		
		Map<Histogram,List<String>> map = new HashMap<Histogram,List<String>>();
		for(String s:inp){
			Histogram h = new Histogram(s);
			List<String> curr;
			if(map.containsKey(h))
				curr = map.get(h);
			else
				curr = new ArrayList<String>();
			curr.add(s);
			map.put(h,curr);
		}
		List<List<String>> fin = new ArrayList<List<String>>();
		for(List<String> list:map.values()){
			if(list.size()>1)
				fin.add(list);
		}
		//for(Map.Entry<char[],List<String>> entr:map.entrySet())
			//System.out.println(entr.getKey()+ " "+entr.getValue());
		return fin;
	}
	
	// Java 8
	private static Collection<List<String>> getAnagrams(final List<String> words) {
			Collection<List<String>> fin = new ArrayList<List<String>>();
		    for(List<String> list:words.stream()
		        .collect(Collectors.groupingBy(s -> {
		          final char[] sortedArray = s.toLowerCase().toCharArray();
		          Arrays.sort(sortedArray);
		          return new String(sortedArray).intern();
		        })).values()){
		    	if(list.size()>1)
		    		fin.add(list);
		    }
		    return fin;
		  }
}
