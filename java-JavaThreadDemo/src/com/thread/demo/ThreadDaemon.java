package com.thread.demo;

/**
 * 后台线程和setDaemon()方法
 */
public class ThreadDaemon {
    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();

        Thread tt = new Thread(t);
        tt.setDaemon(true);// 设置后台线程
        //因为他是后台线程，因此整个进程在主线程结束时就随之终止运行了
        //验证了如果进程中只有后台线程的时候，进程就会终止
        tt.start();
    }
}
class ThreadTest implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+"is running!");
        }
    }
}
