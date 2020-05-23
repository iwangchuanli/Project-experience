package com.thread.basic;

/**
 * java thread 线程相关
 *
 */
public class ThreadBasic {
    //内部类
    public static void main(String[] args) {
        //线程启动方法一
        Thread thread1 = new Thread("线程一"){
            @Override
            public void run() {
                System.out.println(this.getName()+"started");
            }
        };
        thread1.start();

        //线程方法二
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                System.out.println(this.getName()+"started");
            }
        };
        thread2.setName("线程二");
        thread2.start();

        //线程方法三
        MyThread thread3 = new MyThread();
        thread3.start();

//        //传入task任务
//        Thread thread4 = new Thread(task);
//        thread4.setName("线程四");
//        thread4.start();
//
//        //传入任务及线程名
//        Thread thread5 = new Thread(task,"线程五");
//        thread5.start();
    }
    public static void task(){
        System.out.println("started");
    }
}
//外部继承的方法
class MyThread extends Thread{
    public MyThread() {
        super("线程三");
    }
    @Override
    public void run() {
        System.out.println(this.getName()+"started");
    }
}
