package web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import domain.Cart;
import service.impl.BusinessServiceImpl;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/client/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String bookid=request.getParameter("bookid");
			BusinessServiceImpl service =new BusinessServiceImpl();
			Book book=service.findBook(bookid);
			Cart cart=(Cart)request.getSession().getAttribute("cart");//测当前时候是否有session存在，如果不存在则创建一个，如果存在就返回当前的。
			if(cart==null){
				cart=new Cart();
				request.getSession().setAttribute("cart", cart);
			}
			service.buyBook(cart, book);
			request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "购买失败");
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
