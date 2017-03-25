#include <stdio.h>  
#include <string.h>  
#include <time.h>  
const int maxnum=3000;   
int f[maxnum];  
int delzero(int x)  
 {  
    while (x >= 0 && !f[x]) --x;  
    return x;  
 }  
int main(void)  
{   
	int i, j, n;
	printf("enter an integer(e.g.20) : \n");  
	scanf("%d", &n);  
	memset( f, 0, sizeof( f ));  
	clock_t beg, end;  
	beg = clock();  
	f[0] = 1;  
	for ( i = 2; i<=n; ++i){  
        	int c = 0;  
        	int multiplycnt = delzero(maxnum - 1) + 3;
        	for (j = 0; j <= multiplycnt; ++j){  
           		int s = f[j] * i + c;// mutiply i  
            		f[j] = s % 10;  
            		c = s / 10;  
        		}  
    }  
    	for (j = maxnum - 1; j >= 0; --j){  
        	if ( f[j] )   break;
    	}  
	end = clock();  
	printf("The factorial of n is :\n");
	for (i = j; i >= 0; --i)     
	printf("%d", f[i]);  
	printf("\n");  
	printf("time used: %.5lf ms \n",(double)(end-beg)/CLOCKS_PER_SEC);

   	return 0;  
 } 
