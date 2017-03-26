#include <stdio.h>
// #include <iostream>
// // #include <stack>
// using namespace std;
// //中缀转后缀  
// int cmp(char c1,char c2)//相同为0，  
// {  
//     int v1=0,v2=0;  
//     if(c2=='#')  
//         return -1;  
//     if(c1=='+' || c1=='-')  
//         v1=1;  
//     if(c1=='*' || c1=='/')  
//         v1=2;  
//   
//     if(c2=='+' || c2=='-')  
//         v2=1;  
//     if(c2=='*' || c2=='/')  
//         v2=2;   
//     return v2-v1;  
//       
// }  
// void change(char *p1,char *p2)  
// {  
//     stack<char> st;  
//     st.push('\0');  
//     int i=0,j=0;  
//     int len=strlen(p1);  
//     for(i=0;i<len;i++)  
//     {  
//           
//         if(p1[i]>='0' && p1[i]<='9')  
//             p2[j++]=p1[i];  
//           
//          if(p1[i]=='+'||p1[i]=='-'||p1[i]=='*')    
//          {  
//              if(cmp(st.top(),p1[i])>0)  
//              {  
//                  st.push(p1[i]);  
//              }  
//              else  
//              {  
//                  while(st.top()!='\0' && cmp(st.top(),p1[i])<=0)  
//                 {  
//                     p2[j++]=st.top();  
//                      st.pop();  
//                  }  
//                  st.push(p1[i]);  
//              }  
//          }  
//     }  
//     while(st.top()!='\0')  
//     {  
//         p2[j++]=st.top();  
//          st.pop();  
//     }  
//     p2[j]='\0';  
// }  
int  compute(char * p)  
{  
     int result = 0;
    for(int i=0;i<strlen(p);i++)  
    {  
    	
        if(p[i]>='0' && p[i]<='9')  
        {  
            result = result + (p[i]-'0');
        }  
        else  if(p1[i]=='+')
        {
        	result  
        }||p1[i]=='-'||p1[i]=='*')  
        {  
        	
        	result = result 
            int n=0;  
            int n1=st.top();  
            st.pop();  
            int n2=st.top();  
            st.pop();  
            if(p[i]=='+')  
                n=n1+n2;  
            else if(p[i]=='-')  
                n=n2-n1;  
            else if(p[i]=='*')  
                n=n1*n2;  
            st.push(n);  
        }  
    }  
    cout<<st.top()<<endl;  
}  
int main()    
{    
    char expression[100];    //定义表达式
    int result；
//     char stack2[100];    //操作符的栈
    scanf("%s",stack1);//输入表达式
    
//     change(stacknumber,stackoperator);    
//     cout<<p2<<endl;    
    result = compute(stack1);  
//     system("pause");    
} 