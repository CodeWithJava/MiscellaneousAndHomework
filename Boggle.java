/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: TrieDictionary
JDK Version: 1.8.0_77
Author: Yabin Han
Create Date: March 28th 2016
Finish Date: March 30th 2016
Description: Implement Boggle
*/

import java.util.Scanner; 
import java.io.FileInputStream; 
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Boggle
{
	Set<String> set;
	public static void main (String [] args) throws IOException
	{
		Trie trie = new Trie();
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Please input path of the dictionary data file"); 
		String path = scanner.next(); 
		Scanner data = new Scanner(new FileInputStream(path)); 
		while(data.hasNext())
			trie.insert(data.next());
		data.close();
		
		System.out.println("Please input path of the test data file"); 
		String path2 = scanner.next(); 
		Scanner data2 = new Scanner(new FileInputStream(path2));
		
		int n = data2.nextInt();
		String [][] board = new String [n][n];
		
		for(int i = 0;i < n;i++)
			for(int j = 0;j < n;j++)
			{
				if(data2.hasNext())
					board[i][j] = data2.next();
			}

		scanner.close();
		data2.close();

		new Boggle().wordSearch(board,trie);
	}

	private void wordSearch(String [][] board,Trie trie)
	{
		if(board == null || board.length == 0)
			System.out.println("Input is invalid");

		set = new HashSet<>();
		int r = board.length;
		int c = board[0].length;

		boolean [][] visited = new boolean[r][c];

		for(int i = 0;i < r;i++)
			for(int j = 0;j < c;j++)
				wordSearch(board,i,j,"",visited,trie);

		int i = 0;
		int j = 0;
		for(String word: set)
		{
			System.out.println(word + " is a word");
			i++;
			j++;
			if(i == 5)
			{
				System.out.println();
				i = 0;
			}
		}
		System.out.println("\nThere are " + j + " words in the matrix !!!");
	}

	private void wordSearch(String [][] board,int i,int j,String subword,boolean [][] visited,Trie trie)
	{
		if(i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1)
			return;

		if(visited[i][j])
			return;

		subword += board[i][j];

		if(!trie.initializeWith(subword))
			return;

		if(trie.search(subword))
			set.add(subword);

		visited[i][j] = true;
		
		wordSearch(board,i-1,j-1,subword,visited,trie);
		wordSearch(board,i-1,j+1,subword,visited,trie);
		wordSearch(board,i+1,j-1,subword,visited,trie);
		wordSearch(board,i+1,j+1,subword,visited,trie);
		wordSearch(board,i-1,j,subword,visited,trie);
		wordSearch(board,i,j-1,subword,visited,trie);
		wordSearch(board,i+1,j,subword,visited,trie);
		wordSearch(board,i,j+1,subword,visited,trie);
		
		visited[i][j] = false;
		
	}

	private static class Trie
	{
		private static class TrieNode
		{
			TrieNode [] children;
			boolean isLeaf;

			TrieNode()
			{
				children = new TrieNode [58];
				isLeaf = false;
			}
		}

		TrieNode root;

		Trie()
		{
			root = new TrieNode();
		}

		public boolean insert(String word)
		{
			if(word == null || word.length() == 0)
				return false;

			TrieNode node = root;
			for(char x: word.toCharArray())
			{
				if(node.children[x - 'A'] == null)
					node.children[x - 'A'] = new TrieNode();
				node = node.children[x - 'A'];
			}
			if(node.isLeaf)
				return false;
			node.isLeaf = true;
			return true;
		}

		public boolean search(String word)
		{
			if(word == null || word.length() == 0)
				return false;

			TrieNode node = root;
			for(char x: word.toCharArray())
			{
				if(node.children[x - 'A'] == null)
					return false;
				else
					node = node.children[x - 'A'];
			}
			return node.isLeaf;
		}

		public boolean initializeWith(String prefix)
		{
			if(prefix == null || prefix.length() == 0)
				return false;

			TrieNode node = root;
			for(char x: prefix.toCharArray())
			{
				if(node.children[x - 'A'] == null)
					return false;
				else
					node = node.children[x - 'A'];				
			}
			return true;
		}
	}
}