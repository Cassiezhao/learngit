/*
* 文件名：filestat.c
* 描述：打印指定文件名的inode信息
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

struct stat file_stat;

void print_file_stat(struct stat *fs)
{
	printf(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
	printf("inode:\t\t\t\t%ld\n",fs->st_ino);
	printf("protection:\t\t\t%o\n",fs->st_mode);
	printf("number of hard links:\t\t%lu\n",fs->st_nlink);
	printf("user ID of owner:\t\t%d\n",fs->st_uid);
	printf("group ID of owner:\t\t%d\n",fs->st_gid);
	printf("file size in bytes:\t\t%ld\n",fs->st_size);
	printf("time of last access:\t\t%s\n",ctime(&fs->st_atime));
	printf("time of last modification:\t%s\n",ctime(&fs->st_mtime));
}
int main(int argc,char * argv[])
{
	if(2 != argc)
	{
		fprintf(stderr,"Usage:%s filename...\n",argv[0]);
		exit(-1);
	}
	if(0 != stat(argv[1] , &file_stat))
	{
		perror("stat");	
		exit(-1);
	}
	print_file_stat(&file_stat);

	return 0;
}
