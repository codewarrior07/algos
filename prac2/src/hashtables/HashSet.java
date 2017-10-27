package hashtables;

import java.util.Random;

// open addressing
public class HashSet<T> {
	public DataNode<T>[] arr;
	public int size;
	@SuppressWarnings("unchecked")
	public HashSet() {
		size = getPrime();
		arr = new DataNode[size];
	}
	public int getHash(T key) {
		if(key instanceof Integer)
			return ((int)key)%size;
		else if(key instanceof String) {
			String temp = ((String) key).toLowerCase();
			int sum = 0;
			for(int i=temp.length()-1;i>=0;--i)
				sum += (temp.charAt(i)-97)*Math.pow(27, i);
			return sum % size;
		}
		return ((int)key)%size;
	}
	public void insert(T key) {
		// linear probing
		// get hash
		int hash = getHash(key);
		DataNode<T> node = new DataNode<T>(key);
		int curr = hash, next = hash;
		while(curr+1 != next) {
			if(arr[curr] != null && !arr[curr].isDeleted && arr[curr].data.equals(key)) {
				System.out.println("Duplicate value");
				return;
			}
			else if(arr[curr] == null || arr[curr].isDeleted) {
				arr[curr] = node;
				return;
			}
			++curr;
			curr = curr % size;
		}
		System.out.println("HashTable full");
	}
	public DataNode<T> find(T key) {
		int hash = getHash(key);
		int curr = hash, next = hash;
		while(curr+1 != next) {
			if(arr[curr] != null && !arr[curr].isDeleted && arr[curr].data.equals(key))
				return arr[curr];
			if(arr[curr] == null)
				return null;
			++curr;
			curr = curr % size;
		}
		return null;
	}
	public DataNode<T> delete(T key) {
		int hash = getHash(key);
		int curr = hash, next = hash;
		while(curr+1 != next) {
			if(arr[curr] == null)
				return null;
			else if(arr[curr].data == key) {
				DataNode<T> del = arr[curr];
				arr[curr].isDeleted = true;
				return del;
			}
			++curr;
			curr = curr % size;
		}
		return null;
	}
	public int getPrime() {
		Random r = new Random();
		int size = r.nextInt(100)+50;
		for(;;++size) {
			boolean isPrime = true;
			for(int i=2;i*i<=size;++i) {
				if(size%i==0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime)
				return size;
		}
	}
	public void display() {
		for(DataNode<T> d:arr) {
			if(d != null && !d.isDeleted)
				System.out.println(d);
		}
	}
	public void displayAll() {
		for(int i=0;i<size;++i)
			System.out.println(i+ " "+arr[i]);
	}
	public static void main(String[] args) {
		/*HashSet<Integer> h = new HashSet<Integer>();
		h.insert(100);
		h.insert(100+h.size);
		h.displayAll();*/
		
		HashSetQuad<String> h = new HashSetQuad<String>();
		char first = 'a', second = 'a';
		for(int i=0;i<h.maxSize;++i){
			System.out.println(Character.toString(first).concat(Character.toString(second)));
			h.insert(Character.toString(first).concat(Character.toString(second)));
			first = second == 'z' ? ++first:first;
			second = second == 'z'? 'a':++second;
		}
		/*h.insert("bc");
		h.insert("ad");*/
		//System.out.println(h.find("dd"));
		//System.out.println(h.delete("bc"));
		//h.display();
		//h.displayAll();
	}
}

class DataNode<T> {
	public T data;
	public boolean isDeleted;
	public DataNode(T data) {
		this.data = data;
		isDeleted = false;
	}
	@Override
	public String toString() {
		return String.valueOf(data);
	}
}

class HashSetQuad<T> {
	public DataNode<T>[] arr;
	public int maxSize;
	public int currSize;
	@SuppressWarnings("unchecked")
	public HashSetQuad() {
		maxSize = getPowerOf2();
		arr = new DataNode[maxSize];
		currSize = 0;
	}
	public int getHash(T key) {
		if(key instanceof Integer)
			return ((int)key)%maxSize;
		else if(key instanceof String) {
			String temp = ((String) key).toLowerCase();
			int sum = 0;
			for(int i=temp.length()-1;i>=0;--i)
				sum += (temp.charAt(i)-97)*Math.pow(27, i);
			return sum % maxSize;
		}
		return ((int)key)%maxSize;
	}
	public void insert(T key) {
		// quadratic probing (i^2+i)/2
		if(currSize == maxSize)
			return;
		int hash = getHash(key);
		DataNode<T> node = new DataNode<T>(key);
		while(arr[hash] != null && !arr[hash].isDeleted) {
			if(arr[hash].data.equals(key)) {
				System.out.println("Duplicate value");
				return;
			}
			hash = ((int)(Math.pow(hash, 2)+hash)/2)%maxSize;
			System.out.println(hash);
		}
		arr[hash] = node;
		++currSize;
	}
	public DataNode<T> find(T key) {
		int hash = getHash(key);
		while(arr[hash] != null && !arr[hash].isDeleted && !arr[hash].data.equals(key)) {
			hash = ((int)(Math.pow(hash, 2)+hash)/2)%maxSize;
		}
		return arr[hash];
	}
	public DataNode<T> delete(T key) {
		if(currSize == 0)
			return null;
		int hash = getHash(key);
		while(arr[hash] != null && !arr[hash].isDeleted) {
			if(arr[hash].data.equals(key)) {
				DataNode<T> del = arr[hash];
				arr[hash].isDeleted = true;
				--currSize;
				return del;
			}
		}
		return null;
	}
	public int getPowerOf2() {
		Random r = new Random();
		int size = r.nextInt(8);
		return (int) Math.pow(2, size);
	}
	public void display() {
		for(DataNode<T> d:arr) {
			if(d != null && !d.isDeleted)
				System.out.println(d);
		}
	}
	public void displayAll() {
		for(int i=0;i<maxSize;++i)
			System.out.println(i+ " "+arr[i]);
	}
}
