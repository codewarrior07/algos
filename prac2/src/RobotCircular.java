import java.util.HashMap;
import java.util.Map;

//http://www.geeksforgeeks.org/check-if-a-given-sequence-of-moves-for-a-robot-is-circular-or-not/

public class RobotCircular {
	public static void main(String[] args){
		System.out.println(new RobotCircular().isCircular("GLRRG"));
	}
	//doesn't work
	public boolean isCircular(String inp){
		int x = 0, y = 0;
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		map.put('e',0);
		map.put('s',1);
		map.put('w',2);
		map.put('n',3);
		char dir ='e';
		for(int i=0;i<inp.length();++i){
			char c = inp.charAt(i);
			if(c == 'G'){
				if(dir == 'n')
					--y;
				else if(dir == 'e')
					++x;
				else if(dir == 's')
					++y;
				else if(dir == 'w')
					--x;
			}
			else if(c == 'L')
				dir = (+1)%4;
			
		}
		return false;
	}
}
