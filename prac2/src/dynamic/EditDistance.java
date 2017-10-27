package dynamic;

public class EditDistance {
	public static void main(String[] args){
		System.out.println(new EditDistance().editDistance("abcde","xyz"));
	}
	
	public int editDistance(String s1,String s2){
		int EDIT_DIST = 1, LEVENSHTEIN_DIST = 2;
		int[][] edit = new int[s1.length()][s2.length()];
		for(int i=0;i<s1.length();++i)
			edit[i][0] = i;
		for(int i=0;i<s2.length();++i)
			edit[0][i] = i;
		for(int i=1;i<s1.length();++i){
			for(int j=1;j<s2.length();++j){
				edit[i][j] = Math.min(Math.min(edit[i-1][j]+1,
									  edit[i][j-1]+1),
									  edit[i-1][j-1]+(s1.charAt(i)==s2.charAt(j)?0:EDIT_DIST));
			}
		}
		for(int i=0;i<s1.length();++i){
			for(int j=0;j<s2.length();++j)
				System.out.print(edit[i][j]+" ");
			System.out.println();
		}
		return edit[s1.length()-1][s2.length()-1];
	}
}
