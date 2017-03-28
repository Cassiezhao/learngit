#include <stdio.h>
#include <stdlib.h>
typedef struct 
{
	int top;
	int maxsize;
	int * stack;
}st;
//初始化栈
void initstack(st * s,int ms)
{
	s -> top = -1;
	s -> maxsize = ms;
	s -> stack = malloc(ms * sizeof(int));
}
//出栈
int pop(st * s)
{
	s->top--;
	return s->stack[s->top+1];
}

//入栈
void push(st * s,int val)
{
	if(s->top + 1 == s->maxsize)
	{	
		printf("栈已满，%d不可入栈！\n",val);
		printf("%d已出栈.\n",pop(s));
	}
	s->top++;
	s->stack[s->top] = val;
	
}
//释放栈空间
void clearstack(st * s)
{
	free(s->stack);
	s->stack= NULL;
}
int main(void)
{
	st s;
	int a[7] = {10,12,14,16,18,20,22};
	int maxs;//栈存放的最大容量
	printf("请输入栈的最大元素个数:\n");
	scanf("%d",&maxs);
	//初始化栈
	initstack(&s,maxs);
	//用数组a的元素对栈进行初始化
	for(int i =0;i < 7;i++)
	{	
		push(&s,a[i]);
		printf("%d已入栈\n",a[i]);
	}
	//出栈操作
	while(s.top != 1)
		printf("%d已出栈.\n",pop(&s));
	//释放栈空间
	clearstack(&s);
	return 0;
}
