/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: Prime Test with Miller-Rabin Algorithm
JDK Version: 1.8.0_71
Author: Yabin Han
Create Date: January 26th 2016
Finish Date: January 31th 2016
Description: Confirm the input number is prime or not
*/
import java.util.Scanner;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
public class MillerRabin
{
	public static void main (String [] args) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input path of the data file");
		String path = scanner.next();
		while(path.equals(""))
			System.out.println("Please input the path of the data file");
		Scanner data = new Scanner(new FileInputStream(path));
		while(data.hasNextLong())
		{
			long x = data.nextLong();
			System.out.println(x + " " + millerRabin(x));
		}
	}
	public static boolean millerRabin(long n)
	{
		if(n == 0L || n == 1L)
			return false;
		if(n == 2L || n == 3L)
			return true;
		if(n % 2L == 0L)
			return false;
		int d = 0;
		int s = 0;
		long m = n - 1;
		while ((m & 1) == 0)
		{
			s++;
			m = m >> 1;
		}
		d = (int) m;
		int temp = s;
		Random random = new Random();
		for (int i = 1; i <= 6; i++)
		{
			long a = (long)random.nextInt((int)n-3) + 2L;
			long x = PowerMod(a, d, n);
			if (x == 1 || x == (int) (n - 1))
				continue;
			boolean witness = false;
			s = temp;
			while(s > 1 && !witness)
			{
					x = (x * x) % n;
					if (x == 1)
						return false;
					if (x == n - 1)
					{
						witness = true;
						break;
					}
					s--;
			}
			if (!witness)
				return false;
		}
		return true;
	}

	public static long PowerMod(long a, int d, long n)
	{
		long prod = 1;
		while(d > 0)
		{
			if (d % 2 != 0)
				prod = (prod * a) % n;
			a = (a * a) % n;
			d = d / 2;
		}
		return prod;
	}
}
