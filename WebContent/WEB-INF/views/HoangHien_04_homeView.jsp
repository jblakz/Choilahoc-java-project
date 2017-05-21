<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ChoiLaHoc.vn - Home Page</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
	<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
</head>
<body>
	<jsp:include page="_HoangHien_04_header.jsp"></jsp:include>
	
	<div id="body" class="home">
			<div class="header">
				<img src="${pageContext.request.contextPath}/images/bg-home.jpeg" alt=""
				style="width: 1545px; left: 36%">
				<div>
					<a href="${pageContext.request.contextPath}/activitiesList">Các bài đăng</a>
				</div>
			</div>
			<div class="body">
				<div>
					<div style="margin:auto; width: 820px;">
						<h1>Kính chào!</h1>
						<h2 style="line-height: 40px;">ChoiLaHoc.vn - Website cung cấp các hoạt động cho trẻ</h2>
						<p style="margin: 0 0 24px 0; width: 820px;">Website này là một hệ thống cung cấp hoạt động 
						vui chơi cho trẻ, hướng tới người dùng là các phụ huynh của trẻ em. Hệ thống được thiết kế để các phụ huynh dễ 
						dàng tìm kiếm và nghiên cứu các hoạt động vui chơi bổ ích cho con em mình. Do đó nâng cao chất lượng nuôi dạy 
						trẻ, hỗ trợ các trẻ học hỏi các kỹ năng sống hữu ích và tối ưu tiềm năng của trẻ.
						</p>
						<p style="margin: 0 0 80px 0; width: 820px;">
						Chúc các phụ huynh nuôi dạy con trẻ thành công!
						<p>
					</div>
				</div>
			</div>
			
		</div>
	
    <jsp:include page="_HoangHien_04_footer.jsp"></jsp:include>
</body>
</html>