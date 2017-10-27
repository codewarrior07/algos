// Take two binary numbers(given as strings) and add them 
public class AddTwoBinaryNumbers {
	public static void main(String[] args){
		AddTwoBinaryNumbers a = new AddTwoBinaryNumbers();
		System.out.println(a.addBin("10111","1101"));
	}
	
	public String addBin(String s1,String s2){
		if(s1==null || s2==null)
			return null;
		int i=s1.length()-1, j=s2.length()-1;
		int carry=0;
		StringBuilder sum=new StringBuilder();
		while(i>=0 && j>=0) {
			int temp = Character.getNumericValue(s1.charAt(i--)) + Character.getNumericValue(s2.charAt(j--)) + carry;
			if((temp%2)==0)
				sum.append("0");
			else
				sum.append("1");
			if(temp>1)
				carry=1;
		}
		while(i>=0){
			int temp = (int)s1.charAt(i--) + carry;
			if((temp%2)==0)
				sum.append("0");
			else
				sum.append("1");
			if(temp>1)
				carry=1;
		}
		while(j>=0){
			int temp = (int)s2.charAt(j--) + carry;
			if((temp%2)==0)
				sum.append("0");
			else
				sum.append("1");
			if(temp>1)
				carry=1;
		}
		if(carry>0)
			sum.append(carry);
		sum.reverse();
		return sum.toString();
	}
}
