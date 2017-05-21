<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ChoiLaHoc.vn - Log In</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
	<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
</head>
<body>
	<jsp:include page="_HoangHien_04_header.jsp"></jsp:include>
	<div id="body" class="contact">
			<div class="header">
				<div>
					<h1>Đăng nhập</h1>
				</div>
			</div>
			<div class="footer">
				<div class="contact">
					<h1>Nhập tên đăng nhập và mật khẩu</h1>
					<p style="color: red;">${errorString}</p>
					<p style="color: green; padding-bottom: 8px;">${infoString}</p>
					<form method="POST" action="doLogin">
						<input type="text" name="username" placeholder="Nhập tên đăng nhập"
						onblur="this.placeholder=!this.placeholder?'Nhập tên đăng nhập':this.placeholder;" onfocus="this.select()">
						<input type="password" name="password" autocomplete="off" placeholder="Nhập mật khẩu"
						onblur="this.placeholder=!this.placeholder?'Nhập mật khẩu':this.placeholder;" onfocus="this.select()">
						<input type="checkbox" name="rememberMe" value="N" id="checkbox">
						<label for="rememberMe">Ghi nhớ đăng nhập</label>
						<input type="submit" value="Đăng nhập" id="submit">
					</form>
				</div>
				<div class="section" style="margin-top: 64px">
					<h1 style="line-height: 30px;">BẠN CÓ ĐI NHẦM KHÔNG?</h1>
					<p>Xin Chào! chức năng đăng nhập chỉ dành riêng cho quản trị viên. Nếu bạn không phải
					quản trị viên, xin bạn vui lòng quay lại. Cảm ơn bạn!</p>
				</div>
			</div>
		</div>
	<jsp:include page="_HoangHien_04_footer.jsp"></jsp:include>
</body>
</html>