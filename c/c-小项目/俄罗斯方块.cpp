#include <stdio.h>
#include <windows.h>
#include <conio.h>
#include <time.h>
//��Ϸ����
#define FrameX 4   //��Ϸ�������Ͻǵ�X������
#define FrameY 4   //��Ϸ�������Ͻǵ�Y������
#define Frame_height  20 //��Ϸ���ڵĸ߶�
#define Frame_width   18 //��Ϸ���ڵĿ��
//����ȫ�ֱ���
int i,j,temp,temp1,temp2; //temp,temp1,temp2���ڼ�ס��ת�����������ֵ
int a[80][80]={0};   //�����Ϸ��Ļ��ͼ����2,1,0�ֱ��ʾ��λ��Ϊ��Ϸ�߿򡢷��顢��ͼ��;��ʼ��Ϊ��ͼ��
int b[4];     //���4��"��"���飺1��ʾ�з��飬0��ʾ�޷���
  
//��������˹����Ľṹ��
struct Tetris
{
 int x;     //���ķ����x������
 int y;     //���ķ����y������
 int flag;    //��Ƿ������͵����
 int next;    //��һ������˹�������͵����
 int speed;    //����˹�����ƶ����ٶ�
 int count;    //��������˹����ĸ���
 int score;    //��Ϸ�ķ���
 int level;    //��Ϸ�ĵȼ�
};
//����ԭ������
//����Ƶ�ָ��λ��
void gotoxy(HANDLE hOut, int x, int y);
//������Ϸ����
void make_frame();
//��������������͵����
void get_flag(struct Tetris *);
//��������˹����
void make_tetris(struct Tetris *);
//��ӡ����˹����
void print_tetris(HANDLE hOut,struct Tetris *);
//�������˹����ĺۼ�
void clear_tetris(HANDLE hOut,struct Tetris *);
//�ж��Ƿ����ƶ�������ֵΪ1�����ƶ������򣬲���
int if_moveable(struct Tetris *);
//�ж��Ƿ����У���ɾ�����еĶ���˹����
void del_full(HANDLE hOut,struct Tetris *);
//��ʼ��Ϸ
void start_game();

int main()
{ 
 //������Ϸ����
 make_frame();      
 //��ʼ��Ϸ
 start_game();
}
/******����Ƶ�ָ��λ��**************************************************************/
void gotoxy(HANDLE hOut, int x, int y)
{
 COORD pos;
 pos.X = x;  //������
 pos.Y = y;  //������
 SetConsoleCursorPosition(hOut, pos);
}
/******������Ϸ����******************************************************************/
void make_frame()
{
 HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);  //������ʾ���������
 gotoxy(hOut,FrameX+Frame_width-5,FrameY-2);   //��ӡ��Ϸ����
 printf("����˹����");
 gotoxy(hOut,FrameX+2*Frame_width+3,FrameY+7);  //��ӡѡ��˵�
 printf("**********��һ�����飺");
 gotoxy(hOut,FrameX+2*Frame_width+3,FrameY+13);
 printf("**********");
 gotoxy(hOut,FrameX+2*Frame_width+3,FrameY+17);
 printf("����������");
 gotoxy(hOut,FrameX+2*Frame_width+3,FrameY+19);
 printf("�ո���ͣ��Ϸ");
 gotoxy(hOut,FrameX+2*Frame_width+3,FrameY+15);
 printf("Esc ���˳���Ϸ");
 gotoxy(hOut,FrameX,FrameY);       //��ӡ��ǲ���ס�ô�����ͼ��
 printf("�X");
 gotoxy(hOut,FrameX+2*Frame_width-2,FrameY);
 printf("�[");
 gotoxy(hOut,FrameX,FrameY+Frame_height);
 printf("�^");
 gotoxy(hOut,FrameX+2*Frame_width-2,FrameY+Frame_height);
 printf("�a");
 a[FrameX][FrameY+Frame_height]=2;     
 a[FrameX+2*Frame_width-2][FrameY+Frame_height]=2;
 for(i=2;i<2*Frame_width-2;i+=2)
 {
  gotoxy(hOut,FrameX+i,FrameY);
  printf("�T");         //��ӡ�Ϻ��
 }
 for(i=2;i<2*Frame_width-2;i+=2)
 {
  gotoxy(hOut,FrameX+i,FrameY+Frame_height);
  printf("�T");         //��ӡ�º��
  a[FrameX+i][FrameY+Frame_height]=2;    //��ס�º����ͼ��
 }
 for(i=1;i<Frame_height;i++)
 {
  gotoxy(hOut,FrameX,FrameY+i); 
  printf("�U");         //��ӡ������
  a[FrameX][FrameY+i]=2;       //��ס��������ͼ��
 }
 for(i=1;i<Frame_height;i++)
 {
  gotoxy(hOut,FrameX+2*Frame_width-2,FrameY+i); 
  printf("�U");         //��ӡ������
  a[FrameX+2*Frame_width-2][FrameY+i]=2;   //��ס��������ͼ��
 }
}
/******��������˹����********************************************************************/
void make_tetris(struct Tetris *tetris)
{
 a[tetris->x][tetris->y]=b[0];    //���ķ���λ�õ�ͼ��״̬:1-��,0-��
 switch(tetris->flag)      //��6���࣬19������
 {
  case 1:         //���ַ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x+2][tetris->y-1]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
  case 2:         //ֱ�߷���:----
   {  
    a[tetris->x-2][tetris->y]=b[1];
    a[tetris->x+2][tetris->y]=b[2];
    a[tetris->x+4][tetris->y]=b[3];
    break;
   }
  case 3:         //ֱ�߷���: |
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x][tetris->y-2]=b[2];
    a[tetris->x][tetris->y+1]=b[3];
    break;
   }
  case 4:         //T�ַ���
   {  
    a[tetris->x-2][tetris->y]=b[1];
    a[tetris->x+2][tetris->y]=b[2];
    a[tetris->x][tetris->y+1]=b[3];
    break;
   }
  case 5:         //T��˳ʱ��ת90�ȷ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x][tetris->y+1]=b[2];
    a[tetris->x-2][tetris->y]=b[3];
    break;
   }
  case 6:         //T��˳ʱ��ת180�ȷ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x-2][tetris->y]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
  case 7:         //T��˳ʱ��ת270�ȷ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x][tetris->y+1]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
  case 8:         //Z�ַ���
   {  
    a[tetris->x][tetris->y+1]=b[1];
    a[tetris->x-2][tetris->y]=b[2];
    a[tetris->x+2][tetris->y+1]=b[3];
    break;
   }
  case 9:         //Z��˳ʱ��ת90�ȷ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x-2][tetris->y]=b[2];
    a[tetris->x-2][tetris->y+1]=b[3];
    break;
   }
  case 10:        //Z��˳ʱ��ת180�ȷ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x-2][tetris->y-1]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
  case 11:        //Z��˳ʱ��ת270�ȷ���
   {  
    a[tetris->x][tetris->y+1]=b[1];
    a[tetris->x+2][tetris->y-1]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
  case 12:        //7�ַ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x][tetris->y+1]=b[2];
    a[tetris->x-2][tetris->y-1]=b[3];
    break;
   }
  case 13:        //7��˳ʱ��ת90�ȷ���
   {  
    a[tetris->x-2][tetris->y]=b[1];
    a[tetris->x-2][tetris->y+1]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
  case 14:        //7��˳ʱ��ת180�ȷ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x][tetris->y+1]=b[2];
    a[tetris->x+2][tetris->y+1]=b[3];
    break;
   }
  case 15:        //7��˳ʱ��ת270�ȷ���
   {
    a[tetris->x-2][tetris->y]=b[1];
    a[tetris->x+2][tetris->y-1]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
  case 16:        //��7�ַ���
   { 
    a[tetris->x][tetris->y+1]=b[1];
    a[tetris->x][tetris->y-1]=b[2];
    a[tetris->x+2][tetris->y-1]=b[3];
    break;
   }
  case 17:        //��7��˳ָ��ת90�ȷ���
   { 
    a[tetris->x-2][tetris->y]=b[1];
    a[tetris->x-2][tetris->y-1]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
  case 18:        //��7��˳ʱ��ת180�ȷ���
   {  
    a[tetris->x][tetris->y-1]=b[1];
    a[tetris->x][tetris->y+1]=b[2];
    a[tetris->x-2][tetris->y+1]=b[3];
    break;
   }
  case 19:        //��7��˳ʱ��ת270�ȷ���
   {  
    a[tetris->x-2][tetris->y]=b[1];
    a[tetris->x+2][tetris->y+1]=b[2];
    a[tetris->x+2][tetris->y]=b[3];
    break;
   }
 } 
}
//******�ж��Ƿ�ɶ�*************************************************************************/
int if_moveable(struct Tetris *tetris)
{
 if(a[tetris->x][tetris->y]!=0)//�����ķ���λ������ͼ��ʱ������ֵΪ0���������ƶ�
 {
  return 0;
 }
 else
 {
  if( //��Ϊ���ַ����ҳ����ķ���λ���⣬����"��"�ַ���λ������ͼ��ʱ������ֵΪ1�������ƶ�
   ( tetris->flag==1  && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x+2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   //��Ϊֱ�߷����ҳ����ķ���λ���⣬����"��"�ַ���λ������ͼ��ʱ������ֵΪ1�������ƶ�
   ( tetris->flag==2  && ( a[tetris->x-2][tetris->y]==0   && 
    a[tetris->x+2][tetris->y]==0 && a[tetris->x+4][tetris->y]==0 ) )   ||
   ( tetris->flag==3  && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x][tetris->y-2]==0 && a[tetris->x][tetris->y+1]==0 ) )   ||
   ( tetris->flag==4  && ( a[tetris->x-2][tetris->y]==0   &&
    a[tetris->x+2][tetris->y]==0 && a[tetris->x][tetris->y+1]==0 ) )   ||
   ( tetris->flag==5  && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x][tetris->y+1]==0 && a[tetris->x-2][tetris->y]==0 ) )   ||
   ( tetris->flag==6  && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x-2][tetris->y]==0 && a[tetris->x+2][tetris->y]==0 ) )   ||
   ( tetris->flag==7  && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x][tetris->y+1]==0 && a[tetris->x+2][tetris->y]==0 ) )   ||
   ( tetris->flag==8  && ( a[tetris->x][tetris->y+1]==0   &&
    a[tetris->x-2][tetris->y]==0 && a[tetris->x+2][tetris->y+1]==0 ) ) ||
   ( tetris->flag==9  && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x-2][tetris->y]==0 && a[tetris->x-2][tetris->y+1]==0 ) ) ||
   ( tetris->flag==10 && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x-2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   ( tetris->flag==11 && ( a[tetris->x][tetris->y+1]==0   &&
    a[tetris->x+2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   ( tetris->flag==12 && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x][tetris->y+1]==0 && a[tetris->x-2][tetris->y-1]==0 ) ) ||
   ( tetris->flag==13 && ( a[tetris->x-2][tetris->y]==0   &&
    a[tetris->x-2][tetris->y+1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   ( tetris->flag==14 && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x][tetris->y+1]==0 && a[tetris->x+2][tetris->y+1]==0 ) ) ||
   ( tetris->flag==15 && ( a[tetris->x-2][tetris->y]==0   &&
    a[tetris->x+2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   ( tetris->flag==16 && ( a[tetris->x][tetris->y+1]==0   &&
    a[tetris->x][tetris->y-1]==0 && a[tetris->x+2][tetris->y-1]==0 ) ) ||
   ( tetris->flag==17 && ( a[tetris->x-2][tetris->y]==0   &&
    a[tetris->x-2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   ( tetris->flag==18 && ( a[tetris->x][tetris->y-1]==0   &&
    a[tetris->x][tetris->y+1]==0 && a[tetris->x-2][tetris->y+1]==0 ) ) ||
   ( tetris->flag==19 && ( a[tetris->x-2][tetris->y]==0   &&
    a[tetris->x+2][tetris->y+1]==0 && a[tetris->x+2][tetris->y]==0 ) ) )
   {
    return 1;
   }
 }
 return 0;
}
/******�����������˹�������͵����**********************************************************/
void get_flag(struct Tetris *tetris)
{
 tetris->count++;     //��ס��������ĸ���
 srand((unsigned)time(NULL));  //��ʼ�������
 if(tetris->count==1)
 {
  tetris->flag = rand()%19+1;  //��ס��һ����������
 }
 tetris->next = rand()%19+1;   //��ס��һ����������
}
/******��ӡ����˹����**********************************************************************/
void print_tetris(HANDLE hOut,struct Tetris *tetris)
{
 for(i=0;i<4;i++)
 {
  b[i]=1;         //����b[4]��ÿ��Ԫ�ص�ֵ��Ϊ1
 }
 make_tetris(tetris);      //��������˹����
 for( i=tetris->x-2; i<=tetris->x+4; i+=2 )
 {
  for(j=tetris->y-2;j<=tetris->y+1;j++)
  {
   if( a[i][j]==1 && j>FrameY )
   {
    gotoxy(hOut,i,j);
    printf("��");     //��ӡ�߿��ڵķ���
   }
  }
 }
 //��ӡ�˵���Ϣ
 gotoxy(hOut,FrameX+2*Frame_width+3,FrameY+1);
 printf("level : %d",tetris->level);
 gotoxy(hOut,FrameX+2*Frame_width+3,FrameY+3);
 printf("score : %d",tetris->score);
 gotoxy(hOut,FrameX+2*Frame_width+3,FrameY+5);
 printf("speed : %dms",tetris->speed);
}
/******�������˹����ĺۼ�****************************************************************/
void clear_tetris(HANDLE hOut,struct Tetris *tetris)
{
 for(i=0;i<4;i++)
 {
  b[i]=0;         //����b[4]��ÿ��Ԫ�ص�ֵ��Ϊ0
 }
 make_tetris(tetris);      //��������˹����
 for( i=tetris->x-2; i<=tetris->x+4; i+=2 )
 {
  for(j=tetris->y-2;j<=tetris->y+1;j++)
  {
   if( a[i][j]==0 && j>FrameY )
   {
    gotoxy(hOut,i,j);
    printf("  ");     //�������
   }
  }
 }
}
/******�ж��Ƿ����в�ɾ�����еĶ���˹����****************************************************/
void del_full(HANDLE hOut,struct Tetris *tetris)
{       //��ĳ����Frame_width-2������ʱ��������
 int k,del_count=0;  //�ֱ����ڼ�¼ĳ�з���ĸ�����ɾ������������ı���
 for(j=FrameY+Frame_height-1;j>=FrameY+1;j--)
 {
  k=0;
  for(i=FrameX+2;i<FrameX+2*Frame_width-2;i+=2)
  {  
   if(a[i][j]==1) //���������δ������ϣ��������������������ж��Ƿ�����
   {
    k++;  //��¼���з���ĸ���
    if(k==Frame_width-2)
    {
     for(k=FrameX+2;k<FrameX+2*Frame_width-2;k+=2)
     {  //ɾ�����еķ���
      a[k][j]=0;
      gotoxy(hOut,k,j);
      printf("  ");
      Sleep(1);
     }
     for(k=j-1;k>FrameY;k--)
     {  //���ɾ�������ϵ�λ���з��飬����������ٽ���������һ��λ��
      for(i=FrameX+2;i<FrameX+2*Frame_width-2;i+=2)
      {
       if(a[i][k]==1)
       {
        a[i][k]=0;
        gotoxy(hOut,i,k);
        printf("  ");
        a[i][k+1]=1;
        gotoxy(hOut,i,k+1);
        printf("��");
       }
      }
     }
     j++;   //�������ƺ������ж�ɾ�����Ƿ�����
     del_count++; //��¼ɾ�����������
    }
   }
  }
 }
 tetris->score+=100*del_count; //ÿɾ��һ�У���100��
 if( del_count>0 && ( tetris->score%1000==0 || tetris->score/1000>tetris->level-1 ) )
 {        //�����1000�ּ��ۼ�ɾ��10�У��ٶȼӿ�20ms����һ��
  tetris->speed-=20;
  tetris->level++;
 }
}
/******��ʼ��Ϸ******************************************************************************/
void start_game()
{
 HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);  //������ʾ���������
 struct Tetris t,*tetris=&t;       //����ṹ���ָ�벢ָ��ṹ�����
 unsigned char ch;         //������ռ�������ı���
 tetris->count=0;      //��ʼ������˹������Ϊ0��
 tetris->speed=300;      //��ʼ�ƶ��ٶ�Ϊ300ms
 tetris->score=0;      //��ʼ��Ϸ�ķ���Ϊ0��
 tetris->level=1;      //��ʼ��ϷΪ��1��
 while(1)
 {//ѭ���������飬ֱ����Ϸ����
  get_flag(tetris);     //�õ���������˹�������͵����
  temp=tetris->flag;     //��ס��ǰ����˹�������
  //��ӡ��һ������˹�����ͼ��(�ұߴ���)
  tetris->x=FrameX+2*Frame_width+6;
  tetris->y=FrameY+10;
  tetris->flag = tetris->next;
  print_tetris(hOut,tetris);
  tetris->x=FrameX+Frame_width;  //��ʼ���ķ���x����
  tetris->y=FrameY-1;     //��ʼ���ķ���y����
  tetris->flag=temp;     //ȡ����ǰ�Ķ���˹�������
  while(1)
  {//���Ʒ��鷽��ֱ�����鲻������
   label:print_tetris(hOut,tetris);//��ӡ����˹����
   Sleep(tetris->speed);   //�ӻ�ʱ��
   clear_tetris(hOut,tetris);  //����ۼ�
   temp1=tetris->x;    //��ס���ķ���������ֵ
   temp2=tetris->flag;    //��ס��ǰ����˹�������
   if(kbhit())   
   {        //�ж��Ƿ��м������룬������ch������
    ch=getch(); 
    if(ch==75)     //�����������󶯣����ĺ������2
    {      
     tetris->x-=2;
    }
    if(ch==77)     //�����������Ҷ������ĺ������2
    {      
     tetris->x+=2;    
    }
    if(ch==72)     //����������弴��ǰ����˳ʱ��ת90��
    {      
     if( tetris->flag>=2 && tetris->flag<=3 )
     {
      tetris->flag++; 
      tetris->flag%=2;
      tetris->flag+=2;
     }
     if( tetris->flag>=4 && tetris->flag<=7 )
     {
      tetris->flag++;
      tetris->flag%=4;
      tetris->flag+=4;
     }    
     if( tetris->flag>=8 && tetris->flag<=11 )
     {
      tetris->flag++;
      tetris->flag%=4;
      tetris->flag+=8;
     }    
     if( tetris->flag>=12 && tetris->flag<=15 )
     {
      tetris->flag++;
      tetris->flag%=4;
      tetris->flag+=12;
     }    
     if( tetris->flag>=16 && tetris->flag<=19 )
     {
      tetris->flag++;
      tetris->flag%=4;
      tetris->flag+=16;
     }
    }
    if(ch==32)     //���ո������ͣ
    {
     print_tetris(hOut,tetris);
     while(1)
     {
      if(kbhit())   //�ٰ��ո����������Ϸ
      {
       ch=getch();
       if(ch==32)
       {
        goto label;
       }
      }
     }
    }
    if(if_moveable(tetris)==0) //������ɶ������������Ч
    {
     tetris->x=temp1;
     tetris->flag=temp2;
    }
    else      //����ɶ���ִ�в���
    {
     goto label;
    }
   }
   tetris->y++;     //���û�в���ָ����������ƶ�
   if(if_moveable(tetris)==0)  //��������ƶ��Ҳ��ɶ���������ڴ˴�
   {    
    tetris->y--;
    print_tetris(hOut,tetris);
    del_full(hOut,tetris);
    break;
   }
  }
  for(i=tetris->y-2;i<tetris->y+2;i++)
  {//��Ϸ�������������鴥����λ��
   if(i==FrameY)
   {
    j=0;      //�����Ϸ������j=0
   }
  }
  if(j==0)       
  {
   system("cls");
   getch();
   break;
  }
  //�����һ������˹�����ͼ��(�ұߴ���)
  tetris->flag = tetris->next;
  tetris->x=FrameX+2*Frame_width+6;
  tetris->y=FrameY+10;
  clear_tetris(hOut,tetris);  
 }
}
