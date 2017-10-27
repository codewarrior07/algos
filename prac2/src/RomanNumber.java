import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class RomanNumber {
	public String intToRoman(int num) {
	    Map<Integer,String> map = new TreeMap<Integer,String>(Collections.reverseOrder());
	    map.put(1,"I");
	    map.put(4,"IV");
	    map.put(5,"V");
	    map.put(9,"IX");
	    map.put(10,"X");
	    map.put(40,"XL");
	    map.put(50,"L");
	    map.put(90,"XC");
	    map.put(100,"C");
	    map.put(400,"CD");
	    map.put(500,"D");
	    map.put(900,"CM");
	    map.put(1000,"M");
	    StringBuilder roman = new StringBuilder();
	    // 133
	    Iterator<Entry<Integer, String>> entries = map.entrySet().iterator();
	    while(entries.hasNext() && num > 0) {
	        Map.Entry<Integer,String> entr = (Map.Entry<Integer,String>)entries.next();
	        int div = entr.getKey();
	        if(num/div > 0) {
	            int q = num/div;
	            while(q > 0) {
	                roman.append(entr.getValue());
	                --q;
	            }
	            num = num%div;
	        }
	    }
	    return roman.toString();
	}
	
	//USE THIS
	public String intToRoman2(int num){
        int[] numbers = new int[] {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] codes = new String[] {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<numbers.length;++i){
            while(num >= numbers[i]) {
                roman.append(codes[i]);
                num -= numbers[i];
            }
        }
        return roman.toString();
    }
	
	public static void main(String[] args) {
		RomanNumber r = new RomanNumber();
		System.out.println(r.intToRoman2(4999));
	}
}
