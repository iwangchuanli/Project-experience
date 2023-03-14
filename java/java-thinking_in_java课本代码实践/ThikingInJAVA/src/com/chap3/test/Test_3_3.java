package com.chap3.test;

/**
 * 创建一个switch 语句，为每一种case 都显示一条消息。并将 switch置入一个 for循环里，令其尝试每
 * 一种case。在每个case 后面都放置一个break，并对其进行测试。然后，删除break，看看会有什么情况出
 * 现
 */
public class Test_3_3 {
    public static void main(String[] args) {
        for ( int i = 0 ;i <= 100 ;i++){
            switch (i){
                case 0:
                    System.out.println(1);
                    break;
                case 1:
                    System.out.println(2);
                    break;

                    default:
                        break;
            }



        }
    }
}
