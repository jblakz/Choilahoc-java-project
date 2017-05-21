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
 * Servlet implementation class HoangHien_04_SinglePostServlet
 */
@WebServlet(urlPatterns = { "/singlePost" })
public class HoangHien_04_SinglePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_SinglePostServlet() {
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
		HoangHien_04_Activity featured = (HoangHien_04_Activity) request.getSession().getAttribute("featured");
		List<HoangHien_04_Activity> list = null;
		String errorString = null;
		int idFeatured = 16;
		int idActivities = 0;
		if(HoangHien_04_MyUtils.isInteger(request.getParameter("idActivities")))
		{
			idActivities = Integer.parseInt((String)request.getParameter("idActivities"));
			try {
		      	  //Các lệnh DBUtils
					activity = HoangHien_04_DBUtils.HoangHien_04_findActivity(conn, idActivities);
					if (activity != null){
						activity.setCatName(HoangHien_04_DBUtils.HoangHien_04_findCategory(conn, activity.getIdCategory()).getCatName());
						if (featured == null){
							featured = HoangHien_04_DBUtils.HoangHien_04_findActivity(conn, idFeatured);
						}
						list = HoangHien_04_DBUtils.HoangHien_04_queryRelated(conn, activity.getIdCategory(), idActivities);
					}
					else errorString = "ERROR: Cannot find post with provided 'idActivities'. Return to original site.";
		        } catch (SQLException e) {
		            e.printStackTrace();
		            errorString = e.getMessage();
		        }
		}
		else errorString = "NUMBER FORMAT EXCEPTION: parameter 'idActivities' is not a integer. Return to original site.";
	    
		
		if (errorString != null) {
			request.getSession().setAttribute("errorString", errorString);
    		request.getSession().removeAttribute("infoString");
    		response.sendRedirect(request.getContextPath() + "/activitiesList");
	      }
	      // Nếu mọi thứ tốt đẹp.
	      // Redirect sang trang chi tiết bài đăng.
	      else {
	    	  request.setAttribute("activity", activity);
	    	  request.getSession().setAttribute("featured", featured);
	    	  request.setAttribute("activitiesList", list);
	    	  RequestDispatcher dispatcher = this.getServletContext()
	    			  .getRequestDispatcher("/WEB-INF/views/HoangHien_04_singlePostView.jsp");
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
