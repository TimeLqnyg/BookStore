package dao;

import java.util.List;

import domain.Book;

//都是和书有关的
public interface BookDao {

	void add(Book book);

	Book find(String id);

	public List<Book> getPageData(int startindex, int pagesize);

	public int getTotalRecord();

	public List<Book> getPageData(int startindex, int pagesize, String category_id);

	public int getTotalRecord(String category_id);

	public Book getBook(String bookname);
}
