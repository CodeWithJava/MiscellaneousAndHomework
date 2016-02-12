import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
public class InsertionSort
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
		for(int i = 1;i < array.length;i++)
		{
			int j = i - 1;
			int temp = array[i];
			while(j >= 0 && array[j] > temp)
			{
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = temp;
		}
	}
}
