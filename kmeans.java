//K-means clustering
import java.io.*;
class kmeans
{
	public static void main(String args[])throws IOException
	{
	kmean k=new kmean();
	k.accept();
	}
}

class kmean
{
	int n; int a[];int c;double m[];int cluster[][];
	void accept()throws IOException
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Enter the no of elements");
	n=Integer.parseInt(br.readLine());
	a=new int[n];
	System.out.println("Enter the elements");
	for(int i=0;i<n;i++)
		a[i]=Integer.parseInt(br.readLine());
	System.out.println("Enter the no of clusters");
	c=Integer.parseInt(br.readLine());
	m=new double[c];
	cluster=new int[c][n];
	System.out.println("Enter the initial means");
	for(int i=0;i<c;i++)
	m[i]=Integer.parseInt(br.readLine());
	cal();
	}

	void cal()
	{
	double diff;int b;int k[]=new int[c];int iter=1;int sum=0;
	int temp[][]=new int[c][n];
	int no[]=new int[c];
	while(true)
	{
		for(int i=0;i<n;i++)
		{
			diff=Math.abs(a[i]-m[0]);
			b=0;
			for(int j=1;j<c;j++)
			{
			if(Math.abs(a[i]-m[j])<diff)
			{
				diff=Math.abs(a[i]-m[j]);
				b=j;			
			}
			}
			cluster[b][k[b]++]=a[i];
			no[b]+=1;
		}
		int flag=0;

		for(int j=0;j<c;j++)
			for(int i=0;i<n;i++)
			{
			if(cluster[j][i]!=temp[j][i])
				{flag=1;break;}
			}
		if(flag==0)
			break;

		for(int j=0;j<c;j++)
		{
			for(int i=0;i<no[j];i++)
				sum+=cluster[j][i];
			m[j]=(double)sum/no[j];
			sum=0;
		}
		System.out.println("Iteration "+(iter++));
		for(int j=0;j<c;j++)
			System.out.println("Mean "+(j+1)+":"+m[j]);
		for(int j=0;j<c;j++)
		{
			System.out.print("Cluster "+(j+1)+":");
			for(int i=0;i<no[j];i++)
			{
				System.out.print(cluster[j][i]+" ");
				temp[j][i]=cluster[j][i];
			}
			System.out.println();
		}

		for(int j=0;j<c;j++)
			for(int i=no[j];i<n;i++)
				temp[j][i]=0;

		System.out.println();
		for(int j=0;j<c;j++)
			for(int i=0;i<n;i++)
				cluster[j][i]=0;

		for(int i=0;i<c;i++)
			{no[i]=0;k[i]=0;}
				
		}			
	}
}

/*OUTPUT
Enter the no of elements
9
Enter the elements
2
4
10
12
3
20
30
11
25
Enter the no of clusters
2
Enter the initial means
3
4
Iteration 1
Mean 1:2.5
Mean 2:16.0
Cluster 1:2 3
Cluster 2:4 10 12 20 30 11 25

Iteration 2
Mean 1:3.0
Mean 2:18.0
Cluster 1:2 4 3
Cluster 2:10 12 20 30 11 25

Iteration 3
Mean 1:4.75
Mean 2:19.6
Cluster 1:2 4 10 3
Cluster 2:12 20 30 11 25

Iteration 4
Mean 1:7.0
Mean 2:25.0
Cluster 1:2 4 10 12 3 11
Cluster 2:20 30 25
*/