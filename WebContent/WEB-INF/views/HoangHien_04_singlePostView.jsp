<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChoiLaHoc.vn - ${activity.title}</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
<style type="text/css">
#body div.singlepost .featured img{
box-shadow: 0 4px 8px 0 rgba(0,0,0,.8);
}
h2{
color: #CB3362;
font-family: Tahoma;
font-size: 16px;
font-weight: normal;
line-height: 28px;
padding: 6px 0 3px;
text-align: center;
margin: 0 auto 18px auto;
}
</style>
</head>
<body>
	<jsp:include page="_HoangHien_04_header.jsp"></jsp:include>
	<div id="body">
		<div class="header">
			<div>
				<h1>Chi tiết Bài đăng</h1>
			</div>
		</div>
		<div class="singlepost" style="max-width:1280px">
			<div class="featured" style="width:900px; margin:0 30px 0 30px; padding: 0 25px 0 0;
			border-left: 0.5px solid rgba(60, 60, 60, 0.2);
			border-right: 0.5px solid rgba(60, 60, 60, 0.2);">
				<img style="width:180px; height: 180px; border-radius: 50%;object-fit: cover; margin: 0 0 0 25px; padding: 0 0;
				display:inline;"
				src="${pageContext.request.contextPath}/imagesURL/${activity.idActivities}">
				<h1 style="display:inline; position: absolute; padding: 80px 0 4px 30px;">${activity.title}</h1>
				<span style="display:inline;position: absolute; height:24px">By Hoàng Hiến on ${activity.time}</span>
				<span style="display:inline;position: absolute; height:24px;top:170px; font-size: 16px">
				${activity.catName}</span>
				<p>${activity.content}</p>
				<a href="${pageContext.request.contextPath}/activitiesList" class="load">Quay lại</a>
			</div>
			<div class="sidebar">
				<h1>Bài đăng nổi bật</h1>
				<img style="width:180px; height: 180px; border-radius: 50%;object-fit: cover;"
				src="${pageContext.request.contextPath}/imagesURL/${featured.idActivities}">
				<h2 style="line-height: 28px; width: 200px; margin: auto;">${featured.title}</h2>
				<span>By Hoàng Hiến on ${featured.time}</span>
				<p style="padding: 3px;">${featured.summary}</p>
				<a href="${pageContext.request.contextPath}/singlePost?idActivities=${featured.idActivities}" 
				style="padding: 3px;" class="more">Đọc thêm ...</a>
			</div>
			<div class="featured" id = "relatedSec" style="background-color: #EFEDEE;margin:50px 30px 0 30px; padding: 0 25px 32px 0">
				<h1 style="padding: 18px 12px; margin-bottom: 32px;">Các bài đăng liên quan</h1>
				<c:forEach items="${activitiesList}" var="activity" >
					<div style="display: inline-block; margin: 0;
					border-left: 0.5px solid rgba(60, 60, 60, 0.2);
					 padding: 0 12px; width: 197px">
						<a href="${pageContext.request.contextPath}/singlePost?idActivities=${activity.idActivities}">
							<img style="width:160px; height: 160px; border-radius: 50%;object-fit: cover; box-shadow: none; margin:0 20px;"
							src="${pageContext.request.contextPath}/imagesURL/${activity.idActivities}">
						</a>
						<h2>${activity.title}</h2>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<jsp:include page="_HoangHien_04_footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	window.onload = function(){
		var relatedSec = document.getElementById('relatedSec');
		if ('${activitiesList}' == '[]')
			relatedSec.style.visibility = 'hidden';
	}
	</script>
</body>
</html>