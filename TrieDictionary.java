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

public class TrieDictionary
{
        public static void main(String [] args) throws IOException
        {
                Trie x = new Trie();
                Scanner scanner = new Scanner(System.in); 
                System.out.println("Please input path of the data file"); 
                String path = scanner.next(); 
                while(path.equals("")) 
                    System.out.println("Please input the path of the data file"); 
                Scanner data = new Scanner(new FileInputStream(path)); 
                while(data.hasNext())
                        x.insert(data.next());
                data.close();
                boolean flag = true;
                while(flag)
                {
                        System.out.println("Please input the word you want to search");
                        System.out.println("Tips you can use . to represent any single letter, lowercase only");
                        String s = scanner.next();
                        System.out.println(x.match(s));
                        System.out.println("Continue searching? Y/N");
                        String w = scanner.next();
                        if(w.equals("n") || w.equals("N"))
                        {
                                flag = false;
                                System.out.println("Thank you for work with our Han's Dictionary");
                        }        
                }
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