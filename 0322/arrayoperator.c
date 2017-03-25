#include <stdio.h>
#define SWAP( x, y, t)  ( (t) = (x),  (x) = (y),  (y) = (t) )
//输入数组元素
void creatarray(int array[ ], int n)
{
	for(int i=0; i < n; i++){
		printf("please enter the %d value of array a:\n",i+1);
		scanf("%d",array+i);
	}
}
//选择排序：
//思想：从未被排序的整数中找出最小的整数，
//           将其放在已经排好序的整数列表中的下一个位置。
void selectsort(int array[ ], int n)
{
	int i, j, min, temp;
	for( i = 0; i < n-1; i++){
		min = i;
		for(j = i+1; j < n; j++){
			if(array[j] < array[min])
				min = j;
		}
		SWAP(array[i], array[min], temp);
	}
}
//已经排好序的数组中，删除重复元素
int delrepeatedvalue(int array[ ], int n)
{
	int j=0,i=0;
 	for (i = 0; i < n; i++) {
        	while (array[i] == array[i+1]) 
		{
           		i++;
       		}
        	array[j++] = array[i];
	}
	return j;
}
//输出数组元素
void display(int array[ ], int n)
{
	for(int i=0 ; i < n; i++){
		printf("%d\t",array[i]);
	}
	printf("\n");
}
int main(void)
{
	int  j, array[10];
	creatarray(array,10);
	
	selectsort(array, 10);	
	printf("after select sort, the array is:\n");
	display(array,10);
	
	j = delrepeatedvalue(array,10);
	printf("after delete repeated value ,array is :\n");
	display(array,j);
	return 0;
}
