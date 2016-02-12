import java.util.Scanner;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
public class BinarySearchRangeWithBug
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
			int x = binarySearch(nums,0,nums.length - 1,0,target);
			if(x == -1)
				System.out.println("NOT FOUND " + target);
			else
				System.out.println(target + " " + x);
			loop--;
		}
	}
	private static int binarySearch(int [] nums,int l,int r,int iteration,int target)
	{
		if(target < nums[l] || target > nums[r])
			return -1;
		while(l <= r)
		{
			int m = l + (r - l ) / 2;
			if(nums[m] == target)//I am here! I am wonderful bug which starts from this line
			{
				iteration++;
				int p = binarySearch(nums,l,m - 1,iteration,target);
				if(p != -1)
					iteration++;
				int q = binarySearch(nums,m + 1,r,iteration,target);
				if(q != -1)
					iteration++;
				return iteration;
			}//I am here! I am wonderful bug which ends to this line
			else if (nums[m] < target)
				l = m + 1;
			else
				r = m - 1;	
		}
		return -1;
	}

}