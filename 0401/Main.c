#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;
int N;
int A[30], B[30];  // 中序， 前序 

struct BiNode
{
    int data;
    int li, ri;
};

BiNode bT[31];
int nodeN = 1;

void create_tree(int ti, int fi1, int li1, int fi2, int li2)
{
    bT[ti].data = B[fi2];

    //在中序序列中找到根节点位置 
    int rooti = fi1;
    for(int i = fi1; i < li1; ++ i){
        if(A[i] == bT[ti].data){
            rooti = i;
            break;
        }
    }

    int leftN = rooti - fi1, rightN = li1 - (rooti + 1);
    if(leftN){
        bT[ti].li = ++ nodeN;
        create_tree(bT[ti].li, fi1, rooti, fi2 + 1, fi2 + 1 + leftN); 
    }
    if(rightN){
        bT[ti].ri = ++ nodeN;
        create_tree(bT[ti].ri, rooti + 1, li1, fi2 + 1 + leftN, li2);
    }
}

void reverse_print(int ti)
{
    int arr[30], n = 0;

    queue<int> que;
    que.push(ti);
    while(!que.empty()){
        int i = que.front();
        if(i){
            arr[n ++] = bT[i].data;
            que.push(bT[i].li);
            que.push(bT[i].ri);
        }
        que.pop();
    }

    for(int i = 0; i < n; ++ i){
        cout << arr[i];
        if(i + 1 < n){
            cout << " ";
        }else{
            cout << endl;
        }
    }
}

int main()
{
    cin >> N;
    for(int i = 0; i < N; ++ i){
        cin >> A[i];
    }
    for(int i = 0; i < N; ++ i){
        cin >> B[i];
    }
    create_tree(1, 0, N, 0, N);
    reverse_print(1);
    return 0;
}
