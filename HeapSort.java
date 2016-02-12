import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
public class HeapSort
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
		buildHeap(array);
		for(int i = array.length - 1;i >= 1;i--)
		{
			swap(array,0,i);
			maxHeap(array,i,0);
		}
	}
	private static void buildHeap(int [] array)
	{
		for(int i = array.length / 2;i >= 0;i--)
			maxHeap(array,array.length,i);
	}
	private static void maxHeap(int [] array,int heapSize,int parent)
	{
		int left = parent * 2 + 1;
		int right = parent * 2 + 2;
		int largest = parent;
		if(left < heapSize && array[left] > array[largest])
			largest = left;
		if(right < heapSize && array[right] > array[largest])
			largest = right;
		if(largest != parent)
		{
			swap(array,parent,largest);
			maxHeap(array,heapSize,largest);
		}
	}
	private static void swap(int [] array,int i,int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}