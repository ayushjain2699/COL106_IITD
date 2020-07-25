class MyCalculator
{
	MyCalculator()
	{
	}
	int calculate(String expression) 
	{
		MyStack<Integer> num = new MyStack<>();
		try
		{
			MyStack<String> sym = new MyStack<>();
			int a,b;
			String c;
			
			expression = "(" + expression + ")";
			//System.out.println(expression);
			String temp = "";
			for(int i = 0;i<expression.length();i++)
			{
				if(expression.charAt(i)==' '){	continue;	}
				if(expression.charAt(i)!=')'&&expression.charAt(i)!='('&&expression.charAt(i)!='+'&&expression.charAt(i)!='-'&&expression.charAt(i)!='*')
				{
					temp = String.valueOf(expression.charAt(i));
					for(int j = i+1;j<expression.length();j++)
					{
						if(expression.charAt(j)!=' '&&expression.charAt(j)!=')'&&expression.charAt(j)!='('&&expression.charAt(j)!='+'&&expression.charAt(j)!='-'&&expression.charAt(j)!='*')
						{
							temp = temp + String.valueOf(expression.charAt(j));
							i = j;
						}
						else break;
					}
					//System.out.println(temp);
					num.push(Integer.valueOf(temp));
				}
				else if(expression.charAt(i)=='(')
				{
					sym.push(String.valueOf(expression.charAt(i)));
					//sym.display();
				}
				else if(expression.charAt(i)==')')
				{
					
					while(sym.isEmpty()==false&&!(sym.top().equals("(")))
					{
						//System.out.println(8);
						a = num.pop();
						b = num.pop();
						c = sym.pop();
						if(c.equals("*"))
						{	
							num.push(a*b);	
						}
						if(c.equals("+"))
						{	
							num.push(a+b);	
						}
						if(c.equals("-"))
						{	
							num.push(b-a);	
						}
					}
					if(!sym.isEmpty())
						{	sym.pop();	}
					//num.display();
				}
				else if(expression.charAt(i)=='*')
				{
					while(!((sym.top()).equals("("))&&sym.isEmpty()==false&&!((sym.top()).equals("-"))&&!((sym.top()).equals("+")))
					{
						c = sym.top();
						//System.out.println(c);
						if(c.equals("*"))
						{
							a = num.pop();
							b = num.pop();
							sym.pop();
							num.push(a*b);
						}
					}
					sym.push(String.valueOf(expression.charAt(i)));
				}
				else if(expression.charAt(i)=='+'||expression.charAt(i)=='-')
				{
					c = sym.top();
					while(!(c.equals("("))&&sym.isEmpty()==false)
					{	
						c = sym.top();
						if(c.equals("*"))
						{	
							a = num.pop();
							b = num.pop();
							sym.pop();
							num.push(a*b);	
						}
						if(c.equals("+"))
						{	
							a = num.pop();
							b = num.pop();
							sym.pop();
							num.push(a+b);	
						}
						if(c.equals("-"))
						{	
							a = num.pop();
							b = num.pop();
							sym.pop();
							num.push(b-a);	
						}
					}
					sym.push(String.valueOf(expression.charAt(i)));
					//sym.display();
				}
			}
			//num.display();
			// sym.display();
			// System.out.println();
			return num.top();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return -1;
		}

	}
	// public static void main(String[] args)
	// {
	// 	try{
	// 		MyCalculator C = new MyCalculator();
	// 		System.out.println(C.calculate("1+2"));
	// 		// A.push("1");
	// 		// A.push("2");
	// 		// // A.push(4);
	// 		// // A.push(4);
	// 		// // A.push(8);
	// 		// A.display();
	// 		// // A.pop();
	// 		// // A.pop();
	// 		// //A.display();
	// 		// System.out.println(A.isEmpty());
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e.getMessage());
	// 	}
	// }
}