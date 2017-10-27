package graphs;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
This line in the question is ambiguous - "Once a letter is used in a word, it cannot be used again."
I've worked with the assumption that a 'letter' here refers to the cell in the boggle, not the alphabet itself.
So a cell cannot be revisited to form a word, but a word can contain repeated alphabets eg., "apple" is a valid
word, assuming there are two p's in the board that are reachable.
*/
public class NewBoggle {
    public static void main(String args[] ) throws Exception {
        Scanner scan = new Scanner(System.in);
        String line = null;
        List<String> input = new ArrayList<>();
        while(!(line = scan.nextLine()).isEmpty()) {
            input.add(line.replaceAll("\\s+","").toLowerCase());
        }
        int size = input.size();
        char[][] board = new char[size][size];
        for(int i=0;i<size;++i) {
            board[i] = input.get(i).toCharArray();
        }
        
        Trie dictionary = new Trie();
        String nextLine = null;
        while(!(nextLine = scan.nextLine()).isEmpty()) {
            dictionary.put(nextLine.toLowerCase());
        }
        
        if(board==null || dictionary.isEmpty())
            return;
        NewBoggle solution = new NewBoggle();
        Set<String> words = solution.findWords(board,dictionary,size);
        for(String word:words) {
            System.out.println(word);
        }
    }
    
    public Set<String> findWords(char[][] board, Trie dictionary,
			int size) {
		Set<String> words = new HashSet<>();
		Set<String> finalSet = new TreeSet<>();
		for (int row = 0; row < size; ++row) {
			words.clear();
			for (int col = 0; col < size; ++col) {
				boolean[][] visited = new boolean[size][size];
				findWordsHelper(board, visited, dictionary, row, col, words,
						new StringBuilder(), size);
				finalSet.addAll(words);
			}
		}
		return finalSet;
	}

	private void findWordsHelper(char[][] board, boolean[][] visited,
			Trie dictionary, int row, int col, Set<String> words,
			StringBuilder temp, int size) {
		if (row < 0 || col < 0 || row >= size || col >= size
				|| visited[row][col])
			return;
		visited[row][col] = true;
		temp.append(board[row][col]);
		if (temp.length() >= 3 && dictionary.contains(temp.toString()))
			words.add(temp.toString());
		for (int i = -1; i < 2; ++i) {
			for (int j = -1; j < 2; ++j) {
				if (!(i == 0 && j == 0)) {
					findWordsHelper(board, visited, dictionary, row + i, col
							+ j, words, temp, size);
				}
			}// end inner for loop
		}// end outer for loop
		temp.deleteCharAt(temp.length() - 1);
		visited[row][col] = false;
	}
}

class Trie {
    private TrieNode root;
    private int n;
    private static class TrieNode {
        private boolean isWord;
        private TrieNode[] children = new TrieNode[256];
    }
    
    public Trie() {}
    
    public boolean get(String key) {
        if(root==null) 
            return false;
        TrieNode curr = this.root;
        for(int i=0;i<key.length();++i) {
            char c = Character.toLowerCase(key.charAt(i));
            TrieNode child = curr.children[c];
            if(child==null) {
                return false;
            }
            curr=child;
        }
        return curr.isWord;
    }
    
    public boolean contains(String key) {
        return get(key);
    }
    
    public void put(String key) {
        if(root == null)
            root = new TrieNode();
        TrieNode curr = this.root;
        for(int i=0;i<key.length();++i) {
            char c = Character.toLowerCase(key.charAt(i));
            TrieNode child = curr.children[c];
            if(child==null) {
                child = new TrieNode();
                child.isWord = false;
                curr.children[c] = child;
            }
            curr = child;
        }
        curr.isWord = true;
    }
    
    public boolean isEmpty() {
        return root==null;
    }
}