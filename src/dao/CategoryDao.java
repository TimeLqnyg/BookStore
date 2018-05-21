package dao;

import java.util.List;

import domain.Category;
//都是和书类有关的
public interface CategoryDao {
	
	public abstract void add(Category category);
	
	public abstract Category find(String id);
	
	public abstract List<Category> getAll();
	
	public void delete(String category_id);
	
	public void update(Category category);
}
