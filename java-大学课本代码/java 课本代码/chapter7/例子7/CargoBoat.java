public class CargoBoat {
     int realContent;  //装载的重量
     int maxContent;   //最大装载量
     public void setMaxContent(int c) {
         maxContent = c;
     }
     public void loading(int m) throws DangerException {
       realContent += m;
       if(realContent>maxContent) {
          throw new DangerException(); 
       }
       System.out.println("目前装载了"+realContent+"吨货物");
     }
}
