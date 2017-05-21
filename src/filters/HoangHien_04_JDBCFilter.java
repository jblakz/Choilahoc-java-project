package filters;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import conn.HoangHien_04_ConnectionUtils;
import utils.HoangHien_04_MyUtils;

//Filter này sẽ lọc đường dẫn
// nếu dẫn tới servlet, jsp thì tạo mới Connection, còn ko thì ko cần mở
//Mọi URL đều đi qua filter này
@WebFilter(filterName="jdbcFilter", urlPatterns = { "/*" })
public class HoangHien_04_JDBCFilter implements Filter {
	public HoangHien_04_JDBCFilter() {
	   }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		 
	       //
	       // Chỉ mở kết nối đối với các request có đường dẫn đặc biệt cần
	       // connection. (Chẳng hạn đường dẫn tới các servlet, jsp, ..)
	       //
	       // Tránh tình trạng mở connection với các yêu cầu thông thường
	       // (chẳng hạn image, css, javascript,... )
	       //
	       if (this.HoangHien_04_needJDBC(req)) {
	            
	           System.out.println("Open Connection for: " + req.getServletPath());
	            
	           Connection conn = null;
	           try {
	               // Tạo đối tượng Connection kết nối database.
	               conn = HoangHien_04_ConnectionUtils.HoangHien_04_getConnection();
	               // Sét tự động commit false, để chủ động điều khiển.
	               conn.setAutoCommit(false);
	 
	               // Lưu trữ vào attribute của request.
	               HoangHien_04_MyUtils.HoangHien_04_storeConnection(request, conn);
	 
	               // Cho phép request đi tiếp.
	               chain.doFilter(request, response);
	 
	               // Gọi commit() để commit giao dịch với DB.
	               conn.commit();
	           } catch (Exception e) {
	               e.printStackTrace();
	               HoangHien_04_ConnectionUtils.HoangHien_04_rollbackQuietly(conn);
	               throw new ServletException();
	           } finally {
	               HoangHien_04_ConnectionUtils.HoangHien_04_closeQuietly(conn);
	           }
	       }
	       // Với các request thông thường (image,css,html,..)
	       // không cần mở connection, cho tiếp tục.
	       else {
	           // Cho phép request đi tiếp.
	           chain.doFilter(request, response);
	       }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	// Kiểm tra xem request hiện tại là 1 Servlet?
	   private boolean HoangHien_04_needJDBC(HttpServletRequest request) {  
	       System.out.println("JDBC Filter");
	       //
	       // Servlet Url-pattern: /spath/abc/mnp?p1=1
	       //
	       // servletPath = spath/
	       String servletPath = request.getServletPath();
	       // pathInfo = /abc/mnp
	       String pathInfo = request.getPathInfo();
	       
	       String urlPattern = servletPath;
	 
	       if (pathInfo != null) {
	           // => /spath/*
	           urlPattern = servletPath + "/*";
	       }
	        
	       // Key: servletName.
	       // Value: ServletRegistration
	       Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
	               .getServletRegistrations();  
	 
	       // Tập hợp tất cả các Servlet trong project.
	       Collection<? extends ServletRegistration> values = servletRegistrations.values();
	       for (ServletRegistration sr : values) {
	           Collection<String> mappings = sr.getMappings();
	           if (mappings.contains(urlPattern)) {
	               return true;
	           }
	       }
	       return false;
	   }
}
