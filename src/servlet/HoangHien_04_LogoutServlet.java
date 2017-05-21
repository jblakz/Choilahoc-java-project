package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.HoangHien_04_MyUtils;

/**
 * Servlet implementation class HoangHien_04_LogoutServlet
 */
@WebServlet(urlPatterns = { "/logout"})
public class HoangHien_04_LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String infoString = null;
		
		if(request.getAttribute("infoString")!=null)
			infoString = (String)session.getAttribute("infoString");
		else infoString = "Đã đăng xuất!";
		
		session.removeAttribute("loginedUser");
		HoangHien_04_MyUtils.HoangHien_04_deleteUserCookie(response);
		session.setAttribute("infoString", infoString);
		session.removeAttribute("errorString");
		response.sendRedirect(request.getContextPath() + "/login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
