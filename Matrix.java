public class Matrix
{
        private double [] array;
        private int row;
        private int column;
    
        Matrix()
        {
                this(2,2);
        }

        Matrix(int row,int column)
        {
                this.row = row;
                this.column = column;
                array = new double [row * column];
        }
        
        public int getRow()
        {
                if(this.row == 0)
                        return -1;
                else
                        return this.row;
        }
        
        public int getColumn()
        {
                if(this.column == 0)
                        return -1;
                else
                        return this.column;
        }

        public void set(int row,int column,double x)
        {
                if(row < 0 || row > this.row - 1 || column < 0 || column > this.column - 1)
                        return;
                else
                        this.array[row * this.column + column] = x;
        }

        public double get(int row,int column)
        {
                if(row < 0 || row > this.row - 1 || column < 0 || column > this.column - 1)
                        throw new IllegalArgumentException("Out of Matrix Bound");
                else
                        return this.array[row * this.column + column];
        }

        public static Matrix setMainDiagonal(Matrix m)
        {
                if(m.row != m.column)
                        return null;

                for(int i = 0;i < m.row;i++)
                        m.set(i,i,1.0);

                return m;
        }

        public static Matrix add(Matrix m1,Matrix m2)
        {
                if(m1.row != m2.row || m1.column != m2.column)
                        return null;

                Matrix m = new Matrix(m1.row,m1.column);

                for(int i = 0;i < m1.row * m1.column;i++)
                        m.array[i] = m1.array[i] + m2.array[i];

                return m;
        }

        public static Matrix subtract(Matrix m1,Matrix m2)
        {
                if(m1.row != m2.row || m1.column != m2.column)
                        return null;

                Matrix m = new Matrix(m1.row,m1.column);

                for(int i = 0;i < m1.row * m1.column;i++)
                        m.array[i] = m1.array[i] - m2.array[i];

                return m;
        }

        public String toString()
        {
                StringBuilder sb = new StringBuilder();
                for(int i = 0;i < this.row;i++)
                {
                        for(int j = 0;j < this.column;j++)
                                sb.append(array[i * column + j] + " ");
                        sb.append("\n");
                }
                return sb.toString();
        }
}