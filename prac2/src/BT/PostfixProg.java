package BT;

import java.util.Stack;

public class PostfixProg {
	public static void main(String[] args) {
		PostFixTree<String> pf = new PostFixTree<String>("36 7 1 - /");
		System.out.println(pf.eval());
	}
	/*public String toPostfixString(String expr) {
		String post = "";
		
	}*/
}

class PostFixTree<T> {
	BT<String> bt;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PostFixTree(String expr) {
		Stack st = new Stack();
		String[] arr = expr.split("\\s");
		for(int i=0;i<arr.length;++i) {
			if(isNum(arr[i]))
				st.push(arr[i]);
			else {
				Object right = st.pop();
				Object left = st.pop();
				BT<Object> temp = new BT<Object>();
				temp.insert(arr[i]);
				if(left instanceof BT) {
					BTNode<Object> leftRoot = ((BT)left).root;
					temp.root.left = leftRoot;
				} else {
					temp.insert(left);
				}
				if(right instanceof BT) {
					BTNode<Object> rightRoot = ((BT)right).root;
					temp.root.right = rightRoot;
				} else {
					temp.insert(right);
				}
				st.push(temp);
			}
		}
		Object finTree = st.pop();
		if(finTree instanceof BT)
			bt = (BT)finTree;
	}
	public boolean isEmpty() {
		return bt==null;
	}
	private boolean isNum(String inp) {
		for(int i=0;i<inp.length();++i) {
			if(inp.charAt(i) < '0' || inp.charAt(i) > '9') {
				if(!(inp.charAt(i) == '-' && i==0 && inp.length() > 1)) {
					return false;
				}
			}
		}
		return true;
	}
	public int eval(){
		if(bt.isEmpty())
			return 0;
		return this.evaluate(bt.root);
	}
	private int evaluate(BTNode<String> curr){
		if(curr == null)
			return 0;
		if(isNum(curr.data)) {
			return Integer.parseInt(curr.data);
		}
		switch(curr.data) {
		case "+":
			return evaluate(curr.left) + evaluate(curr.right);
		case "-":
			return evaluate(curr.left) - evaluate(curr.right);
		case "*":
			return evaluate(curr.left) * evaluate(curr.right);
		case "/":
			return evaluate(curr.left) / evaluate(curr.right);
		default:
			return 0;
		}
	}
	public void dislay() {
		bt.display();
	}
}


