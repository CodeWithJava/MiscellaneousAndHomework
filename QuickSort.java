/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: Prime Test with Miller-Rabin Algorithm
JDK Version: 1.8.0_71
Author: Yabin Han
Create Date: Feburary 2nd 2016
Finish Date: Feburary 4th 2016
Description: QuickSort
*/
import java.util.Scanner;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
public class QuickSort
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
			quickSort(nums,0,nums.length - 1);
			for(int i: nums)
				System.out.print(i + " ");
			System.out.println();
		}
	}
	private static void quickSort(int [] nums,int l,int r)
	{
		int key;
		if(l < r)
		{
			key = partition(nums,l,r);
			quickSort(nums,l,key - 1);
			quickSort(nums,key + 1,r);
		}
	}
	private static int partition(int [] nums,int l,int r)
	{	int i = randomPivotIndex(nums,l,r);
		swap(nums,l,i);
		int pivot = nums[l];
		while(l < r)
		{
			while(l < r && nums[r] > pivot)
				r--;
			nums[l] = nums[r];
			while(l < r && nums[l] <= pivot)
				l++;
			nums[r] = nums[l];
		}
		nums[l] = pivot;
		return l;
	}
	private static int randomPivotIndex(int [] nums,int l,int r)
	{
		Random random = new Random();
		return random.nextInt(r + 1 - l) + l;
	}
	private static void swap(int [] nums,int l,int r)
	{
		int temp = nums[l];
		nums[l] = nums[r];
		nums[r] = temp;
	}
}