package domain;
/*
 * 在数据库添加订单时(dao.impl.OrderDao#add(domain.Order)) 添加的属性是id,quantity,price,order_id,book_id 后两个是数据库字段
 * book属性是在dao.impl.OrderDao#find(java.lang.String) 方法中添加的
 */
public class OrderItem {

	private String id;
	private Book book;
	private int quantity;
	private double price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
