#include <stdio.h>
int main(void)
{
    int N;
    printf("首先输入一个正整数N表示点的个数(2~50),接下来N行每行两个整数x,y表示该点坐标(绝对值均小于100)：\n");
    scanf("%d\n",&N);
    int array[N][2];
    for(int i = 0;i < N; i++){
			scanf("%d",&array[i][0]);
			scanf("%d",&array[i][1]);
    }

    int maxx = array[0][0];
    int maxy = array[0][1];
    int minx = array[0][0];
    int miny = array[0][1];
    int area;    

/*     for(int i = 0;i < N; i++){ */
/*         if(array[i][0] > maxx || array[i][1] > maxy){ */
/*             maxx = array[i][0]; */
/*             maxy = array[i][1]; */
/*         } */
/* 		else if(array[i][0] < minx || array[i][0] < miny){ */
/*             minx = array[i][0]; */
/*             miny = array[i][1]; */
/*         } */
/*     } */

		for (int i = 0; i < N; i++) {
			// 遍历坐标 求出x 坐标最大 和 最小值
			if (array[i][0] > maxx) {
				maxx = array[i][0];
			}
			if (array[i][0] < maxx) {
				minx = array[i][0];
			}

			// 遍历坐标 求出y 坐标最大 和 最小值
			if (array[i][1] > maxy) {
				maxy = array[i][1];
			}
			if (array[i][1] < maxy) {
				miny = array[i][1];
			}
		}

    area = (maxx - minx) * (maxy - miny);
    printf("%d\n",area);
    return 0;
}
