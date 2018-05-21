package dao;

import java.util.List;

import domain.Category;
//���Ǻ������йص�
public interface CategoryDao {
	
	public abstract void add(Category category);
	
	public abstract Category find(String id);
	
	public abstract List<Category> getAll();
	
	public void delete(String category_id);
	
	public void update(Category category);
}
