/*
 * 文件名：mypwd1.c
 * 描述：通过系统函数getcwd实现pwd命令
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main()
{
        char buf[1024];
        char * cwd = getcwd(buf,sizeof(buf));

        if(NULL == cwd)
	{
		perror("get current working directory fail.\n");
		exit(-1);
	}
	else
	{
		printf("current working directory is: %s \n",cwd);
	}
	
	return 0;
}
