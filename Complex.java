import java.util.Scanner;
public class Complex
{
	public double x;
	public double y;
	public static void main(String [] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input the real quantity of the first complex");
		double m = scanner.nextDouble();
		System.out.println("Please input the image quantity of the first complex");
		double n = scanner.nextDouble();
		System.out.println("Please input the real quantity of the second complex");
		double p = scanner.nextDouble();
		System.out.println("Please input the image quantity of the second complex");
		double q = scanner.nextDouble();
		Complex a = new Complex(m,n);
		Complex b = new Complex(p,q);
		System.out.println("The sum of the complex numbers is " + add(a,b));
		System.out.println("The difference of the complex numbers is " + substract(a,b));
		System.out.println("The product of the complex numbers is " + multiply(a,b));
		Complex x = divide(a,b);
		if(x == null)
			System.out.println("The second complex number is ZERO");
		else
			System.out.println("The quotient of the complex numbers is " + x);
		System.out.println("The modulo of the first complex number is " + modulo(a));
		System.out.println("The argument of the first complex number is " + argument(a));
		System.out.println("The modulo of the second complex number is " + modulo(b));
		System.out.println("The argument of the second complex number is " + argument(b));
	}

	Complex(double x,double y)
	{
		this.x = x;
		this.y = y;
	}

	public static Complex add(Complex p,Complex q)
	{
		return new Complex(p.x + q.x,p.y + q.y);
	}

	public static Complex substract(Complex p,Complex q)
	{
		return new Complex(p.x - q.x,p.y - q.y);
	}

	public static Complex multiply(Complex p,Complex q)
	{
		return new Complex(p.x * q.x - p.y * q.y,p.y * q.x + p.x * q.y);
	}

	public static Complex divide(Complex p,Complex q)
	{
		double numerator1 = p.x * q.x + p.y * q.y;
		double numerator2 = p.y * q.x - p.x * q.y;
		double denominator = q.x * q.x + q.y * q.y;

		if(denominator == 0)
			return null;

		return new Complex(numerator1 / denominator,numerator2 / denominator);
	}

	public static double modulo(Complex complex)
	{
		return Math.sqrt(complex.x * complex.x + complex.y * complex.y);
	}

	public static double argument(Complex complex)
	{
		double x = Math.abs(complex.x);
		double y = Math.abs(complex.y);

		if(y == 0)
			return 0;

		if(x == 0)
		{
			if(y > 0)
				return 90;
			else if(y < 0)
				return -90;
		}

		double argument = Math.atan(y / x) / Math.PI * 180;

		if(complex.x > 0 && complex.y > 0)
			return argument;
		else if(complex.x > 0 && complex.y < 0 )
			return 360 - argument;
		else if(complex.x < 0 && complex.y > 0)
			return argument + 90;
		else
			return argument + 180;
	}
	
	public String toString()
	{
		if(x == 0 && y == 0)
			return 0.0 + "";
		if(x == 0)
			return y + "i";
		if(y == 0)
			return x + "";
		else if(y < 0)
			return x + "" + y + "i";
		else
			return x + "+" + y + "i";
	}
}