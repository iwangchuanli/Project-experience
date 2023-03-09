#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>
using namespace std;

// input: �������У�i ��ʾ���뵽�� i ����N ��ʾ�� N ������Ԫ�أ� seq: ĳһ��������У� result : �洢���е�����
void GetAllSequence(const int* input, int i, const int N, stack<int> &stk, vector<int> &seq,vector<vector<int> > &result) {
    if (i == N) {
        // ��������ȫ����ջ��ϣ�ֻ�ܳ�ջ����ջ�е�Ԫ����ӵ�seq �ĺ���, ���� seq
        if (!stk.empty()) {
            int top = stk.top();
            seq.push_back(top);
            stk.pop();
            GetAllSequence(input, i, N, stk, seq, result); // ���� i == N���ݹ�ؽ� stk Ԫ�ظ��Ƶ� seq
            stk.push(top); //����
            seq.pop_back();
        } else {
            result.push_back(seq); // ������
        }
    } else {
        // ����һ������Ԫ�أ�������ջ�����Բ��룬����ջ������Ԫ��
        // ��ջ
        stk.push(input[i]);
        GetAllSequence(input, i+1, N, stk, seq, result); // �� i+1 �ݹ�
        stk.pop(); // ���ݣ��ָ�ջ֮ǰ��״̬

        // ��ջ
        if (!stk.empty()) {
            int top = stk.top(); //��¼
            stk.pop();
            seq.push_back(top);
            GetAllSequence(input, i, N, stk, seq, result); // ���� i ����
            seq.pop_back(); // ���ݣ��ָ�ջ������֮ǰ��״̬
            stk.push(top);
        }
    }
}

int main()
{
    int input[] = {1,2,3}; // ��������
    const int N = sizeof(input)/sizeof(input[0]);
    vector<vector<int> > result; //������������
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
