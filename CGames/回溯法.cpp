#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>
using namespace std;

// input: 输入序列，i 表示输入到第 i 个，N 表示有 N 个输入元素； seq: 某一个输出序列； result : 存储所有的序列
void GetAllSequence(const int* input, int i, const int N, stack<int> &stk, vector<int> &seq,vector<vector<int> > &result) {
    if (i == N) {
        // 输入序列全部入栈完毕，只能出栈。将栈中的元素添加到seq 的后面, 保存 seq
        if (!stk.empty()) {
            int top = stk.top();
            seq.push_back(top);
            stk.pop();
            GetAllSequence(input, i, N, stk, seq, result); // 保持 i == N，递归地将 stk 元素复制到 seq
            stk.push(top); //回溯
            seq.pop_back();
        } else {
            result.push_back(seq); // 保存结果
        }
    } else {
        // 对于一个输入元素，可以入栈；可以不入，弹出栈中已有元素
        // 入栈
        stk.push(input[i]);
        GetAllSequence(input, i+1, N, stk, seq, result); // 向 i+1 递归
        stk.pop(); // 回溯，恢复栈之前的状态

        // 出栈
        if (!stk.empty()) {
            int top = stk.top(); //记录
            stk.pop();
            seq.push_back(top);
            GetAllSequence(input, i, N, stk, seq, result); // 保持 i 不变
            seq.pop_back(); // 回溯，恢复栈和序列之前的状态
            stk.push(top);
        }
    }
}

int main()
{
    int input[] = {1,2,3}; // 输入序列
    const int N = sizeof(input)/sizeof(input[0]);
    vector<vector<int> > result; //保存所有序列
    vector<int> seq;
    stack<int> stk;
    GetAllSequence(input, 0, N, stk, seq, result);
    for (int i = 0; i < result.size(); i++) {
        for (int j = 0; j < result[0].size(); j++) {
            cout << result[i][j] << " ";
        }
        cout << endl;
    }
}
