#include <stdio.h>  
#include <stdlib.h>  
#include <conio.h>  
#include <string.h>  
#include <time.h>  
const int H = 8;   //��ͼ�ĸ�  
const int L = 16;  //��ͼ�ĳ�  
char GameMap[H][L];   //��Ϸ��ͼ  
int  key;  //��������  
int  sum = 1, over = 0;  //�ߵĳ���, ��Ϸ����(�ԳԻ���ǽ)  
int  dx[4] = {0, 0, -1, 1};  //���ҡ��ϡ��µķ���  
int  dy[4] = {-1, 1, 0, 0};  
struct Snake   //�ߵ�ÿ���ڵ����������  
{  
 int x, y;  //���λ��  
 int now;   //���浱ǰ�ڵ�ķ���, 0,1,2,3�ֱ�Ϊ��������  
}Snake[H*L];  
const char Shead = '@';  //��ͷ  
const char Sbody = '#';  //����  
const char Sfood = '*';  //ʳ��  
const char Snode = '.';  //'.'�ڵ�ͼ�ϱ�ʾΪ��  
void Initial();  //��ͼ�ĳ�ʼ��  
void Create_Food(); //�ڵ�ͼ���������ʳ��  
void Show();   //ˢ����ʾ��ͼ  
void Button();  //ȡ������,���жϷ���  
void Move();   //�ߵ��ƶ�  
void Check_Border();  //�����ͷ�Ƿ�Խ��  
void Check_Head(int x, int y);   //�����ͷ�ƶ����λ�����  
int main()   
{  
 Initial();  
 Show();  
 return 0;  
}  
void Initial()  //��ͼ�ĳ�ʼ��  
{  
 int i, j;  
 int hx, hy;  
 system("title ̰����");  //����̨�ı���  
 memset(GameMap, '.', sizeof(GameMap));  //��ʼ����ͼȫ��Ϊ��'.'  
 system("cls");  
 srand(time(0));   //�������  
 hx = rand()%H;    //������ͷ  
 hy = rand()%L;  
 GameMap[hx][hy] = Shead;  
 Snake[0].x = hx;  Snake[0].y = hy;  
 Snake[0].now = -1;  
 Create_Food();   //�������ʳ��  
 for(i = 0; i < H; i++)   //��ͼ��ʾ  
 {   
  for(j = 0; j < L; j++)  
   printf("%c", GameMap[i][j]);  
  printf("\n");  
 }  
     
 printf("\nССC����̰����\n");  
 printf("�����ⷽ�����ʼ��Ϸ\n");  
    
 getch();   //�Ƚ���һ������,ʹ�߿�ʼ���÷�����  
 Button();  //ȡ������,���жϷ���  
}  
void Create_Food()  //�ڵ�ͼ���������ʳ��  
{  
 int fx, fy;  
 while(1)  
 {  
  fx = rand()%H;  
     fy = rand()%L;  
     
  if(GameMap[fx][fy] == '.')  //���ܳ���������ռ�е�λ��  
  {   
   GameMap[fx][fy] = Sfood;  
      break;  
  }  
 }  
}  
void Show()  //ˢ����ʾ��ͼ  
{  
 int i, j;  
 while(1)  
 {    
  _sleep(500); //�ӳٰ���(1000Ϊ1s),��ÿ����ˢ��һ�ε�ͼ  
  Button();   //���жϰ������ƶ�  
  Move();  
  if(over)  //�ԳԻ���ǽ����Ϸ����  
  {   
   printf("\n**��Ϸ����**\n");  
   printf("     >_<\n");  
   getchar();  
      break;  
  }  
  system("cls");   //��յ�ͼ����ʾˢ�º�ĵ�ͼ  
  for(i = 0; i < H; i++)   
  {   
   for(j = 0; j < L; j++)  
    printf("%c", GameMap[i][j]);  
   printf("\n");  
  }  
     
  printf("\nССC����̰����\n");  
  printf("�����ⷽ�����ʼ��Ϸ\n");  
 }  
}  
void Button()  //ȡ������,���жϷ���  
{  
 if(kbhit() != 0) //��鵱ǰ�Ƿ��м������룬�����򷵻�һ����0ֵ�����򷵻�0  
 {   
  while(kbhit() != 0)  //���ܴ��ڶ������,Ҫȫ��ȡ��,�����һ��Ϊ��  
      key = getch(); //�������ӿ���̨��ȡ�������浽key��  
  switch(key)  
  {   //��  
   case 75:  Snake[0].now = 0;  
          break;  
            //��  
            case 77:  Snake[0].now = 1;       
          break;  
            //��  
   case 72:  Snake[0].now = 2;  
          break;  
            //��  
   case 80:  Snake[0].now = 3;  
          break;  
  }  
 }  
}  
void Move()   //�ߵ��ƶ�  
{  
 int i, x, y;  
    int t = sum;  //���浱ǰ�ߵĳ���  
 //��¼��ǰ��ͷ��λ��,������Ϊ��,��ͷ���ƶ�  
 x = Snake[0].x;  y = Snake[0].y;  GameMap[x][y] = '.';  
 Snake[0].x = Snake[0].x + dx[ Snake[0].now ];  
 Snake[0].y = Snake[0].y + dy[ Snake[0].now ];  
 Check_Border();   //��ͷ�Ƿ�Խ��  
 Check_Head(x, y);  //��ͷ�ƶ����λ�����,����Ϊ: ��ͷ�Ŀ�ʼλ��  
 if(sum == t)  //δ�Ե�ʳ�Ｔ�����ƶ�Ŷ  
    for(i = 1; i < sum; i++)  //Ҫ����β�ڵ���ǰ�ƶ�Ŷ,ǰһ���ڵ���Ϊ����  
 {  
  if(i == 1)   //β�ڵ�����Ϊ�����ƶ�  
   GameMap[ Snake[i].x ][ Snake[i].y ] = '.';  
     
  if(i == sum-1)  //Ϊ��ͷ���������ڵ�,���⴦��  
  {  
   Snake[i].x = x;  
         Snake[i].y = y;  
      Snake[i].now = Snake[0].now;  
  }  
  else   //���������ߵ�ǰһ������λ��  
  {  
   Snake[i].x = Snake[i+1].x;  
         Snake[i].y = Snake[i+1].y;  
      Snake[i].now = Snake[i+1].now;  
  }  
      
  GameMap[ Snake[i].x ][ Snake[i].y ] = '#'; //�ƶ���Ҫ��Ϊ'#'����   
 }  
}  
void Check_Border()  //�����ͷ�Ƿ�Խ��  
{  
 if(Snake[0].x < 0 || Snake[0].x >= H  
 || Snake[0].y < 0 || Snake[0].y >= L)  
     over = 1;  
}  
void Check_Head(int x, int y)  //�����ͷ�ƶ����λ�����  
{  
    
 if(GameMap[ Snake[0].x ][ Snake[0].y ] == '.')  //Ϊ��  
  GameMap[ Snake[0].x ][ Snake[0].y ] = '@';  
 else 
  if(GameMap[ Snake[0].x ][ Snake[0].y ] == '*')  //Ϊʳ��  
  {  
   GameMap[ Snake[0].x ][ Snake[0].y ] = '@';    
   Snake[sum].x = x;   //�����ӵ�����Ϊ��ͷ������Ǹ�  
      Snake[sum].y = y;  
      Snake[sum].now = Snake[0].now;  
         GameMap[ Snake[sum].x ][ Snake[sum].y ] = '#';   
   sum++;  
   Create_Food();  //ʳ������������ٲ���һ��ʳ��  
  }  
  else 
   over = 1;  
}
