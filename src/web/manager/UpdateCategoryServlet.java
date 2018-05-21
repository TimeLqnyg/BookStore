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
 * Servlet implementation class UpdateCategoryServlet
 */
@WebServlet("/manager/UpdateCategoryServlet")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method.equals("getCategory")){
			getCategory(request,response);
		}else if(method.equals("updateCategory")){
			updateCategory(request,response);
		}
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String category_id=request.getParameter("category_id");
			String category_name=request.getParameter("category_name");
			String category_description=request.getParameter("category_description");
			BusinessServiceImpl service=new BusinessServiceImpl();
			Category category=service.findCategory(category_id);
			category.setDescription(category_description);
			category.setName(category_name);
			service.updateCategory(category);
			List<Category> CategoryList=service.getAllCategory();
			request.setAttribute("categories", CategoryList);
			request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ÐÞ¸ÄÊ§°Ü");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	private void getCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category_id=request.getParameter("category_id");
		BusinessServiceImpl service=new BusinessServiceImpl();
		Category category=service.findCategory(category_id);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/manager/updateCategory.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
