package sorting;

public class SelectionSort {
	public static void main(String[] args) {
		int[] inp = {5,1,4,2,3};
		for(int i=0;i<inp.length;++i) {
			int smallIndex = i;
			for(int j=i+1;j<inp.length;++j) {
				if (inp[j] < inp[smallIndex]) 
					smallIndex = j;				
			}
			if(i != smallIndex) {
				int temp = inp[i];
				inp[i] = inp[smallIndex];
				inp[smallIndex] = temp;
			}
		}
		for(Integer i:inp)
			System.out.println(i);
	}
}
