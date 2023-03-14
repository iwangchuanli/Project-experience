public class Example5_3 {
  public static void main(String args[]) {
     CheapGoods cheapGoods=new CheapGoods();
     //cheapGoods.weight=198.98; 是非法的，因为子类对象的weight已经是int型
     cheapGoods.newSetWeight(198);
     System.out.println("对象cheapGoods的weight的值是:"+cheapGoods.weight);
     System.out.println("cheapGoods用子类新增的优惠方法计算价格："+
                         cheapGoods.newGetPrice());
     cheapGoods.oldSetWeight(198.987); //子类对象调用继承的方法操作隐藏的double型变量weight
     System.out.println("cheapGoods使用继承的方法（无优惠）计算价格："+
                          cheapGoods.oldGetPrice());
  }  
}
