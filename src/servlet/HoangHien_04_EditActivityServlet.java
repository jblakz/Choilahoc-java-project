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
import beans.HoangHien_04_Category;
import utils.HoangHien_04_DBUtils;
import utils.HoangHien_04_MyUtils;

/**
 * Servlet implementation class HoangHien_04_EditActivityServlet
 */
@WebServlet(urlPatterns= {"/edit"})
public class HoangHien_04_EditActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_EditActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
		HoangHien_04_Activity activity = new HoangHien_04_Activity();
		List<HoangHien_04_Category> categoriesList = null;
		String errorString = null;
		int idActivities = Integer.parseInt((String)request.getParameter("idActivities"));
		
		//Chuyển request sang module khác để kiểm tra đăng nhập
		request = HoangHien_04_MyUtils.HoangHien_04_checkLogined(request);
		if (request.getAttribute("user")==null)
		{
			//Nếu chưa đăng nhập chuyển về trang login và báo lỗi
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	    try {
	    	//Lấy các thể loại bài đăng
	    	categoriesList = HoangHien_04_DBUtils.HoangHien_04_queryCategory(conn);
	    	//Lấy dữ liệu bài đăng theo id
	    	activity = HoangHien_04_DBUtils.HoangHien_04_findActivity(conn, idActivities);
	    } catch (SQLException e) {
	          e.printStackTrace();
	          errorString = e.getMessage();
	      }
	    if (errorString != null) {
	    	request.getSession().setAttribute("errorString", errorString);
    		request.getSession().removeAttribute("infoString");
    		response.sendRedirect(request.getContextPath() + "/manage");
	    }
	    request.setAttribute("activity", activity);
	    request.setAttribute("categoriesList", categoriesList);
	    // Forward sang jsp
	      RequestDispatcher dispatcher = request.getServletContext()
	              .getRequestDispatcher("/WEB-INF/views/HoangHien_04_editActivityView.jsp");
	      dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
