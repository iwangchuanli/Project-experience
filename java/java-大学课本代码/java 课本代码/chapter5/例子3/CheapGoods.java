public class CheapGoods extends Goods {
    public int weight;
    public void newSetWeight(int w) {
       weight=w;
       System.out.println("intÐÍµÄweight="+weight);
    }
    public double newGetPrice() {
        double price = weight*10;
        return price;
    }
}