package domain;
//����ǹ��ﳵ�еĵ�Ʒ����
public class CartItem {
	private Book book;
	private int quantity;//����
	private double price;//�ܼ۸�
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
		return this.book.getPrice()*this.quantity;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
