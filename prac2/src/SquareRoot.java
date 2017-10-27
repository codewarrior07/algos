
public class SquareRoot {
	public static void main(String[] args){
		System.out.println(sqrt(26));
	}
	public static double sqrt(double number) {
		double t;
		double squareRoot = number/2;
		
		do {
			t = squareRoot;
			squareRoot = (t + (number / t)) / 2;
		} while ((t - squareRoot) != 0);
	 
		return squareRoot;
	}
}
