package col106.assignment5;

public class DateNode implements DateInterface {

	int day;
	int month;
	int year;

	public DateNode(int day, int month , int year){
		this.day = day;
		this.month= month;
		this.year = year;
	}
	public int compareTo(DateNode d2)
	{
		DateNode d1 = this;
		if (d1.getYear() >= d2.getYear())
		{
			if (d1.getYear() > d2.getYear()) return 1;
			else
			{
				if (d1.getMonth() >= d2.getMonth())
				{
					if (d1.getMonth() > d2.getMonth()) return 1;
					else
					{
						if(d1.getDay()>=d2.getDay())
						{
							if(d1.getDay()>d2.getDay())	return 1;
							else
							{
								return 0;
							}
						}
						else	return -1;
					}
				}
				else	return -1;
			}
		}
		else	return -1;
	}
	public int getDay(){
		return this.day;
	}

	public int getMonth(){
		return this.month;
	}

	public int getYear(){
		return this.year;
	}

}
