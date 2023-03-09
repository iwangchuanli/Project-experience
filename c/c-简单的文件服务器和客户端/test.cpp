#include <iostream>
#include <stdio.h>
#include <string.h>

#define MAX_VALUE_LEN 100

int query_string_key_to_value(char *query_string, char *key, char *value, int len);

int main(int argc, char *argv[])
{
	char *query_string = "name=zhangsan&age=lisi";
	char value[MAX_VALUE_LEN] = {'\0'};

	query_string_key_to_value(query_string, "name", value, MAX_VALUE_LEN);

	printf("name : %s\n", value);

	return 0;
}

int query_string_key_to_value(char *query_string, char *key, char *value, int len)
{
	int length = 0;
	char *tmp = NULL;
	char *end = NULL;

	if((tmp = strstr(query_string, key)) == NULL)
		return 1;

	tmp = tmp + strlen(key) + 1;
	end = tmp;

	while(*end != '&' && *end != '\0') {
		end++;
		length++;
	}

	if(length >= len-1)
		return 2;

	strncpy(value, tmp, length);

	return 0;
}
