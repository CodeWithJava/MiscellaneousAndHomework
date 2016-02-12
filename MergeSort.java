import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
public class MergeSort
{
	public static void main(String [] args) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input path of the data file");
		String path = scanner.next();
		while(path.equals(""))
			System.out.println("Please input the path of the data file");
		Scanner data = new Scanner(new FileInputStream(path));
		while(data.hasNextInt())
		{
			int size = data.nextInt();
			int [] nums = new int [size];
			for(int i = 0;i < nums.length;i++)
			{
				nums[i] = data.nextInt();
			}
			sort(nums);
			for(int i: nums)
				System.out.print(i + " ");
			System.out.println();
		}
	}
	public static void sort(int [] array)
	{
		seperate(array,0,array.length - 1);
	}
	private static void seperate(int [] array,int l,int r)
	{
		if(l < r)
		{
			int m = l + (r - l) / 2;
			seperate(array,l,m);//divide
			seperate(array,m+1,r);//divide
			merge(array,l,m,r);//conquer
		}
	}
	private static void merge(int [] array,int l,int m,int r)
	{
		int [] left = Arrays.copyOfRange(array,l,m+1);
		int [] right = Arrays.copyOfRange(array,m+1,r+1);
		int i = 0;//pointer for left part
		int j = 0;//pointer for right part
		int k = 0;//pointer for whole array
		while(k < r - l + 1)
		{
			if(i == left.length)//if the length of left part is less than the right part's just copy the remaining of right part
				array[l+k] = right[j++];
			else if(j == right.length)//if the length of right part is less than the right part's just copy the remaining of left part
				array[l+k] = left[i++];
			else if(left[i] < right[j])
				array[l+k] = left[i++];
			else
				array[l+k] = right[j++];
			k++;
		}
	}
}