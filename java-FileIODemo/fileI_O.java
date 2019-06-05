package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OperateFileDemo {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
    private Date start_time = null;//开始时间
    private Date end_time = null;//结束时间

    public static void main(String[] args) {
        OperateFileDemo demo = new OperateFileDemo();
        demo.operateFile1();
        demo.operateFile2();
        demo.operateFile3();
        demo.fileCopy1();
        demo.fileCopy2();
    }

    /**
     * the first method of reading file
     */
    public void operateFile1(){
        start_time = new Date();
        File f = new File("E:"+File.separator+"test.txt");//File.separator——windows is '\'，unix is '/'
        try {
            //创建一个流对象
            InputStream in = new FileInputStream(f);
            //读取数据，并将读取的数据存储到数组中
            byte[] b = new byte[(int) f.length()];//数据存储的数组
            int len = 0;
            int temp = 0;
            while((temp = in.read()) != -1){//循环读取数据，未到达流的末尾
                b[len] = (byte) temp;//将有效数据存储在数组中
                len ++;
            }

            System.out.println(new String(b, 0, len, "GBK"));
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            end_time = new Date();
            System.out.println("=第一种方式——start_time:"+df.format(start_time));
            System.out.println("=第一种方式——end_time:"+df.format(end_time));
            System.out.println("=第一种方式总耗时："+(end_time.getTime() - start_time.getTime())+"毫秒");
        }
    }

    /**
     * the second method of reading file
     */
    public void operateFile2(){
        start_time = new Date();
        File f = new File("E:"+File.separator+"test.txt");
        try {
            InputStream in = new FileInputStream(f);
            byte[] b = new byte[1024];
            int len = 0;
            while((len = in.read(b)) != -1){
                System.out.println(new String(b, 0, len, "GBK"));
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            end_time = new Date();
            System.out.println("=第二种方式——start_time:"+df.format(start_time));
            System.out.println("=第二种方式——end_time:"+df.format(end_time));
            System.out.println("=第二种方式总耗时："+(end_time.getTime() - start_time.getTime())+"毫秒");
        }
    }

    /**
     * the third method of reading file(文件读取（Memory mapping-内存映射方式）)
     * 这种方式的效率是最好的，速度也是最快的，因为程序直接操作的是内存
     */
    public void operateFile3(){
        start_time = new Date();
        File f = new File("E:"+File.separator+"test.txt");
        try {
            FileInputStream in = new FileInputStream(f);
            FileChannel chan = in.getChannel();//内存与磁盘文件的通道,获取通道，通过文件通道读写文件。
            MappedByteBuffer buf = chan.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            byte[] b = new byte[(int) f.length()];
            int len = 0;
            while(buf.hasRemaining()){
                b[len] = buf.get();
                len++;
            }
            chan.close();
            in.close();
            System.out.println(new String(b,0,len,"GBK"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            end_time = new Date();
            System.out.println("=第三种方式——start_time:"+df.format(start_time));
            System.out.println("=第三种方式——end_time:"+df.format(end_time));
            System.out.println("=第三种方式总耗时："+(end_time.getTime() - start_time.getTime())+"毫秒");
        }
    }

    /**
     * the first method of copying file
     */
    public void fileCopy1(){
        start_time = new Date();
        File f = new File("E:"+File.separator+"test.txt");
        try {
            InputStream in = new FileInputStream(f);
            OutputStream out = new FileOutputStream("F:"+File.separator+"test.txt");
            int len = 0;
            while((len = in.read()) != -1){
                out.write(len);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            end_time = new Date();
            System.out.println("=第一种文件复制方式——start_time:"+df.format(start_time));
            System.out.println("=第一种文件复制方式——end_time:"+df.format(end_time));
            System.out.println("=第一种文件复制方式总耗时："+(end_time.getTime() - start_time.getTime())+"毫秒");
        }
    }

    /**
     * 使用内存映射实现文件复制操作
     */
    public void fileCopy2(){
        start_time = new Date();
        File f = new File("E:"+File.separator+"test.txt");
        try {
            FileInputStream in = new FileInputStream(f);
            FileOutputStream out = new FileOutputStream("F:"+File.separator+"test2.txt");
            FileChannel inChan = in.getChannel();
            FileChannel outChan = out.getChannel();
            //开辟缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while ((inChan.read(buf)) != -1){
                //重设缓冲区
                buf.flip();
                //输出缓冲区
                outChan.write(buf);
                //清空缓冲区
                buf.clear();
            }
            inChan.close();
            outChan.close();
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            end_time = new Date();
            System.out.println("=第二种文件复制方式——start_time:"+df.format(start_time));
            System.out.println("=第二种文件复制方式——end_time:"+df.format(end_time));
            System.out.println("=第二种文件复制方式总耗时："+(end_time.getTime() - start_time.getTime())+"毫秒");
        }
    }
}