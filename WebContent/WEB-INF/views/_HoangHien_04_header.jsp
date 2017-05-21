<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page Header</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
<style type="text/css">
form#searchForm{
top: 20%; left: 50%;
padding: 20px 30px; visibility:hidden; position:absolute;
width: 420px;
z-index: 10;
}
form#searchForm input{
border: 1px solid #B6ADB0;
box-shadow: 0 4px 8px 0 rgba(0,0,0,.16);
color: #7A6666;
display: inline;
font-family:Tahoma;
font-size: 14px;
font-weight: normal;
height: 32px;
line-height: 38px;
margin: 0 0 8px;
padding: 0 0 0 10px;
text-align: left;
width: 380px;
}
form#searchForm label{
color: #7A6666;
display: inline;
font-family: Tahoma;
font-size: 14px;
text-align: center;
max-width: 100%;
margin-bottom: 5px;
font-weight: bold;
}
.overlay {
position: absolute;
top: 0%;
left: 0%;
width: 100%;
height: 100%;
background-color: black;
z-index: 9;
-moz-opacity: 0;
 opacity: 0;
visibility: hidden;
}
</style>
</head>
<body>
<div id="header">
	<div>
		<a href="${pageContext.request.contextPath}/home" class="logo"><img style="width: 270px;" src="${pageContext.request.contextPath}/images/logo.png" alt=""></a>
		<ul id="navigation">
			<li class="menu">
				<a href="${pageContext.request.contextPath}/home">Trang chủ</a>
			</li>
			<li class="menu">
				<a href="${pageContext.request.contextPath}/about">Thông tin</a>
			</li>
			<li class="menu">
				<a href="${pageContext.request.contextPath}/activitiesList">Bài đăng</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/login">
				<%
				if(session.getAttribute("loginedUser")!=null)
				out.print("Quản lý");
				else out.print("Đăng nhập");
				%>
				</a>
			</li>
			<li>
				<a onclick="searchPopup(true)"><i class="material-icons" style="cursor: pointer;">search</i></a>
			</li>
		</ul>
		<form action="search" method="GET" id="searchForm">
			<input type="text" name="searchString" id="search">
		</form>
	</div>
</div>
<div id="fade" class="overlay" onclick="searchPopup(false)"></div>
<script type="text/javascript">
function searchPopup (swi) {
	if(!swi){
		var popup = document.getElementById('searchForm');
			popup.style.visibility = "hidden";
		var fade = document.getElementById('fade');
		   	fade.style.visibility = "hidden";
	}
	else{
		var popup = document.getElementById('searchForm');
		   	popup.style.visibility = "visible";
		var fade = document.getElementById('fade');
		   	fade.style.visibility = "visible";
	}
}
</script>
</body>
</html>