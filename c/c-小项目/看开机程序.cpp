#include<stdio.h>
#include<windows.h>

int main()
{
    DWORD k = GetTickCount();
    int s = k/1000;
    int min = 0, h = 0;
    if (s >= 60){
        min = s / 60;
        s = s % 60;
    }
    if (min >= 60){
        h = min / 60;
        min = min % 60;
    }
    printf("��ϵͳ���������ڹ��˵�ʱ�䣺%d h %d min %d s\n",h,min,s);

    system("pause");
    return 0;
}
