package conn;

import java.sql.Connection;
import java.sql.SQLException;

//Lớp này tạo kết nối tới DB tùy theo loại DB khác nhau
public class HoangHien_04_ConnectionUtils {
	public static Connection HoangHien_04_getConnection()
            throws ClassNotFoundException, SQLException {

      // kết nối tới MySQL Database.
      return HoangHien_04_MySQLConnUtils.HoangHien_04_getMySQLConnection();
  }
   
  public static void HoangHien_04_closeQuietly(Connection conn) {
      try {
          conn.close();
      } catch (Exception e) {
      }
  }

  public static void HoangHien_04_rollbackQuietly(Connection conn) {
      try {
          conn.rollback();
      } catch (Exception e) {
      }
  }
}
