#ifndef __HEADER_H__
#define __HEADER_H__

// DEBUG模式
#define __DEBUG__ 0

// 最大的数据传输长度
#define MAX_DATA_LEN 100

#define BUFFER_SIZE 100

// 定义不同的命令值
#define CMD_LIST 1
#define CMD_GET 2
#define CMD_PUT 3
#define CMD_QUIT 4

// 定义数据包结构体
typedef struct msg 
{
	int cmd;					// 命令
	char data[MAX_DATA_LEN];	// 数据
}msg_t;

#endif
