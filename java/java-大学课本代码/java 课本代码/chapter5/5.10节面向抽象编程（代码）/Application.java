public class Application{
    public static void main(String args[]){
        Pillar pillar;
        Geometry bottom;
        bottom=new Rectangle(12,22);
        pillar =new Pillar (bottom,58);  //pillar�Ǿ��о��ε׵�����
        System.out.println("���ε׵���������"+pillar.getVolume());
        bottom=new Circle(10);
        pillar =new Pillar (bottom,58); //pillar�Ǿ���Բ�ε׵�����
        System.out.println("Բ�ε׵���������"+pillar.getVolume());
    }
} 
