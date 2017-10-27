public class BoyerMooreWithSuffix {
  
  public static final int ALPHABET_SIZE = 256;
    
  private String text;
  private String pattern;
  
  private int[] last;
  private int[] match;
  private int[] suffix;
  
  BoyerMooreWithSuffix(String text,String pattern){
	  this.text = text;
	  this.pattern = pattern;
	  last = new int[ALPHABET_SIZE];
	  match = new int[pattern.length()];
	  suffix = new int[pattern.length()];
  }

  public static void main(String[] args) {
	  BoyerMooreWithSuffix b = new BoyerMooreWithSuffix("abacadabrabracabracadabrabrabracad","abcdeabc");
	  System.out.println(b.match());
  }
  
  /**
   * Searches the pattern in the text.
   * returns the position of the first occurrence, if found and -1 otherwise.
   */
  public int match() {
    // Preprocessing
    computeLast();
    computeMatch();
    
    // Searching
    int i = pattern.length() - 1;
    int j = pattern.length() - 1;    
    while (i < text.length()) {
      if (pattern.charAt(j) == text.charAt(i)) {
        if (j == 0) { 
          return i;
        }
        j--;
        i--;
      } else {
          i += pattern.length() - j - 1 + Math.max(j - last[text.charAt(i)], match[j]);
          j = pattern.length() - 1;
      }
    }
    return -1;    
  }  
  
  /**
   * Computes the function last and stores its values in the array last.
   * last(Char ch) = the index of the right-most occurrence of the character ch
   *                                                           in the pattern; 
   *                 -1 if ch does not occur in the pattern.
   */
  private void computeLast() {
    for (int k = 0; k < last.length; k++) { 
      last[k] = -1;
    }
    for (int j = pattern.length()-1; j >= 0; j--) {
      if (last[pattern.charAt(j)] < 0) {
        last[pattern.charAt(j)] = j;
      }
    }
  }
  
  /**
   * Computes the function match and stores its values in the array match.
   * match(j) = min{ s | 0 < s <= j && p[j-s]!=p[j]
   *                            && p[j-s+1]..p[m-s-1] is suffix of p[j+1]..p[m-1] }, 
   *                                                         if such s exists, else
   *            min{ s | j+1 <= s <= m 
   *                            && p[0]..p[m-s-1] is suffix of p[j+1]..p[m-1] }, 
   *                                                         if such s exists,
   *            m, otherwise,
   * where p is the pattern and m is its length.
   */
  private void computeMatch() {
    /* Phase 1 */
    for (int j = 0; j < match.length; j++) { 
      match[j] = match.length;
    } //O(m) 
    
    computeSuffix(); //O(m)
        
    /* Phase 2 */
    //Uses an auxiliary array, backwards version of the KMP failure function.
    //suffix[i] = the smallest j > i s.t. p[j..m-1] is a prefix of p[i..m-1],
    //if there is no such j, suffix[i] = m
    
    //Compute the smallest shift s, such that 0 < s <= j and
    //p[j-s]!=p[j] and p[j-s+1..m-s-1] is suffix of p[j+1..m-1] or j == m-1}, 
    //                                                         if such s exists,
    for (int i = 0; i < match.length - 1; i++) {
      int j = suffix[i + 1] - 1; // suffix[i+1] <= suffix[i] + 1
      if (suffix[i] > j) { // therefore pattern[i] != pattern[j]
        match[j] = j - i;
      } else {// j == suffix[i]
        match[j] = Math.min(j - i + match[i], match[j]);
      }  
    }  
    
    /* Phase 3 */
    //Uses the suffix array to compute each shift s such that
    //p[0..m-s-1] is a suffix of p[j+1..m-1] with j < s < m
    //and stores the minimum of this shift and the previously computed one.
    if (suffix[0] < pattern.length()) {
      for (int j = suffix[0] - 1; j >= 0; j--) {
        if (suffix[0] < match[j]) { match[j] = suffix[0]; }
      }
      int j = suffix[0];
      for (int k = suffix[j]; k < pattern.length(); k = suffix[k]) {
        while (j < k) {
          if (match[j] > k) match[j] = k;
          j++;
        }       
      }
    }
  }
    
  /**
   * Computes the values of suffix, which is an auxiliary array, 
   * backwards version of the KMP failure function.
   * 
   * suffix[i] = the smallest j > i s.t. p[j..m-1] is a prefix of p[i..m-1],
   * if there is no such j, suffix[i] = m, i.e. 

   * p[suffix[i]..m-1] is the longest prefix of p[i..m-1], if suffix[i] < m.
   */
  private void computeSuffix() {        
    suffix[suffix.length-1] = suffix.length;            
    int j = suffix.length - 1;
    for (int i = suffix.length - 2; i >= 0; i--) {  
      while (j < suffix.length - 1 && pattern.charAt(j) != pattern.charAt(i)) {
        j = suffix[j + 1] - 1; 
      }
      if (pattern.charAt(j) == pattern.charAt(i)) { 
    	  j--; 
      }
      suffix[i] = j + 1;
    }
  }
}