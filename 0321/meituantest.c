#include <stdio.h>  
#include <string.h>  
#include <time.h>  
// #define LOCAL  
static int maxnum = 3000; 
int f[maxnum];  
// delete leading zero  
int delzero(int x)
{  
	while (x>=0 && !f[x]) 
		--x;  
	return x;  
}  
int main(void)  
{  
	// 	#ifdef LOCAL  
// 		freopen("output.txt","a+",stdout);  
//  		#endif  
    	int i,j,n;  
    	printf(" Enter an integer n (e.g.20) : \n");
    	scanf(" %d\n", &n);  
    	memset( f, 0, sizeof(f));  
    	f[0]=1;  
   		clock_t beg,end;  
    	beg = clock();  
    	/* modify following codes */  
    	for (i = 2; i <= n; ++i){  
        	int c = 0;  
        	int multiplyCnt = delzero(maxnum - 1) + 3;// note, here  
        	for (j=0; j<=multiplyCnt; ++j){  
            		int s=f[j]*i+c;// mutiply i  
            		f[j]=s%10;  
            		c=s/10;  
        	}  
    	}  
    	for (j=maxnum-1; j>=0; --j){  
    		// ignore the leading zero  
        	if (f[j])   break;
    	}  
    	end=clock();  
    	// print the result  
    	for (i=j; i>=0; --i)   
			printf("%d",f[i]);  
    	printf("\n");  
    	printf(" time used: %.5lf\n",(double)(end-beg)/CLOCKS_PER_SEC);
   		return 0;  
} 
