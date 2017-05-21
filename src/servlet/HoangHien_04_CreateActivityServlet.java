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

import utils.HoangHien_04_DBUtils;
import utils.HoangHien_04_MyUtils;
import beans.HoangHien_04_Category;

/**
 * Servlet implementation class HoangHien_04_CreateActivitiesServlet
 */
@WebServlet(urlPatterns = { "/createActivity" })
public class HoangHien_04_CreateActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_CreateActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
		List<HoangHien_04_Category> categoriesList = null;
		String errorString = (String)request.getSession().getAttribute("errorString");
		
		//Chuyển request sang module khác để kiểm tra đăng nhập
		request = HoangHien_04_MyUtils.HoangHien_04_checkLogined(request);
	      if (request.getAttribute("user")==null)
	      {
	    	  //Nếu chưa đăng nhập chuyển về trang login và báo lỗi
	    	  response.sendRedirect(request.getContextPath() + "/login");
	          return;
	      }
		
		try{categoriesList = HoangHien_04_DBUtils.HoangHien_04_queryCategory(conn);}
		catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
		
		request.getSession().setAttribute("errorString", errorString);
		request.getSession().removeAttribute("infoString");
	    request.setAttribute("categoriesList", categoriesList);
	    
		RequestDispatcher dispatcher = request.getServletContext()
	              .getRequestDispatcher("/WEB-INF/views/HoangHien_04_createActivityView.jsp");
	      dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
