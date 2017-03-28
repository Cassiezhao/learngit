#include<stdio.h>
void insertsort(int a[],int n)
{
	int i,j,x;
	for(i=1;i<n;++i)
	{
		x = a[i];
		for(j=i-1;j>-1;j--)
			if(a[j]> x)
				a[j+1] = a[j];
			else
				break;
		a[j+1] = x;
	}

}
int main(void)
{
	int a[8] ={36,25,48,12,65,43,20,58};
	insertsort(a,8);
	for(int i =0;i<8;i++)
		printf("%d\t",a[i]);
}
