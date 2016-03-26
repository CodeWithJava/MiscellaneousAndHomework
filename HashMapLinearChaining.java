import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Ying Cui
 * 
 * 
 */
public class HashMapLinearChaining
{
	public static class Chain
	{
		private int key;
		private String word;
		private Chain next;
		
		Chain(int key, String word)
		{
			this.key = key;
			this.word = word;
			this.next = null;
		}
		
		public String getWord()
		{
			return this.word;
		}
		
		public void setWord(String word)
		{
			this.word = word;
		}
		
		public int getKey()
		{
			return this.key;
		}
		
		public Chain getNext()
		{
			return this.next;
		}
		
		public void setNext(Chain next)
		{
			this.next = next;
		}
	}
	
	private Chain[] table;
	private int used; // index
	private double limit; // threshold
	private int[] hist; // collision times
	
	// after grow, the hash table should be calculated again
	private void grow()
	{
		Chain[] temp = this.table;
		int length = this.table.length;
		this.table = new Chain[2 * length];
		for (int i = 0; i < this.table.length; i++)
			this.table[i] = null;
		this.used = 0;
		for (int code = 0; code < temp.length; code++)
		{
			if (temp[code] != null)
			{
				Chain entry = this.table[code];
				while (entry != null)
				{
					add(entry.getWord());
					entry = entry.getNext();
				}
			}
		}
	}
	
	public HashMapLinearChaining(int initialSize)
	{
		int i;
		for (i = 1; i < initialSize; i *= 2)
			;
		this.table = new Chain[i];
		this.used = 0;
		this.limit = 0.8 * initialSize;
		this.hist = new int[11];
	}
	
	public int hash(String word)
	{
		return (Math.abs(word.hashCode()) % this.table.length);
	}
	
	public void add(String word)
	{
		this.used++;
		if (this.used > this.limit)
			grow();
		int code = hash(word);
		int key = Math.abs(word.hashCode());
		int count = 0;
		if (table[code] == null) // not exist
			table[code] = new Chain(key, word);
		else // exist and collision
		{
			Chain entry = table[code];
			while (entry.getNext() != null && entry.getKey() != key) // exist in chain
				entry = entry.getNext();
			if (entry.getKey() == key) // add in chain
				entry.setWord(word);
			else
				entry.setNext(new Chain(key, word)); // come next chain node and add in chain
			used--;
			count++;
			if (count >= 10)
				count = 10;
			hist[count]++;
		}
	}
	
	public boolean contains(String word)
	{
		int code = hash(word);
		while (table[code] != null)
		{
			Chain entry = table[code];
			if (entry.getWord() == word)
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception
	{
		HashMapLinearChaining dic = new HashMapLinearChaining(100);
		Scanner dictionary = new Scanner(new FileInputStream("dict.txt"));
		while (dictionary.hasNext())
			dic.add(dictionary.next());
		dictionary.close();
		Scanner test = new Scanner(new FileInputStream("hw8.dat"));
		while (test.hasNext())
		{
			System.out.println("Test word in dictionary: " + dic.contains(test.next()));
		}
		test.close();
	}
}
