import java.util.ArrayList;
import java.util.List;

/*
 * Let 1 represent A, 2 represents B, etc. Given a digit sequence, count the number of possible decodings of the given digit sequence.
	Examples:
	Input:  digits[] = "121"
	Output: 3
	// The possible decodings are "ABA", "AU", "LA"
	Input: digits[] = "1234"
	Output: 3
	// The possible decodings are "ABCD", "LCD", "AWD"
	https://leetcode.com/problems/decode-ways/
 */
public class AlphabetPermutation {

	public static void main(String[] args) {
		AlphabetPermutation a = new AlphabetPermutation();
		System.out.println(a.getNumDecodings("17"));
	}

	public int getNumDecodings(String inp){
		if(inp==null || inp.length()==0)
			return 0;
		int n = inp.length();
		int[] count = new int[n+1];
		count[n] = 1;
		count[n-1] = inp.charAt(n-1)=='0' ? 0:1;
		for(int i=n-2;i>=0;--i){
			if(inp.charAt(i)!='0'){
				if((inp.charAt(i)=='2' && inp.charAt(i+1)<='6') || (inp.charAt(i)=='1')){
					count[i] = count[i+1]+count[i+2];
				}
				else
					count[i] = count[i+1];
			}
		}
		return count[0];
	}
}
