import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parse {
	public static void main(String [] args) throws Exception
	{
		//Scanner data  =  new Scanner(new FileInputStream("D:\\data"));
		BufferedReader in = new BufferedReader(new FileReader("D:\\data.txt"));
		String inputLine;
		while((inputLine = in.readLine() )!= null)
		{
			System.out.println(inputLine);
			String pattern = "([0-9]+[\\.]*[0-9]*)\\s*([-+*/])\\s*([0-9]+[\\.]*[0-9]*)";//bug:12.---

			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(inputLine);
			if(m.find())
			{
				double left = Double.parseDouble(m.group(1));
				double right = Double.parseDouble(m.group(3));
				if(m.group(2).equals("+"))
				{
					double f = left + right;
					System.out.println("output = "+f);
				}
				else if(m.group(2).equals("-"))
				{
					double f = left - right;
					System.out.println("output = "+f);
				}
				if(m.group(2).equals("*"))
				{
					System.out.println("output = "+left*right);
				}
				if(m.group(2).equals("/"))
				{
					System.out.println("output = "+left/right);
				}
			}
			else
			{
				System.out.println("No match");
			}
		}
	}
}
