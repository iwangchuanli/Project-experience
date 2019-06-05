#include<stdio.h>
#include <afx.h>//CString的头文件
#include<string.h>
#include<stdlib.h>
#include<time.h>
#include <windows.h>
#include<mmsystem.h>
#pragma comment(lib,"winmm.lib")
int main()
{
	MCIERROR re;
	FILE *fp;
	char slen[256]={0},title[30]={0},artist[30]={0},test,song[30],st;
	CString fe;
	long here,llen;
	int m,n,v,s;
	here=clock();//时间函数必须放在循环外面
	printf("请输入歌名: ");
	fflush(stdin);
	gets(song);//输入曲目名称
	fe.Format("open %s alias beatit",song); 
	re=mciSendString(TEXT(fe.GetBuffer(fe.GetLength())),0,0,0);//TEXT是宏变量,alias不能少
	printf("\n");
	printf("请选择一个命令对应的数字:\n1.播放 2.暂停 3.继续 4.快进 5.回放 6.音量 7.属性 8.停止\n");
	while(scanf("%d",&n)!=EOF)
	{
		if(re!=0)
		{
			n=7;
		}
		if(n==1)
		{
			mciSendString(TEXT("play beatit"),0,0,0);
			printf("successful\n\n");
		}
		else if(n==4)//快进
		{
			here=here+10000;
			fe.Format("seek beatit to %ld",here);
			mciSendString(TEXT(fe.GetBuffer(fe.GetLength())),0,0,0);//to后面的long数值必须真的是一个long数值，不能用变量名
			mciSendString(TEXT("play beatit"),0,0,0);//seek后还需要继续用open才能播放音乐
			printf("successful\n\n");
			//增加一个播放状态函数进行测量
		}
		else if(n==5)//快退
		{
			here=here-10000;
			if(here<0)//快退到开始状态
				here=0;
			fe.Format("seek beatit to %ld",here);
			mciSendString(TEXT(fe.GetBuffer(fe.GetLength())),0,0,0);
			mciSendString(TEXT("play beatit"),0,0,0);
			printf("successful\n\n");
		}
		else if(n==8)//停止
		{
			mciSendString(TEXT("close beatit"),0,0,0);
			printf("successful\n\n");
			exit(0);
		}
		else if(n==6)//音量
		{
			printf("请输入新的音量大小,范围:1 to 1000\n");
			scanf("%d",&v);
			if(v<0||v>1000)
				printf("failed\n\n");
			else
			{
				fe.Format("setaudio beatit volume to %d",v);
				mciSendString(TEXT(fe.GetBuffer(fe.GetLength())),0,0,0);
				mciSendString(TEXT("play beatit"),0,0,0);
				printf("successful\n\n");
			}
		}
		else if(n==2)//暂停
		{
			mciSendString(TEXT("pause beatit"),0,0,0);
			printf("successful\n\n");
		}
		else if(n==3)//播放
		{
			mciSendString(TEXT("resume beatit"),0,0,0);
			printf("successful\n\n");
		}
		else if(n==7)//属性
		{
			if(re!=0)
				printf("信息:\n歌曲打开失败\n\n");
			else
			{
				mciSendString(TEXT("status beatit length"),slen,255,0);
				llen=strtol(slen,0,0)/1000;//测量出媒体长度，单位为秒，需要转化
				m=llen/60;//分
				s=llen%60;//秒
				fp=fopen(song,"r");
				if(fp==NULL)
				{
					printf("failed\n\n");
					exit(0);
				}
				fseek(fp,-125L,2);//讲指针移动到距离歌曲末尾128个字节处
				fgets(title,30,fp);
				while((test=fgetc(fp))!='\0');//指针跳跃
				fflush(stdin);
				fgets(artist,30,fp);
				printf("信息:\n");
				printf("歌名: %s\n",title);
				printf("歌手: %s\n",artist);
				if(m>=10&&s>=10)
					printf("时长: %d:%d\n",m,s);
				else if(m<10&&s>=10)
					printf("时长: 0%d:%d\n",m,s);
				else if(m>=10&&s<10)
					printf("时长: %d:0%d\n",m,s);
				else
					printf("时长: 0%d:0%d\n",m,s);
				printf("\n");
			}
		}
		else
			continue;
	}	
	Sleep(10*60*1000);
	return 0;
}