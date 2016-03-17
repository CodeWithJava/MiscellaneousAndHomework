/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: TrieDictionary
JDK Version: 1.8.0_71
Author: Yabin Han
Create Date: February 26th 2016
Finish Date: February 29th 2016
Description: Implement Dictionary with Trie
*/

import java.util.Scanner; 
import java.io.FileInputStream; 
import java.io.IOException; 

public class Dictionary
{
        public static void main(String [] args) throws IOException
        {
                Trie x = new Trie();
                Scanner scanner = new Scanner(System.in); 
                System.out.println("Please input path of the dictionary data file"); 
                String path = scanner.next(); 
                Scanner data = new Scanner(new FileInputStream(path)); 
                while(data.hasNext())
                        x.insert(data.next());
                data.close();
                
                System.out.println("Please input path of the test data file"); 
                String path2 = scanner.next(); 
                Scanner data2 = new Scanner(new FileInputStream(path2)); 
                while(data2.hasNext())
                        System.out.println(x.match(data2.next()));
                scanner.close();
                data2.close();
        }
}

class Trie
{
        private static class TrieNode
        {
                TrieNode [] children;
                boolean isLeaf;
                
                TrieNode()
                {
                        children = new TrieNode[26];
                        isLeaf = false;
                }
        }

        private TrieNode root;
        
        Trie()
        {
                root = new TrieNode();
        }
        
        public void insert(String word)
        {
                TrieNode node = root;
                
                for(char x: word.toCharArray())
                {
                        if(node.children[x - 'a'] == null)
                                node.children[x - 'a'] = new TrieNode();
                        node = node.children[x - 'a'];
                }
                node.isLeaf = true;
        }
        
        public boolean search(String word)
        {
                TrieNode node = root;
                
                for(char x: word.toCharArray())
                {
                        if(node.children[x - 'a'] == null)
                                return false;
                        else
                                node = node.children[x - 'a'];
                }
                return node.isLeaf;
        }
        
        public boolean initializeWith(String prefix)
        {
                TrieNode node = root;
                
                for(char x: prefix.toCharArray())
                {
                        if(node.children[x - 'a'] == null)
                                return false;
                        else
                                node = node.children[x - 'a'];
                }
                return true;
        }
        
        public boolean match(String word)
        {
                return match(root,0,word);
        }
        
        private boolean match(TrieNode node,int k,String word)
        {
                if(node == null)
                        return false;
                else if(k == word.length())
                        return node.isLeaf;

                char c = word.charAt(k);
                
                if(c == '.')
                {
                        for(TrieNode x: node.children)
                                if(match(x,k+1,word))
                                        return true;
                        return false;
                }
                else
                {
                        TrieNode x = node.children[c - 'a'];
                        return match(x,k+1,word);
                }
        }
}