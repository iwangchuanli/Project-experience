#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    char gamer;  // 玩家出拳
    int computer;  // 电脑出拳
    int result;  // 比赛结果

    // 为了避免玩一次游戏就退出程序，可以将代码放在循环中
    while (1){
        printf("这是一个猜拳的小游戏，请输入你要出的拳头：\n");
        printf("A:剪刀\nB:石头\nC:布\nD:不玩了\n");
        scanf("%c%*c",&gamer);
        switch (gamer){
            case 65 | 97:  // A | a
                gamer=4; break;
            case 66 | 98:  // B | b
                gamer=7; break;
            case 67 | 99:  // C | c
                gamer=10; break;
            case 68 | 100:  // D | d
                return 0;
           
            default:
                printf("你的选择为 %c 选择错误,退出...\n",gamer);
                getchar();
                system("cls"); // 清屏
                return 0;
                break;
        }
       
        srand((unsigned)time(NULL));  // 随机数种子
        computer=rand()%3;  // 产生随机数并取余，得到电脑出拳
        result=(int)gamer+computer;  // gamer 为 char 类型，数学运算时要强制转换类型
        printf("电脑出了");
        switch (computer)
        {
            case 0:printf("剪刀\n");break; //4    1
            case 1:printf("石头\n");break; //7  2
            case 2:printf("布\n");break;   //10 3
        }
        printf("你出了");
        switch (gamer)
        {
            case 4:printf("剪刀\n");break;
            case 7:printf("石头\n");break;
            case 10:printf("布\n");break;
        }
        if (result==6||result==7||result==11) printf("你赢了!");
        else if (result==5||result==9||result==10) printf("电脑赢了!");
        else printf("平手");
        system("pause>nul&&cls");  // 暂停并清屏
    }
    return 0;
}
