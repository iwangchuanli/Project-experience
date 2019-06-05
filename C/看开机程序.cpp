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
    printf("重系统启动到现在过了的时间：%d h %d min %d s\n",h,min,s);

    system("pause");
    return 0;
}
