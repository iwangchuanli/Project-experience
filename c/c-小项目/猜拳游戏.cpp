#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    char gamer;  // ��ҳ�ȭ
    int computer;  // ���Գ�ȭ
    int result;  // �������

    // Ϊ�˱�����һ����Ϸ���˳����򣬿��Խ��������ѭ����
    while (1){
        printf("����һ����ȭ��С��Ϸ����������Ҫ����ȭͷ��\n");
        printf("A:����\nB:ʯͷ\nC:��\nD:������\n");
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
                printf("���ѡ��Ϊ %c ѡ�����,�˳�...\n",gamer);
                getchar();
                system("cls"); // ����
                return 0;
                break;
        }
       
        srand((unsigned)time(NULL));  // ���������
        computer=rand()%3;  // �����������ȡ�࣬�õ����Գ�ȭ
        result=(int)gamer+computer;  // gamer Ϊ char ���ͣ���ѧ����ʱҪǿ��ת������
        printf("���Գ���");
        switch (computer)
        {
            case 0:printf("����\n");break; //4    1
            case 1:printf("ʯͷ\n");break; //7  2
            case 2:printf("��\n");break;   //10 3
        }
        printf("�����");
        switch (gamer)
        {
            case 4:printf("����\n");break;
            case 7:printf("ʯͷ\n");break;
            case 10:printf("��\n");break;
        }
        if (result==6||result==7||result==11) printf("��Ӯ��!");
        else if (result==5||result==9||result==10) printf("����Ӯ��!");
        else printf("ƽ��");
        system("pause>nul&&cls");  // ��ͣ������
    }
    return 0;
}
