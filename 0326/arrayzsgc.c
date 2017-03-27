#include <stdio.h>
# define n 3
# define m 3
//冒泡排序
void sort(int a[],int N )
{
	int i ,j ,temp;
	for(i = 0; i < N -1; i++)
	{
		for(j = i + 1;j < N ;j ++)
		{
			if(a[i] > a[j]){
				temp = a[j];
				a[j] = a[i];
				a[i] = temp;	
			}
		}
	}
}
//合并增序数组A,B
void combineAB(int  a[], int b[], int  c[], int N, int M)
{
	int * p;
	int * arraya = a;
	int * arrayb = b;
	int * arrayc = c;
	while(arraya < a + N && arrayb < b+ M)
	{
		if(*arraya < *arrayb)  
			* arrayc++ = * arraya++;
		else
			* arrayc++ = * arrayb++; 
	}
	if(arraya < a + N)
		p = arraya;
	else 
		p = arrayb;	
	while(arrayc < c + N + M)
		* arrayc ++ = * p++;		
}
//获取数组C最大值和次大值并输出
void printmaxvalue(int array[], int N)
{
	int * p1 = array;//假定为数组最大值
	int * p2 = array + 1;//假定为数组次大值
	int * p;
	for(p = array + 1;p <array + N; p ++){//循环遍历数组
	//如果当前要元素比最大值大，
	//则次大值等于之前的最大值，最大值等于当前的最大值
			p2 = p1;
		if( * p > * p1)
		{
			p1 = p;
		}
		//如果当前元素比次大值大，
		//则次大值为该数，最大值不变
		else if (* p > * p2)
			p2 = p;
	}
	printf("数组的最大值和次大值分别为：%d,%d\n",*p1,*p2);
}
//输出数组元素
void print(int array[],int N)
{
	for(int i = 0; i < N; i ++)
		printf("%d\t",array[i]);
	printf("\n");
}
//删除数组C中的x,并返回当前数组元素个数
int delxvalue(int array[], int N,int x)
{
	int * p1 ,* p2;
	//循环遍历数组C ,到等于x的位置
	for(p1 = array; p1 < array + N && *p1 != x; p1++);
	if(p1 == array + N)//在数组中没找到x
		printf("sorry,not found!\n");
	else
	{
		//循环遍历到x的位置
		for(p2 = p1 ; p2 < array + N && * p2 == x; p2 ++);//
		//如果xde位置小于数组长度
		if(p2 < array + N){
			while(p2 < array + N)
				* p1 ++ = *p2++;//将x后剩下的元素复制到数组中
		}
	}	
		//打印输出C的值
		int count = 0;
		for(p2 = array; p2 < p1; p2 ++)
		{
			count ++ ;
			printf("%d\t", * p2);
		}
		printf("\n数组中当前有%d 个元素.\n",count);
		
		return count;
}

//输入x，并将x插入到数组C中(数组C已排好序)
//N为当前元素个数
void insertvalue(int array[], int N, int x)
{
	int * p ,* pa, i = 0;
	//循环遍历数组到数组值大于X停止
	for( p = array ; p < array + N  && *p < x ; p ++);
	if(p == array + N )  //如果遍历完整个数组，则将x加在数组最后元素的后边
		* p  = x;
	else
	{
	//如果未遍历完该数组，则将当前数组元素依次往后挪一个，将x插入到当前位置
		pa = array + N;
		for(  ; pa >= p ; pa --  )
			* (pa + 1) = * pa;
		* pa = x;
	} 
	for(p = array ; p < array + N + 1; p++)
		printf("%d\t", *p);
	printf("\n");
}

int main (void)
{
	int arrayA[n] ;
	int arrayB[m];
	int arrayC[n+m];
	int x,count;
	int * pointA , * pointB;
	int * pointC =  arrayC;
	
	printf("请输入数组A的数组元素：\n");
	for( pointA = arrayA; pointA < arrayA + n; pointA ++){
		scanf("%d",pointA);
	}
	
	printf("请输入数组B的数组元素：\n");
	for(pointB = arrayB; pointB < arrayB + m; pointB++){
		scanf("%d",pointB);
	}
	
	sort(arrayA,n);
	printf("排序后的数组A为：\n");
	print(arrayA,n);
	
	sort(arrayB,m);
	printf("排序后的数组B为：\n");
	print(arrayB,m);
	
	combineAB(arrayA,arrayB,arrayC,n,m);//合并函数
	printf("合并A,B之后数组C的元素为:\n");
	print(arrayC,n+m);
	
	printmaxvalue(arrayC,n+m);//输出数组C的最大值和次大值
	//输入一个X的值，删除数组中x值
	printf("请输入x的值，并删除数组C中的x值：\n");
	scanf("%d",&x);
	count = delxvalue(arrayC,n+m,x);
	//输入x，将x插入到当前数组C中
	printf("请输入x的值，并将x值插入到数组C中：\n");
	scanf("%d",&x);
	
	insertvalue( arrayC , count  , x);
	return 0 ;
}