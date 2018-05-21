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
import utils.WebUtils;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/manager/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method.equals("add")){
			add(request,response);
		}else if (method.equals("listall")){
			listAll(request,response);
		}
	}
	
	private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceImpl service=new BusinessServiceImpl();
		List<Category> CategoryList=service.getAllCategory();
		request.setAttribute("categories", CategoryList);
		request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name=request.getParameter("name");
			String description =request.getParameter("description");
			
			Category category=new Category();
			category.setName(name);
			category.setDescription(description);
			category.setId(WebUtils.makeID());
			
			BusinessServiceImpl service=new BusinessServiceImpl();
			service.addCategory(category);
			request.setAttribute("message", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
