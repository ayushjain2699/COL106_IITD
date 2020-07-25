import java.io.*;
class TwoDBlockMatrix
{
	public float[][] matrix;

	static public int[] dimension(String s) throws IOException
	{
		int[] size = new int[2];
		int row,col,check;
		int i = 0;
		String temp = "";
		while(true)
		{
			if(s.charAt(i)==' ')
			{	row = Integer.valueOf(temp); temp = "";break;	}
			else
			{	temp += String.valueOf(s.charAt(i));i++;	}
		}
		i++;

		while(true)
		{
			if((int)s.charAt(i)==13 ||(int)s.charAt(i)==10)
			{	col = Integer.valueOf(temp); temp = "";break;	}
			else
			{	temp += String.valueOf(s.charAt(i));i++;	}
		}
		check = col;
		for(;i<s.length();i++)
		{
			if(s.charAt(i)!='#')
			{
				if(s.charAt(i)!=' ' && s.charAt(i)!=';' && s.charAt(i)!='#' && (int)s.charAt(i)!=13 && (int)s.charAt(i)!=10)
				{	temp += String.valueOf(s.charAt(i));	}
				if(s.charAt(i)==';')
				{
					temp = "";
					row++;
					if(size[1]<col){size[1] = col;}
					col = check;
				}
				if(s.charAt(i)==' ')
				{
					temp = "";
					col++;
				}
			}
			else
			{
				row--;
				if(row>size[0]){	size[0] = row;	}
				temp = "";
				i++;
				if(i>=s.length()){break;}
				while(true)
				{
					if((int)s.charAt(i)==10 || (int)s.charAt(i)==13)
					{
						i++;
						if(i>=s.length())
						{
							break;
						}
					}
					else {break;}
				}				
				if(i>=s.length()){break;}
				while(true)
				{
					if(s.charAt(i)==' ')
					{	row = Integer.valueOf(temp); temp = "";break;	}
					else
					{	temp += String.valueOf(s.charAt(i));i++;	}
				}
				i++;
				while(true)
				{
					if((int)s.charAt(i)==13 ||(int)s.charAt(i)==10)
					{	col = Integer.valueOf(temp);check = col; temp = "";break;	}
					else
					{	temp += String.valueOf(s.charAt(i));i++;	}
				}
			}
		}
		return size;
	}
	static public TwoDBlockMatrix buildTwoDBlockMatrix(java.io.InputStream in) throws IOException
	{
		String s = "";
		int data = in.read();			
		while(data!=-1)
		{
			s = s + String.valueOf((char)data);
			data = in.read();
		}
		int row,col,check;
		int[] dim = new int[2];
		dim = dimension(s);
		row = dim[0];
		col = dim[1];
		float[][] array = new float[row][col];
		for(int i =  0;i<row;i++)
		{
			for(int j = 0;j<col;j++)
			{
				array[i][j]=0;
			}
		}		
		int i = 0;
		String temp = "";
		while(true)
		{
			if(s.charAt(i)==' ')
			{	row = Integer.valueOf(temp); temp = "";break;	}
			else
			{	temp += String.valueOf(s.charAt(i));i++;	}
		}
		i++;

		while(true)
		{
			if((int)s.charAt(i)==13 ||(int)s.charAt(i)==10)
			{	col = Integer.valueOf(temp); temp = "";break;	}
			else
			{	temp += String.valueOf(s.charAt(i));i++;	}
		}
		check = col;
		for(;i<s.length();i++)
		{
			if(s.charAt(i)!='#')
			{
				if(s.charAt(i)!=' ' && s.charAt(i)!=';' && s.charAt(i)!='#' && (int)s.charAt(i)!=13 && (int)s.charAt(i)!=10)
				{	temp += String.valueOf(s.charAt(i));	}
				if(s.charAt(i)==';')
				{
					array[row-1][col-1] = Float.valueOf(temp);
					temp = "";
					row++;
					col = check;
				}
				if(s.charAt(i)==' ')
				{
					array[row-1][col-1] = Float.valueOf(temp);
					temp = "";
					col++;
				}
			}
			else
			{
				i++;
				if(i>=s.length()){break;}
				while(true)
				{
					if((int)s.charAt(i)==10 || (int)s.charAt(i)==13)
					{
						i++;
						if(i>=s.length())
						{
							break;
						}
					}
					else {break;}
				}
				//i = i+3;
				temp = "";
				if(i>=s.length()){break;}
				while(true)
				{
					if(s.charAt(i)==' ')
					{	row = Integer.valueOf(temp); temp = "";break;	}
					else
					{	temp += String.valueOf(s.charAt(i));i++;	}
				}
				i++;
				while(true)
				{
					if((int)s.charAt(i)==13 ||(int)s.charAt(i)==10)
					{	col = Integer.valueOf(temp);check = col; temp = "";break;	}
					else
					{	temp += String.valueOf(s.charAt(i));i++;	}
				}
			}
		}
		TwoDBlockMatrix obj = new TwoDBlockMatrix(array);
		return obj;
	}
	TwoDBlockMatrix(float[][] array)
	{
		matrix = new float[array.length][array[0].length];
		for(int i =  0;i<array.length;i++)
		{
			for(int j = 0;j<array[0].length;j++)
			{
				matrix[i][j] = array[i][j];
			}
		}		
	}
	public TwoDBlockMatrix transpose()
	{
		int n = matrix.length;
		int m = matrix[0].length;
		float nmatrix[][] = new float[m][n];
		for(int i = 0;i<n;i++)
		{
			for(int j = 0;j<m;j++)
				{
					nmatrix[j][i] = matrix[i][j];
				}
		}
		TwoDBlockMatrix obj = new TwoDBlockMatrix(nmatrix);
		return obj;
	}
	public TwoDBlockMatrix multiply(TwoDBlockMatrix other) throws IncompatibleDimensionException
	{
		if(matrix[0].length!=other.matrix.length)
		{
			throw new IncompatibleDimensionException("IncompatibleDimensionException");
		}
		float[][] answer = new float[matrix.length][other.matrix[0].length];
		for(int i = 0;i<answer.length;i++)
		{
			for(int j = 0;j<answer[0].length;j++)
			{
				answer[i][j] = 0;
				for(int k = 0;k<matrix[0].length;k++)
				{
					answer[i][j] += matrix[i][k]*other.matrix[k][j];
				}
			}
		}
		TwoDBlockMatrix obj = new TwoDBlockMatrix(answer);
		return obj;
	}
	public void display()
	{
		for(int i =  0;i<matrix.length;i++)
		{
			for(int j = 0;j<matrix[0].length;j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	public TwoDBlockMatrix getSubBlock(int row_start,int col_start,int row_end,int col_end) throws SubBlockNotFoundException
	{
		if(row_start > matrix.length || row_end>matrix.length+1 || col_start > matrix[0].length || col_end>matrix[0].length+1||row_start < 1 || row_end < 1 ||col_start < 1 ||col_end < 1 )
		{
			throw new SubBlockNotFoundException("SubBlockNotFoundException");
		}
		int n = row_end-row_start;
		int m = col_end-col_start;
		float[][] answer = new float[n][m];
		for(int i = 0;i<n;i++)
		{
			for(int j = 0;j<m;j++)
			{
				answer[i][j] = matrix[row_start+i-1][col_start+j-1];
			}
		}
		TwoDBlockMatrix obj = new TwoDBlockMatrix(answer);
		return obj;
	}
	public void getCofactor(float A[][], float temp[][], int p, int q, int n) 
	{ 
		int i = 0, j = 0; 
		for (int row = 0; row < n; row++) 
		{ 
			for (int col = 0; col < n; col++) 
			{ 
				if (row != p && col != q) 
				{ 
					temp[i][j++] = A[row][col]; 
					if (j == n - 1) 
					{ 
						j = 0; 
						i++; 
					} 
				} 
			} 
		} 
	} 
	public float determinant(float A[][], int n, int N) 
	{ 
		float D = 0;
		if (n == 1) 
			return A[0][0]; 

		float [][]temp = new float[N][N]; 

		int sign = 1; 
		for (int f = 0; f < n; f++) 
		{ 
			getCofactor(A, temp, 0, f, n); 
			D += sign * A[0][f] * determinant(temp, n - 1,N); 
			sign = -sign; 
		} 

		return D; 
	}  
	public void adjoint(float A[][],float [][]adj,int N) 
	{ 
		if (N == 1) 
		{ 
			adj[0][0] = 1; 
			return; 
		} 
		int sign = 1; 
		float [][]temp = new float[N][N]; 

		for (int i = 0; i < N; i++) 
		{ 
			for (int j = 0; j < N; j++) 
			{ 
				getCofactor(A, temp, i, j, N); 
				sign = ((i + j) % 2 == 0)? 1: -1; 
				adj[j][i] = (sign)*(determinant(temp, N-1,N)); 
			} 
		} 
	} 
	public TwoDBlockMatrix inverse() throws InverseDoesNotExistException
	{ 
		int N = matrix.length;
		if(matrix.length!=matrix[0].length)
		{
			throw new InverseDoesNotExistException("InverseDoesNotExistException");
		}
		float[][] answer = new float[N][N];
		float det = determinant(matrix, N,N); 
		if(det==0)
		{
			throw new InverseDoesNotExistException("InverseDoesNotExistException");
		}
		float [][]adj = new float[N][N]; 
		adjoint(matrix, adj, N); 
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < N; j++) 
				answer[i][j] = adj[i][j]/(float)det; 

		TwoDBlockMatrix obj = new TwoDBlockMatrix(answer);
		return obj;
	} 
	public int get_col(int r, int c, float[][] answer)
	{
		int temp = c+1;
		while(temp<answer[0].length)
		{
			if(answer[r][temp]==0){break;}
			temp++;
		}
		return temp-1;
	}
	public int get_row(int r, int c, int col,float[][] answer)
	{
		int temp = r,flag = 0;
		for(int i = r+1;i<answer.length;i++)
		{
			for(int j = c;j<=col;j++)
			{
				if(answer[i][j]==0)
				{flag = 1;break;}
			}
			if(flag!=1)
				temp++;
			else
				break;
		}
		return temp;
	}
	public boolean check_five(float num)
	{
		int n = (int)(num*1000);
		int d = n%10;
		if(d==5)
			return true;
		else
			return false;
	}
	public float roundof(float num)
	{
		float n = num*100;
		int d = (int)n;
		d = d%10;
		if(d%2==0)
			n = num - 0.001f;
		else
			n = num + 0.001f;
		return n;
	}
	public String toString()
	{
		float[][] answer = new float[matrix.length][matrix[0].length];
		float check;
		for(int i =  0;i<matrix.length;i++)
		{
			for(int j = 0;j<matrix[0].length;j++)
			{
				answer[i][j] = matrix[i][j];
			}
		}
		//roundoff(answer);
		String s = "";
		int c,r;
		int temp;
		float sub;
		for(int i = 0;i<answer.length;i++)
		{
			for(int j = 0;j<answer[0].length;j++)
			{
				if(answer[i][j]!=0)
				{
					s = s + String.valueOf(i+1) + " " + String.valueOf(j+1) + "\n";
					c = get_col(i,j,answer);
					r = get_row(i,j,c,answer);
					for(int l = i;l<=r;l++)
					{
						for(int m = j;m<=c;m++)
						{
							if(m==c)
							{
								temp = (int)answer[l][m];
								sub = temp-answer[l][m];
								//System.out.println(sub);
								if(sub==0)
								{
									s = s + String.valueOf((int)answer[l][m]) + ";" + "\n";
									answer[l][m] = 0;
								}
								else
								{
									if(check_five(answer[l][m]))
										{	answer[l][m] = roundof(answer[l][m]);	}
									s = s + String.valueOf(String.format("%.2f",answer[l][m])) + ";" + "\n";
									answer[l][m] = 0;
								}
							}
							else
							{
								temp = (int)answer[l][m];
								sub = temp-answer[l][m];
								//System.out.println(sub);
								if(sub==0)
								{
									s = s + String.valueOf((int)answer[l][m]) + " ";
									answer[l][m] = 0;
								}
								else	
								{
									if(check_five(answer[l][m]))
										{	answer[l][m] = roundof(answer[l][m]);	}									
									s = s + String.valueOf(String.format("%.2f",answer[l][m])) + " ";
									answer[l][m] = 0;
								}
							}
						}
						if(l==r)
						{
							s = s+"#"+"\n";
						}
					}
				}
			}
		}
		return s.substring(0,s.length()-1);
	}
	 public static void main(String[] args) throws IOException
	 { 
		InputStream in = new FileInputStream("ABC.txt"); 
		TwoDBlockMatrix A;
		A = TwoDBlockMatrix.buildTwoDBlockMatrix(in);
		float[][] mat = {{-0.5f,2,3},{4,5,0},{7,8,9}};
	 	try
	 	{
			TwoDBlockMatrix B = new TwoDBlockMatrix(mat);
	 		A.display();
	// 		 // TwoDBlockMatrix B = A.transpose();
	 		 B.display();
			String s = B.toString();
			System.out.print(s);
	// 		// 	TwoDBlockMatrix C = A.multiply(B);

	 		  // TwoDBlockMatrix D = B.getSubBlock(2,2,4,4);
	 		  // D.display();
	// 		 // TwoDBlockMatrix E = D.inverse();
	// 		 // E.display();
	 	}
	 	catch(Exception e)
	 	{
	 		System.out.println(e.getMessage());
	 	}
	 }
}