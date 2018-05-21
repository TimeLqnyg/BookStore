package service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import dao.BookDao;
import dao.CategoryDao;
import dao.OrderDao;
import dao.UserDao;
import domain.Book;
import domain.Cart;
import domain.CartItem;
import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.Page;
import domain.User;
import service.BusinessService;
import utils.DaoFactory;
import utils.WebUtils;

public class BusinessServiceImpl implements BusinessService {
	/*
	 * 用工厂来实现dao的对象
	 */
	private CategoryDao categoryDao = DaoFactory.getInstance().createDao("dao.impl.CategoryDaoImpl", CategoryDao.class);
	private BookDao bookDao = DaoFactory.getInstance().createDao("dao.impl.BookDaoImpl", BookDao.class);
	private UserDao userDao = DaoFactory.getInstance().createDao("dao.impl.UserDaoImpl", UserDao.class);
	private OrderDao orderDao = DaoFactory.getInstance().createDao("dao.impl.OrderDaoImpl", OrderDao.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.BusinessService#addCategory(domain.Category)
	 */
	@Override
	public void addCategory(Category category) {
		categoryDao.add(category);
	}

	@Override
	public Category findCategory(String id) {
		return categoryDao.find(id);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.getAll();
	}

	// 添加书
	public void addBook(Book book) {
		bookDao.add(book);
	}

	// 获得书
	public Book findBook(String id) {
		return bookDao.find(id);
	}

	// 获得分页数据
	// 这个是所有的书
	/**
	 * 
	 * @Description: TODO
	 * @param @param
	 *            pagenum
	 * @param @return
	 * @return Page
	 * @throws @author
	 *             zjhIM
	 * @date 2017年9月16日
	 */
	public Page getBookPageData(String pagenum) {
		int totalrecord = bookDao.getTotalRecord();
		Page page = null;
		if (pagenum == null) {
			page = new Page(1, totalrecord);// 如果pagenum=null 那么获取所有的书
											// 且当前pagenum=1
		} else {
			page = new Page(Integer.parseInt(pagenum), totalrecord);
		}
		List<Book> list = bookDao.getPageData(page.getStartindex(), page.getPagesize());
		page.setList(list);
		return page;
	}

	// 这个是某种书的所有书
	public Page getBookPageData(String pagenum, String category_id) {
		int totalrecord = bookDao.getTotalRecord(category_id);
		Page page = null;
		if (pagenum == null) {
			page = new Page(1, totalrecord);
		} else {
			page = new Page(Integer.parseInt(pagenum), totalrecord);
		}
		List<Book> list = bookDao.getPageData(page.getStartindex(), page.getPagesize(), category_id);
		page.setList(list);
		return page;
	}

	public void buyBook(Cart cart, Book book) {
		cart.add(book);
	}

	// 注册用户
	public void registerUser(User user) {
		userDao.add(user);
	}

	public User findUser(String id) {
		return userDao.find(id);
	}

	public User userLogin(String username, String password) {
		return userDao.find(username, password);
	}

	// 生成订单
	public void createOrder(Cart cart, User user) {
		if (cart == null) {
			throw new RuntimeException("对不起，您还没有购买任何商品");
		}
		Order order = new Order();
		order.setId(WebUtils.makeID());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		for (Map.Entry<String, CartItem> me : cart.getMap().entrySet()) {
			// 得到一个购物项就生成一个订单项
			CartItem citem = me.getValue();
			OrderItem oitem = new OrderItem();
			oitem.setBook(citem.getBook());
			oitem.setPrice(citem.getPrice());
			oitem.setId(WebUtils.makeID());
			oitem.setQuantity(citem.getQuantity());
			order.getOrderitems().add(oitem);
		}
		orderDao.add(order);

	}

	// 后台获取所有订单信息
	public List<Order> getAll(String state) {// 这里为什么要用String类型
		return orderDao.getAll(Boolean.parseBoolean(state));
	}

	// 列出订单明细
	public Order findOrder(String orderid) {
		return orderDao.find(orderid);
	}

	// 把订单置为发货状态
	public void confirmOrder(String orderid) {
		Order order = orderDao.find(orderid);
		order.setState(true);
		orderDao.update(order);
	}

	// 获取某个用户的订单信息
	public List<Order> listOrder(String state, String userid) {
		return orderDao.getAll(Boolean.parseBoolean(state), userid);
	}

	// 获取某个用户的订单信息
	public List<Order> clientListOrder(String userid) {
		return orderDao.getAllOrder(userid);
	}

	// 删除某个分类
	public void deleteCategory(String category_id) {
		categoryDao.delete(category_id);
	}

	// 修改某个分类
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}

	// 删除订单
	public void deleteOrder(String order_id) {
		orderDao.delete(order_id);
	}

	public Book getBook(String bookname) {
		return bookDao.getBook(bookname);
	}

}