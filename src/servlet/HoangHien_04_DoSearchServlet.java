package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.HoangHien_04_Activity;
import utils.HoangHien_04_DBUtils;
import utils.HoangHien_04_MyUtils;


/**
 * Servlet implementation class HoangHien_04_DoSearchServlet
 */
@WebServlet(urlPatterns = { "/search" })
public class HoangHien_04_DoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_DoSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
		String searchString = (String) request.getParameter("searchString");
		HoangHien_04_Activity featured = (HoangHien_04_Activity) request.getSession().getAttribute("featured");
		List<HoangHien_04_Activity>  list = null;
		int numberOfPages = 0;
		int idFeatured = 16;
		String errorString = null;
		
		//Thực hiện lệnh DBUtils tìm kiếm hoạt động
		try {
			list = HoangHien_04_DBUtils.HoangHien_04_searchActivity(conn, searchString);
			if (featured == null){
				featured = HoangHien_04_DBUtils.HoangHien_04_findActivity(conn, idFeatured);
			}
	      } catch (SQLException e) {
	          e.printStackTrace();
	          errorString = e.getMessage();
	      }
		// Nếu có lỗi forward sang trang các bài đăng và báo lỗi.
	      if (errorString != null) {
	          // Lưu thông tin vào request attribute trước khi forward sang views.
	    	  request.getSession().setAttribute("errorString", errorString);
	    	  request.getSession().removeAttribute("infoString");
	    	  request.getSession().setAttribute("featured", featured);
	          //
	    	// Forward sang jsp
		      RequestDispatcher dispatcher = request.getServletContext()
		              .getRequestDispatcher("/WEB-INF/views/HoangHien_04_activitiesListView.jsp");
		      dispatcher.forward(request, response);
	      }
	      //Nếu ko có bài đăng nào
	      if (list == null)
	      {
	    	// Lưu thông tin vào request attribute trước khi forward sang views.
	    	  errorString = "Không có hoạt động nào với từ khóa đã tìm!";
	    	  request.getSession().setAttribute("errorString", errorString);
	    	  request.getSession().setAttribute("featured", featured);
	    	  request.getSession().removeAttribute("infoString");
	    	// Forward sang jsp
		      RequestDispatcher dispatcher = request.getServletContext()
		              .getRequestDispatcher("/WEB-INF/views/HoangHien_04_activitiesListView.jsp");
		      dispatcher.forward(request, response);
	      }
	      if (errorString==null){
	    	  request.getSession().removeAttribute("errorString");
	    	  request.setAttribute("activitiesList", list);
	    	  request.getSession().setAttribute("featured", featured);
	    	  request.setAttribute("numberOfPages", numberOfPages);
	    	// Forward sang jsp
		      RequestDispatcher dispatcher = request.getServletContext()
		              .getRequestDispatcher("/WEB-INF/views/HoangHien_04_activitiesListView.jsp");
		      dispatcher.forward(request, response);
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
