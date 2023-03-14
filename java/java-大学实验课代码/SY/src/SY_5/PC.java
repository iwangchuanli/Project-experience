package SY_5;

public class PC {
	CPU cpu=new CPU();
	HardDisk HD=new HardDisk();
	void setCPU(CPU c){
		this.cpu=c;
	}
	void setHardDisk(HardDisk h){
		this.HD=h;
	}
	void show(){
		System.out.println("CPU的速度："+cpu.getSpeed());
		System.out.println("硬盘的容量："+HD.getAmount());
	}

}
