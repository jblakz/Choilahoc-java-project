package utils;

import java.sql.Connection;
import java.util.Scanner;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import	beans.HoangHien_04_Admin;

public class HoangHien_04_MyUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	 
	   private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	 
	   // Lưu trữ Connection vào một thuộc tính của request.
	   // Thông tin lưu trữ chỉ tồn tại trong thời gian yêu cầu (request)
	   // cho tới khi dữ liệu được trả về trình duyệt người dùng.
	   public static void HoangHien_04_storeConnection(ServletRequest request, Connection conn) {
	       request.setAttribute(ATT_NAME_CONNECTION, conn);
	   }
	 
	   // Lấy đối tượng Connection đã được lưu trữ trong 1 thuộc tính của request.
	   public static Connection HoangHien_04_getStoredConnection(ServletRequest request) {
	       Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
	       return conn;
	   }
	 
	   // Lưu trữ thông tin người dùng đã login vào Session
	   public static void HoangHien_04_storeLoginedUser(HttpSession session, HoangHien_04_Admin loginedUser) {
	       // Trên JSP có thể truy cập ${loginedUser}
	       session.setAttribute("loginedUser", loginedUser);
	   }
	 
	   // Lấy thông tin người dùng đã login trong session.
	   public static HoangHien_04_Admin HoangHien_04_getLoginedUser(HttpSession session) {
	       HoangHien_04_Admin loginedUser = (HoangHien_04_Admin) session.getAttribute("loginedUser");
	       return loginedUser;
	   }
	 
	   // Lưu thông tin người dùng vào Cookie.
	   public static void HoangHien_04_storeUserCookie(HttpServletResponse response, HoangHien_04_Admin user) {
	       System.out.println("Store user cookie");
	       Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUsername());
	       // 1 ngày (Đã đổi ra giây)
	       cookieUserName.setMaxAge(24 * 60 * 60);
	       response.addCookie(cookieUserName);
	   }
	 
	   public static String HoangHien_04_getUserNameInCookie(HttpServletRequest request) {
	       Cookie[] cookies = request.getCookies();
	       if (cookies != null) {
	           for (Cookie cookie : cookies) {
	               if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
	                   return cookie.getValue();
	               }
	           }
	       }
	       return null;
	   }
	 
	   // Xóa Cookie của người dùng
	   public static void HoangHien_04_deleteUserCookie(HttpServletResponse response) {
	       Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
	       // 0 giây. (Hết hạn ngay lập tức)
	       cookieUserName.setMaxAge(0);
	       response.addCookie(cookieUserName);
	   }
	   // Kiểm tra người dùng đã login, gán thuộc tính user vào request nếu đã login
	   public static HttpServletRequest HoangHien_04_checkLogined(HttpServletRequest request)
	   {
		   HttpSession session = request.getSession();
		   HoangHien_04_Admin loginedUser = HoangHien_04_MyUtils.HoangHien_04_getLoginedUser(session);
		// Chưa login.
		   if (loginedUser == null) {
			   		String errorString = "Chưa đăng nhập hoặc hết thời gian!";
		    	  //Lưu thông tin
		    	  session.setAttribute("errorString", errorString);
		    	  session.removeAttribute("infoString");
		          return request;
		      }
		   request.setAttribute("user", loginedUser);
		   return request;
	   }
	   //Kiểm tra parameter có thể đổi sang số thực hay ko
	   public static boolean isInteger(String s) {
		   	int radix = 10;
			Scanner sc = new Scanner(s.trim());
		    if(!sc.hasNextInt(radix)){
		    	sc.close();
		    	return false;
		    }
		    //Chắc chắn ko còn dữ liệu
		    sc.nextInt(radix);
		    boolean flag = !sc.hasNext();
		    sc.close();
		    return flag;
		}
}
