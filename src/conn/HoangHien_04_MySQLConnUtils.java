package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HoangHien_04_MySQLConnUtils {
	//Đăng nhập thẳng vào DB để lấy dữ liệu
	//khi không cần đăng nhập trên site
	public static Connection HoangHien_04_getMySQLConnection()
	        throws ClassNotFoundException, SQLException {
	    String hostName = "localhost";
	    String dbName = "childrenactivitiesdb";
	    String userName = "root";
	    String password = "1234";
	    return HoangHien_04_getMySQLConnection(hostName, dbName, userName, password);
	}
	//Đăng nhập để lấy các dữ liệu cần phải đăng nhập trên site
	public static Connection HoangHien_04_getMySQLConnection(String hostName, String dbName,
	        String userName, String password) throws SQLException,
	        ClassNotFoundException {
		HoangHien_04_hasDriver();
	    // Cấu trúc URL Connection dành cho MySQL
	    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	 
	    Connection conn = DriverManager.getConnection(connectionURL, userName,
	            password);
	    return conn;
	}
	
	//Kiểm tra MySQL Driver
	protected static void HoangHien_04_hasDriver() throws SQLException
	{
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
		 } catch (ClassNotFoundException ex) {
			 throw new SQLException ("Invalid Driver!!Please check this driver....");
		 }
	}
}
