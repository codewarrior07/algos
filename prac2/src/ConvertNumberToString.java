// Given a number <= 999 999 999 999, convert it to string like "One thousand one hundred twenty two"
public class ConvertNumberToString {
	public static void main(String[] args){
		ConvertNumberToString c = new ConvertNumberToString();
		long num = 471092143519L;
		System.out.println(c.convert(num));
	}

	public String convert(long num){
		String[] big = new String[]{"billion","million","thousand",""};
		String[] ones = new String[]{"","one","two","three","four","five","six","seven","eight","nine",
				"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","ninteen"};
		String[] tens = new String[]{"","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety","hundred"};
		long divider=1000000000L;
		int reducer = 1000;
		int counter=0;
		StringBuilder result = new StringBuilder();
		while(num>0){
			int q = (int)(num/divider);
			if(q>0)
				result.append(convertLessThanThousand(q,ones,tens)).append(" ").append(big[counter]).append(" ");
			++counter;
			num=num%divider;
			divider=divider/reducer;
		}
		return result.toString();
	}

	private String convertLessThanThousand(int inp,String[] ones,String[] tens){
		StringBuilder result = new StringBuilder();
		if(inp>100){
			int temp = inp/100;
			result.append(ones[temp]).append(" hundred ");
			inp = inp%100;
		}
		if(inp%100 < 20){
			result.append(ones[inp%100]);
			inp = inp/100;
		} else {
			int temp = inp/10;
			result.append(tens[temp]).append(" ");

			inp = inp%10;
			result.append(ones[inp]);
		}		
		return result.toString();
	}
}
