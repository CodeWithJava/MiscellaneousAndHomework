import java.util.Scanner;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
public class BinarySearch
{
	public static void main (String [] args) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input path of the data file");
		String path = scanner.next();
		while(path.equals(""))
			System.out.println("Please input the path of the data file");
		Scanner data = new Scanner(new FileInputStream(path));
		data.next();
		int loop = data.nextInt();
		while(loop > 0)
		{
			int size = data.nextInt();
			int [] nums = new int [size];
			for(int i = 0;i < nums.length;i++)
			{
				nums[i] = data.nextInt();
			}
			int target = data.nextInt();
			int [] x = binarySearch(nums,0,nums.length - 1,target);
			if(x[0] == -1)
				System.out.println("NOT FOUND " + x[1]);
			else
				System.out.println(target + " " + x[1]);
			loop--;
		}
	}
	private static int [] binarySearch(int [] nums,int l,int r,int target)
	{
		int count = 0;
		while(l <= r)
		{
			count++;
			int m = l + (r - l ) / 2;
			if(nums[m] == target)
				return new int [] {m,count};
			else if (target < nums[m])
				r = m - 1;
			else
				l = m + 1;
		}
		return new int [] {-1,count};
	}
}