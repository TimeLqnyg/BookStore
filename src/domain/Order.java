package domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/*
 * 在数据库中添加订单时(dao.impl.OrderDao#add(domain.Order)) 添加的属性是 id,ordertime,price,state,user_id  最后一个是数据库字段  
 * user和orderitems 属性是在dao.impl.OrderDao#find(java.lang.String)方法中实现的
 * 
 */
public class Order {
	
	private String id;
	private Date ordertime;
	private double price;
	private boolean state;//状态
	private User user;//记住订单属于哪个用户   user_id
	private Set<OrderItem> orderitems =new HashSet<OrderItem>();//用来保存订单项的集合
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(Set<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
	
	
}
