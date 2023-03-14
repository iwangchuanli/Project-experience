package SY_4;

public class Tank {
	   double speed;//声明double型变量speed,刻画速度
	   int bulletAmount;//声明int型变量bulletAmount,刻画炮弹数量
	    void speedUp(int s) {      
	       speed=s+speed;   //将s+speed赋值给speed
	    } 
	    void speedDown(int d) {  
	       if(speed-d>=0) 
	         speed=speed-d;  //将speed-d赋值给speed
	       else 
	         speed = 0;
	    } 
	    void setBulletAmount(int m) {
	       bulletAmount = m;
	    }
	    int getBulletAmount() {
	       return bulletAmount;
	    }
	    double getSpeed() {
	       return speed;
	    }
	    void fire() {
	        if(bulletAmount>=1){
	        	bulletAmount=bulletAmount-1;  //将bulletAmount-1赋值给bulletAmount
	           System.out.println("打出一发炮弹");
	        } 
	        else {
	           System.out.println("没有炮弹了,无法开火");
	        }
	    }
	}
