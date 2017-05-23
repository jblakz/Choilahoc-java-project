package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


//Filter này chỉ chuyển Character Encoding
//của request thành chuẩn UTF-8
@WebFilter(filterName = "encodingFilter", urlPatterns = { "/*" })
public class HoangHien_04_EncodingFilter implements Filter {
	
	public HoangHien_04_EncodingFilter() {
	  }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	          throws IOException, ServletException {
	      request.setCharacterEncoding("UTF-8");
	      
	      chain.doFilter(request, response);
	  }

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
