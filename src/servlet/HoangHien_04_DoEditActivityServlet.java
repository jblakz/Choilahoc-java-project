package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import utils.HoangHien_04_DBUtils;
import utils.HoangHien_04_MyUtils;
import beans.HoangHien_04_Activity;
import beans.HoangHien_04_Admin;
import beans.HoangHien_04_Category;

/**
 * Servlet implementation class HoangHien_04_DoUpdateActivityServlet
 */
@WebServlet(urlPatterns = { "/doEditActivity" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class HoangHien_04_DoEditActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_DoEditActivityServlet() {
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
		String errorString = null;
		
		//Chuyển request sang module khác để kiểm tra đăng nhập
		request = HoangHien_04_MyUtils.HoangHien_04_checkLogined(request);
		if (request.getAttribute("user")==null)
		{
			//Nếu chưa đăng nhập chuyển về trang login và báo lỗi
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	      
		//Lấy thông tin bài đăng từ form
	      int idActivities = Integer.parseInt((String)request.getParameter("idActivities"));
	      String title = (String) request.getParameter("title");
	      byte[] image = IOUtils.toByteArray(request.getPart("file").getInputStream());
	      String content = (String) request.getParameter("content");
	      String[] contents = content.split("<p>");
	      String summary = contents[1];
	      Timestamp time = new Timestamp(System.currentTimeMillis());
	      int idCategory = Integer.parseInt(request.getParameter("idCategory"));
	      
	      //Kiểm tra bài đăng có thiếu dữ liệu không.
	      if (title == "" || content == "") {
	          // Báo lỗi
	    	  errorString = "Bài đăng ID '"+idActivities+"' thiếu dữ liệu!";
	    	  request.getSession().setAttribute("errorString", errorString);
	    	  request.getSession().removeAttribute("infoString");
	    	  response.sendRedirect(request.getContextPath() + "/manage");
	          return;
	      }
	      int idAdmins = ((HoangHien_04_Admin)request.getAttribute("user")).getIdAdmins();
	      HoangHien_04_Activity activity = new HoangHien_04_Activity(idActivities,title,image,content
	    		  ,summary,time,idCategory,idAdmins);
	 
	      if (errorString == null) {
	          try {
	        	  //Các lệnh DBUtils
	        	  HoangHien_04_DBUtils.HoangHien_04_updateActivity(conn, activity);
	        	  categoriesList = HoangHien_04_DBUtils.HoangHien_04_queryCategory(conn);
	          } catch (SQLException e) {
	              e.printStackTrace();
	              errorString = e.getMessage();
	          }
	      }
	      
	      // Lưu thông tin vào request attribute trước khi forward sang views.
	      request.setAttribute("activity", activity);
	      request.setAttribute("categoriesList", categoriesList);
	 
	      // Nếu có lỗi forward sang trang edit
	      if (errorString != null) {
	    	  request.getSession().setAttribute("errorString", errorString);
	    	  request.getSession().removeAttribute("infoString");
	    	  response.sendRedirect(request.getContextPath() + "/manage");
	      }
	      // Nếu mọi thứ tốt đẹp.
	      // Redirect sang trang danh sách sản phẩm.
	      else {
	    	  String infoString = "Bài đăng ID '"+idActivities+"' được sửa thành công!";
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
