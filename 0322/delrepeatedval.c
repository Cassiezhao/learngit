#include <stdio.h>
int main(void)
{
        int i, j;
        int a[10];
        for(i=0 ; i<10; i++){
                printf("please enter the %d value of array a:\n",i+1);
                scanf("%d", a+i);
        }
        
        for( i=0, j=0 ; j < 10 ; j++){
                if(a[i] != a[j]){
                        a[++i] = a[j];
                }
        }
        printf("after delete repeated value from array,array a:\n");
        for(j=0; j<i; j++){
                printf("%d\t",a[j]);
        }
        printf("\n");
        return 0;
}