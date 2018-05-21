package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.BookDao;
import domain.Book;
import utils.JdbcUtils;

public class BookDaoImpl implements BookDao {

	@Override
	public void add(Book book) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into book(id,name,author,price,image,description,category_id) values(?,?,?,?,?,?,?)";
			Object parems[] = { book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getImage(),
					book.getDescription(), book.getCategory_id() };
			runner.update(sql, parems);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Book find(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where id=?";
			return (Book) runner.query(sql, id, new BeanHandler(Book.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Book> getPageData(int startindex, int pagesize) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book limit ?,?";
			Object params[] = { startindex, pagesize };
			return (List<Book>) runner.query(sql, params, new BeanListHandler(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 获取book 的行数
	@Override
	public int getTotalRecord() {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from book";
			long totalrecord = (Long) runner.query(sql, new ScalarHandler());// ScalarHandler:
																				// 可以返回指定列的一个值或返回一个统计函数的值.
			return (int) totalrecord;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 获取某种书的所有书本
	@Override
	public List<Book> getPageData(int startindex, int pagesize, String category_id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where category_id=? limit ?,?";
			Object params[] = { category_id, startindex, pagesize };
			return (List<Book>) runner.query(sql, params, new BeanListHandler(Book.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 获取某种书的总数量
	@Override
	public int getTotalRecord(String category_id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from book where category_id=?";
			long totalrecord = (Long) runner.query(sql, category_id, new ScalarHandler());
			return (int) totalrecord;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Book getBook(String bookname) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where name=?";
			return (Book) runner.query(sql, bookname, new BeanHandler(Book.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
