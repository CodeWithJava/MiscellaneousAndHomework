import java.util.Scanner;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
public class BinarySearchRange
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
			int x = binarySearch(nums,target);
			if(x == -1)
				System.out.println("NOT FOUND " + target);
			else
				System.out.println(target + " " + x);
			loop--;
		}
	}
	private static int binarySearch(int [] nums,int target)
	{
		int x = binarySearch(nums,0,nums.length - 1,target);
		if(x == -1)
			return -1;
		int l = 0;
		int r = 0;
		int t = x;
		while(t != -1)
		{
			l = t;
			t = binarySearch(nums,0,t - 1,target);
		}
		t = x;
		while(t != -1)
		{
			r = t;
			t = binarySearch(nums,t+1,nums.length - 1,target);
		}
		return r - l + 1;
	}
	private static int binarySearch(int [] nums,int l,int r,int target)
	{
		if(target < nums[l] || target > nums[r])
			return -1;
		while(l <= r)//
		{
			int m = l + (r - l ) / 2;
			if(nums[m] == target)
				return m;
			else if (target < nums[m])
				r = m - 1;
			else
				l = m + 1;
		}
		return -1;
	}
}