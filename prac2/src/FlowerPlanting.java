import java.util.ArrayList;
import java.util.List;

/*Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die. Given a flowerbed (represented as an array containing booleans), return if a given number of new flowers can be planted in it without violating the no-adjacent-flowers rule 
Sample inputs 

Input: 1,0,0,0,0,0,1,0,0 

3 => true 
4 => false 
Input: 1,0,0,1,0,0,1,0,0 

1 => true 
2 => false 
input: 0 

1 => true 
2 => false */ 

public class FlowerPlanting {
	public static void main(String[] args){
		List<Boolean> inp = new ArrayList<Boolean>();
		inp.add(false);
		System.out.println(canPlaceFlowers(inp,1));
	}
	public static boolean canPlaceFlowers(List<Boolean> flowerbed, int numberToPlace) { 
		if(flowerbed == null)
			return false;
		if(numberToPlace==0)
			return true;
		for(int i=0;i<flowerbed.size() && numberToPlace>0;){
			if(flowerbed.get(i))
				i+=2;
			else {
				if(i==flowerbed.size()-1 || !flowerbed.get(i+1)){
					flowerbed.set(i,true);
					--numberToPlace;
					i+=2;
				} else
					i += 3;
			}
		}
		if(numberToPlace==0)
			return true;
		return false;
	}
}
