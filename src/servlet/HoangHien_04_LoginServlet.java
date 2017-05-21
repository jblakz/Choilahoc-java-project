package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.HoangHien_04_MyUtils;

/**
 * Servlet implementation class HoangHien_04_LoginServlet
 */
@WebServlet(urlPatterns = { "/login"})
public class HoangHien_04_LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String infoString = (String)request.getSession().getAttribute("infoString");
		
		// Kiểm tra người dùng login chưa
		//Chuyển request sang module khác để kiểm tra đăng nhập
				request = HoangHien_04_MyUtils.HoangHien_04_checkLogined(request);
			      if (request.getAttribute("user")!=null)
			      {
			    	  //Nếu đã đăng nhập chuyển về trang userInfo
			    	  response.sendRedirect(request.getContextPath()
			    			  + "/userInfo");
			          return;
			      }
			      else{
			    	 request.getSession().removeAttribute("errorString");
			      }
		
		// Forward toi trang /WEB-INF/views/loginView.jsp
	    // (Người dùng không bao giờ truy cập trực tiếp được vào các trang JSP
	    // đặt trong WEB-INF)
	    request.setAttribute("infoString", infoString);
	      
	    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/HoangHien_04_loginView.jsp");
	      
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
