class MyStack<T>
{
	public T[] array;
	public int size = 0;
	@SuppressWarnings({"unchecked"})
	MyStack()
	{	
		array = (T[])new Object[1];
	}
	@SuppressWarnings({"unchecked"})
	public void push(T value)
	{
		if(array.length>size)
		{
			size++;
			array[size-1] = value;
		}
		else
		{
			T[] array_new = (T[])new Object[array.length*2];
			for(int i = 0;i<array.length;i++)
			{
				array_new[i] = array[i];
			}
			size++;
			array_new[size-1] = value;
			array = array_new;
		}
	}
	public void display()
	{
		for(int i = size-1;i>=0;i--)
		{
		
			System.out.println(array[i]);
		}
		
	}
	public T pop() throws EmptyStackException
	{
		if(size==0)
		{
			throw new EmptyStackException("EmptyStackException");
		}
		size--;
		return array[size];
	}
	public T top() throws EmptyStackException
	{
		if(size==0)
		{
			throw new EmptyStackException("EmptyStackException");
		}
		return array[size-1];
	}
	public boolean isEmpty()
	{
		if(size==0){	return true;	}
		else return false;
	}
	// public static void main(String[] args)
	// {
	// 	try{
	// 		MyStack<String> A = new MyStack<>();
	// 		A.push("1");
	// 		A.push("2");
	// 		// A.push(4);
	// 		// A.push(4);
	// 		// A.push(8);
	// 		A.display();
	// 		// A.pop();
	// 		// A.pop();
	// 		//A.display();
	// 		System.out.println(A.isEmpty());
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e.getMessage());
	// 	}
	// }
}