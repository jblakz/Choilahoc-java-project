<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChoiLaHoc.vn - Manage</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<style type="text/css">
h1,#body div.blog .sidebar h1#cath1	{
color: #CB3362;
font-family:Tahoma;
font-size: 24px;
font-weight: normal;
line-height: 24px;
margin: 0;
text-transform: uppercase;
}
#body div.blog .sidebar#category{
background-color: white;
float: left;
padding: 36px 0 34px;
width: 320px;
padding-top:30px;
margin: -30px 10px 0 40px;
}
p {
color: #7a6666;
display: block;
font-family: Tahoma;
font-size: 16px;
font-weight: normal;
line-height: 16px;
margin: 32px 0 0;
padding: 0;
text-align: left;
width: 560px;
}
table {
 color: #7A6666;
  font-family: Tahoma;
  font-size: 14px;
  text-transform: uppercase;
  text-align: left;
  line-height: 40px;
  border-collapse: separate;
  border-spacing: 0;
  border: 2px solid #c5abb4;
  width: 90%;
  margin: 24px auto;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,.16);
  border-radius: 2px;
}

thead tr:first-child {
  border: none;
}

th {font-weight: bold;}
th:first-child, td:first-child {padding: 0 5px 0 20px;}
.category th:first-child, .category td:first-child {padding: 0 18px 0 20px;}

thead tr:last-child th {border-bottom: 2px solid #c5abb4;}

tbody tr:hover {background-color: rgba(200, 193, 13,.2);}
tbody tr:last-child td {border: none;}
tbody td {border-bottom: 1px solid #ddd;}

td:last-child {
  text-align: right;
  padding-right: 10px;
}
.btn1{
display: block;
font-family: Tahoma;
  border-radius: 4px;
  background-color: white;
  border: 2px solid #c5abb4;
  color: #a5838e;
  text-align: center;
  font-size: 16px;
  padding: 10px;
  width: 120px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 30px auto;
}
.btn1:hover{
	background-color: #c8c10d;
	border: 2px solid #c5abb4;
	color: white;
}
.button {
  color: #aaa;
  cursor: pointer;
  vertical-align: middle;
}

.edit:hover {
  color: #0a79df;
}

.delete:hover {
  color: #dc2a2a;
}
.popuptext {
	font-family: Tahoma;
	font-size: 14px;
    visibility: hidden;
    box-shadow:inset 0px -3px 0px 0px rgba(0,0,0,.16);
    width: auto;
    background-color: #efefef;
    color: #7a6666;
    text-align: center;
    border-radius: 6px;
    padding: 16px 16px;
    position: absolute;
    z-index: 10;
    bottom: 40%;
    left: 45%;
    margin-left: -80px;
}
span .popuptext {
	font-family: Tahoma;
	font-size: 14px;
    visibility: hidden;
    box-shadow:inset 0px -3px 0px 0px rgba(0,0,0,.16);
    width: auto;
    background-color: #efefef;
    color: #7a6666;
    text-align: center;
    border-radius: 6px;
    padding: 16px 16px;
    position: absolute;
    z-index: 10;
    bottom: 40%;
    left: 45%;
    margin-left: -80px;
}
.popuptext button {
  	border-radius: 4px;
  	background-color: white;
  	border: 2px solid green;
  	box-shadow:inset 0px -2px 0px 0px rgba(0,0,0,.16);
  	color: green;
  	text-align: center;
  	font-size: 16px;
  	padding: 10px;
  	width: 60px;
	margin: 0 0 0 12px;
	z-index: 11;
}
.black_overlay {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  background-color: black;
  z-index: 9;
  visibility: hidden;
}
.delete .shows {
    visibility: visible;
    -webkit-animation: fadeIn 0.2s;
    animation: fadeIn 0.2s;
}
.shows {
    visibility: visible;
}
#body .blog .sidebar form{
border:2px solid #c5abb4;
box-shadow: 0 4px 8px 0 rgba(0,0,0,.16);
bottom: 40%; left: 35%; background: white;
padding: 20px 30px; visibility:hidden; position:absolute;
width: 420px;
z-index: 10;
}
#body .blog .sidebar form input{
	background: none;
	border: 1px solid #B6ADB0;
	color: #7A6666;
	display: block;
	font-family:Tahoma;
	font-size: 14px;
	font-weight: normal;
	height: 38px;
	line-height: 38px;
	margin: 0 0 8px;
	padding: 0 0 0 10px;
	text-align: left;
	width: 95%;
}
#body .blog .sidebar form input#submit{
	background: white;
    border: 2px solid #c5abb4;
    color: #a5838e;
    cursor: pointer;
    font-family: Tahoma;
    font-size: 16px;
    font-weight: normal;
    height: 40px;
    line-height: 36px;
    margin: 15px 15px 5px 0px;
    padding: 0;
    text-align: center;
    text-transform: uppercase;
    width: 120px;
}
#body .blog .sidebar form label{
color: #7A6666;
display: inline;
font-family: Tahoma;
font-size: 14px;
text-align: center;
max-width: 100%;
margin-bottom: 5px;
font-weight: bold;
}
@-webkit-keyframes fadeIn {
    from {opacity: 0;} 
    to {opacity: 1;}
}

@keyframes fadeIn {
    from {opacity: 0;}
    to {opacity:1 ;}
}
</style>
</head>
<body>
	<jsp:include page="_HoangHien_04_header.jsp"></jsp:include>
	<div id="body">
		<div class="header">
			<div>
				<h1>Quản lý nội dung</h1>
			</div>
		</div>
		<div class="blog" style="max-width:1280px; padding-top:36px">
		<div class="featured"style="width:820px; border-right: 0.5px solid rgba(60, 60, 60, 0.2);">
			<h1>Các bài đăng:</h1>
			<p style="color: red; padding-bottom: 8px; display:inline; line-height: 48px;">${errorString}</p>
			<p style="color: green; padding-bottom: 8px; display:inline; line-height: 48px;">${infoString}</p>
			<a href="${pageContext.request.contextPath}/createActivity"
			style="float:right; margin-right:42px;" class="btn btn-primary btn1" >	
					<i class="material-icons" style="font-size:30px;">plus_one</i>
					<br />Tạo mới</a>
  			<table id="activitiesTB">
				<thead>
    				<tr>
      					<th>ID</th>
      					<th>ID T.Loại</th>
      					<th>Hình ảnh</th>
      					<th colspan="3">Tiêu đề</th>
    				</tr>
  				</thead>
  				<tbody>
  					<c:forEach items="${activitiesList}" var="activity" >
    				<tr>
      					<td>${activity.idActivities}</td>
      					<td>${activity.idCategory}</td>
      					<td><img style="width:40px; height: 40px; border-radius: 50%;object-fit: cover; margin: 8px;"
      					src="${pageContext.request.contextPath}/imagesURL/${activity.idActivities}"></td>
      					<td><a href="${pageContext.request.contextPath}/singlePost?idActivities=${activity.idActivities}"
      					style="text-decoration: underline;">${activity.title}</a></td>
      					<td>
        					<span class="material-icons button edit"
        					onclick="location='${pageContext.request.contextPath}/edit?idActivities=${activity.idActivities}'">edit</span>
        					<span class="material-icons button delete"
        					onclick="deleteActPopup(${activity.idActivities})">delete
        						<span class="popuptext" id="deleteActPopup_${activity.idActivities}">Xóa bài đăng có ID ${activity.idActivities}?
        							<button onclick="location='${pageContext.request.contextPath}/delete?idActivities=${activity.idActivities}'">OK</button>
        							<button style="color:red;border: 2px solid red;z-index: 9;">Hủy</button>
        						</span>
        					</span>
      					</td>
    				</tr>
    				</c:forEach>
  				</tbody>
			</table>
		</div>
		<div class="sidebar" id="category" style="background:none;">
			<h1 id="cath1" style="padding: 0 0 0 0;">Các thể loại:</h1>
			<button onclick="createCatPopup(1)" class="btn btn-primary btn1" >	
					<i class="material-icons" style="font-size:30px;">plus_one</i>
					<br />Tạo mới</button>
			<table class="category" style="width:100%; margin-top:10px">
				<thead>
    				<tr>
      					<th>ID</th>
      					<th colspan="3">Tiêu đề</th>
    				</tr>
  				</thead>
  				<tbody>
  					<c:forEach items="${categoriesList}" var="category" >
    				<tr>
      					<td>${category.idCategory}</td>
      					<td>${category.catName}</td>
      					<td>
        					<div class="material-icons button edit"
        					onclick="editCatPopup(${category.idCategory},'${category.catName}')">edit
        					</div>
        					<div class="material-icons button delete"
        					onclick="deleteCatPopup(${category.idCategory})">delete
        						<span class="popuptext" id="deleteCatPopup_${category.idCategory}"
        						style="padding:16px;">Xóa thể loại có ID ${category.idCategory}?
        							<button onclick="location='${pageContext.request.contextPath}/deleteCat?idCategory=${category.idCategory}'">OK</button>
        							<button style="color:red;border: 2px solid red;z-index: 9;">Hủy</button>
        						</span>
        					</div>
      					</td>
    				</tr>
    				</c:forEach>
  				</tbody>
			</table>
			<form action="doCreateEditCat" method="POST" id="editCatForm">
				<label>ID thể loại</label>
				<input type="text" name="idCategory" id="idCategory">
				<label>Tên thể loại</label>
				<input type="text" name="catName" id="catName">
				<input style="display:inline; color:green; border: 2px solid green;" type="submit" value="OK" id="submit">
				<input style="display:inline; color:red; border: 2px solid red;" type="button" id="submit" value="Hủy"
				onclick="editCatPopup(0,0)">
			</form>
			<form action="doCreateEditCat" method="POST" id="createCatForm">
				<label>Tên thể loại</label>
				<input type="text" name="catName" id="catName">
				<input style="display:inline; color:green; border: 2px solid green;" type="submit" value="OK" id="submit">
				<input style="display:inline; color:red; border: 2px solid red;" type="button" id="submit" value="Hủy"
				onclick="createCatPopup(0)">
			</form>
		</div>
	</div>
</div>
	<div id="fade" class="black_overlay"></div>
	<script type="text/javascript">
	function deleteActPopup(number) {
	    var popup = document.getElementById('deleteActPopup_'+number);
	    popup.classList.toggle('shows');
	    var fade = document.getElementById('fade');
	    fade.classList.toggle('shows');
	}
	</script>
	<script type="text/javascript">
	function deleteCatPopup(number) {
	    var popup = document.getElementById('deleteCatPopup_'+number);
	    popup.classList.toggle('shows');
	    var fade = document.getElementById('fade');
	    fade.classList.toggle('shows');
	}
	</script>
	<script type="text/javascript">
	function editCatPopup(number, name) {
		if(number==0){
			var popup = document.getElementById('editCatForm');
	    	popup.style.visibility = "hidden";
	    	var fade = document.getElementById('fade');
	    	fade.style.visibility = "hidden";
		}
		else{
			var popup = document.getElementById('editCatForm');
	   		popup.style.visibility = "visible";
	    	var fade = document.getElementById('fade');
	    	fade.style.visibility = "visible";
	    	var id = document.getElementById('idCategory');
	    	var catName = document.getElementById('catName');
	    	catName.value=name;
	    	id.value=number;
	    	id.readOnly = true;
		}
	}
	</script>
	<script type="text/javascript">
	function createCatPopup(show) {
		if(show == 1){
			var popup = document.getElementById('createCatForm');
	    	popup.style.visibility = "visible";
	    	var fade = document.getElementById('fade');
	    	fade.style.visibility = "visible";
		}
	    else{
	    	var popup = document.getElementById('createCatForm');
	    	popup.style.visibility = "hidden";
	    	var fade = document.getElementById('fade');
	    	fade.style.visibility = "hidden";
	    }
	}
	</script>
	<jsp:include page="_HoangHien_04_footer.jsp"></jsp:include>
</body>
</html>