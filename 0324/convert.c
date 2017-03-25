#include <stdio.h>

/**
 * convert 将int数翻转
 */
int convert(int x) {
        int result = 0;
        while (x) {
                //      取出个位数
                int tmp = x%10;
                // 切掉个位数
                x /= 10;
                result = result*10 + tmp;
        }
        return result;
}

/* *
 * 拼接两个整数
 * */
int cat_int(int x, int y) {
        while (y) {
                int tmp = y%10;
                y /= 10;
                x = x*10+tmp;
        }
        return x;
}
int getResult(int x, int y) {
        return convert(cat_int(convert(x), convert(y)));
}
int main(void) {
        printf("%d\n", getResult(11021, 22200));
}
