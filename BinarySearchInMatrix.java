/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.

The first integer of each row is greater than the last integer of the previous row.
*/
public class BinarySearchInMatrix
{
	public boolean binarySearchInMatrix(int [][] matrix,int target)
	{
		if(matrix == null || matrix.length == 0)
			return false;
		int r = matrix.length;
		int c = matrix[0].length;
		int left = 0;
		int right = r * c - 1;
		while(left <= right)
		{
			int m = (left + right) / 2;
			int x = m / c;
			int y = m % c;
			if(matrix[x][y] == target)
				return true;
			else if(matrix[x][y] < target)
				left = m + 1;
			else
				right = m - 1;
		}
		return false;
	}
}