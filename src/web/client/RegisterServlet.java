package web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/client/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String phone=request.getParameter("phone");
			String cellphone=request.getParameter("cellphone");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			
			User user=new User();
			user.setId(WebUtils.makeID());
			user.setUsername(username);
			user.setPassword(password);
			user.setPhone(phone);
			user.setCellphone(cellphone);
			user.setEmail(email);
			user.setAddress(address);
			
			BusinessServiceImpl service=new  BusinessServiceImpl();
			service.registerUser(user);
			request.setAttribute("message", "×¢²á³É¹¦");
		} catch (Exception e) {
			request.setAttribute("message", "×¢²áÊ§°Ü");
			e.printStackTrace();
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
