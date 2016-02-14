class Node
{
	public Node [] children = new Node [26];
	public String item = "";
}
public class Tries
{
	public Node root = new Node();
	
	public void insert(String word)
	{
		Node node = root;
		for(char x: word.toCharArray())
		{
			if(node.children[x - 'a'] == null)
				node.children[x - 'a'] = new Node();
			node = node.children[x - 'a'];
		}
		node.item = word;
	}

	public boolean search(String word)
	{
		Node node = root;
		for(char x: word.toCharArray())
		{
			if(node.children[x - 'a'] == null)
				return false;
			else
				node = node.children[x - 'a'];
		}
		if(node.item.equals(word))
			return true;
		else
			return false;
	}

	public boolean initialWith(String prefix)
	{
		Node node = root;
		for(char x: prefix.toCharArray())
		{
			if(node.children[x - 'a'] == null)
				return false;
			else
				node = node.children[x - 'a'];
		}
		return true;
	}
}