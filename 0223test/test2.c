#include <stdio.h>
int main(void)
{
    int N;
    printf("首先输入一个正整数N表示点的个数(2~50),接下来N行每行两个整数x,y表示该点坐标(绝对值均小于100)：\n");
    scanf("%d\n",&N);
    int array[N][2];
    for(int i = 0;i < N; i++){
        for(int j = 0;j < 2;j++){
            scanf("%d",&array[i][j]);
        }
    }
    int maxx = array[0][0];
    int maxy = array[0][1];
    int minx = array[0][0];
    int miny = array[0][1];
    int area;    
    for(int i = 0;i < N; i++){
        if(array[i][0] > maxx || array[i][1] > maxy){
            maxx = array[i][0];
            maxy = array[i][1];
        }
		else if(array[i][0] < minx || array[i][0] < miny){
            minx = array[i][0];
            miny = array[i][1];
        }
    }
    area = (maxx - minx) * (maxy - miny);
    if(area < 0 )
        area = -1 * area;
    printf("%d\n",area);
    return 0;
}