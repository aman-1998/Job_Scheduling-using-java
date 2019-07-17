package huffman_java;

import java.util.*;
import java.lang.*;

class Huff
{
	int heap_size;
	A min1,min2;
	int[] d=new int[100];
	Huff(int i,A min1,A min2)
	{
		heap_size=i;
		this.min1=min1;
		this.min2=min2;
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
		A temp=new A('\0',0,null,null);
		l=2*i;
		r=2*i+1;
		if(l<=heap_size && ob[l].f<ob[i].f)
			smallest=l;
		else
			smallest=i;
		if(r<=heap_size && ob[r].f<ob[smallest].f)
			smallest=r;
		if(smallest!=i)
		{
			temp.f=ob[i].f;
			temp.ch=ob[i].ch;
			temp.left=ob[i].left;
			temp.right=ob[i].right;
			
			ob[i].f=ob[smallest].f;
			ob[i].ch=ob[smallest].ch;
			ob[i].left=ob[smallest].left;
			ob[i].right=ob[smallest].right;
			
			ob[smallest].f=temp.f;
			ob[smallest].ch=temp.ch;
			ob[smallest].left=temp.left;
			ob[smallest].right=temp.right;
			
			ob=min_heapify(ob,smallest);
		}
		return ob;
	}
	A[] delete_min(A[] ob,int flag)
	{
		if(flag==1)
		{
			min1=new A('\0',0,null,null);
			min1.ch=ob[1].ch;
			min1.f=ob[1].f;
			min1.left=ob[1].left;
			min1.right=ob[1].right;
			
			ob[1].ch=ob[heap_size].ch;
			ob[1].f=ob[heap_size].f;
			ob[1].left=ob[heap_size].left;
			ob[1].right=ob[heap_size].right;
			
			heap_size--;
			ob=min_heapify(ob,1);
		}
		else
		{
			min2=new A('\0',0,null,null);
			min2.ch=ob[1].ch;
			min2.f=ob[1].f;
			min2.left=ob[1].left;
			min2.right=ob[1].right;
		}
		return ob;
	}
	A[] insert(A[] ob,A temp)
	{
		ob[1].ch=temp.ch;
		ob[1].f=temp.f;
		ob[1].left=temp.left;
		ob[1].right=temp.right;
		
		ob=min_heapify(ob,1);
		return ob;
	}
	void huffman_decoding(A root,int top)
	{
		if(root.left!=null && root.right!=null)
		{
			d[top]=0;
			huffman_decoding(root.left,top+1);
			d[top]=1;
			huffman_decoding(root.right,top+1);
		}
		else if(root.left==null && root.right==null)
		{
			System.out.print(""+root.ch+" : ");
			for(int i=0;i<=top-1;i++)
				System.out.print(""+d[i]+" ");
			System.out.println();
		}
	}
	A huffman_coding(A[] ob)
	{
		while(heap_size>1)
		{
			A temp=new A('#',0,null,null);
			ob=delete_min(ob,1);
			temp.left=min1;
			ob=delete_min(ob,2);
			temp.right=min2;
			temp.f=min1.f+min2.f;
			ob=insert(ob,temp);
		}
		return ob[1];
	}
	void preorder(A root)
	{
		if(root!=null)
		{
			System.out.print(""+root.ch+" ");
			preorder(root.left);
			preorder(root.right);
		}
	}
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		int n,i,f;
		char ch;
		System.out.print("Enter number of characters used: ");
		n=in.nextInt();
		A[] ob=new A[n+1];
		for(i=1;i<=n;i++)
		{
			System.out.print("Enter character and its frequency: ");
			ch=in.next().charAt(0);
			f=in.nextInt();
			//char garbage=in.next().charAt(0);
			ob[i]=new A(ch,f,null,null);
		}
		Huff x=new Huff(n,null,null);
		//System.out.println("sa");
		ob=x.build_heap(ob);
		//System.out.println("ss");
		A root=x.huffman_coding(ob);
		System.out.print("\nPreorder Traversal of Huffman tree: ");
		x.preorder(root);
		System.out.println();
		System.out.println("\nDecoding: ");
		x.huffman_decoding(root,0);
	}
}
class A
{
	int f;
	char ch;
	A left,right;
	A(char ch,int f,A left,A right)
	{
		this.ch=ch;
		this.f=f;
		this.left=left;
		this.right=right;
	}
}