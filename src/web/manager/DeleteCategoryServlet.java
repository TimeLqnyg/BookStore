package web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import service.impl.BusinessServiceImpl;

/**
 * Servlet implementation class DeleteCategoryServlet
 */
@WebServlet("/manager/DeleteCategoryServlet")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String category_id=request.getParameter("category_id");
			BusinessServiceImpl service=new BusinessServiceImpl();
			service.deleteCategory(category_id);
			List<Category> CategoryList=service.getAllCategory();
			request.setAttribute("categories", CategoryList);
			request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "É¾³ýÊ§°Ü");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
