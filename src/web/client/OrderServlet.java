package web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cart;
import domain.User;
import service.impl.BusinessServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/client/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				request.setAttribute("message", "��û��¼,���ȵ�¼");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} else {
				BusinessServiceImpl service = new BusinessServiceImpl();
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				service.createOrder(cart, user);
				request.getSession().removeAttribute("cart");
				request.setAttribute("message", "�������ɳɹ�");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "��������ʧ��");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
