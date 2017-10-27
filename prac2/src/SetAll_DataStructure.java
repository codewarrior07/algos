// get,set,setAll indices of array to some value in O(1) 
public class SetAll_DataStructure {
	int[] arr;
	int[] from;
	int[] to;
	int initVal;
	int top;
	SetAll_DataStructure(int size,int initVal){
		arr = new int[size];
		from = new int[size];
		to = new int[size+1];
		top = 1;
		this.initVal = initVal;
	}
	
	public void set(int index,int val){
		if(check(index) && used(index))
			arr[index]=val;
		else { //unused so far
			arr[index]=val;
			from[index]=top;
			to[top]=index;
			++top;
		}
	}
	
	public int get(int index){
		if(check(index) && used(index))
			return arr[index];
		else
			return this.initVal;
	}
	
	public void setAll(int val){
		this.initVal = val;
	}
	
	public boolean used(int index){
		return from[index]>0 && to[from[index]]==index;
	}
	
	public boolean check(int index) {
		if(index<0 || index>=arr.length)
			throw new IllegalArgumentException("bad index");
		return true;
	}
	
	public static void main(String[] args){
		SetAll_DataStructure s = new SetAll_DataStructure(5,-1);
		s.set(0,5);
		s.set(0,6);
		s.set(1,2);
		s.set(1,4);
		s.set(4,321);
		System.out.println(s.get(4));
	}
}
