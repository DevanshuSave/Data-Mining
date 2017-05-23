import java.util.*;

class Attr
{
	String name;
	String val[];
	int count[];
	Attr(String name,String val[])
	{
		this.name=name;
		this.val=val;
		count=new int[val.length];
	}
}

class ID3T
{
	String table[][]={
						{"D1","Sunny","Hot","High","Weak","No"},
						{"D2","Sunny","Hot","High","Strong","No"},
						{"D3","Over","Hot","High","Weak","Yes"},
						{"D4","Rain","Mild","High","Weak","Yes"},
						{"D5","Rain","Cool","Normal","Weak","Yes"},
						{"D6","Rain","Cool","Normal","Strong","No"},
						{"D7","Over","Cool","Normal","Strong","Yes"},
						{"D8","Sunny","Mild","High","Weak","No"},
						{"D9","Sunny","Cool","Normal","Weak","Yes"},
						{"D10","Rain","Mild","Normal","Weak","Yes"},
						{"D11","Sunny","Mild","Normal","Strong","Yes"},
						{"D12","Over","Mild","High","Weak","Yes"},
						{"D13","Over","Hot","Normal","Strong","Yes"},
						{"D14","Rain","Mild","High","Strong","No"},
					};
	Attr arr[];
	String s1[]={"Sunny","Over","Rain"};
	String s2[]={"Hot","Mild","Cool"};
	String s3[]={"High","Normal"};
	String s4[]={"Weak","Strong"};
	String s5[]={"Yes","No"};
	int barred[];

	ID3T()
	{
		System.out.println("TABLE:");
		System.out.println("Day\tOut\tTemp\tHum\tWind\tPlay");
		System.out.println();
		for(int i=0;i<table.length;i++)
		{
			for(int j=0;j<6;j++)
			System.out.print(table[i][j]+"\t");
			System.out.println();
		}
		System.out.println();
		arr=new Attr[5];
		arr[0]=new Attr("Outlook",s1);
		arr[1]=new Attr("Temperature",s2);
		arr[2]=new Attr("Humidity",s3);
		arr[3]=new Attr("Wind",s4);
		arr[4]=new Attr("Play",s5);
		barred=new int[5];
		//double x=inf(2,3);
		solve(table);
	}

	void solve(String table[][])
	{
		int yc=0,nc=0;
		double cI=0;
		double gain[]=new double[4];

		for(int i=0;i<table.length;i++)
		if(table[i][5].equals("Yes"))
		yc++;
		else
		nc++;

		cI=inf(yc,nc);
		//System.out.println(cI);

		for(int i=1;i<5;i++)
		if(barred[i-1]!=1)
		gain[i-1]=gain(i,cI,table,yc,nc);

		//System.out.println(Arrays.toString(gain));

		double max=0,pos=-1;

		for(int i=0;i<gain.length;i++)
		if(gain[i]>max)
		{
			max=gain[i];
			pos=i;
		}
		if(pos==-1)
		{
			System.out.println(table[0][5].toUpperCase());
			return;
		}
		//System.out.println(max+" "+pos);
		barred[(int)pos]=1;
		System.out.println();
		System.out.println("CHOSEN ATTRIBUTE: "+arr[(int)pos].name);
		System.out.println();
		if(check()==false)
		{
			//System.out.println("False");
			int cc[]=new int[arr[(int)pos].val.length];
			for(int i=0;i<arr[(int)pos].val.length;i++)
			{
				for(int j=0;j<table.length;j++)
				if(table[j][(int)(pos+1)].equals(arr[(int)pos].val[i]))
				cc[i]++;
			}
			String tab[][];
			int k=0;
			for(int i=0;i<arr[(int)pos].val.length;i++)
			{
				k=0;
				tab=new String[cc[i]][6];

				for(int j=0;j<table.length;j++)
				if(table[j][(int)(pos+1)].equals(arr[(int)pos].val[i]))
				tab[k++]=table[j];
				System.out.println("FOR ATTRIBUTE AND VALUE: "+arr[(int)pos].name+" "+arr[(int)pos].val[i]);
				solve(tab);
				System.out.println("BACK TO ATTRIBUTE: "+arr[(int)pos].name);
				System.out.println();
			}
			//System.out.println(Arrays.toString(cc));

		}
		else
		return;
	}

	boolean check()
	{
		//System.out.println(Arrays.toString(barred));
		for(int i=0;i<barred.length;i++)
		if(barred[i]==0)
		return false;
		return true;
	}

	double gain(int attr,double Inf,String table[][],int yc,int nc)
	{
		double gain=0;
		gain=Inf-entropy(attr,table,yc,nc);
		return gain;
	}

	double entropy(int a,String table[][],int yc,int nc)
	{
		//System.out.println(arr[a-1].name+":");
		double t1[][]=new double[arr[a-1].val.length][3];
		for(int i=0;i<arr[a-1].val.length;i++)
		{
			//System.out.println(arr[a-1].val[i]+":");
			for(int j=0;j<table.length;j++)
			if(table[j][5].equals("Yes") && table[j][a].equals(arr[a-1].val[i]))
			t1[i][0]++;
			else if(table[j][5].equals("No") && table[j][a].equals(arr[a-1].val[i]))
			t1[i][1]++;

			t1[i][2]=inf(t1[i][0],t1[i][1]);

			/*if(t1[i][2]==0)
			{
				if(t1[i][0]>t1[i][1])
				System.out.print(" YES ");
				else
				System.out.print(" NO ");
			}*/
		//System.out.println();
			//System.out.println(Arrays.toString(t1[i]));
		}

		double e=0;

		for(int i=0;i<t1.length;i++)
		e=e+(((t1[i][0]+t1[i][1])/(yc+nc))*t1[i][2]);
		//System.out.println("Entropy: "+e);
		return e;
	}


	double inf(double p,double n)
	{
		if(p==n)
		return 1;
		if(p==0 || n==0)
		return 0;
		double r=0;
		r=-(((p/(p+n))*Math.log((p/(p+n))))+((n/(p+n))*Math.log((n/(p+n)))));
		r=r/Math.log(2);
		//System.out.println(r);
		return r;
	}
}

class ID3
{
	public static void main(String args[])
	{
		ID3T obj=new ID3T();
	}
}
/*OUTPUT:
TABLE:
Day     Out     Temp    Hum     Wind    Play

D1      Sunny   Hot     High    Weak    No
D2      Sunny   Hot     High    Strong  No
D3      Over    Hot     High    Weak    Yes
D4      Rain    Mild    High    Weak    Yes
D5      Rain    Cool    Normal  Weak    Yes
D6      Rain    Cool    Normal  Strong  No
D7      Over    Cool    Normal  Strong  Yes
D8      Sunny   Mild    High    Weak    No
D9      Sunny   Cool    Normal  Weak    Yes
D10     Rain    Mild    Normal  Weak    Yes
D11     Sunny   Mild    Normal  Strong  Yes
D12     Over    Mild    High    Weak    Yes
D13     Over    Hot     Normal  Strong  Yes
D14     Rain    Mild    High    Strong  No


CHOSEN ATTRIBUTE: Outlook

FOR ATTRIBUTE AND VALUE: Outlook Sunny

CHOSEN ATTRIBUTE: Humidity

FOR ATTRIBUTE AND VALUE: Humidity High
NO
BACK TO ATTRIBUTE: Humidity

FOR ATTRIBUTE AND VALUE: Humidity Normal
YES
BACK TO ATTRIBUTE: Humidity

BACK TO ATTRIBUTE: Outlook

FOR ATTRIBUTE AND VALUE: Outlook Over
YES
BACK TO ATTRIBUTE: Outlook

FOR ATTRIBUTE AND VALUE: Outlook Rain

CHOSEN ATTRIBUTE: Wind

FOR ATTRIBUTE AND VALUE: Wind Weak
YES
BACK TO ATTRIBUTE: Wind

FOR ATTRIBUTE AND VALUE: Wind Strong
NO
BACK TO ATTRIBUTE: Wind

BACK TO ATTRIBUTE: Outlook
*/