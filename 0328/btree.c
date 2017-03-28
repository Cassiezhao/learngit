#include<stdio.h>
#include<stdlib.h>
#define N 9
typedef struct node
{
	int data;
	struct node *left,*right;
}Btree;
//创建二叉查找树
Btree * creatbtree(int a[])
{
	Btree * root, * pa,*cu,*p;
	int i;
	//创建二叉树的根节点
	root = (Btree *)malloc(sizeof(Btree));
	root -> data = a[0];
	root -> left = NULL;
	root -> right = NULL;
	
	for(i = 1;i < N;i++)
	{
		p = (Btree *)malloc(sizeof(Btree));
		p -> data = a[i];
		p -> left = p->right = NULL;
		cu = root;
		while(cu)
		{
			pa = cu;
			if(cu->data > p->data)
				cu = cu -> left;
			else
				cu = cu -> right;
		}
			if(pa->data > p->data)
				pa -> left = p;
			else
				pa -> right = p;
	}
	return root;
}
//前序遍历输出二叉树的节点
void porder(Btree * root)
{
	if(root)
	{
		printf("%d\t",root->data);
		porder(root->left);
		porder(root->right);
	}
}
//中序遍历输出二叉树的节点
void iorder(Btree * root)
{
	if(root)
	{
		iorder(root->left);
		printf("%d\t",root->data);
		iorder(root->right);
	}
}
//后序遍历输出二叉树的节点
void forder(Btree * root)
{
	if(root)
	{
		forder(root->left);
		forder(root->right);
		printf("%d\t",root->data);
	}
}
int main(void)
{
	int a[N] = {5,3,8,7,2,6,4,1,9};
	Btree * root;
	//创建二叉查找树
	root = creatbtree(a);
	//前序遍历
	printf("\n前序遍历为:\n");
	porder(root);
	//中序遍历
	printf("\n中序遍历为:\n");
	iorder(root);
	//后序遍历
	printf("\n后序遍历为:\n");
	forder(root);
	printf("\n");
	return 0;			
}
