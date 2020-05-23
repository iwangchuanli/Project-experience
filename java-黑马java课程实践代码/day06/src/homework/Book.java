package homework;

public class Book {

	private String bookId;
	private String name;
	private double price;
	public Book() {
		// TODO Auto-generated constructor stub
	}
	public Book(String bookId,String name,double price) {
		// TODO Auto-generated constructor stub
		this.bookId = bookId;
		this.name = name;
		this.price = price;
	}
	private void setter(String bookId,String name,double price) {
		// TODO Auto-generated method stub
		this.bookId = bookId;
		this.name = name;
		this.price = price;
	}
	private void getter() {
		// TODO Auto-generated method stub

	}
	public double getPrice(){
		return price;
	}
}
