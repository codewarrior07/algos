package tries;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Trie<Value> {
	private TrieNode root;
	private int N;
	private static class TrieNode{
		private boolean isWord;
		private TrieNode[] children = new TrieNode[256];
	}
	public Trie(){
		
	}
	@SuppressWarnings("unchecked")
	public boolean get(String key) {
		if(root == null)
			return false;
		TrieNode curr = this.root;
		for(int i=0;i<key.length();++i){
			char c = Character.toLowerCase(key.charAt(i));
			TrieNode child = curr.children[c];
			if(child == null)
				return false;
			curr = child;
		}
		//return (Value)curr.val;
		return curr.isWord;
	}
	public boolean contains(String key){
		return get(key);
	}
	public void put(String key) {
		if(root == null){
			root = new TrieNode();
		}
		TrieNode curr = this.root;
		for(int i=0;i<key.length();++i){
			char c = Character.toLowerCase(key.charAt(i));
			TrieNode child = curr.children[c];
			if(child == null) {
				child = new TrieNode();
				child.isWord = false;
				curr.children[c] = child;
			}
			curr = child;
		}
		curr.isWord = true;
	}
	public Iterable<String> keys(){
		if(root == null)
			return null;
		List<String> words = new ArrayList<String>();
		Stack<TrieNode> st = new Stack<TrieNode>();
		st.push(this.root);
		while(!st.isEmpty()){
			TrieNode curr = st.peek();
			TrieNode next = null;
			for(int i=0;i<256;++i){
				if(curr.children[i] != null){
					next = curr.children[i];
					break;
				}
			}
			if(next != null) {
				if(next.isWord == true) {
					StringBuilder sb = new StringBuilder();
					for(TrieNode t:st)
						sb.append(t);
					words.add(sb.toString());
				}
				st.push(next);
			} else
				st.pop();
		}
		return words;
	}
	public static void main(String[] args) {
		Trie<Integer> trie = new Trie<Integer>();
		trie.put("ha");
		trie.put("homer");
		trie.put("homers");
		System.out.println(trie.contains("homer"));
		//Stack<String> st = new Stack<String>();
		//st.push("a");
		//st.push("b");
		
	}
}
