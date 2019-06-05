#include <stdio.h>  
#include <stdlib.h>  
#include <conio.h>  
#include <string.h>  
#include <time.h>  
const int H = 8;   //地图的高  
const int L = 16;  //地图的长  
char GameMap[H][L];   //游戏地图  
int  key;  //按键保存  
int  sum = 1, over = 0;  //蛇的长度, 游戏结束(自吃或碰墙)  
int  dx[4] = {0, 0, -1, 1};  //左、右、上、下的方向  
int  dy[4] = {-1, 1, 0, 0};  
struct Snake   //蛇的每个节点的数据类型  
{  
 int x, y;  //左边位置  
 int now;   //保存当前节点的方向, 0,1,2,3分别为左右上下  
}Snake[H*L];  
const char Shead = '@';  //蛇头  
const char Sbody = '#';  //蛇身  
const char Sfood = '*';  //食物  
const char Snode = '.';  //'.'在地图上标示为空  
void Initial();  //地图的初始化  
void Create_Food(); //在地图上随机产生食物  
void Show();   //刷新显示地图  
void Button();  //取出按键,并判断方向  
void Move();   //蛇的移动  
void Check_Border();  //检查蛇头是否越界  
void Check_Head(int x, int y);   //检查蛇头移动后的位置情况  
int main()   
{  
 Initial();  
 Show();  
 return 0;  
}  
void Initial()  //地图的初始化  
{  
 int i, j;  
 int hx, hy;  
 system("title 贪吃蛇");  //控制台的标题  
 memset(GameMap, '.', sizeof(GameMap));  //初始化地图全部为空'.'  
 system("cls");  
 srand(time(0));   //随机种子  
 hx = rand()%H;    //产生蛇头  
 hy = rand()%L;  
 GameMap[hx][hy] = Shead;  
 Snake[0].x = hx;  Snake[0].y = hy;  
 Snake[0].now = -1;  
 Create_Food();   //随机产生食物  
 for(i = 0; i < H; i++)   //地图显示  
 {   
  for(j = 0; j < L; j++)  
   printf("%c", GameMap[i][j]);  
  printf("\n");  
 }  
     
 printf("\n小小C语言贪吃蛇\n");  
 printf("按任意方向键开始游戏\n");  
    
 getch();   //先接受一个按键,使蛇开始往该方向走  
 Button();  //取出按键,并判断方向  
}  
void Create_Food()  //在地图上随机产生食物  
{  
 int fx, fy;  
 while(1)  
 {  
  fx = rand()%H;  
     fy = rand()%L;  
     
  if(GameMap[fx][fy] == '.')  //不能出现在蛇所占有的位置  
  {   
   GameMap[fx][fy] = Sfood;  
      break;  
  }  
 }  
}  
void Show()  //刷新显示地图  
{  
 int i, j;  
 while(1)  
 {    
  _sleep(500); //延迟半秒(1000为1s),即每半秒刷新一次地图  
  Button();   //先判断按键在移动  
  Move();  
  if(over)  //自吃或碰墙即游戏结束  
  {   
   printf("\n**游戏结束**\n");  
   printf("     >_<\n");  
   getchar();  
      break;  
  }  
  system("cls");   //清空地图再显示刷新吼的地图  
  for(i = 0; i < H; i++)   
  {   
   for(j = 0; j < L; j++)  
    printf("%c", GameMap[i][j]);  
   printf("\n");  
  }  
     
  printf("\n小小C语言贪吃蛇\n");  
  printf("按任意方向键开始游戏\n");  
 }  
}  
void Button()  //取出按键,并判断方向  
{  
 if(kbhit() != 0) //检查当前是否有键盘输入，若有则返回一个非0值，否则返回0  
 {   
  while(kbhit() != 0)  //可能存在多个按键,要全部取完,以最后一个为主  
      key = getch(); //将按键从控制台中取出并保存到key中  
  switch(key)  
  {   //左  
   case 75:  Snake[0].now = 0;  
          break;  
            //右  
            case 77:  Snake[0].now = 1;       
          break;  
            //上  
   case 72:  Snake[0].now = 2;  
          break;  
            //下  
   case 80:  Snake[0].now = 3;  
          break;  
  }  
 }  
}  
void Move()   //蛇的移动  
{  
 int i, x, y;  
    int t = sum;  //保存当前蛇的长度  
 //记录当前蛇头的位置,并设置为空,蛇头先移动  
 x = Snake[0].x;  y = Snake[0].y;  GameMap[x][y] = '.';  
 Snake[0].x = Snake[0].x + dx[ Snake[0].now ];  
 Snake[0].y = Snake[0].y + dy[ Snake[0].now ];  
 Check_Border();   //蛇头是否越界  
 Check_Head(x, y);  //蛇头移动后的位置情况,参数为: 蛇头的开始位置  
 if(sum == t)  //未吃到食物即蛇身移动哦  
    for(i = 1; i < sum; i++)  //要从蛇尾节点向前移动哦,前一个节点作为参照  
 {  
  if(i == 1)   //尾节点设置为空再移动  
   GameMap[ Snake[i].x ][ Snake[i].y ] = '.';  
     
  if(i == sum-1)  //为蛇头后面的蛇身节点,特殊处理  
  {  
   Snake[i].x = x;  
         Snake[i].y = y;  
      Snake[i].now = Snake[0].now;  
  }  
  else   //其他蛇身即走到前一个蛇身位置  
  {  
   Snake[i].x = Snake[i+1].x;  
         Snake[i].y = Snake[i+1].y;  
      Snake[i].now = Snake[i+1].now;  
  }  
      
  GameMap[ Snake[i].x ][ Snake[i].y ] = '#'; //移动后要置为'#'蛇身   
 }  
}  
void Check_Border()  //检查蛇头是否越界  
{  
 if(Snake[0].x < 0 || Snake[0].x >= H  
 || Snake[0].y < 0 || Snake[0].y >= L)  
     over = 1;  
}  
void Check_Head(int x, int y)  //检查蛇头移动后的位置情况  
{  
    
 if(GameMap[ Snake[0].x ][ Snake[0].y ] == '.')  //为空  
  GameMap[ Snake[0].x ][ Snake[0].y ] = '@';  
 else 
  if(GameMap[ Snake[0].x ][ Snake[0].y ] == '*')  //为食物  
  {  
   GameMap[ Snake[0].x ][ Snake[0].y ] = '@';    
   Snake[sum].x = x;   //新增加的蛇身为蛇头后面的那个  
      Snake[sum].y = y;  
      Snake[sum].now = Snake[0].now;  
         GameMap[ Snake[sum].x ][ Snake[sum].y ] = '#';   
   sum++;  
   Create_Food();  //食物吃完了马上再产生一个食物  
  }  
  else 
   over = 1;  
}
