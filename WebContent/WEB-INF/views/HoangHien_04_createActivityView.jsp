<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ChoiLaHoc.vn - Create New Post</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
	<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
	<style>
	.btn1{
  		border-radius: 4px;
  		background-color: white;
  		border: 2px solid #c5abb4;
  		padding: 4px;
  		cursor: pointer;
  		height: 16px;
	}
	.btn2{
	background: none;
    border: 2px solid #c5abb4;
    color: #a5838e;
    cursor: pointer;
    display: inline;
    font-family: Tahoma;
    font-size: 16px;
    font-weight: normal;
    height: 40px;
    line-height: 36px;
    margin: 15px 15px 15px 0;
    padding: 0;
    text-align: center;
    text-transform: uppercase;
    width: 141px;
	}
	.btn2:hover{
	background: #c8c10d;
	color: white;
	}
	select{
	background: none;
	border: 1px solid #B6ADB0;
	color: #7A6666;
	display: inline;
	font-family:Tahoma;
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
					<h1>Tạo mới hoạt động</h1>
				</div>
			</div>
			<div class="footer">
				<div class="contact">
					<h1>Nhập thông số bài đăng hoạt động</h1>
					<p style="color: red;">${errorString}</p>
					<form method="POST" action="doCreateActivity" enctype="multipart/form-data">
						<input type="text" name="title" placeholder="Nhập tiêu đề hoạt động"
						onblur="this.placeholder=!this.placeholder?'Nhập tiêu đề hoạt động':this.placeholder;" onfocus="this.select()">
						<select name="idCategory">
							<c:forEach items='${categoriesList}' var="category" >
								<option value="${category.idCategory}">
									${category.catName}
								</option>
							</c:forEach>
						</select>
						<textarea name="content" id="editor1" cols="50" rows="7">Nội dung bài đăng</textarea>
						<input style="display:inline;"
						type="submit" value="Đăng bài" id="submit">
						<input style="display:inline;"
						type="button" value="Chọn hình ảnh" id="submit">
						<input id="file" type="file" name="file" accept="image/*"
							 onchange="return readURL(this);">
					</form>
					<ckeditor:replace replace="editor1" basePath="${pageContext.request.contextPath}/ckeditor/" />
				</div>
				<div class="section" style="margin-top:68px;">
					<h1 style="padding:18px;font-size:18px;">HÌNH ẢNH TIÊU ĐỀ</h1>
					<img src="${pageContext.request.contextPath}/images/blank.jpg" alt="Image File"
						style="width:180px; height: 180px; border-radius: 50%;object-fit: cover; display:block;margin:auto;"
						id="thumb">
				</div>
				<button class="btn2" onclick="location='${pageContext.request.contextPath}/userInfo'">Quay lại</button>
			</div>
		</div>
		<script type="text/javascript">
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
        </script>
	<jsp:include page="_HoangHien_04_footer.jsp"></jsp:include>
</body>
</html>