/*package BT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class SerializeDeserialize<T> {
	private String MARKER = "NULL";
	class BTNode<T> implements Serializable{
		private static final long serialVersionUID = 1L;
		T data;
		BTNode<T> left;
		BTNode<T> right;
		BTNode<T> parent; // not used
		public BTNode(T data) {
			this.data = data;
		}
		public String toString() {
			return String.valueOf(this.data);
		}
	}

	public static void main(String[] args) {
		SerializeDeserialize s = new SerializeDeserialize();
		BTNode<Integer> node0=s.new BTNode<Integer>(0);
		BTNode<Integer> node1=s.new BTNode<Integer>(1);
		BTNode<Integer> node2=s.new BTNode<Integer>(2);
		node0.left = node1;
		node0.right=node2;
		//serialize(node0);
	}
	
	public void serialize(BTNode<T> node){
		if(node == null)
			return;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("C:\\Users\\Sriram\\workspace\\prac2\\src\\BT\\bt.ser");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			serialize(node,os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void serialize(BTNode<T> node,ObjectOutputStream os) throws IOException{
		if(node == null)
			os.writeObject(MARKER);
		os.writeObject(node.data);
		serialize(node.left,os);
		serialize(node.right,os);
	}
	
	public BTNode<T> deserialize(String path){
		if(path==null)
			return null;
		BTNode<T> root=null;
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream is = new ObjectInputStream(fis);
			deserialize(root,is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return root;
	}
	@SuppressWarnings("unchecked")
	public void deserialize(BTNode<T> root, ObjectInputStream is) throws ClassNotFoundException, IOException{
		T data = (T)is.readObject();
		if(data.equals(MARKER))
			return;
		root = new BTNode<T>(data);
		deserialize(root.left,is);
		deserialize(root.right,is);
	}
}
*/