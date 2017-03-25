#include <stdio.h>  
#include <string.h>  
#include <time.h>  
#define LOCAL  
const int maxnum=3000;  
int f[maxnum];  
int main(void)  
{  
	#ifdef LOCAL  
    	freopen("output.txt","a+",stdout);  
	#endif  
    	int i, j, n;  
	printf("enter an integer n (e.g.n):\n");
    	scanf("%d",&n);  
    	memset(f,0,sizeof(f));  
    	f[0]=1;  
    	clock_t beg, end;  
    	beg=clock();  
    	for (i=2; i<=n; ++i){  
        	int c=0;  
        	for (j=0;j < maxnum ; ++j){  
            		int s=f[j]*i+c;
            		f[j]=s%10;  
            		c=s/10;  
        	}  
    	}  
    	for (j=maxnum-1; j>=0; --j)  {  
        	if (f[j])   
			break;
    	}  
    	end=clock();  
    	for (i=j; i>=0; --i)   
		printf("%d",f[i]);  
    	printf("\n");  
    	printf("time used: %.5lf\n",(double)(end-beg)/CLOCKS_PER_SEC);
    	return 0;  
}  
