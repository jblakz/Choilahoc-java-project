package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.owasp.esapi.ESAPI;

import servlet.HoangHien_04_ActivitiesListServlet;
import beans.HoangHien_04_Admin;
import beans.HoangHien_04_Activity;
import beans.HoangHien_04_Category;

public class HoangHien_04_DBUtils {
	//Lớp này tạo ra các hàm đổ dữ liệu từ MySQL vào các Model
	//hay chỉnh sửa dữ liệu trong model và cập nhật vào MySQL
	
	//Thao tác trên Model Admin
	public static HoangHien_04_Admin HoangHien_04_findUserWithPass(Connection conn, String username, String password) throws SQLException {
		 
	      String sql = "Select * from admins a"
	              + " where a.username = ?";
	      
	      
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, username);
	      ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	    	  //Compare generated password from database
	          byte[] salt = rs.getBytes("salt");
	          String genPassword = get_SHA_512_SecurePassword(password, salt);
	          if (!genPassword.equals(rs.getString("genPassword")))
	          {
	        	  System.out.println(genPassword +" does not match " + rs.getString("genPassword"));
	        	  return null;
	          }
	          //
	          int idAdmins = rs.getInt("idAdmins");
	          String name = rs.getString("name");
	          String email =rs.getString("email");
	          HoangHien_04_Admin user = new HoangHien_04_Admin(idAdmins, name, email, username, genPassword, salt);
	          return user;
	      }
	      return null;
	  }
	 
	  public static HoangHien_04_Admin HoangHien_04_findUser(Connection conn, String username) throws SQLException {
	 
		  String sql = "Select * from admins a"
	              + " where a.username = ?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, username);
	      ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	    	  int idAdmins = rs.getInt("idAdmins");
	          String name = rs.getString("name");
	          String email =rs.getString("email");
	          String password = rs.getString("genPassword");
	          byte[] salt = rs.getBytes("salt");
	          HoangHien_04_Admin user = new HoangHien_04_Admin(idAdmins, name, email, username, password, salt);
	          return user;
	      }
	      return null;
	  }
	  public static void HoangHien_04_updateAdmin(Connection conn, HoangHien_04_Admin user) throws SQLException, NoSuchAlgorithmException {
	      String sql = "Update admins set name=?, email=?, genPassword=?, salt = ?"
	      		+ " where idAdmins=? ";
	      
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      
	      pstm.setString(1, encodeValueForHTML(user.getName()));
	      pstm.setString(2, encodeValueForHTML(user.getEmail()));
	      pstm.setString(3, encodeValueForHTML(user.getPassword()));
	      pstm.setBytes(4, user.getSalt());
	      pstm.setInt(5, user.getIdAdmins());
	      pstm.executeUpdate();
	  }
	  
	//Thao tác trên Model Category
	  public static HoangHien_04_Category HoangHien_04_findCategory(Connection conn, int idCategory) throws SQLException {
			 
		  String sql = "Select * from categories c"
	              + " where c.idCategory = ?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setInt(1, idCategory);
	      ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	          String catName = rs.getString("catName");
	          HoangHien_04_Category category = new HoangHien_04_Category(idCategory,catName,false);
	          return category;
	      }
	      return null;
	  }
	  public static List<HoangHien_04_Category> HoangHien_04_queryCategory(Connection conn) throws SQLException {
	      String sql = "Select * from categories a ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<HoangHien_04_Category> list = new ArrayList<HoangHien_04_Category>();
	      while (rs.next()) {
	    	  int idCategory = rs.getInt("idCategory");
	          String catName = rs.getString("catName");
	          Boolean hide = rs.getBoolean("hide");
	          HoangHien_04_Category category = new HoangHien_04_Category(idCategory, catName, hide);
	          list.add(category);
	      }
	      return list;
	  }
	  public static void HoangHien_04_updateCategory(Connection conn, HoangHien_04_Category category) throws SQLException {
	      String sql = "Update categories set catName =?, hide=?"
	      		+ " where idCategory=? ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setString(1, encodeValueForHTML(category.getCatName()));
	      pstm.setBoolean(2, category.getHide());
	      pstm.setInt(3, category.getIdCategory());
	      pstm.executeUpdate();
	  }
	 
	  public static void HoangHien_04_insertCategory(Connection conn, HoangHien_04_Category category) throws SQLException {
	      String sql = "Insert into categories(catName, hide)"
	      		+ " values (?,?)";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setString(1, encodeValueForHTML(category.getCatName()));
	      pstm.setBoolean(2, category.getHide());
	      
	      pstm.executeUpdate();
	  }
	 
	  public static void HoangHien_04_deleteCategory(Connection conn, int idCategory) throws SQLException {
	      String sql = "Delete from categories where idCategory= ?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setInt(1, idCategory);
	 
	      pstm.executeUpdate();
	  }
	  
	//Thao tác trên Model Activity
	  public static List<HoangHien_04_Activity> HoangHien_04_queryActivity(Connection conn) throws SQLException {
	      String sql = "Select * from activities a ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<HoangHien_04_Activity> list = new ArrayList<HoangHien_04_Activity>();
	      while (rs.next()) {
	          int idActivities = rs.getInt("idActivities");
	          String title = rs.getString("title");
	          byte[] image = rs.getBytes("image");
	          String content = rs.getString("content");
	          String summary = rs.getString("summary");
	          Timestamp time = rs.getTimestamp("time");
	          int idCategory = rs.getInt("idCategory");
	          int idAdmins = rs.getInt("idAdmins");
	          HoangHien_04_Activity activity = new HoangHien_04_Activity(idActivities, title, image, content,
	        		  summary, time, idCategory, idAdmins);
	          list.add(activity);
	      }
	      return list;
	  }
	  //Lấy hoạt động theo trang
	  public static List<HoangHien_04_Activity> HoangHien_04_queryActivity(Connection conn, int startRow) throws SQLException {
	      String sql = "Select * from activities a order by idActivities DESC limit ?,?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setInt(1, startRow);
	      pstm.setInt(2, HoangHien_04_ActivitiesListServlet.ACTIVITIES_DISPLAYED_PER_PAGE);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<HoangHien_04_Activity> list = new ArrayList<HoangHien_04_Activity>();
	      while (rs.next()) {
	          int idActivities = rs.getInt("idActivities");
	          String title = rs.getString("title");
	          byte[] image = rs.getBytes("image");
	          String content = rs.getString("content");
	          String summary = rs.getString("summary");
	          Timestamp time = rs.getTimestamp("time");
	          int idCategory = rs.getInt("idCategory");
	          int idAdmins = rs.getInt("idAdmins");
	          HoangHien_04_Activity activity = new HoangHien_04_Activity(idActivities, title, image, content,
	        		  summary, time, idCategory, idAdmins);
	          list.add(activity);
	      }
	      return list;
	  }
	  //Lấy hoạt động theo thể loại
	  public static List<HoangHien_04_Activity> HoangHien_04_queryActivity(Connection conn, int idCategory, int startRow) throws SQLException {
	      String sql1 = "Select * from activities a where a.idCategory = ? order by idActivities DESC limit ?,?";
	      String sql2 = "Select * from activities a where a.idCategory = ? order by idActivities DESC";
	      
	      PreparedStatement pstm;
	      
	      if (startRow!=-1)
	      {
	    	  pstm = conn.prepareStatement(sql1);
	    	  pstm.setInt(1, idCategory);
	    	  pstm.setInt(2, startRow);
	    	  pstm.setInt(3, HoangHien_04_ActivitiesListServlet.ACTIVITIES_DISPLAYED_PER_PAGE);
	      }
	      else
	      {
	    	  pstm = conn.prepareStatement(sql2);
	    	  pstm.setInt(1, idCategory);
	      }
	 
	      ResultSet rs = pstm.executeQuery();
	      List<HoangHien_04_Activity> list = new ArrayList<HoangHien_04_Activity>();
	      while (rs.next()) {
	          int idActivities = rs.getInt("idActivities");
	          String title = rs.getString("title");
	          byte[] image = rs.getBytes("image");
	          String content = rs.getString("content");
	          String summary = rs.getString("summary");
	          Timestamp time = rs.getTimestamp("time");
	          int idAdmins = rs.getInt("idAdmins");
	          HoangHien_04_Activity activity = new HoangHien_04_Activity(idActivities, title, image, content,
	        		  summary, time, idCategory, idAdmins);
	          list.add(activity);
	      }
	      return list;
	  }
	  
	  //Lấy hoạt động liên quan
	  public static List<HoangHien_04_Activity> HoangHien_04_queryRelated(Connection conn, int idCategory, int idMain) throws SQLException {
	      String sql = "Select * from activities a where a.idCategory = ? and not idActivities = ? order by idActivities DESC";
	      
	      PreparedStatement pstm;
	      
	      pstm = conn.prepareStatement(sql);
	      pstm.setInt(1, idCategory);
	      pstm.setInt(2, idMain);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<HoangHien_04_Activity> list = new ArrayList<HoangHien_04_Activity>();
	      while (rs.next()) {
	          int idActivities = rs.getInt("idActivities");
	          String title = rs.getString("title");
	          byte[] image = rs.getBytes("image");
	          String content = rs.getString("content");
	          String summary = rs.getString("summary");
	          Timestamp time = rs.getTimestamp("time");
	          int idAdmins = rs.getInt("idAdmins");
	          HoangHien_04_Activity activity = new HoangHien_04_Activity(idActivities, title, image, content,
	        		  summary, time, idCategory, idAdmins);
	          list.add(activity);
	      }
	      return list;
	  }
	  
	  public static HoangHien_04_Activity HoangHien_04_findActivity(Connection conn, int idActivities) throws SQLException {
	      String sql = "Select * from activities a where a.idActivities=?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setInt(1, idActivities);
	 
	      ResultSet rs = pstm.executeQuery();
	 
	      while (rs.next()) {
	          String title = rs.getString("title");
	          byte[] image = rs.getBytes("image");
	          String content = rs.getString("content");
	          String summary = rs.getString("summary");
	          Timestamp time = rs.getTimestamp("time");
	          int idCategory = rs.getInt("idCategory");
	          int idAdmins = rs.getInt("idAdmins");
	          HoangHien_04_Activity activity = new HoangHien_04_Activity(idActivities, title, image, content,
	        		  summary, time, idCategory, idAdmins);
	          return activity;
	      }
	      return null;
	  }
	  
	  //Tìm kiếm theo từ khóa
	  public static List<HoangHien_04_Activity> HoangHien_04_searchActivity(Connection conn, String searchString) throws SQLException {
		  String sql = "Select * from activities a where a.title like ?";
		  String pattern = "%" + searchString + "%";
		  PreparedStatement pstm = conn.prepareStatement(sql);
		  pstm.setString(1, pattern);

		  ResultSet rs = pstm.executeQuery();
		  
	      List<HoangHien_04_Activity> list = new ArrayList<HoangHien_04_Activity>();
	      while (rs.next()) {
	          int idActivities = rs.getInt("idActivities");
	          String title = rs.getString("title");
	          byte[] image = rs.getBytes("image");
	          String content = rs.getString("content");
	          String summary = rs.getString("summary");
	          Timestamp time = rs.getTimestamp("time");
	          int idCategory = rs.getInt("idCategory");
	          int idAdmins = rs.getInt("idAdmins");
	          HoangHien_04_Activity activity = new HoangHien_04_Activity(idActivities, title, image, content,
	        		  summary, time, idCategory, idAdmins);
	          list.add(activity);
	      }
	      return list;
	  }
	 
	  public static void HoangHien_04_updateActivity(Connection conn, HoangHien_04_Activity activity) throws SQLException {
	      String sql1 = "Update activities set title =?, content=?, summary=?, time=?, idCategory=?, idAdmins=?, image=?"
	      		+ " where idActivities=? ";
	      String sql2 = "Update activities set title =?, content=?, summary=?, time=?, idCategory=?, idAdmins=?"
		      		+ " where idActivities=? ";
	      PreparedStatement pstm;
	      if(activity.getImage().length == 0){
	    	  pstm = conn.prepareStatement(sql2);
	    	  pstm.setInt(7, activity.getIdActivities());
	      }else{
	    	  pstm = conn.prepareStatement(sql1);
	    	  pstm.setBytes(7, activity.getImage());
	    	  pstm.setInt(8, activity.getIdActivities());
	      }
	      
	      pstm.setString(1, encodeValueForHTML(activity.getTitle()));
	      pstm.setString(2, encodeValueForHTML(activity.getContent()));
	      pstm.setString(3, encodeValueForHTML(activity.getSummary()));
	      pstm.setTimestamp(4, activity.getTime());
	      pstm.setInt(5, activity.getIdCategory());
	      pstm.setInt(6, activity.getIdAdmins());
	      
	      pstm.executeUpdate();
	  }
	 
	  public static void HoangHien_04_insertActivity(Connection conn, HoangHien_04_Activity activity) throws SQLException {
	      String sql = "Insert into activities(title, image, content, summary, time, idCategory, idAdmins)"
	      		+ " values (?,?,?,?,?,?,?)";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      
	      pstm.setString(1, encodeValueForHTML(activity.getTitle()));
	      pstm.setBytes(2, activity.getImage());
	      pstm.setString(3, encodeValueForHTML(activity.getContent()));
	      pstm.setString(4, encodeValueForHTML(activity.getSummary()));
	      pstm.setTimestamp(5, activity.getTime());
	      pstm.setInt(6, activity.getIdCategory());
	      pstm.setInt(7, activity.getIdAdmins());
	      
	      pstm.executeUpdate();
	  }
	 
	  public static void HoangHien_04_deleteActivity(Connection conn, int idActivities) throws SQLException {
	      String sql = "Delete from activities where idActivities=?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      pstm.setInt(1, idActivities);
	 
	      pstm.executeUpdate();
	  }
	  
	  public static byte[] getSalt() throws NoSuchAlgorithmException {
	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	        byte[] salt = new byte[16];
	        sr.nextBytes(salt);
	        return salt;
	    }
	  
	  public static String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt){
		  String generatedPassword = null;
		      try {
		           MessageDigest md = MessageDigest.getInstance("SHA-512");
		           md.update(salt);
		           byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
		           StringBuilder sb = new StringBuilder();
		           for(int i=0; i< bytes.length ;i++){
		              sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		           }
		           generatedPassword = sb.toString();
		          } 
		         catch (NoSuchAlgorithmException e){
		          e.printStackTrace();
		         }
		      	 catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      return generatedPassword.toUpperCase();
		  }
	  
	  private static String encodeValueForHTML(String input){
		  String value = "";
		  value = (String) ESAPI.encoder().encodeForHTML(input);
		  return value;
	  }
}
