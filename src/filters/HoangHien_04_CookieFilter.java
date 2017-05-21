package filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.HoangHien_04_Admin;
import utils.HoangHien_04_MyUtils;
import utils.HoangHien_04_DBUtils;

//Lớp này lọc cookie từ lớp MyUtils đã lưu
//Sẽ cho phép tự động login nếu người dùng đã đăng nhập từ trước
//Mọi URL đều phải đi qua filter này
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class HoangHien_04_CookieFilter implements Filter {

	
	public HoangHien_04_CookieFilter() {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	           throws IOException, ServletException {
	       HttpServletRequest req = (HttpServletRequest) request;
	       HttpSession session = req.getSession();
	 
	       HoangHien_04_Admin userInSession = HoangHien_04_MyUtils.HoangHien_04_getLoginedUser(session);
	       // Đang login.
	       if (userInSession != null) {
	           session.setAttribute("COOKIE_CHECKED", "CHECKED");
	           chain.doFilter(request, response);
	           return;
	       }
	 
	       // Đã được tạo trong JDBCFilter.
	       Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
	 
	       // Có cần kiểm tra Cookie ko?
	       String checked = (String) session.getAttribute("COOKIE_CHECKED");
	       if (checked == null && conn != null) {
	           String username = HoangHien_04_MyUtils.HoangHien_04_getUserNameInCookie(req);
	           try {
	               HoangHien_04_Admin user = HoangHien_04_DBUtils.HoangHien_04_findUser(conn, username);
	               HoangHien_04_MyUtils.HoangHien_04_storeLoginedUser(session, user);
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	           // Đã kiểm tra cookie
	           session.setAttribute("COOKIE_CHECKED", "CHECKED");
	       }
	 
	       chain.doFilter(request, response);
	   }

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
