public class Example5_3 {
  public static void main(String args[]) {
     CheapGoods cheapGoods=new CheapGoods();
     //cheapGoods.weight=198.98; �ǷǷ��ģ���Ϊ��������weight�Ѿ���int��
     cheapGoods.newSetWeight(198);
     System.out.println("����cheapGoods��weight��ֵ��:"+cheapGoods.weight);
     System.out.println("cheapGoods�������������Żݷ�������۸�"+
                         cheapGoods.newGetPrice());
     cheapGoods.oldSetWeight(198.987); //���������ü̳еķ����������ص�double�ͱ���weight
     System.out.println("cheapGoodsʹ�ü̳еķ��������Żݣ�����۸�"+
                          cheapGoods.oldGetPrice());
  }  
}
