package hashtables;

import java.util.Iterator;
import java.util.Random;

import LinkedLists.DLL;

public class HashSetChain<T> implements Iterable<T>{
	DLL<DataNode<T>>[] arr;
	int maxSize,currSize;
	double loadFactor;
	
	@SuppressWarnings("unchecked")
	public HashSetChain() {
		maxSize = getPrime();
		arr = new DLL[maxSize];
		loadFactor = 0.0;
		currSize = 0;
	}
	
	@SuppressWarnings("unchecked")
	public HashSetChain(int size) {
		maxSize = getNextPrime(size);
		arr = new DLL[maxSize];
		loadFactor = 0.0;
		currSize = 0;
	}
	public int getHash(T key) {
		if(key instanceof Integer)
			return (int)key%maxSize;
		else if(key instanceof String) {
			int sum = 0;
			String temp = String.valueOf(key);
			for(int i=0;i<temp.length();++i) {
				sum += Math.pow(27, i)*(temp.charAt(i));
			}
			return sum%maxSize;
		}
		else 
			return key.hashCode()%maxSize;
		//return (int)key%maxSize;
	}
	public int getPrime() {
		Random r = new Random();
		int rand = r.nextInt(100)+50;
		return getNextPrime(rand);
	}
	public int getNextPrime(int num) {
		while(true){
			boolean isPrime = true;
			for(int i=2;i*i<=num;++i) {
				if(num%i == 0) {
					++num;
					isPrime = false;
					break;
				}
			}
			if(isPrime)
				return num;
		}
	}
	@SuppressWarnings("unchecked")
	private void increaseSize() {
		maxSize *= 2;
		DLL<DataNode<T>>[] temp = new DLL[maxSize];
		for(int i=0;i<arr.length;++i) {
			DLL<DataNode<T>> curr = arr[i];
			while(curr.isEmpty()) {
				T key = curr.deleteFirst().data.data;
				int hash = getHash(key);
				DLL<DataNode<T>> dll;
				if(temp[hash] == null)
					dll = new DLL<DataNode<T>>();
				 else
					dll = temp[hash];
				dll.insertFirst(new DataNode<T>(key));
				temp[hash] = dll;
			}
		}
		arr = temp;
		loadFactor = 0.0;
	}
	public void insert(T key) {
		// if load factor goes above 50%, increase arr size
		if(loadFactor > 0.5)
			increaseSize();
		int hash = getHash(key);
		DLL<DataNode<T>> dll;
		if(arr[hash] == null)
			dll = new DLL<DataNode<T>>();
		 else
			dll = arr[hash];
		dll.insertFirst(new DataNode<T>(key));
		arr[hash] = dll;
		++currSize;
		loadFactor = currSize/maxSize;
	}
	public T find(T key) {
		int hash = getHash(key);
		if(arr[hash] != null) {
				DataNode<T> dn = arr[hash].find(new DataNode<T>(key));
				if(dn != null)
					return dn.data;
		}
		return null;
	}
	public T delete(T key) {
		int hash = getHash(key);
		if(arr[hash] != null) {
			DataNode<T> del = arr[hash].deleteFind(new DataNode<T>(key));
			if(del != null)
				return del.data;
		}
		--currSize;
		return null;
	}
	public void display() {
		for(DLL<DataNode<T>> list:arr) {
			if(list != null)
				list.displayForward();
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> itr = new Iterator<T>() {
			int currIndex = 0;
			Iterator<DataNode<T>> dllItr; 
			
			@Override
			public boolean hasNext() {
				while(currIndex < maxSize) {
					if(dllItr==null) {
						if(arr[currIndex] != null) {
							dllItr = arr[currIndex].iterator();
							return dllItr.hasNext();
						} else
							++currIndex;
					}
					else if(!dllItr.hasNext()) {
						dllItr = null;
						++currIndex;
					}
					else {
						return true;
					}
				}
				return false;
			}
			
			@Override
			public T next() {
				return dllItr.next().data;
			}
		};
		return itr;
	}
	public static void main(String[] args) {
		HashSetChain<Integer> set = new HashSetChain<Integer>();
		set.insert(1);
		set.insert(set.maxSize+1);
		set.insert(17);
		for(Integer i:set) {
			System.out.println(i);
		}
	}
}

