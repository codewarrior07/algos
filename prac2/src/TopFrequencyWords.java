import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// Find top k words with highest frequency
public class TopFrequencyWords {
	public static void main(String[] args){
		TopFrequencyWords t = new TopFrequencyWords();
		String[] s = new String[]{"a","b","c","a","a","x","c","c","b","z"};
		System.out.println(t.topKWords2(Arrays.asList(s),3));		
	}

	class WordFreq {
		String word;
		int freq;
		public WordFreq(String w,int f){
			this.word = w;
			this.freq = f;
		}

		@Override
		public int hashCode(){
			return word.hashCode();
		}

		@Override
		public boolean equals(Object o){
			if(o != null && o instanceof WordFreq)
				return this.word.equals(((WordFreq)o).word);
			return false;
		}
	}

	//using map and pq
	// O(nlgk), O(n)+O(k)
	public Map<String,Integer> topKWords(List<String> list,int k) {
		if(list == null || k<=0)
			return null;
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(String s:list){
			if(map.containsKey(s))
				map.put(s,map.get(s)+1);
			else
				map.put(s,1);
		}
		PriorityQueue<WordFreq> pq = new PriorityQueue<WordFreq>(new Comparator<WordFreq>(){
			@Override
			public int compare(WordFreq w1, WordFreq w2){
				return w1.freq-w2.freq;
			}
		});
		for(Map.Entry<String,Integer> entr:map.entrySet()){
			WordFreq w = new WordFreq(entr.getKey(),entr.getValue());
			pq.offer(w);
			if(pq.size()>k)
				pq.poll();
		}
		map = new LinkedHashMap<String,Integer>();
		for(WordFreq w:pq)
			map.put(w.word,w.freq);
		return map;
	}
	
	//using quick select
	//O(n),O(n)
	public Map<String,Integer> topKWords2(List<String> list,int k) {
		if(list == null || k<=0)
			return null;
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(String s:list){
			if(map.containsKey(s))
				map.put(s,map.get(s)+1);
			else
				map.put(s,1);
		}
		List<WordFreq> arr = new ArrayList<WordFreq>();
		for(Map.Entry<String,Integer> entr:map.entrySet()){
			WordFreq w = new WordFreq(entr.getKey(),entr.getValue());
			arr.add(w);
		}
		int pivot = quickSelect(arr,k,0,arr.size()-1);
		map = new LinkedHashMap<String,Integer>();
		for(int i=pivot;i<arr.size();++i)
			map.put(arr.get(i).word,arr.get(i).freq);
		return map;
	}
	
	public int quickSelect(List<WordFreq> arr,int k,int s,int e){
		if(s<0 || e>=arr.size() || s>e)
			return -1;
		int part = partition(arr,s,e);
		if(part==arr.size()-k)
			return part;
		else if(part<arr.size()-k)
			return quickSelect(arr,k,part+1,e);
		else
			return quickSelect(arr,k,s,part-1);
	}
	public int partition(List<WordFreq> arr,int s,int e){
		if(s>e)
			return -1;
		int j=s,pivot=e;
		for(int i=s;i<=e;++i){
			if(arr.get(i).freq<arr.get(pivot).freq){
				WordFreq temp = arr.get(i);
				arr.set(i,arr.get(j));
				arr.set(j++,temp);
			}
		}
		WordFreq temp = arr.get(j);
		arr.set(j,arr.get(pivot));
		arr.set(pivot,temp);
		return j;
	}

}
