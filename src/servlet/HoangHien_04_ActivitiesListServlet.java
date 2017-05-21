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
 * Servlet implementation class HoangHien_04_ProductListServlet
 */
@WebServlet(urlPatterns = { "/activitiesList" })
public class HoangHien_04_ActivitiesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int ACTIVITIES_DISPLAYED_PER_PAGE = 3;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_ActivitiesListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
		List<HoangHien_04_Activity> list = null;
		HoangHien_04_Activity featured = (HoangHien_04_Activity) request.getSession().getAttribute("featured");
		List<HoangHien_04_Category> categoriesList = null;
		String errorString = null;
		int idFeatured = 16;
		int idCategory = 0;
		int pageNumber = 1;
		int numberOfPages = 1;
		int startRow = 0;
		
		//Bắt số trang hiện tại từ request
		if (request.getParameter("page")!=null)
			if(HoangHien_04_MyUtils.isInteger(request.getParameter("page")))
				pageNumber = Integer.parseInt(request.getParameter("page"));
			else errorString = "NUMBER FORMAT EXCEPTION: parameter 'page' is not a integer. Return to original site.";
		//Bắt thể loại hiện tại từ request
		if (request.getParameter("idCategory")!=null)
			if(HoangHien_04_MyUtils.isInteger(request.getParameter("idCategory")))
				idCategory = Integer.parseInt(request.getParameter("idCategory"));
			else errorString = "NUMBER FORMAT EXCEPTION: parameter 'idCategory' is not a integer. Return to original site.";
		
		try {
			categoriesList = HoangHien_04_DBUtils.HoangHien_04_queryCategory(conn);
			if (idCategory == 0){
				list = HoangHien_04_DBUtils.HoangHien_04_queryActivity(conn);
				if ((list.size() % ACTIVITIES_DISPLAYED_PER_PAGE)!=0
						&& list.size() > ACTIVITIES_DISPLAYED_PER_PAGE)
					numberOfPages = list.size() / ACTIVITIES_DISPLAYED_PER_PAGE + 1;
				else numberOfPages = list.size() / ACTIVITIES_DISPLAYED_PER_PAGE;
				startRow = (pageNumber-1)* ACTIVITIES_DISPLAYED_PER_PAGE;
				list = HoangHien_04_DBUtils.HoangHien_04_queryActivity(conn, startRow);
			}
			else{
				list = HoangHien_04_DBUtils.HoangHien_04_queryActivity(conn, idCategory, -1);
				if ((list.size() % ACTIVITIES_DISPLAYED_PER_PAGE)!=0
						&& list.size() > ACTIVITIES_DISPLAYED_PER_PAGE)
					numberOfPages = list.size() / ACTIVITIES_DISPLAYED_PER_PAGE + 1;
				else numberOfPages = list.size() / ACTIVITIES_DISPLAYED_PER_PAGE;
				startRow = (pageNumber-1)* ACTIVITIES_DISPLAYED_PER_PAGE;
				list = HoangHien_04_DBUtils.HoangHien_04_queryActivity(conn, idCategory, startRow);
			}
			if (featured == null){
				featured = HoangHien_04_DBUtils.HoangHien_04_findActivity(conn, idFeatured);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = "SQL Exception: " + e.getMessage();
		}
		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("activitiesList", list);
		request.getSession().setAttribute("featured", featured);
		request.setAttribute("categoriesList", categoriesList);
		request.setAttribute("page", pageNumber);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("idCategory", idCategory);
		System.out.print(numberOfPages);
	      
		// Forward sang jsp
		RequestDispatcher dispatcher = request.getServletContext()
	              .getRequestDispatcher("/WEB-INF/views/HoangHien_04_activitiesListView.jsp");
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
