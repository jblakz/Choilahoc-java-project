package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HoangHien_04_UserInfoServlet
 */


import beans.HoangHien_04_Admin;
import utils.HoangHien_04_MyUtils;

@WebServlet(urlPatterns = { "/userInfo" })
public class HoangHien_04_UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      // Kiểm tra người dùng login chưa
		//Chuyển request sang module khác để kiểm tra đăng nhập
		request = HoangHien_04_MyUtils.HoangHien_04_checkLogined(request);
		if (request.getAttribute("user")==null)
		{
			//Nếu chưa đăng nhập chuyển về trang login và báo lỗi
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	      HoangHien_04_Admin loginedUser = (HoangHien_04_Admin)request.getAttribute("user");
	      // Ghi thông tin vào request trước khi forward.
	      request.setAttribute("user", loginedUser);
	 
	      // Đã login rồi thì chuyển tiếp sang /WEB-INF/views/userInfoView.jsp
	      RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/HoangHien_04_userInfoView.jsp");
	      dispatcher.forward(request, response);
	 
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
