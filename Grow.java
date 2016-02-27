/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: GrowArray
JDK Version: 1.8.0_71
Author: Yabin Han
Create Date: February 26th 2016
Finish Date: February 29th 2016
Description: Implement GrowArry
*/

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Solution
{
	public static void main(String [] args) throws IOException
	{
		GrowArray a = new GrowArray();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input path of the data file");
		String path = scanner.next();
		while(path.equals(""))
			System.out.println("Please input the path of the data file");
		scanner.close();
		Scanner data = new Scanner(new FileInputStream(path));
		while(data.hasNextLine())
		{
			List<Integer> x = new ArrayList<>();
			String s = data.next();
			if(s.equals("ADD_FRONT"))
			{
				while(data.hasNextInt())
					x.add(data.nextInt());
				a.addHead(x);
			}
			else if(s.equals("ADD_BACK"))
			{
				while(data.hasNextInt())
					x.add(data.nextInt());
				a.addTail(x);
			}
			else if(s.equals("OUTPUT"))
				System.out.println(a.toString());
			else if(s.equals("REMOVE_FRONT"))
				a.removeHead(data.nextInt());
			else if(s.equals("REMOVE_BACK"))
				a.removeTail(data.nextInt());
		}
		data.close();
	}
}

class GrowArray
{
	private int capacity;
	private int size;
	private int [] array;

	GrowArray()
	{
		capacity = 10;
		size = 0;
		array = new int [capacity];
	}
	
	public void addHead(List<Integer> nums)
	{
		for(int i = nums.size() - 1;i >= 0;i--)
			add(0,nums.get(i));
	}
	
	public void addTail(List<Integer> nums)
	{
		for(int i = 0;i < nums.size();i++)
			add(size,nums.get(i));
	}
	
	public void removeHead(int x)
	{
		if(x > size)
			System.out.println("Beyond the size of the array");
		else
		{
			for(int i = 0;i < x;i++)
				remove(0);
		}
	}
	
	public void removeTail(int x)
	{
		if(x > size)
			System.out.println("Beyond the size of the array");
		else
		{
			for(int i = 0;i < x;i++)
				remove(size-1);
		}
	}
	
	public String toString()
	{
		if(size == 0)
			return "Empty Array";
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < size;i++)
			sb.append(array[i]+" ");
		return sb.substring(0,sb.length() - 1);
	}
	
	private void add(int index,int num)
	{
		if(index < 0)
			System.out.println("Out of ArrayBound:" + index);
		if(size == capacity)
			expansion();
		for(int i = size;i > index;i--)
			array[i] = array[i-1];
		array[index] = num;
		size++;
	}
	
	private void remove(int index)
	{
		for(int i = index;i < size;i++)
			array[i] = array[i+1];
		size--;
	}
	
	private void expansion()
	{
		int [] temp = new int [capacity * 2 + 1];
		for(int i = 0;i < size;i++)
			temp[i] = array[i];
		array = temp;
	}
}