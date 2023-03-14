package homework;

public class prjMain04 {

	public static void main(String[] args) {
		Book book1 = new Book("1","book1",100);
		Book book2 = new Book("2","book2",300);
		Book book3 = new Book("3","book3",268);
		
		double maxPrice = book1.getPrice();
		int index = 1;
		
		if (maxPrice < book2.getPrice()) {
			maxPrice = book2.getPrice();
			index = 2;
		}
		if (maxPrice < book3.getPrice()) {
			maxPrice = book3.getPrice();
			index = 3;
		}
		System.out.println("第"+index+"本书价格高，最高价格为："+maxPrice);
	}
}
