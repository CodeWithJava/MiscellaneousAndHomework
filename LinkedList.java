/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: GrowArray
JDK Version: 1.8.0_71
Author: Yabin Han
Create Date: March 4th 2016
Finish Date: March 6th 2016
Description: Implement LinkedList
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
		DoubleLinkedList a = new DoubleLinkedList();
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

class ListNode
{
	int val;
	ListNode pre;
	ListNode next;
	ListNode(int val)
	{
		this.val = val;
		pre = null;
		next = null;
	}
}
class DoubleLinkedList
{
	private int size;
	public ListNode head;
	public ListNode tail;

	DoubleLinkedList()
	{
		size = 0;
		head = null;
		tail = null;
	}
	
	public void addHead(List<Integer> nums)
	{
		for(int i = nums.size() - 1;i >= 0;i--)
			addHead(new ListNode(nums.get(i)));
	}
	
	public void addTail(List<Integer> nums)
	{
		for(int i = 0;i < nums.size();i++)
			addTail(new ListNode(nums.get(i)));
	}
	
	public void removeHead(int x)
	{
		if(x > size)
			System.out.println("Beyond the size of the array");
		else
		{
			for(int i = 0;i < x;i++)
				removeHead();
		}
	}
	
	public void removeTail(int x)
	{
		if(x > size)
			System.out.println("Beyond the size of the array");
		else
		{
			for(int i = 0;i < x;i++)
				removeTail();
		}
	}
	
	public String toString()
	{
		if(size == 0)
			return "Empty Array";
		StringBuilder sb = new StringBuilder();
		ListNode p = head;
		while(p != null)
		{
			sb.append(p.val);
			p = p.next;
		}
		return sb.substring(0,sb.length() - 1);
	}
	
	private void addHead(ListNode node)
	{
		node.next = head;
		node.pre = null;
		if(head != null)
			head.pre = node;
		head = node;
		if(tail == null)
			tail = head;
		size++;
	}

	private void addTail(ListNode node)
	{
		node.pre = tail;
		node.next = null;
		if(tail != null)
			tail.next = node;
		tail = node;
		if(head == null)
			head = tail;
		size++;
	}

	private void removeHead()
	{
		head = head.next;
		size--;
	}

	private void removeTail()
	{
		tail = tail.pre;
		size--;
	}
}