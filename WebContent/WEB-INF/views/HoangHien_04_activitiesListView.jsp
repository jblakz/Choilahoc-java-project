<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChoiLaHoc.vn - Activities Blog</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
<style type="text/css">
.page{
color: #a5838e;
border: 2px solid #cbb5bc;
font-family: Tahoma;
font-size: 16px;
font-weight: normal;
padding: 6px 12px;
}
.page:hover{
background-color: #cb3362;
color: #ffffff;
}
#body p{
color: #7a6666;
display: inline;
font-family: Tahoma;
font-size: 16px;
font-weight: normal;
padding-right: 6px
}
#body div .categoriesSelector{
margin: 0 0 32px;
}
.categoriesSelector select {
border: 1px solid #B6ADB0;
color: #7A6666;
display: inline;
font-family: Tahoma;
font-size: 14px;
font-weight: normal;
height: 38px;
line-height: 38px;
margin: 0 0 8px;
padding: 0 0 0 10px;
text-align: left;
width: 240px;
}
</style>
</head>
<body>
	<jsp:include page="_HoangHien_04_header.jsp"></jsp:include>
	<div id="body">
		<div class="header">
			<div>
				<h1>Danh sách Hoạt động</h1>
			</div>
		</div>

	

		<div class="blog">
			<div class="featured">
				<p style="color: red; padding-bottom: 8px;">${errorString}</p>
				<form class="categoriesSelector" method="GET" action="activitiesList" id="catSelector">
					<p>Thể loại: </p>
					<select name="idCategory" onchange="submit()">
						<option value="0"
							id = "catOption_${category.idCategory}">Tất cả</option>
						<c:forEach items='${categoriesList}' var="category">
							<option value="${category.idCategory}"
							id = "catOption_${category.idCategory}">${category.catName}</option>
						</c:forEach>
					</select>
				</form>
				
				<ul>
					<c:forEach items="${activitiesList}" var="activity" >
						<li>
							<img style="width:180px; height: 180px; border-radius: 50%;object-fit: cover;"
							src="${pageContext.request.contextPath}/imagesURL/${activity.idActivities}">
							<div>
								<h1>${activity.title}</h1>
								<span>By Hoàng Hiến on ${activity.time}</span>
								<p>${activity.summary}</p>
								<a href="${pageContext.request.contextPath}/singlePost?idActivities=${activity.idActivities}" 
								class="more">Đọc thêm ...</a>
							</div>
						</li>
					</c:forEach>
				</ul>
				<div style = "text-align: center;" id="pages">
					<p id="pageText">Trang: </p>
					<c:forEach var="i" begin="1" end="${numberOfPages}">
						<a href="${pageContext.request.contextPath}/activitiesList?idCategory=${idCategory}&page=${i}"
						class="page" id="page_${i}">${i}</a>
					</c:forEach>
				</div>
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
			</div>
		</div>
	<jsp:include page="_HoangHien_04_footer.jsp"></jsp:include>
	<script type="text/javascript">
	window.onload = function(){
		var categoryId = '${idCategory}';
		var pages = '${numberOfPages}'
		if (pages==0){
			var pageDiv = document.getElementById('pages');
			var pageText = document.getElementById('pageText');
			pageDiv.visibility = 'hidden';
			pageText.text = "";
		}
		if (categoryId!=0){
			var option = document.getElementById('catOption_'+categoryId);
			option.selected = 'selected';
		}
	}
	</script>
</body>
</html>