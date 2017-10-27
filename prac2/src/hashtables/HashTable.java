package hashtables;

import java.util.Iterator;
import java.util.Random;

import LinkedLists.DLL;

public class HashTable<Key,Value> implements Iterable<HashTableEntry<Key,Value>>{
	DLL<HashTableEntry<Key,Value>>[] arr;
	int maxSize,currSize;
	double loadFactor = 0.0;
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		maxSize = getPrime();
		arr = new DLL[maxSize];
		loadFactor = 0.0;
		currSize = 0;
	}
	public int getPrime() {
		Random r = new Random();
		int rand = r.nextInt(100)+50;
		while(true){
			boolean isPrime = true;
			for(int i=2;i*i<=rand;++i) {
				if(rand%i == 0) {
					++rand;
					isPrime = false;
					break;
				}
			}
			if(isPrime)
				return rand;
		}
	}
	public int getHash(Key key) {
		if(key instanceof Integer)
			return (int)key%maxSize;
		if(key instanceof String) {
			int sum = 0;
			String temp = String.valueOf(key);
			for(int i=0;i<temp.length();++i) {
				sum += Math.pow(27, i)*(temp.charAt(i));
			}
			return sum%maxSize;
		}
		return (int)key%maxSize;
	}
	public void put(Key key, Value val) {
		int hash = getHash(key);
		HashTableEntry<Key,Value> nn = new HashTableEntry<Key,Value>(key,val);
		DLL<HashTableEntry<Key,Value>> dll;
		if(arr[hash] == null) {
			dll = new DLL<HashTableEntry<Key,Value>>();
			dll.insertFirst(nn);
			arr[hash] = dll;
		}
		else {
			dll = arr[hash];
			HashTableEntry<Key,Value> entr = dll.find(nn);
			if(entr != null) 
				entr.value = val;
			else {
				dll.insertFirst(nn);
				arr[hash] = dll;
			}
		}
		/*HashTableEntry<Key,Value> entr = dll.find(nn);
		if(entr != null) 
			entr.value = val;
		else
			dll.insertFirst(nn);
		arr[hash] = dll;*/
		++currSize;
		loadFactor = currSize/maxSize;
	}
	public boolean containsKey(Key key) {
		int hash = getHash(key);
		if(arr[hash] != null) {
			HashTableEntry<Key,Value> dn = arr[hash].find(new HashTableEntry<Key,Value>(key,null));
			if(dn != null)
				return true;
		}
		return false;
	}
	public boolean containsValue(Value val) {
		for(DLL<HashTableEntry<Key,Value>> dll:arr) {
			for(HashTableEntry<Key,Value> node:dll) {
				if(node.value.equals(val))
					return true;
			}
		}
		return false;
	}
	public Value get(Key key) {
		int hash = getHash(key);
		if(arr[hash] != null) {
			HashTableEntry<Key,Value> dn = arr[hash].find(new HashTableEntry<Key,Value>(key,null));
			if(dn != null)
				return dn.value;
		}
		return null;
	}
	public Value remove(Key key) {
		int hash = getHash(key);
		if(arr[hash] != null) {
			HashTableEntry<Key,Value> dn = arr[hash].deleteFind(new HashTableEntry<Key,Value>(key,null));
			if(dn != null)
				return dn.value;
		}
		return null;
	}
	public Iterator<HashTableEntry<Key,Value>> iterator() {
		Iterator<HashTableEntry<Key,Value>> itr = new Iterator<HashTableEntry<Key,Value>>() {
			int currIndex = 0;
			
			@Override
			public boolean hasNext() {
				return currIndex < maxSize && arr[currIndex] != null && arr[currIndex].iterator().hasNext(); 
			}
			
			@Override
			public HashTableEntry<Key,Value> next() {
				HashTableEntry<Key,Value> entr = arr[currIndex].iterator().next();
				++currIndex;
				return entr;
			}
		};
		return itr;
	}
	public HashSetChain<HashTableEntry<Key,Value>> entrySet() {
		HashSetChain<HashTableEntry<Key,Value>> set = new HashSetChain<HashTableEntry<Key,Value>>(currSize);
		for(DLL<HashTableEntry<Key,Value>> dll:arr) {
			if(dll != null) {
				for(HashTableEntry<Key,Value> entr:dll)
					set.insert(entr);
			}
		}
		return set;
	}
	public HashSetChain<Key> keySet() {
		HashSetChain<Key> set = new HashSetChain<Key>(currSize);
		for(DLL<HashTableEntry<Key,Value>> dll:arr) {
			if(dll != null) {
				for(HashTableEntry<Key,Value> entr:dll)
					set.insert(entr.key);
			}
		}
		return set;
	}
	public void display() {
		for(DLL<HashTableEntry<Key,Value>> dll:arr) {
			if(dll != null){
				for(HashTableEntry<Key,Value> node:dll)
					System.out.println(node.getKey()+ " "+node.getValue());
				System.out.println("-----");
			}
		}
	}
	/*public Collection<Value> values() {
		Collection<Value> coll = new ArrayList<Value>();
		for(DLL<HashTableEntry<Key,Value>> dll:arr) {
			for(HashTableEntry<Key,Value> entr:dll)
				coll.add(arg0);
		}
		return set;
	}*/
	public static void main(String[] args) {
		/*HashMap<Integer,String> map = new HashMap<Integer,String>();
		map.put(1,"hi");
		map.put(2,"hello");
		map.put(2,"asd");
		Set<Integer> set = map.keySet();
		set.remove(1);
		for(Map.Entry<Integer,String> entr:map.entrySet()) {
			System.out.println(entr.getKey() + entr.getValue());
		}*/
		HashTable<Integer,String> map = new HashTable<Integer,String>();
		map.put(1, "hi");
		map.put(map.maxSize+1, "bye");
		//map.put(2, "hello");
		map.put(2, "zello");
		//map.put(map.maxSize+1, "byeeee");
		//map.display();
		HashSetChain<HashTableEntry<Integer,String>> set = map.entrySet();
		set.display();
		System.out.println("**************");
		for(HashTableEntry<Integer,String> entr:map.entrySet()){
			System.out.println(entr.getKey() + " "+entr.getValue());
		}
	}
}

class HashTableEntry<Key,Value> {
	public Key key;
	public Value value;
	public HashTableEntry(Key key, Value val) {
		this.key = key;
		this.value = val;
	}
	public String getKey() {
		return String.valueOf(key);
	}
	public String getValue() {
		return String.valueOf(value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof HashTableEntry) {
			return ((HashTableEntry<Key,Value>)obj).key.equals(this.key);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
	
	@Override
	public String toString() {
		return String.valueOf(key).concat(String.valueOf(value));
	}
}
