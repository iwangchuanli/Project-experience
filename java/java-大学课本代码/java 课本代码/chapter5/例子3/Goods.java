public class Goods {
    public double weight;
    public void oldSetWeight(double w) {
       weight=w;
       System.out.println("double�͵�weight="+weight);
    }
    public double oldGetPrice() {
        double price = weight*10;
        return price;
    }
}
