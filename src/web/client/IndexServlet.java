package web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import domain.Category;
import domain.Page;
import service.impl.BusinessServiceImpl;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/client/IndexServlet") // 待会儿改
public class IndexServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		// String.equalsIgnoreCase忽略大小写比较是否相等
		if (method.equalsIgnoreCase("getAll")) {
			getAll(request, response);
		} else if (method.equalsIgnoreCase("listBookWithCategory")) {
			listBookWithCategory(request, response);
		}
	}

	private void listBookWithCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessServiceImpl service = new BusinessServiceImpl();
		String category_id = request.getParameter("category_id");
		// System.out.println(category_id);
		List<Category> categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		String pagenum = request.getParameter("pagenum");
		// System.out.println("hello");
		Page page = service.getBookPageData(pagenum, category_id);
		List<Book> list = page.getList();
		// for (Book book : list) {
		// System.out.println(book.getName());
		// }
		request.setAttribute("page", page);

		request.getRequestDispatcher("/client/body.jsp").forward(request, response);

	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Category> categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		String pagenum = request.getParameter("pagenum");
		Page page = service.getBookPageData(pagenum);
		request.setAttribute("page", page);

		request.getRequestDispatcher("/client/body.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
