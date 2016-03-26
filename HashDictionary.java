/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: HashTable
JDK Version: 1.8.0_71
Author: Yabin Han
Create Date: March 23th 2016
Finish Date: March 28th 2016
Description: Implement Dictionary with HashTable
*/

import java.util.Scanner; 
import java.io.FileInputStream; 
import java.io.IOException; 

public class HashDictionary
{
        public static void main(String [] args) throws IOException
        {
                HashTable x = new HashTable(500000);
                Scanner scanner = new Scanner(System.in); 
                System.out.println("Please input path of the dictionary data file"); 
                String path = scanner.next(); 
                Scanner data = new Scanner(new FileInputStream(path)); 
                while(data.hasNext())
                        x.add(data.next());
                int [] y = x.getCollisions();
                for(int i = 1;i < y.length;i++)
                        System.out.println("There are " + y[i] + "times insert with");
                        
                data.close();
                
                System.out.println("Please input path of the test data file"); 
                String path2 = scanner.next(); 
                Scanner data2 = new Scanner(new FileInputStream(path2)); 
                while(data2.hasNext())
                        System.out.println(x.contains(data2.next()));
          
                scanner.close();
                data2.close();
        }
}

class HashTable
{
        private String [] table;
        private int size;
        private int capacity;
        private int [] collisions;

        HashTable()
        {
                this(16);
        }

        HashTable(int initialSize)
        {
                int i = 1;
                while(i < initialSize)
                        i *= 2;
                table = new String [i];
                size = 0;
                capacity = i / 2;
                collisions = new int [11];
        }

        public void add(String s)
        {
                size++;
                if(size > capacity)
                        expend();
                int i = hash(s);
                int collision = 0;
                while(table[i] != null)
                {
                        if(table[i].equals(s))
                                size--;
                        i = (i+1) & (table.length-1);
                        collision++;
                        if(collision >= 10)
                                collision = 10;
                        collisions[collision]++;
                }
                table[i] = s;              
        }

        public boolean contains(String s)
        {
                int i = hash(s);
                while(table[i] != null)
                {
                        if(table[i].equals(s))
                                return true;
                        i = (i+1) & (table.length-1);
                }
                return false;
        }

        public int [] getCollisions()
        {
                return collisions;
        }

        private int hash(String s)
        {
                int h = s.hashCode();
                h = h & (table.length-1);
                return h;
        }
}