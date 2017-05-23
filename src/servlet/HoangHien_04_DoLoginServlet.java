package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HoangHien_04_DoLoginServlet
 */

import javax.servlet.http.HttpSession;

import beans.HoangHien_04_Admin;
import utils.HoangHien_04_DBUtils;
import utils.HoangHien_04_MyUtils;

@WebServlet(urlPatterns = { "/doLogin" })
public class HoangHien_04_DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_DoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username = request.getParameter("username");
	     String password = request.getParameter("password");
	     String rememberMeStr = request.getParameter("rememberMe");
	     boolean remember= "Y".equals(rememberMeStr);
	     
	     HoangHien_04_Admin user = null;
	      boolean hasError = false;
	      String errorString = null;
	      if (username == null || password == null
	               || username.length() == 0 || password.length() == 0) {
	          hasError = true;
	          errorString = "Cần nhập tên đăng nhập và mật khẩu!";
	      } else {
	          Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
	          try {
	              // Tìm user trong DB.
	              user = HoangHien_04_DBUtils.HoangHien_04_findUserWithPass(conn, username, password);
	              
	              if (user == null) {
	                  hasError = true;
	                  errorString = "Sai tên đăng nhập hoặc mật khẩu!";
	              }
	          } catch (SQLException e) {
	              e.printStackTrace();
	              hasError = true;
	              errorString = e.getMessage();
	          }
	      }
	      // Trong trường hợp có lỗi, forward về trang /WEB-INF/views/login.jsp
	      if (hasError) {
	          user = new HoangHien_04_Admin();
	          user.setUsername(username);
	          user.setPassword(password);
	          
	          // Ghi các thông tin vào request trước khi forward.
	          request.getSession().setAttribute("errorString", errorString);
	          request.getSession().removeAttribute("infoString");
	          request.setAttribute("user", user);
	 
	          // Chuyển tiếp tới trang /WEB-INF/views/login.jsp
	          response.sendRedirect(request.getContextPath() + "/login");
	      }
	      // Trường hợp không có lỗi.
	      // Lưu thông tin người dùng vào Session.
	      // Và chuyển hướng sang trang userInfo.
	      else {
	          HttpSession session = request.getSession();
	          HoangHien_04_MyUtils.HoangHien_04_storeLoginedUser(session, user);
	          
	          // Nếu người dùng chọn lưu thông tin đăng nhập vào Cookie
	          if(remember)  {
	        	  HoangHien_04_MyUtils.HoangHien_04_storeUserCookie(response,user);
	          }
	          // Ngược lại xóa Cookie
	          else  {
	        	  HoangHien_04_MyUtils.HoangHien_04_deleteUserCookie(response);
	          }            
	          
	          // Rồi chuyển hướng sang trang /userInfo.
	          request.getSession().removeAttribute("errorString");
	          request.getSession().removeAttribute("infoString");
	          response.sendRedirect(request.getContextPath() + "/userInfo");
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
