
public class BoyerMoore {
	public static void main(String[] args) {
		System.out.println(boyerMoore("hello aba"," aba"));
	}
	
	public static int boyerMoore(String text,String pat) {
		int n = text.length();
		int m = pat.length();
		int skip;
		int[] occurence = new int[256];
		for(int i=0;i<occurence.length;++i)
			occurence[i] = -1;
		for(int i=0;i<pat.length();++i){
			occurence[pat.charAt(i)] = i;
		}
		for(int i=0;i<=(n-m);i+=skip){
			skip=0;
			for(int j=m-1;j>=0;--j){
				if(pat.charAt(j) != text.charAt(i+j)) {
					skip = Math.max(1,j-occurence[text.charAt(i+j)]);
					break;
				}
			}
			if(skip == 0)
				return i;
		}
		return -1;
	}
}
