package domain;
/*
 * �����ݿ���Ӷ���ʱ(dao.impl.OrderDao#add(domain.Order)) ��ӵ�������id,quantity,price,order_id,book_id �����������ݿ��ֶ�
 * book��������dao.impl.OrderDao#find(java.lang.String) ��������ӵ�
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
