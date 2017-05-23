package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.HoangHien_04_Admin;
import utils.HoangHien_04_DBUtils;
import utils.HoangHien_04_MyUtils;

/**
 * Servlet implementation class HoangHien_04_DoChangeUserInfoServlet
 */
@WebServlet(urlPatterns = { "/doChangeUser" })
public class HoangHien_04_DoChangeUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_DoChangeUserInfoServlet() {
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
		//Chuyển request sang module khác để kiểm tra đăng nhập
				request = HoangHien_04_MyUtils.HoangHien_04_checkLogined(request);
			      if (request.getAttribute("user")==null)
			      {
			    	  //Nếu chưa đăng nhập chuyển về trang login và báo lỗi
			    	  response.sendRedirect(request.getContextPath() + "/login");
			          return;
			      }
	      HoangHien_04_Admin loginedUser = (HoangHien_04_Admin)request.getAttribute("user");
		
		//Lấy thông tin user từ form
	      String name = (String) request.getParameter("name");
	      String email = (String) request.getParameter("email");
	      String oldPass = (String) request.getParameter("oldPass");
	      String newPass = (String) request.getParameter("newPass");
	      String retype = (String) request.getParameter("retype");
	      
	      //Băm các thông số password
	      /*byte[] newSalt = loginedUser.getSalt();
	      try {
			newSalt = HoangHien_04_DBUtils.getSalt();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			errorString = e1.getMessage();
		}*/
	      String genOldPass = HoangHien_04_DBUtils.get_SHA_512_SecurePassword(oldPass, loginedUser.getSalt());
	      String genNewPass = HoangHien_04_DBUtils.get_SHA_512_SecurePassword(newPass, loginedUser.getSalt());
	      
	    //Kiểm tra password
	      //Nếu nhập mật khẩu cũ ko đúng
	      if(!loginedUser.getPassword().equals(genOldPass))
	      {
	    	// Báo lỗi.
	    	  System.out.println("CANNOT UPDATE: "+loginedUser.getPassword() + " does not match "+genOldPass);
	    	  errorString = "Nhập sai mật khẩu! ";
	    	  request.getSession().setAttribute("errorString", errorString);
	    	  request.getSession().removeAttribute("infoString");
	    	  response.sendRedirect(request.getContextPath() + "/userInfo");
	          return;
	      }
	      //Nếu pass mới và nhập lại pass ko đúng
	      if(!newPass.equals(retype))
	      {
	    	// Báo lỗi.
	    	  errorString = "Nhập lại mật khẩu không chính xác!";
	    	  request.getSession().setAttribute("errorString", errorString);
	    	  request.getSession().removeAttribute("infoString");
	    	  response.sendRedirect(request.getContextPath() + "/userInfo");
	          return;
	      }
	      // Trường hợp người dùng không muốn đổi mật khẩu
	      if(newPass == "" || retype == "")
	      {
	    	 genNewPass = loginedUser.getPassword();
	    	 //newSalt = loginedUser.getSalt();
	      }
	      HoangHien_04_Admin admin = new HoangHien_04_Admin(loginedUser.getIdAdmins());
	      admin.setName(name);
	      admin.setEmail(email);
	      admin.setUsername(loginedUser.getUsername());
	      admin.setPassword(genNewPass);
	      admin.setSalt(loginedUser.getSalt());
	      // Các lệnh từ DBUtils
	      try{HoangHien_04_DBUtils.HoangHien_04_updateAdmin(conn, admin);}
	      catch (SQLException e) {
              e.printStackTrace();
              errorString = e.getMessage();
          } 
	      catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorString = e.getMessage();
		}
	   // Lưu thông tin vào request attribute trước khi forward sang views.
	      request.setAttribute("userInfo", admin);
	 
	      // Nếu có lỗi forward sang trang edit
	      if (errorString != null) {
	    	  request.getSession().setAttribute("errorString", errorString);
	    	  request.getSession().removeAttribute("infoString");
	    	  response.sendRedirect(request.getContextPath() + "/userInfo");
	      }
	      // Nếu mọi thứ tốt đẹp.
	      String infoString = "Chỉnh sửa thành công! Mời đăng nhập lại";
	      request.getSession().setAttribute("infoString", infoString);
	      request.getSession().removeAttribute("errorString");
	      response.sendRedirect(request.getContextPath() + "/logout");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
