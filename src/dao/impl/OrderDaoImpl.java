package dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.OrderDao;
import domain.Book;
import domain.Order;
import domain.OrderItem;
import domain.User;
import utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {

	// 不太清楚
	@Override
	public void add(Order order) {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			// 1,把order的基本信息保存在order表中
			String sql = "insert into orders(id, ordertime,price,state,user_id) values (?,?,?,?,?)";
			Object params[] = { order.getId(), order.getOrdertime(), order.getPrice(), order.isState(),
					order.getUser().getId() };
			runner.update(sql, params);
			// 2, 把order中 的订单项保存到 orderitem 表中
			// 在order是有orderitem组成的 orderitem 才是用户订购的书
			Set<OrderItem> set = order.getOrderitems();// 这里是拿出来用 会在之前装进去
			for (OrderItem item : set) {
				sql = "insert into orderitem(id,quantity,price,order_id,book_id) values(?,?,?,?,?)";
				params = new Object[] { item.getId(), item.getQuantity(), item.getPrice(), order.getId(),
						item.getBook().getId() };
				runner.update(sql, params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order find(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			// 1,找出订单的基本信息
			String sql = "select * from orders where id=?";
			Order order = (Order) runner.query(sql, id, new BeanHandler(Order.class));
			// 下面的是为了找到点单的属于哪个用户 set User这个属性
			// 2,找出订单中所有的订单项
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = runner.query(sql, id, new BeanListHandler(OrderItem.class));
			for (OrderItem item : list) {
				sql = "select book.* from orderitem,book where orderitem.id=? and orderitem.book_id=book.id";// book.*
																												// 查询结果的列为book表中的所有列
				Book book = (Book) runner.query(sql, id, new BeanHandler(Book.class));

				item.setBook(book);
			}
			// 获得Set集合 将订单项添加到order中
			order.getOrderitems().addAll(list);
			// 3,
			sql = "select user.* from orders,user where orders.id=?  and orders.user_id=user.id";
			User user = (User) runner.query(sql, order.getId(), new BeanHandler(User.class));// 这里order.getId()=id
			order.setUser(user);
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	// 后台获取所有订单
	public List<Order> getAll(boolean state) {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=?";
			List<Order> list = (List<Order>) runner.query(sql, state, new BeanListHandler(Order.class));
			// 找出当前订单属于哪个用户
			for (Order order : list) {
				sql = "select user.* from orders,user where orders.id=? and orders.user_id=user.id";
				User user = (User) runner.query(sql, order.getId(), new BeanHandler(User.class));
				order.setUser(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override

	public void update(Order order) {
		// 这里只改变发货状态 ，实际上还可以改变购买数量等其他信息
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update orders set state=? where id=?";
			Object params[] = { order.isState(), order.getId() };
			runner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	// 前端页面中获取某个用户的所有订单
	public List<Order> getAll(boolean state, String userid) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=? and orders.user_id=?";
			Object params[] = { state, userid };
			List<Order> list = (List<Order>) runner.query(sql, params, new BeanListHandler(Order.class));
			// 将list中所有order的user加进去
			for (Order order : list) {
				sql = "select * from user where id=?";
				User user = (User) runner.query(sql, userid, new BeanHandler(User.class));
				order.setUser(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> getAllOrder(String userid) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where orders.user_id=?";
			List<Order> list = (List<Order>) runner.query(sql, userid, new BeanListHandler(Order.class));
			// 将list中所有order的user加进去
			for (Order order : list) {
				sql = "select * from user where id=?";
				User user = (User) runner.query(sql, userid, new BeanHandler(User.class));
				order.setUser(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 删除订单
	@Override
	public void delete(String order_id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "DELETE from orderitem where order_id=?";
			runner.update(sql, order_id);
			sql = "DELETE from orders where id=?";
			runner.update(sql, order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
