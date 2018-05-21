package filter;

import java.io.IOException;
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
 * Servlet Filter implementation class HtmlFilter
 */
//@WebFilter("/HtmlFilter")
public class HtmlFilter implements Filter {

    /**
     * Default constructor. 
     */
    public HtmlFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		chain.doFilter(new MyRequest2(request), response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

class MyRequest2 extends HttpServletRequestWrapper{
	
	HttpServletRequest request;
	public MyRequest2(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	public String getParameter(String name){
		String value=this.request.getParameter(name);
		if(value==null){
			return null;
		}else{
			return filter(value);
		}
	}

	private String filter(String message) {
		if(message==null){
			return null;
		}else{
			char content[] = new char[message.length()];
			message.getChars(0, message.length(), content, 0);
			StringBuilder result=new StringBuilder(content.length+50);
			for(int i=0;i<content.length;i++){
				switch(content[i]){
				case '<':
					result.append("&lt;");
					break;
				case '>':
					result.append("&gt;");
					break;
				case '&':
					result.append("&amp;");
					break;
				case '"':
					result.append("&quot");
					break;
				default:
					result.append(content[i]);
				}
			}
			return (result.toString());
		}
	}
	
}
