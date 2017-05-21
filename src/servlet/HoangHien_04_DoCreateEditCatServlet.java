package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.HoangHien_04_Category;
import utils.HoangHien_04_DBUtils;
import utils.HoangHien_04_MyUtils;

/**
 * Servlet implementation class HoangHien_04_DoCreateEditCatServlet
 */
@WebServlet(urlPatterns = { "/doCreateEditCat" })
public class HoangHien_04_DoCreateEditCatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_DoCreateEditCatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
		String errorString = null;
		String infoString = null;
		HoangHien_04_Category category = null;
		String idCatString;
		int idCategory;
		
		//Chuyển request sang module khác để kiểm tra đăng nhập
		request = HoangHien_04_MyUtils.HoangHien_04_checkLogined(request);
		if (request.getAttribute("user")==null)
		{
			//Nếu chưa đăng nhập chuyển về trang login và báo lỗi
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	   idCatString = (String)request.getParameter("idCategory");
	    //Nếu có thể loại theo id, thực hiện lệnh edit thể loại
	    if (idCatString!=null)
	    {
	    	//Lấy thông tin id của thể loại
	    	idCategory = Integer.parseInt((String)request.getParameter("idCategory"));
	    	String catName = request.getParameter("catName");
	    	try {
	      	  //Thực hiện lệnh query trong DBUtils
	    	category = HoangHien_04_DBUtils.HoangHien_04_findCategory(conn, idCategory);
	    	category.setCatName(catName);
	      	  HoangHien_04_DBUtils.HoangHien_04_updateCategory(conn, category);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            errorString = e.getMessage();
	        }

	    	category.setCatName(catName);
	    	
	    	// Lưu thông tin vào request attribute trước khi forward sang views.
		      request.setAttribute("category", category);
	    	//Nếu có lỗi
	    	if (errorString!=null)
	    	{
	    		request.getSession().setAttribute("errorString", errorString);
	    		request.getSession().removeAttribute("infoString");
	    		response.sendRedirect(request.getContextPath() + "/manage");
	    	}
	    	else{
	    		infoString = "Thể loại có ID '"+idCategory+"' được sửa thành công!";
	    		request.getSession().setAttribute("infoString", infoString);
	    		request.getSession().removeAttribute("errorString");
	    		response.sendRedirect(request.getContextPath() + "/manage");
	    	}
	    }
	    
	  //Nếu ko có thể loại nào theo id, thực hiện lệnh create thể loại
	    else{
	    	String catName = request.getParameter("catName");
	    	category = new HoangHien_04_Category(catName);
	    	try {
		      	  //Thực hiện lệnh query trong DBUtils
		      	  HoangHien_04_DBUtils.HoangHien_04_insertCategory(conn, category);
		        } catch (SQLException e) {
		            e.printStackTrace();
		            errorString = e.getMessage();
		        }
		    // Lưu thông tin vào request attribute trước khi forward sang views.
			request.setAttribute("category", category);
		    //Nếu có lỗi
		    if (errorString!=null)
		    {
		    	errorString = errorString+" at create";
		    	request.getSession().setAttribute("errorString", errorString);
	    		request.getSession().removeAttribute("infoString");
	    		response.sendRedirect(request.getContextPath() + "/manage");
		    }
		    else{
		    	infoString = "Thể loại mới được thêm thành công!";
		    	request.getSession().setAttribute("infoString", infoString);
	    		request.getSession().removeAttribute("errorString");
	    		response.sendRedirect(request.getContextPath() + "/manage");
		    }
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
