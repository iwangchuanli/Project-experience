#include<stdio.h>
#include <afx.h>//CString��ͷ�ļ�
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
	here=clock();//ʱ�亯���������ѭ������
	printf("���������: ");
	fflush(stdin);
	gets(song);//������Ŀ����
	fe.Format("open %s alias beatit",song); 
	re=mciSendString(TEXT(fe.GetBuffer(fe.GetLength())),0,0,0);//TEXT�Ǻ����,alias������
	printf("\n");
	printf("��ѡ��һ�������Ӧ������:\n1.���� 2.��ͣ 3.���� 4.��� 5.�ط� 6.���� 7.���� 8.ֹͣ\n");
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
		else if(n==4)//���
		{
			here=here+10000;
			fe.Format("seek beatit to %ld",here);
			mciSendString(TEXT(fe.GetBuffer(fe.GetLength())),0,0,0);//to�����long��ֵ���������һ��long��ֵ�������ñ�����
			mciSendString(TEXT("play beatit"),0,0,0);//seek����Ҫ������open���ܲ�������
			printf("successful\n\n");
			//����һ������״̬�������в���
		}
		else if(n==5)//����
		{
			here=here-10000;
			if(here<0)//���˵���ʼ״̬
				here=0;
			fe.Format("seek beatit to %ld",here);
			mciSendString(TEXT(fe.GetBuffer(fe.GetLength())),0,0,0);
			mciSendString(TEXT("play beatit"),0,0,0);
			printf("successful\n\n");
		}
		else if(n==8)//ֹͣ
		{
			mciSendString(TEXT("close beatit"),0,0,0);
			printf("successful\n\n");
			exit(0);
		}
		else if(n==6)//����
		{
			printf("�������µ�������С,��Χ:1 to 1000\n");
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
		else if(n==2)//��ͣ
		{
			mciSendString(TEXT("pause beatit"),0,0,0);
			printf("successful\n\n");
		}
		else if(n==3)//����
		{
			mciSendString(TEXT("resume beatit"),0,0,0);
			printf("successful\n\n");
		}
		else if(n==7)//����
		{
			if(re!=0)
				printf("��Ϣ:\n������ʧ��\n\n");
			else
			{
				mciSendString(TEXT("status beatit length"),slen,255,0);
				llen=strtol(slen,0,0)/1000;//������ý�峤�ȣ���λΪ�룬��Ҫת��
				m=llen/60;//��
				s=llen%60;//��
				fp=fopen(song,"r");
				if(fp==NULL)
				{
					printf("failed\n\n");
					exit(0);
				}
				fseek(fp,-125L,2);//��ָ���ƶ����������ĩβ128���ֽڴ�
				fgets(title,30,fp);
				while((test=fgetc(fp))!='\0');//ָ����Ծ
				fflush(stdin);
				fgets(artist,30,fp);
				printf("��Ϣ:\n");
				printf("����: %s\n",title);
				printf("����: %s\n",artist);
				if(m>=10&&s>=10)
					printf("ʱ��: %d:%d\n",m,s);
				else if(m<10&&s>=10)
					printf("ʱ��: 0%d:%d\n",m,s);
				else if(m>=10&&s<10)
					printf("ʱ��: %d:0%d\n",m,s);
				else
					printf("ʱ��: 0%d:0%d\n",m,s);
				printf("\n");
			}
		}
		else
			continue;
	}	
	Sleep(10*60*1000);
	return 0;
}