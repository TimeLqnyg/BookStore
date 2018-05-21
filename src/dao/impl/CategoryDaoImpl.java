package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.CategoryDao;
import domain.Category;
import utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public void add(Category category) {

		try {
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into category(id,name,description) values(?,?,?)";
			Object params[]={category.getId(),category.getName(),category.getDescription()};
			//这个方法能自己处理close 但要先获通过dataSource获取到Connection
			runner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Category find(String id) {
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from category where id=?";
			return (Category)runner.query(sql, id,new BeanHandler(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> getAll() {
		try {
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from category";
			return (List<Category>)runner.query(sql, new BeanListHandler(Category.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void delete(String category_id) {
		try {
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from book where category_id=?";
			runner.update(sql, category_id);
			sql="delete from category where id=?";
			runner.update(sql, category_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Category category) {
		try {
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="update category set name=?,description=? where id=?";
			Object params[]={category.getName(),category.getDescription(),category.getId()};
			runner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
