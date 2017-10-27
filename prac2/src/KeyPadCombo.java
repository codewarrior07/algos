import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given a sequence of numbers (34128) and an input map such as a dial pad on a phone (2[a,b,c], 3[d,e,f], 4[g,h,i]) 
//write an algorithm to return all possible words from the sequence. 
//		E.g. Input: 232 Output: 
//		[ada, adb, adc, aea, aeb, aec, afa, afb, afc, bda, bdb, bdc, bea, beb, bec, bfa, bfb, bfc, cda, cdb, cdc, cea, ceb, cec, cfa, cfb, cfc]  
public class KeyPadCombo {

	public static void main(String[] args) {
		Map<Character,List<Character>> map = new HashMap<Character,List<Character>>();
		map.put('1',new ArrayList<Character>(Arrays.asList('1')));
		map.put('2',new ArrayList<Character>(Arrays.asList('a','b','c')));
		map.put('3',new ArrayList<Character>(Arrays.asList('d','e','f')));
		map.put('4',new ArrayList<Character>(Arrays.asList('g','h','e')));
		map.put('5',new ArrayList<Character>(Arrays.asList('j','k','l')));
		map.put('6',new ArrayList<Character>(Arrays.asList('m','n','o')));
		map.put('7',new ArrayList<Character>(Arrays.asList('p','q','r','s')));
		map.put('8',new ArrayList<Character>(Arrays.asList('t','u','v')));
		map.put('9',new ArrayList<Character>(Arrays.asList('w','x','y','z')));
		map.put('0',new ArrayList<Character>(Arrays.asList('0')));
		KeyPadCombo k  = new KeyPadCombo();
		System.out.println(k.genWords("23",map));
		System.out.println(k.genWords("23",map));
		System.out.println(k.genWords("2323",map));
	}
	
	public List<String> genWords(String inp,Map<Character,List<Character>> map){
		if(map==null || inp==null)
			return null;
		if(map.isEmpty() || inp.length()==0)
			return new ArrayList<String>();
		return genWordsHelper(inp,map,inp.length()-1);
	}
	private List<String> genWordsHelper(String inp,Map<Character,List<Character>> map,int pos){
		if(pos==0){
			List<String> temp = new ArrayList<String>();
			for(char c:map.get(inp.charAt(pos)))
				temp.add(Character.toString(c));
			return temp;
		}
		List<String> prev = genWordsHelper(inp,map,pos-1);
		List<String> temp = new ArrayList<String>();
		for(String s:prev){
			for(char c:map.get(inp.charAt(pos))){
				temp.add(s+c);
			}
		}
		return temp;
	}
}
