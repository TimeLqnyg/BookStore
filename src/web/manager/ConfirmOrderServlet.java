package web.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessServiceImpl;

/**
 * Servlet implementation class ConfirmOrderServlet
 */
@WebServlet("/manager/ConfirmOrderServlet")
public class ConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String orderid=request.getParameter("orderid");
			BusinessServiceImpl service=new BusinessServiceImpl();
			service.confirmOrder(orderid);
			request.setAttribute("message", "��������Ϊ����״̬���뼰ʱ����");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ȷ��ʧ��");
		}
		request.getRequestDispatcher("/message").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
