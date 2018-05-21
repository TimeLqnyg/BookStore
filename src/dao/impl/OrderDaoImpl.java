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

	// ��̫���
	@Override
	public void add(Order order) {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			// 1,��order�Ļ�����Ϣ������order����
			String sql = "insert into orders(id, ordertime,price,state,user_id) values (?,?,?,?,?)";
			Object params[] = { order.getId(), order.getOrdertime(), order.getPrice(), order.isState(),
					order.getUser().getId() };
			runner.update(sql, params);
			// 2, ��order�� �Ķ�����浽 orderitem ����
			// ��order����orderitem��ɵ� orderitem �����û���������
			Set<OrderItem> set = order.getOrderitems();// �������ó����� ����֮ǰװ��ȥ
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
			// 1,�ҳ������Ļ�����Ϣ
			String sql = "select * from orders where id=?";
			Order order = (Order) runner.query(sql, id, new BeanHandler(Order.class));
			// �������Ϊ���ҵ��㵥�������ĸ��û� set User�������
			// 2,�ҳ����������еĶ�����
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = runner.query(sql, id, new BeanListHandler(OrderItem.class));
			for (OrderItem item : list) {
				sql = "select book.* from orderitem,book where orderitem.id=? and orderitem.book_id=book.id";// book.*
																												// ��ѯ�������Ϊbook���е�������
				Book book = (Book) runner.query(sql, id, new BeanHandler(Book.class));

				item.setBook(book);
			}
			// ���Set���� ����������ӵ�order��
			order.getOrderitems().addAll(list);
			// 3,
			sql = "select user.* from orders,user where orders.id=?  and orders.user_id=user.id";
			User user = (User) runner.query(sql, order.getId(), new BeanHandler(User.class));// ����order.getId()=id
			order.setUser(user);
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	// ��̨��ȡ���ж���
	public List<Order> getAll(boolean state) {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=?";
			List<Order> list = (List<Order>) runner.query(sql, state, new BeanListHandler(Order.class));
			// �ҳ���ǰ���������ĸ��û�
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
		// ����ֻ�ı䷢��״̬ ��ʵ���ϻ����Ըı乺��������������Ϣ
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
	// ǰ��ҳ���л�ȡĳ���û������ж���
	public List<Order> getAll(boolean state, String userid) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=? and orders.user_id=?";
			Object params[] = { state, userid };
			List<Order> list = (List<Order>) runner.query(sql, params, new BeanListHandler(Order.class));
			// ��list������order��user�ӽ�ȥ
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
			// ��list������order��user�ӽ�ȥ
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

	// ɾ������
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
