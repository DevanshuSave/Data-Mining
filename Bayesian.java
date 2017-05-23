import java.util.*;
class table
{	String data[][]={
				{"youth","high","no","fair","no"},
				{"youth","high","no","excellent","no"},
				{"middle_aged","high","no","fair","yes"},
				{"senior","medium","no","fair","yes"},
				{"senior","low","yes","fair","yes"},
				{"senior","low","yes","excellent","no"},
				{"middle_aged","low","yes","excellent","yes"},
				{"youth","medium","no","fair","no"},
				{"youth","low","yes","fair","yes"},
				{"senior","medium","yes","excellent","yes"},
				{"youth","medium","yes","excellent","yes"},
				{"middle_aged","medium","no","fair","yes"},
				{"middle_aged","high","yes","fair","yes"},
				{"senior","medium","no","excellent","no"}
			};
	String attrib[][]={
						{"age","youth","middle_aged","senior"},
						{"income","high","medium","low"},
						{"student","no","yes"},
						{"credit_rating","fair","excellent"},
						{"buys_comp","yes","no"}
					};
	String temp[];
	int yes,no;
	int m,n,decide;
	table()
	{	n=5;
		m=14;
		Scanner in=new Scanner(System.in);
		for(int i=0;i<m;i++)
		System.out.println(Arrays.toString(data[i]));
		System.out.println("Enter the deciding attribute");
		for(int i=0;i<n;i++)
		System.out.println("press "+i+" for : "+attrib[i][0]);
		decide=in.nextInt();
		System.out.println("Enter the tuple you wish to classify");
		temp=new String[n-1];
		for(int i=0;i<n-1;i++)
		{	System.out.println(attrib[i][0] +" :");
			temp[i]=in.next();
		}
		for(int i=0;i<m;i++)
			if(data[i][4].equals("yes"))
			yes++;
			else
			no++;
		for(int i=0;i<4;i++)
			for(int j=1;j<attrib[i].length;j++)
				showall(attrib[i][j],i);
		compute(decide);
	}
	void showall(String atr,int t)
	{	int count=0,c1=0,c2=0;
		for(int i=0;i<m;i++)
			if(data[i][t].equals(atr))
			count++;
		System.out.println("\n");
		System.out.println("for attribute : "+attrib[t][0]);
		for(int i=0;i<m;i++)
		if(data[i][t].equals(atr) && data[i][4].equals("yes"))
		c1++;
		System.out.println("P( "+atr+" |  yes ) :  ( "+c1+" / "+yes +" ) ");
		c1=0;
		for(int i=0;i<m;i++)
		if(data[i][t].equals(atr) && data[i][4].equals("no"))
		c1++;
		System.out.println("P( "+atr+" |  no ) :  ( "+c1+" / "+no +" ) ");
	}
	void compute(int t)
	{	int count=0;
		int yes[][]=new int[m][5];
		int ansy[]=new int[n];
		int ansn[]=new int[n];
		for(int j=1;j<3;j++)
		{	count=0;
			for(int i=0;i<m;i++)
			{	if(attrib[decide][j]==data[i][decide])
				count++;
			}
			yes[decide][j]=count;
		}
		for(int i=0;i<temp.length;i++)
		{	count=0;
			for(int j=0;j<m;j++)
				if(temp[i].equals(data[j][i]) && (data[j][n-1]).equals("yes"))
					count++;
			ansy[i]=count;
		}
		ansy[n-1]=yes[n-1][1];
		ansn[n-1]=yes[n-1][2];
		for(int i=0;i<temp.length;i++)
		{	count=0;
			for(int j=0;j<m;j++)
				if(temp[i].equals(data[j][i]) && (data[j][n-1]).equals("no"))
					count++;
			ansn[i]=count;
		}
		System.out.println(Arrays.toString(temp));
		System.out.println("\n\n");
		System.out.println(Arrays.toString(ansy));
		double proby=1;
		for(int i=0;i<n;i++)
		proby=proby * ((double)ansy[i]/ansy[n-1]);
		System.out.println("The probability of yes is :"+proby);
		double probn=1;
		for(int i=0;i<n;i++)
		probn=probn * ((double)ansn[i]/ansn[n-1]);
		System.out.println("The probability of no is :"+probn);
		if(probn<proby)
		System.out.println("BUYS : YES");
		else
		System.out.println("BUYS : NO");
	}
}
class Bayesian
{	public static void main (String args[])
	{		table t=new table();
	}
}
/*
OUTPUT:[youth, high, no, fair, no]
[youth, high, no, excellent, no]
[middle_aged, high, no, fair, yes]
[senior, medium, no, fair, yes]
[senior, low, yes, fair, yes]
[senior, low, yes, excellent, no]
[middle_aged, low, yes, excellent, yes]
[youth, medium, no, fair, no]
[youth, low, yes, fair, yes]
[senior, medium, yes, excellent, yes]
[youth, medium, yes, excellent, yes]
[middle_aged, medium, no, fair, yes]
[middle_aged, high, yes, fair, yes]
[senior, medium, no, excellent, no]
Enter the deciding attribute
press 0 for : age
press 1 for : income
press 2 for : student
press 3 for : credit_rating
press 4 for : buys_comp
4
Enter the tuple you wish to classify
age :
youth
income :
medium
student :
yes
credit_rating :
fair
for attribute : age
P( youth |  yes ) :  ( 2 / 9 )
P( youth |  no ) :  ( 3 / 5 )

for attribute : age
P( middle_aged |  yes ) :  ( 4 / 9 )
P( middle_aged |  no ) :  ( 0 / 5 )

for attribute : age
P( senior |  yes ) :  ( 3 / 9 )
P( senior |  no ) :  ( 2 / 5 )

for attribute : income
P( high |  yes ) :  ( 2 / 9 )
P( high |  no ) :  ( 2 / 5 )

for attribute : income
P( medium |  yes ) :  ( 4 / 9 )
P( medium |  no ) :  ( 2 / 5 )

for attribute : income
P( low |  yes ) :  ( 3 / 9 )
P( low |  no ) :  ( 1 / 5 )

for attribute : student
P( no |  yes ) :  ( 3 / 9 )
P( no |  no ) :  ( 4 / 5 )

for attribute : student
P( yes |  yes ) :  ( 6 / 9 )
P( yes |  no ) :  ( 1 / 5 )

for attribute : credit_rating
P( fair |  yes ) :  ( 6 / 9 )
P( fair |  no ) :  ( 2 / 5 )

for attribute : credit_rating
P( excellent |  yes ) :  ( 3 / 9 )
P( excellent |  no ) :  ( 3 / 5 )
[youth, medium, yes, fair]

[2, 4, 6, 6, 9]
The probability of yes is :0.04389574759945129
The probability of no is :0.019200000000000002
BUYS : YES  /*