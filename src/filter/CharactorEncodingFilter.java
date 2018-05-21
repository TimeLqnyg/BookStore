package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CharactorEncodingFilter
 */

public class CharactorEncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CharactorEncodingFilter() {
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response =(HttpServletResponse) resp;
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		chain.doFilter(new MyRequest(request), response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
}

class MyRequest extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	@Override
	public String getParameter(String name) {
		String value=request.getParameter(name);
//		String[] dirtyData=null;
//		//【如何value中出现dirtyData中数据，用****替换】  
//		for (String data : dirtyData) {
//			// 判断当前输入数据(value), 是否包含无效数据
//			if (value.contains(data)){
//				value = value.replace(data, "*****");
//			}
//		}
		if(value==null)
			return null;
		//如果使用get提交的话是用ISO8859-1解码的  所以要转码
		//post方法是根据表单设置的编码方式的 所以不用转码
		if(!this.request.getMethod().equalsIgnoreCase("get")){
			return value;
		}
		try {
			value=new String(value.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
}
