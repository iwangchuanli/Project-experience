all:server client

server:server.o
	gcc server.o -o server

client:client.o
	gcc client.o -o client

clean:
	rm -rf *.o server client

.PHONY:all clean
