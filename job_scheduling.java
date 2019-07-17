package job;

import java.util.*;
import java.lang.*;

class js
{
	int heap_size;
	js(int i)
	{
		heap_size=i;
	}
	A[] build_heap(A[] ob)
	{
		int i;
		for(i=heap_size/2;i>=1;i--)
			ob=min_heapify(ob,i);
		return ob;
	}
	A[] min_heapify(A[] ob,int i)
	{
		int l,r,smallest;
		A temp=new A(0,0,0);
		l=2*i;
		r=2*i+1;
		if(l<=heap_size && ob[l].p<ob[i].p)
			smallest=l;
		else
			smallest=i;
		if(r<=heap_size && ob[r].p<ob[smallest].p)
			smallest=r;
		if(i!=smallest)
		{
			temp.job=ob[i].job;
			temp.p=ob[i].p;
			temp.d=ob[i].d;
			
			ob[i].job=ob[smallest].job;
			ob[i].p=ob[smallest].p;
			ob[i].d=ob[smallest].d;
			
			ob[smallest].job=temp.job;
			ob[smallest].p=temp.p;
			ob[smallest].d=temp.d;
			
			ob=min_heapify(ob,smallest);
		}
		return ob;
	}
	A[] heap_sort_dec(A[] ob)
	{
		A temp=new A(0,0,0);
		while(heap_size>1)
		{
			temp.job=ob[1].job;
			temp.p=ob[1].p;
			temp.d=ob[1].d;
			
			ob[1].job=ob[heap_size].job;
			ob[1].p=ob[heap_size].p;
			ob[1].d=ob[heap_size].d;
			
			ob[heap_size].job=temp.job;
			ob[heap_size].p=temp.p;
			ob[heap_size].d=temp.d;
			
			heap_size--;
			ob=min_heapify(ob,1);
		}
		return ob;
	}
	float job_scheduling(A[] ob,int max)
	{
		int i,j;
		float p=0;
		int[] slots=new int[max];
		for(i=0;i<=max-1;i++)
			slots[i]=0;
		for(i=1;i<=ob.length-1;i++)
		{
			for(j=ob[i].d-1;j>=0;j--)
			{
				if(slots[j]==0)
				{
					slots[j]=1;
					System.out.println("job "+(ob[i].job)+" is to be done from "+j+" to "+(j+1));
					p=p+ob[i].p;
					break;
				}
			}
		}
		return p;
	}
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		int n,i,d,job;
		float p,max_profit;
		System.out.print("Enter no. of jobs: ");
		n=in.nextInt();
		A[] ob=new A[n+1];
		int max=0;
		for(i=1;i<=n;i++)
		{
			System.out.print("Enter profit & deadline for job "+i+": ");
			p=in.nextFloat();
			d=in.nextInt();
			if(d>max)
				max=d;
			ob[i]=new A(i,p,d);
		}
		js x=new js(n);
		ob=x.build_heap(ob);
		ob=x.heap_sort_dec(ob);
		System.out.println();
		max_profit=x.job_scheduling(ob,max);
		System.out.println("\nMaximum Profit = "+max_profit);
	}
}
class A
{
	int d,job;
	float p;
	A(int i,float p,int d)
	{
		job=i;
		this.p=p;
		this.d=d;
	}
}