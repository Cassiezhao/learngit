#include<stdio.h>
#define size 4
int inqueue(int queue[ ], int *pf, int *pr, int val)
{
	int flag = 0;
	if(( *pr + 1) % size != *pf)
	{
		*pr = (*pr + 1) % size;
		queue[*pr] = val;
		flag = 1;
	}	
	return flag;
}
int outqueue(int queue[ ],int *pf,int  *pr)
{
	if(*pf != * pr)
	{
		*pf = (*pf + 1)%size;

	}
	return queue[*pf];
}
int main(void)
{
	int a[6] = {10,20,30,40,50,60};
	int Queue[size],rear,front,x,flag;
	rear = front = 0;
	//入队操作
	for(int i = 0;i < 6; i++)
	{
		flag = inqueue(Queue, &front, &rear, a[i]);
		if(flag == 1)
			printf("%d已入队列!\n",a[i]);
		else
		{	
			printf("队列已满，%d无法入队列.\n",a[i]);
			x = outqueue(Queue,&front,&rear);
			printf("%d已出队列.\n",x);
			flag = inqueue(Queue,&front,&rear,a[i]);
			printf("%d已入队列.\n",a[i]);
		}
	}
	//出队操作
	while(front != rear)
	{	
		x = outqueue(Queue,&front,&rear);
		printf("%d已出队列.\n",x);
	}
	return 0;
}
