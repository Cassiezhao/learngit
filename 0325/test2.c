#include<stdio.h>
#include <string.h>
int main(void)
{
	int result;
	char expression[100];	
	result = 0;
	printf("输入一个字符串，该字符串是一个运算表达式（运算符只有+，-，*;数字只有0 -9）:\n");	
	scanf("%s\n", expression);
	result = expression[ 0 ] - '0';
	int i = 1;	
	   while(expression[i] != '\0'){
           
				switch(expression[i]){
					case '+':
									result = result + (expression[++ i ] - '0');
									break;
					case '-':
									result = result - (expression[++ i ] - '0');
									break;
					case '*':
									result = result * (expression[++ i ] - '0');
									break;
				}
           i++;
		}
		printf("%d\n", result);
		return 0;
}