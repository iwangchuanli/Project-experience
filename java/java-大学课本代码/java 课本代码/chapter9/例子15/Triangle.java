public class Triangle {
   double sideA,sideB,sideC,area;
   boolean isTriange;
   public String getArea() {
     if(isTriange) {
        double p=(sideA+sideB+sideC)/2.0;
        area=Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC)) ;
        return String.valueOf(area);
     }
     else { 
        return "�޷��������";
     }
   } 
   public void setA(double a) {
     sideA=a;
     if(sideA+sideB>sideC&&sideA+sideC>sideB&&sideC+sideB>sideA) 
       isTriange=true;
     else 
       isTriange=false;
   }
   public void setB(double b) {
     sideB=b;
     if(sideA+sideB>sideC&&sideA+sideC>sideB&&sideC+sideB>sideA) 
       isTriange=true;
     else 
       isTriange=false;
   }
   public void setC(double c) {
     sideC=c;
     if(sideA+sideB>sideC&&sideA+sideC>sideB&&sideC+sideB>sideA) 
       isTriange=true;
     else 
       isTriange=false;
   }
}
