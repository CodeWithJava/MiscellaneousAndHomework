import java.util.Arrays;
public class Solution
{
	public int strStr(String haystack,String needle)
	{
		char [] x = needle.toCharArray();
		Arrays.sort(x);
		needle = String.valueOf(x);
		int size = needle.length();
		for(int i = 0;;i++)
		{
			if(i+size == haystack.length() + 1)
				return -1;
			char [] y = haystack.substring(i,i+size).toCharArray();
			Arrays.sort(y);
			String s = String.valueOf(y);
			if(s.equals(needle))
				return i;
		}
	}
}