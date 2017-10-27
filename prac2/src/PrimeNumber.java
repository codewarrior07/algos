import java.util.HashMap;
import java.util.Map;

public class PrimeNumber {
	public static void main(String[] args) {
		//sieveOfEratosthenes(18);
		//System.out.println(isPrime(17));
		primeFactors(11271);
	}
	public static void sieveOfEratosthenes(int n) {
		int[] primes = new int[n+1];
		primes[0] = 0; // 0 not prime
		primes[1] = 0; // 1 not prime
		for(int i=2;i<=n;i++)
			primes[i] = 1;
		for(int i=2;i*i<=n;++i) {
			for(int j=2;j*i<=n;++j)
				primes[j*i] = 0;
		}
		for(int i=0;i<=n;++i) {
			if(primes[i] == 1)
				System.out.println(i);
		}
	}
	public static boolean isPrime(int n) {
		if(n%2==0)
			return false;
		for(int i=3;i*i<=n;++i) {
			if(n%i==0)
				return false;
		}
		return true;
	}
	public static void primeFactors(int n) {
		Map<Integer,Integer> factors = new HashMap<Integer,Integer>();
		for(int i=2;i*i<=n;++i) {
			while(n%i==0) {
				if(factors.containsKey(i)) {
					int val = factors.get(i);
					factors.put(i, ++val);
				} else
					factors.put(i, 1);
				n = n/i;
			}
		}
		if(n!=1)
			factors.put(n, 1);
		if(factors.isEmpty())
			System.out.println("Prime number");
		else {
			for(Map.Entry<Integer,Integer> entry:factors.entrySet())
				System.out.println(entry.getKey()+" "+entry.getValue());
		}
	}
}
