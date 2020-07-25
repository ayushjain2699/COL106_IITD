//import javafx.util.Pair;
class StackSort
{
	StackSort()
	{

	}
	public int check_sort(int[] check)
	{
		if(check.length==1){	return 1;	}
		for(int i = 0;i<check.length-1;i++)
		{
			if(check[i]>check[i+1])
			{
				return 0;
			}
		}
		return 1;
	}
	public String[] sort(int[] nums) 
	{
		try
		{
			MyStack<Integer> num = new MyStack<>();
			MyStack<String> answer = new MyStack<>();
			MyStack<Integer> check = new MyStack<>();
			num.push(nums[0]);
			answer.push("PUSH");
			for(int i = 1;i<nums.length;i++)
			{
				if(nums[i]>num.top())
				{
					while(num.isEmpty()==false && num.top()<nums[i])
					{
						check.push(num.top());
						num.pop();
						answer.push("POP");
					}
					num.push(nums[i]);
					answer.push("PUSH");
				}
				else
				{
					num.push(nums[i]);
					answer.push("PUSH");
				}
			}
			while(num.isEmpty()==false)
			{
				check.push(num.top());
				num.pop();
				answer.push("POP");
			}
			int[] check_ans = new int[check.size];
			for(int i = check.size-1;i>=0;i--)
			{
				check_ans[i] = check.pop();
			}
			int flag = check_sort(check_ans);
			String[] output;
			if(flag==1)
			{
				output = new String[answer.size];
				for(int i = answer.size-1;i>=0;i--)
				{
					output[i] = answer.pop();
				}
				
			}
			else
			{
				output = new String[1];
				output[0] = "NOTPOSSIBLE";
			}
			// answer.display();
			// check.display();
			return output;
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			String[] a = new String[1];
			a[0] = "";
			return a ;
		}

	}
	public String[][] kSort(int[] nums) 
	{
		try
		{
			int flag = 0;
			String[] output;
			int[] check_ans = new int[nums.length];
			MyStack<Integer> num = new MyStack<>();
			MyStack<String> answer = new MyStack<>();
			MyStack<Integer> check = new MyStack<>();
			MyStack<String[]> answer2 = new MyStack<>();
			while(true)
			{
				//System.out.println(1);
				num.push(nums[0]);
				answer.push("PUSH");
				for(int i = 1;i<nums.length;i++)
				{
					if(nums[i]>num.top())
					{
						while(num.isEmpty()==false && num.top()<nums[i])
						{
							check.push(num.top());
							num.pop();
							answer.push("POP");
						}
						num.push(nums[i]);
						answer.push("PUSH");
					}
					else
					{
						num.push(nums[i]);
						answer.push("PUSH");
					}
				}
				while(num.isEmpty()==false)
				{
					check.push(num.top());
					num.pop();
					answer.push("POP");
				}
				for(int i = check.size-1;i>=0;i--)
				{
					check_ans[i] = check.pop();
				}
				output = new String[answer.size];
				for(int i = answer.size-1;i>=0;i--)
				{
					output[i] = answer.pop();
				}
				flag = check_sort(check_ans);
				answer2.push(output);
				if(flag==1)
				{	break;	}
				else
				{
					for(int i = 0;i<nums.length;i++)
					{
						nums[i] = check_ans[i];
					}
				}
			}
			String[][] final_ans = new String[answer2.size][(nums.length)*2];
			String[] temp;
			for(int i = answer2.size-1;i>=0;i--)
			{
				temp = answer2.pop();
				for(int j = 0;j<temp.length;j++)
				{
					final_ans[i][j] = temp[j];
				}
			}
			// for(int i = 0;i<final_ans.length;i++)
			// {
			// 	for(int j = 0;j<final_ans[0].length;j++)
			// 	{
			// 		System.out.print(final_ans[i][j] + " ");
			// 	}
			// 	System.out.println();
			// }
			return final_ans;
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			String[][] a = new String[1][1];
			a[0][0] = "";
			return a;
		}
		
	}
	// public static void main(String[] args)
	// {
	// 	try
	// 	{	
	// 		StackSort S = new StackSort();
	// 		int[] nums = {4,5,-1,2,1};
	// 		//String[] output = S.sort(nums);
	// 		String[][] output2 = S.kSort(nums);
	// 		// for(int i = 0;i<output.length;i++)
	// 		// {
	// 		// 	System.out.print(output[i]+" ");
	// 		// }
	// 	}
	// 	catch(Exception e)
	// 	{	System.out.println(e.getMessage());	}
	// }
}