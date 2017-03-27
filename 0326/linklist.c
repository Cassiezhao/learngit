#include <stdio.h>
// #include <malloc.h>
#include <stdlib.h>
# define n 7 
typedef struct st
{
	int data;
	struct st * next;
}snode;
//创建链表
snode * creatlink(int a[] ,int N)
{
	snode * head, *p , *q;
	int i ;
	head  = p = (snode * )malloc(sizeof(snode));
	head -> data = a[0];
	head -> next = NULL;
	for(int i = 1; i < N; i ++)
	{
		p= p->next  = (snode * ) malloc(sizeof(snode));
		p -> data = a[i];
		p -> next = NULL;
	}
	return head;
}
//输出链表最后一个节点的值
void printlastnode(snode * head)
{
	snode * p;
	for(p = head; p && p -> next ; p = p -> next);
	if( ! p -> next)
		printf("%5d\n",p->data);
}
void printlink(snode * head)
{
	snode * p;
	for(p = head; p ; p = p -> next)
		printf("%d\t",p->data);
	printf("\n");
}
//逆向输出链表各个节点的数值
void printprelink(snode * head)
{
	snode * p ,* q = NULL;
	while(q != head)
	{
		for( p = head ; p -> next != q; p = p-> next );
		printf("%d\t",p->data);
		q = p;
	}
	printf("\n");
}
//删除链表的头结点
snode *  delheadnode(snode * head)
{
	snode * p = head;
	head = p -> next;
	free(p);
	p =NULL;
	return head;
}
//删除值为x的节点的值
snode * delxnode(snode * head, int x)
{
	snode *p,*q;
	for(p = head; p && p->data != x;q = p, p = p-> next);
	if(!p)
		printf("sorry ,not found\n");
	else
	{	
		if(p == head)
			{
				head = p-> next;
			}
		else 
			q -> next = p -> next;
		free(p);
		p=NULL;
	}
	return head;
}
//删除链表的尾节点
void deltailnode(snode * head)
{
	snode * p , * q;
	for(p = head ; p -> next ; q = p, p = p -> next);
	q -> next = p -> next;
	free(p);
	p =NULL;
}
//删除链表最大值
snode * delmaxnode(snode * head)
{
	snode * p, *q, *maxp, *maxq;
	maxp = head;
	p = head-> next;
	for(; p ; q = p , p = p -> next)
		if(maxp -> data < p ->data)
			{	
				maxp = p;
				maxq = q;
			}
		if(maxp == head)
			head = maxp -> next;
		else
			maxq -> next = maxp -> next;
		free(maxp);
		maxp = NULL;
		return head;
}
//合并链表a和链表b
snode * combineab(snode * heada, snode * headb)
{
	snode * head = NULL, *end ;
	if(heada -> data < headb -> data)
	{	
		head = end = heada;
		heada = heada -> next;
	}
	else
	{
		head = end = headb;
		headb = head -> next;
	}
	while(heada && headb)
	{
		if(heada -> data < headb -> data)
		{
			end = end -> next = heada;
			heada = heada -> next;
		}
		else
		{
			end = end -> next = headb;
			headb = headb -> next;
		}
	}
		if(!heada)
			end -> next = headb;
		else
			end -> next = heada;
			
		return head;
}

//逆置合并后的链表c
// snode * reverselink(snode * head)
// {
// 	snode * p, *q;
// 	p = head;
// 	q = head -> next;
// 	for(; p ; p = p-> next)
// 	{
// 		q = p -> next;
// 		p -> next = head -> next;
// 		head = p;
// 		p = q;
// 	}
// 	return head;
// } 
snode * reverselink(snode * head){
    //head是不带头结点的单链表，本算法将链表就地逆置
    snode * p,* q;
    p = head ;  //p从头结点开始
    head  = NULL;  //先将头结点head置为NULL
    while(p != NULL)
	{
		q = p; //将p指向q
		p = p->next;//将q挪到下一个节点
		q -> next = head;//将q的next置为head
		head = q;//将head挪到q
}
    return head;
}
snode * insertheadnode(snode * head, int x)
{
	snode * s;
	s = (snode * )malloc(sizeof(snode));
	s -> data = x;
	s -> next = NULL;
	s -> next = head;
	head = s;
	return head;
}
//将x插入到链表中，保证链表顺序不变
void insertnode(snode * head, int x)
{
	snode * s,*p,*q;
	s = (snode *)malloc(sizeof(snode));
	s-> data = x;
	s->next = NULL;
	for(p = head; p->next && p-> data < x ; q=p, p = p-> next);
	s -> next = p ;
	q -> next = s;
}
snode *  clearlink(snode * head)
{
	snode *p;
	while(head)
	{
		p = head;
		head = head-> next;
		free(p);
	}
	return head;
}
int main(void)
{
	int a [n] = {13,15,17,21,28,42,45};
	int b[n] = {12,16,19,23,32,39,48};
	int x ;
	snode * heada,*headb,*headc;
	heada = creatlink(a,n);
	headb = creatlink(b,n);
	printf("输出链表最后一个节点数据域的值：\n");
	printlastnode(heada);
	printlastnode(headb);
	printf("正向输出链表各个节点的数值:\n");
	printlink(heada);//正向输出链表各节点值
	printlink(headb);
	printf("逆向输出链表各节点数值：\n");
	printprelink(heada);
	printprelink(headb);
	printf("删除链表的头结点:\n");
	heada = delheadnode(heada);
	headb = delheadnode(headb);
	printlink(heada);
	printlink(headb);
// 	printf("删除链表的尾节点(原理同中间节点)：\n");
	deltailnode(heada);
	deltailnode(headb);
	printf("删除链表的尾节点(原理同中间节点)链表a：\n");
	printlink(heada);
	printf("删除链表的尾节点(原理同中间节点)链表b：\n");
	printlink(headb);
// 	printf("请输入一个数x，并在链表中删除x的值:\n");
// 	scanf("%d",&x);
// 	heada = delxnode(heada, x);
// 	printf("删除值为x的节点值后的链表a:\n");
// 	printlink(heada);
// 	printf("删除最大节点后链表a :\n");
// 	heada = delmaxnode(heada);//删除最大节点
// 	printlink(heada);	
	headc = combineab(heada,headb);
	printf("合并链表a和链表b后的链表c为:\n");
	printlink(headc);
	//逆置合并A,B后的链表C
	printf("逆置合并A,B后的链表C:\n");
	headc = reverselink(headc);
	printlink(headc);
	headc = reverselink(headc);
	printf("请输入一个数x，该值将作为链表C的头结点插入链表:\n");
	scanf("%d",&x);
	headc = insertheadnode(headc,x);
	printf("插入头结点值后的链表C为：\n");
	printlink(headc);
	printf("请输入一个x，并将x插入到链表中，并保证链表顺序不变：\n");
	scanf("%d",&x);
	insertnode(headc,x);
	printlink(headc);
	headc = clearlink(headc);
	printf("释放链表后输出为:\n");
	printlink(headc);
	return 0;
}