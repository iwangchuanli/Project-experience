package chapter5;

public class Application {
	public static void main(String[] args) {
		MobileTelephone telephone = new MobileTelephone();
		SIM sim = new SIMOfChinaMobile();
		sim.setNumber("13889656432");
		telephone.useSIM(sim);
		telephone.shoeMess();
		sim=new SIMOfChinaUnicom();
		sim.setNumber("13097656437");
		telephone.useSIM(sim);
		telephone.shoeMess();
	}

}
