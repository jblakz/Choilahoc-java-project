package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.HoangHien_04_DBUtils;
import utils.HoangHien_04_MyUtils;

/**
 * Servlet implementation class HoangHien_04_DoDeleteActivityServlet
 */
@WebServlet(urlPatterns = { "/delete" })
public class HoangHien_04_DoDeleteActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_DoDeleteActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
		int idActivities = Integer.parseInt((String)request.getParameter("idActivities"));
		String errorString = null;
		//Chuyển request sang module khác để kiểm tra đăng nhập
		request = HoangHien_04_MyUtils.HoangHien_04_checkLogined(request);
		if (request.getAttribute("user")==null)
		{
			//Nếu chưa đăng nhập chuyển về trang login và báo lỗi
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		try {
			HoangHien_04_DBUtils.HoangHien_04_deleteActivity(conn, idActivities);
	      } catch (SQLException e) {
	          e.printStackTrace();
	          errorString = e.getMessage();
	      }
		// Nếu có lỗi forward sang trang quản lý và báo lỗi.
	      if (errorString != null) {
	          // Lưu thông tin vào request attribute trước khi forward sang views.
	    	  request.getSession().setAttribute("errorString", errorString);
	    	  request.getSession().removeAttribute("infoString");
	          //
	    	  response.sendRedirect(request.getContextPath() + "/manage");
	      }
	      // Nếu mọi thứ tốt đẹp.
	      // Redirect sang trang danh sách sản phẩm.
	      else {
	    	  String infoString = "Bài đăng có ID '"+idActivities+"' được xóa thành công!";
	    	  request.getSession().setAttribute("infoString", infoString);
	    	  request.getSession().removeAttribute("errorString");
	    	  response.sendRedirect(request.getContextPath() + "/manage");
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
