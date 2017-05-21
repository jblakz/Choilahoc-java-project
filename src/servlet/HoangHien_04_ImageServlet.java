package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.HoangHien_04_MyUtils;

/**
 * Servlet implementation class HoangHien_04_ImageServlet
 */
@WebServlet(urlPatterns = {"/imagesURL/*"})
public class HoangHien_04_ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String sql = "SELECT image FROM activities WHERE idActivities = ?";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoangHien_04_ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = HoangHien_04_MyUtils.HoangHien_04_getStoredConnection(request);
		int idActivities = Integer.parseInt(request.getPathInfo().substring(1));
		
		String errorString = null;
		try{
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idActivities);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				byte[] content = rs.getBytes("image");
				response.setContentType("image/jpeg");
				response.setContentLength(content.length);
				response.getOutputStream().write(content);
			} else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,errorString); // 404.
			}
		}catch (SQLException e) {
	          e.printStackTrace();
	          errorString = e.getMessage();
	      }
		request.setAttribute("errorString", errorString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	/* // Hàm readURL(tag input type file) 
	//tích hợp bằng cách thêm trực tiếp vào trang jsp qua thẻ <script>
	//hoặc tạo mới file javascript
	function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#thumb')
                    .attr('src', e.target.result)
            	};

            reader.readAsDataURL(input.files[0]);
        	}
		}
		*/

}
