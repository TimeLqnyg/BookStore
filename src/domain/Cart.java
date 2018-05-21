package domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//购物车
public class Cart {
	private Map<String,CartItem> map=new HashMap<String,CartItem>();//<book.id,CartItem>
	private double price;
	
	//添加同种书 的数量 每次加一本
	public void add(Book book){
		CartItem item=map.get(book.getId());
		if(item==null){
			item=new CartItem();
			item.setBook(book);
			item.setQuantity(1);
			map.put(book.getId(),item);
		}
		else{
			item.setQuantity(item.getQuantity()+1);
		}
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public double getPrice() {
		
		double totalprice=0;
		Set<Map.Entry<String, CartItem>> entrySet=map.entrySet();
		Iterator<Map.Entry<String, CartItem>> it=entrySet.iterator();
		while(it.hasNext()){
			Map.Entry<String, CartItem> me=it.next();
			CartItem cartItem=me.getValue();
			totalprice+=cartItem.getPrice();
		}
		this.price=totalprice;
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
