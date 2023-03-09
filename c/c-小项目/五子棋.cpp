#include<iostream>
#include<iomanip>
using namespace std;
 
const int X = 21; //��������
const int Y = 21; //��������
char p[X][Y];  //��������
int m=0;//������ʱ�㣬������������
int n=0;
 
void display()  //�������
{
    for(int i=0; i<X; i++)
        cout<<setw(3)<<setfill(' ')<<i;
    cout<<endl;
    for(int i=1; i<Y; i++)
    {
        cout<<setw(3)<<setfill(' ')<<i;
        for(int j=1;j<X;j++)
            cout<<setw(3)<<setfill(' ')<<p[i][j];
        cout<<endl;
    }
     
}
 
void black()  //�ڷ�����
{
    cout<<"��ڷ���������λ�ã�\n"
        <<"���������ӵ�������";
    cin>>m;
    cout<<"���������ӵ�������";
    cin>>n;
    if(m<=0||m>=X||n>=Y||n<=0)
    {
        cout<<"�������̷�Χ��������������ȷ���꣡\n";
        black();
    }
    else if((p[m][n]==1)||p[m][n]==2)
    {
        cout<<"�õ��������ӣ�������ѡȡ���ӵ㣡\n";
        black();
    }
    else
        p[m][n]=1; //�ڷ���1����ʾ
    system("cls");
    display();
}
 
void red()  //�췽����
{
    cout<<"��췽��������λ�ã�\n"
        <<"���������ӵ�������";
    cin>>m;
    cout<<"���������ӵ�������";
    cin>>n;
    if(m>=X||m<=0||n<=0||n>=Y)
    {
        cout<<"�������̷�Χ��������������ȷ���꣡\n";
        red();
    }
    else if((p[m][n]==1)||p[m][n]==2)
    {
        cout<<"�õ��������ӣ�������ѡȡ���ӵ㣡\n";
        red();
    }
    else
        p[m][n]=2; //�췽��2����ʾ
    system("cls");
    display();
}
 
int evalue()  //ֻ��Ҫ�ж����ӵ�Ϊ���ĵľŵ㡰�ס����Ƿ���������
{
    int k = 0,r = 0;
    /*б���ж�*/
    for(k=3;k<X-2;k++)  //���������е�p[k][r]!='-'���ų����ӵ����
    {
        for(r=3;r<Y-2;r++)
        {
            if(p[k][r]!='-'&&p[k-2][r-2]==p[k][r]&&p[k-1][r-1]==p[k][r]&&p[k+1][r+1]==p[k][r]&&p[k+2][r+2]==p[k][r])
                return 1;
            else if(p[k][r]!='-'&&p[k+2][r-2]==p[k][r]&&p[k+1][r-1]==p[k][r]&&p[k-1][r+1]==p[k][r]&&p[k-2][r+2]==p[k][r])
                return 1;
        }
    }
    /*�����ж�*/
    for(k=1;k<X;k++)  //p[k][r]!='-'���ų����ӵ����
        for(r=3;r<Y-2;r++)
            if(p[k][r]!='-'&&p[k][r-2]==p[k][r]&&p[k][r-1]==p[k][r]&&p[k][r+1]==p[k][r]&&p[k][r+2]==p[k][r])
                return 1;
    /*�����ж�*/
    for(k=3;k<X-2;k++)  //p[k][r]!='-'���ų����ӵ����
        for(r=1;r<Y;r++)
            if(p[k][r]!='-'&&p[k-2][r]==p[k][r]&&p[k-1][r]==p[k][r]&&p[k+1][r]==p[k][r]&&p[k+2][r]==p[k][r])
                return 1;
    return 0; 
}
 
int main()
{
    memset(p,'-',441);  //��ʼ��Ϊ��-��
    cout<<"��ӭʹ�ü���˫�˶�ս��������Ϸ\n"
        <<"�������������£�\n";
    display();
    while(1)
    {
        red();
        if(evalue())
        {
            cout<<"�췽Ӯ��\n";
            break;
        }
        black();
        if(evalue())
        {
            cout<<"�ڷ�Ӯ��\n";
            break;
        }  
    }
    return 0;
}
