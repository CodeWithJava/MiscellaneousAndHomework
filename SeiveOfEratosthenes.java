/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: CountPrimes
JDK Version: 1.8.0_71
Author: Yabin Han
Create Date: January 21st 2016
Finish Date: January 24th 2016
Description: Get the number of primes between two integer a and b
*/
import java.util.Scanner;
import java.util.BitSet;
public class SeiveOfEratosthenes
{
	public static void main (String [] args)
	{
		// Create a Scanner to get the input
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please input the low bound");

		// Create a variable to record the low bound value
		long a = scanner.nextLong();

		System.out.println("Please input the up bound");

		// Create a variable to record the up bound value
		long b = scanner.nextLong();

		// Record the start time of countPrimes
		long start = System.currentTimeMillis();

		// Output the result of countPrimes between the low bound and the up bound
		System.out.println("The number of primes between the two numbers is " + countPrimes(a,b));

		// Record the end time of CountPrimes
		long end = System.currentTimeMillis();

		// Output the runtime
		System.out.println("Run Time is " + (end - start) + " milliseconds");
	}
	
	private static long countPrimes(long a,long b)
	{
		// Handle the corner case 1: low bound is greater than the up bound
		if(a > b)
		{
			System.out.println("ERROR: low bound is greater than up bound");
			return -1L;
		}
		
		// Handle the corner case 2:Either low bound or up bound is negative number
		if(a < 0L || b < 0L)
		{
			System.out.println("ERROR: either low bound or up bound is negative");
			return -1L;
		}
		
		//Handle the corner case 3: if low bound is less than up bound and up bound is less than 2
		//But there is no prime number which the input is less than 2
		if(b < 2L)
			return 0L;
		//To improve the time complexity
		//There are two methods for different range of input
		if(b - a >= Integer.MAX_VALUE / 100 && b < Integer.MAX_VALUE)
		{
			//Method I: for the input b is less than max value of integer
			BitSet notPrime = new BitSet((int)b+1);
			notPrime.set(0);
			notPrime.set(1);
			int nop = 0;//The number of primes among the range
			for(int i = 2;i * i <=  b;i++)
			{
				if(i == 2)
				{
					int x = 4;
					while(x <= b)
					{
						notPrime.set(x);
						x += 2;
					}
					continue;
				}
				if(!notPrime.get(i))
				{
					int x = i * i;
					while(x <= b)
					{
						notPrime.set(x);
						x += i * 2;
					}
				}
			}

			for(long j = a;j <= b;j++)
			{
				if(!notPrime.get((int)j))
					nop++;
			}
			return nop;
		}
		else
		{	
			//Method II: for the input a is greater than max value of integer 
			long sqrtB = (long)Math.sqrt(b);
			long nop = b - a + 1;//The number of primes among the range
			BitSet seive = new BitSet((int)sqrtB + 1);
			BitSet range = new BitSet((int)nop + 1);
			for(int i = 2;i <= sqrtB;i++)
			{
					if(!seive.get(i))
					{
						for(int k = i;k <= sqrtB;k += i)
						{
							seive.set(k);
						}
						long ratio = a / i;
						for(long j = ratio * i;j <= b;j += i)
						{
							if(j >= a)
							{
								if(j == i)
									continue;
								int index = (int)(j - a);
								if(!range.get(index))
								{
									range.set(index);
									nop--;
								}
							}
						}
					}
			}
			if(a <= 1L)
				return --nop;
			return nop;
		}
	}
}