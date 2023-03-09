#include<stdio.h>
#include<conio.h>
#include<math.h>
#include<string.h>
#include<stdlib.h>
int i, row = 0, line = 0;
char a[1000];  //����
int number[1000][100];  //������
char mark[100][5];   //��ʶ����
//�ʷ�����
int wordanalysis()
{
    if ((a[i] >= 'A'&&a[i] <= 'Z')||(a[i]>='a'&&a[i]<='z'))  //������ʶ���ͱ�����
    {
        char word[10];
        char pro[100][100] = { "PROGRAM", "BEGIN", "END", "VAR", "INTEGER", "WHILE", "IF", "THEN", "ELSE", "DO", "PROCEDURE" ,
                                "char","int","if","else","var" ,"return","break","do","while","for","double","float","short"}; //�����ֱ�

        int n = 0;
        word[n++] = a[i++];
        //���ַ�ΪA~Z��0~9���������ȡ
        while ((a[i] >= 'A'&&a[i] <= 'Z') || (a[i] >= '0' && a[i] <= '9')||(a[i]>='a'&&a[i]<='z'))
        {
            word[n++] = a[i++];
        }
        word[n] = '\0';
        i--;
        //�жϸñ�ʶ���Ƿ�Ϊ������
        for (n = 0; n < 100; n++)
        {
            if (strcmp(word, pro[n]) == 0)
            {
                printf("%s\t(%d,-) ������\n", pro[n], n + 1);
                return 3;
            }
        }
        //�жϱ�ʶ�������Ƿ񳬳��涨
        if (strlen(word)>10)
        {
            printf("%s\tERROR\n",word);
            return 0;
        }

        //�жϸñ�ʶ���Ƿ���ڱ�ʶ������
        int m = 0;
        if (line != 0)
        {
            int q = 0;
            while (q<line)
            {
                if (strcmp(word, mark[q++]) == 0)
                {
                    printf("%s\t(12,%d) ��ʶ��\n", word, q);
                    return 3;
                }
            }

        }

        //���ñ�ʶ�����浽��ʶ������
        strcpy(mark[line], word);

        printf("%s\t(12, %d) ��ʶ��\n", word, line + 1);
        line++;
        return 3;

    }

    else if (a[i] >= '0' && a[i] <= '9')  //��������
    {
        char x[100];
        int n = 0, sum;
        x[n++] = a[i++];
        //�ж��ַ��Ƿ���0~9
        while (a[i] >= '0' && a[i] <= '9')
        {
            x[n++] = a[i++];
        }
        x[n] = '\0';
        i--;
        int num = atoi(x); //���ַ���ת����int��

        //�жϸó����Ƿ�����ڳ�������
        if (row != 0)
        {   
            int y; 
            for (y = 0; y < 1000; y++)
            {
                int w = number[y][0];
                sum = 0;
                int d;
                for (d = 1; d <= number[y][0]; d++)
                {
                    w = w - 1;
                    sum = sum + number[y][d] * pow(2, w);
                }
                if (num == sum)
                {
                    printf("%d\t(13,%d)\n", num, y + 1);
                    return 3;
                }
            }
        }
        int z = num, c = num;
        int m = 0;
        do        //�����Ǽ�λ��������
        {
            z = z / 2;
            m++;
        } while (z != 0);

        for (n = m; n > 0; n--)  //�������Ʊ����ڳ�������
        {
            number[row][n] = c % 2;
            c = c / 2;
        }
        number[row][0] = m;

        int line = row;
        printf("%d\t(13,%d)\n", num, line + 1);
        row++;

        return 3;
    }

    else                      //��������
        switch (a[i])
    {
        case ' ':
        case '\n':
            return -1;
        case '#': return 0;
        case '=':printf("=\t(14,-)\n"); return 3;
        case '<':
            i++;
            if (a[i] == '=')
            {
                printf("<= \t(16,-)\n");
                return 3;
            }
            else if (a[i] == '>')
            {
                printf("<>\t(19,-)\n");
                return 3;
            }
            else
            {
                i--;
                printf("<\t(15,-)\n");
                return 3;
            }
        case '>':
            i++;
            if (a[i] == '=')
            {
                printf(">=\t(18,-)\n");
                return 3;
            }
            else
            {
                i--;
                printf(">\t(17,-)\n");
                return 3;
            }
        case '+': printf("+\t(20,-)\n"); return 3;
        case '-': printf("-\t(21,-)\n"); return 3;
        case '*': printf("*\t(22,-)\n"); return 3;
        case '/': 
            i++;
            if(a[i]!='/'){
                i--;
                printf("/\t(23,-)\n"); return 3;
            }

            else{

                while(1){
                    if(a[i++]=='\n')
                        return -1;
                }
                printf("//\t(35,-)\n");return 3;

            }

        case ':': printf(":\t(24,-)\n"); return 3;
        case ';': printf(";\t(25,-)\n"); return 3;
        case '(': printf("(\t(26,-)\n"); return 3;
        case ')': printf(")\t(27,-)\n"); return 3;
        case '{': printf("{\t(28,-)\n"); return 3;
        case '}': printf("}\t(29,-)\n"); return 3;
        case '[': printf("[\t(30,-)\n"); return 3;
        case ']': printf("]\t(31,-)\n"); return 3;
        case '|': printf("|\t(32,-)\n"); return 3;
        case '"': printf("\"\t(33,-)\n"); return 3;
        case ',': printf(",\t(34,-)\n"); return 3;
        case '\'': printf("'\t(36,-)\n"); return 3;//������
        case '&': 
            i++;
            if(a[i]!='&'){
                i--;
                printf("&\t(37,-)\n"); return 3;
            }   
            else{   
                printf("&&\t(38,-)\n");return 3;

            }
        case '\\': printf("\\\t(39,-)\n"); return 3;
    }

}

int main()
{

    int l = 0;
    int m;
    i = 0;
    FILE *fp;
    fp=fopen("D:\\test.txt","r");
    if (fp == NULL)
    {
        printf("Can't open file!\n");
        exit(0);
  
    }

    while (!feof(fp))
    {
        a[l++] = fgetc(fp);
    }
    a[l] = '#';
    do
    {
        m = wordanalysis();

        switch (m)
        {
        case -1:i++; break;
        case 0: i++; break;
        case 3: i++; break;
        }
    } while (m != 0);

    _getch();
    return 0;
}
