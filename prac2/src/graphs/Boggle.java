package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Boggle {
	public static void main(String[] args) throws Exception{
		/*char[][] board = {{'a','s'},
						  {'b','m'}};
		for(String s:findWords(board))
			System.out.println(s);*/
		
		/*Scanner keyboard = new Scanner(System.in);
		String line = null;
		List<String> inp = new ArrayList<String>();
		while(!(line = keyboard.nextLine()).isEmpty()) {
		 inp.add(line.replaceAll("\\s+",""));
		}*/
		
		
		/*Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        int cols = scan.nextInt();
        char[][] board = new char[rows][cols];
        for(int i=0;i<rows;++i){
            String currLine = scan.next();
            if(currLine.length() != cols)
                throw new Exception("bad format");
            for(int j=0;j<cols;++j)
                board[i][j] = currLine.charAt(j);
        }
        char[][] board = new char[][]{{'s','m','e','f'},{'r','a','t','d'},{'l','o','n','i'},{'k','a','f','b'}};
        System.out.println(isWordInBoard(board,"tone".toCharArray()));
        while(scan.hasNext()) {
            System.out.println(isWordInBoard(board,scan.next().toCharArray()));
        }*/
		
		/*Scanner scan = new Scanner(System.in);
		String line = null;
		List<String> input = new ArrayList<>();
		while (!(line = scan.nextLine()).isEmpty()) {
			input.add(line.replaceAll("\\s+", ""));
		}
		int size = input.size();
		char[][] board = new char[size][size];
		for (int i = 0; i < size; ++i) {
			board[i] = input.get(i).toCharArray();
		}

		Set<String> dictionary = new HashSet<>();
		while (!(line = scan.nextLine()).isEmpty()) {
			dictionary.add(line);
		}

		if (board == null || dictionary.size() == 0)
			return;
		Boggle solution = new Boggle();
		for (String word : solution.findWords(board, dictionary)) {
			System.out.println(word);
		}*/
		
		Scanner scan = new Scanner(System.in);
		List<String> inp = new ArrayList<String>();
		while(scan.hasNext()) {
			String str = scan.next();
			if(str.length()==0)
				break;
			inp.add(str);
		}
		System.out.println(inp);
	}

	public static List<String> findWords(char[][] board,Set<String> dictionary) {
		if (board == null || board.length == 0)
			return null;
		boolean[][] st = new boolean[board.length][board.length];
		List<String> words = new ArrayList<String>();
		List<String> fin = new ArrayList<String>();
		for (int i = 0; i < board.length; ++i) {
			words.clear();
			for (int j = 0; j < board.length; ++j) {
				boggleHelper(board, st, i, j, words, new StringBuilder(),dictionary);
				fin.addAll(words);
				for (boolean[] row : st)
					Arrays.fill(row, false);
			}
		}
		return fin;
	}

	private static void boggleHelper(char[][] board, boolean[][] st, int i,
			int j, List<String> words, StringBuilder temp,Set<String> dictionary) {
		if (i < 0 || j < 0 || i >= board.length || j >= board.length
				|| st[i][j])
			return;
		st[i][j] = true;
		temp.append(board[i][j]);
		if (isWord(temp,dictionary))
			words.add(temp.toString());
		boggleHelper(board, st, i - 1, j, words, temp,dictionary);
		boggleHelper(board, st, i + 1, j, words, temp,dictionary);
		boggleHelper(board, st, i, j - 1, words, temp,dictionary);
		boggleHelper(board, st, i, j + 1, words, temp,dictionary);
		temp.deleteCharAt(temp.length() - 1);
		st[i][j] = false;
	}

	private static boolean isWord(StringBuilder inp,Set<String> dictionary) {
		if(dictionary.contains(inp.toString()))
			return true;
		return false;
	}

	public static boolean isWordInBoard(char[][] board, char[] word) {
		int rowSize = board.length;
		int colSize = board[0].length;
		for (int row = 0; row < rowSize; ++row) {
			for (int col = 0; col < colSize; ++col) {
				if (board[row][col] == word[0]) {
					boolean[][] visited = new boolean[rowSize][colSize];
					boolean result = isWordInBoardHelper(board, row, col,
							visited, word, 0,rowSize, colSize);
					if (result)
						return true;
				}
			}
		}
		return false;
	}

	private static boolean isWordInBoardHelper(char[][] board, int row, int col,
			boolean[][] visited, char[] word, int currIndex, int rowSize,
			int colSize) {
		if (currIndex == word.length)
			return true;
		if (row < 0 || col < 0 || row >= rowSize || col >= colSize
				|| visited[row][col] || board[row][col] != word[currIndex])
			return false;
		visited[row][col] = true;
		for(int i=-1;i<2;++i) {
			for(int j=-1;j<2;++j) {
				if(!(i==0 && j==0)) {
					boolean result = isWordInBoardHelper(board, row + i, col + j,
							visited, word, currIndex + 1, rowSize, colSize);
					if (result)
						return result;
				}
			}
		}
		return false;
	}
}
