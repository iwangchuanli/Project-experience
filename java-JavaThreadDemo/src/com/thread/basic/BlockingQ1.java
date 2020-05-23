package com.thread.basic;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQ1 {
    private Object notempty = new Object();
    private Queue<Object> linkedList = new LinkedList<Object>();

    public Object take() throws InterruptedException {
        synchronized (notempty){
            if (linkedList.size() == 0){
                notempty.wait();
            }
            return linkedList.poll();
        }
    }

    public void offer(Object object){
        synchronized (notempty){
            if (linkedList.size() == 0){
                notempty.notify();
            }
            linkedList.add(object);
        }
    }
}
