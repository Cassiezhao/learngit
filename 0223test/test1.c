#include <stdio.h>
int main(void)
{
    int n;
    printf("请输入一个整数N：\n");
    scanf("%d",&n);
    int a[n];
    for(int i = 0; i < n; i++){
        scanf("%d", a+i);
    }
    int count = 0;
    for(int i = 0;i < n-2;i++){
        for(int j = i+1;j < n-1; j++){
            for(int k = j+1;k < n;k++){
                if(  ((a[i]+a[j])>a[k])&&((a[k]+a[j])>a[i])&&((a[i]+a[k])>a[j])
                   &&((a[i]-a[j])<a[k])&&((a[j]-a[k])<a[i])&&((a[i]-a[k])<a[j])  ){
                    	if(a[i] != a[j] || a[i] !=a[k] || a[j] != a[k])
                        	 count ++;
                }
            }
        }
    }
    printf("%d\n",count);
	return 0 ;
}