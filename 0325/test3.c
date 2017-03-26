#include <stdlib.h>
#include <stdio.h>
#include <string.h>
int n, m = 0;
// 存放单词
char input[50][100];
char sys[50][100];
void uniq();
int check(char* word);
int main(void) {
        int points = 0;
        scanf("%d%d", &n, &m);
        // 小易输入的单词
        for (int i = 0; i < n; ++i) {
                scanf("%s", &input[i]);
        }

        uniq();

        // 系统输入的单词
        for (int i = 0; i < m; ++i) {
                scanf("%s", &sys[i]);
        }

        // 遍历小易输入的单词
        for (int i = 0; i < n; ++i) {
                points += check(&input[i]);
        }

        printf("%d\n", points);
}

// 输入去重
void uniq(void) {
        // 遍历系统单词
        for (int i = 0; i < n - 1; ++i) {
                for (int j = i; i < n; ++j) {
                 if (0 == strcmp(&input[i], &input[j])) {
                         // shift
                         for (int k = i + 1; k+1 < n; ++k) {
                                 input[k] = input[k+1];
                         }
                 };
                }
        }
}

int check(char* word) {
        // 遍历系统单词
        for (int i = 0; i < m; ++i) {
                 if (0 == strcmp(word, &sys[i])) {
                         return (int)strlen(word) * (int)strlen(word);
                 };
        }
        return 0;
}