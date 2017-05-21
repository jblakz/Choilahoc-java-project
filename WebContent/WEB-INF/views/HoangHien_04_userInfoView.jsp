<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChoiLaHoc.vn - Admin Info</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
<style>
.btn1{
	display: inline-block;
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
  margin: 5px;
}
.btn1:hover{
	background-color: #c8c10d;
	border: 2px solid #c5abb4;
}
.btn1:click{
	background-color: #b5ae0a;
	border: 2px solid #c5abb4;
}
.btn1:focus{
	background-color: #b5ae0a;
	border: 2px solid #c5abb4;
}
#body div.footer .section2 {
	float: left;
	margin: 80px 0 0 50px;
	max-width: none;
	padding: 0;
	width: 280px;
}
</style>
</head>
<body>
	<jsp:include page="_HoangHien_04_header.jsp"></jsp:include>
	<div id="body" class="contact">
		<div class="header">
				<div>
					<h1>Thông tin quản trị viên</h1>
				</div>
			</div>
		<div class="footer">
			<div class="contact">
				<h1>Xin chào, ${user.name} !</h1>
				<p style="color: red;">${errorString}</p>
				<ul class="nav nav-tabs">
      				<li class="active"><a>Thông tin</a></li>
    			</ul>
    			<div id="myTabContent" class="tab-content">
      				<div class="tab-pane active in" id="home">
        				<form id="tab" method="POST" action="doChangeUser">
            				<label>Tên đăng nhập</label>
            				<input type="text" value='${user.username}'
            				onblur="this.value=!this.value?'${user.username}':this.value;"
            				class="input-xlarge" readonly="readonly">
            				<label>Tên quản lý</label>
            				<input type="text" value='${user.name}' name="name"
            				onblur="this.value=!this.value?'${user.name}':this.value;" class="input-xlarge">
           					<label>Email</label>
            				<input type="text" value='${user.email}' name="email"
            				onblur="this.value=!this.value?'${user.email}':this.value;" class="input-xlarge">
            				<label>Mật khẩu cũ</label>
        					<input type="password" name="oldPass" class="input-xlarge">
            				<label>Mật khẩu mới</label>
        					<input type="password" name="newPass" class="input-xlarge">
        					<label>Nhập lại mật khẩu</label>
        					<input type="password" name="retype" class="input-xlarge">
          					<div>
        	    				<input type="submit" class="btn btn-primary btn1" value="Cập nhật" id="submit">
        					</div>
        				</form>
      				</div>
  				</div>
			</div>
			<div class="section2">
				<h2 style="color:#8c8c8c;font-size:24px;">Quản lý nội dung</h2>
				<a href="${pageContext.request.contextPath}/manage" class="btn btn-primary btn1">	
					<i class="material-icons" style="font-size:30px;">refresh</i>
					<br />Chỉnh sửa</a>
				<h2 style="color:#8c8c8c;font-size:24px;">Đăng xuất</h2>
				<a href="${pageContext.request.contextPath}/logout" class="btn btn-primary btn1" >	
					<i class="material-icons" style="font-size:30px;">exit_to_app</i>
					<br />Đăng xuất</a>
			</div>
		</div>
	</div>
	<jsp:include page="_HoangHien_04_footer.jsp"></jsp:include>
</body>
</html>