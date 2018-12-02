#include<iostream>
#include<iomanip>
using namespace std;
 
const int X = 21; //棋盘行数
const int Y = 21; //棋盘列数
char p[X][Y];  //定义棋盘
int m=0;//定义临时点，保存输入坐标
int n=0;
 
void display()  //输出棋盘
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
 
void black()  //黑方落子
{
    cout<<"请黑方输入落子位置：\n"
        <<"请输入落子的行数：";
    cin>>m;
    cout<<"请输入落子的列数：";
    cin>>n;
    if(m<=0||m>=X||n>=Y||n<=0)
    {
        cout<<"超出棋盘范围，请重新输入正确坐标！\n";
        black();
    }
    else if((p[m][n]==1)||p[m][n]==2)
    {
        cout<<"该点已有棋子，请重新选取落子点！\n";
        black();
    }
    else
        p[m][n]=1; //黑方用1来表示
    system("cls");
    display();
}
 
void red()  //红方落子
{
    cout<<"请红方输入落子位置：\n"
        <<"请输入落子的行数：";
    cin>>m;
    cout<<"请输入落子的列数：";
    cin>>n;
    if(m>=X||m<=0||n<=0||n>=Y)
    {
        cout<<"超出棋盘范围，请重新输入正确坐标！\n";
        red();
    }
    else if((p[m][n]==1)||p[m][n]==2)
    {
        cout<<"该点已有棋子，请重新选取落子点！\n";
        red();
    }
    else
        p[m][n]=2; //红方用2来表示
    system("cls");
    display();
}
 
int evalue()  //只需要判断落子点为中心的九点“米”字是否连续即可
{
    int k = 0,r = 0;
    /*斜线判断*/
    for(k=3;k<X-2;k++)  //两条，其中的p[k][r]!='-'是排除空子的情况
    {
        for(r=3;r<Y-2;r++)
        {
            if(p[k][r]!='-'&&p[k-2][r-2]==p[k][r]&&p[k-1][r-1]==p[k][r]&&p[k+1][r+1]==p[k][r]&&p[k+2][r+2]==p[k][r])
                return 1;
            else if(p[k][r]!='-'&&p[k+2][r-2]==p[k][r]&&p[k+1][r-1]==p[k][r]&&p[k-1][r+1]==p[k][r]&&p[k-2][r+2]==p[k][r])
                return 1;
        }
    }
    /*横线判断*/
    for(k=1;k<X;k++)  //p[k][r]!='-'是排除空子的情况
        for(r=3;r<Y-2;r++)
            if(p[k][r]!='-'&&p[k][r-2]==p[k][r]&&p[k][r-1]==p[k][r]&&p[k][r+1]==p[k][r]&&p[k][r+2]==p[k][r])
                return 1;
    /*竖线判断*/
    for(k=3;k<X-2;k++)  //p[k][r]!='-'是排除空子的情况
        for(r=1;r<Y;r++)
            if(p[k][r]!='-'&&p[k-2][r]==p[k][r]&&p[k-1][r]==p[k][r]&&p[k+1][r]==p[k][r]&&p[k+2][r]==p[k][r])
                return 1;
    return 0; 
}
 
int main()
{
    memset(p,'-',441);  //初始化为‘-’
    cout<<"欢迎使用简易双人对战五子棋游戏\n"
        <<"五子棋棋谱如下：\n";
    display();
    while(1)
    {
        red();
        if(evalue())
        {
            cout<<"红方赢！\n";
            break;
        }
        black();
        if(evalue())
        {
            cout<<"黑方赢！\n";
            break;
        }  
    }
    return 0;
}
