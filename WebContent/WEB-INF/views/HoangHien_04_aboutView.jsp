<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChoiLaHoc.vn - About Me</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css">
<script>src="${pageContext.request.contextPath}/js/mobile.js"</script>
<style>
#body div.footer div.sidebar h2{
display: block;
line-height: 18px;
margin: 0 auto 32px;
text-align: center;
width: 193px;
color: #CB3362;
font-family: Tahoma;
font-size: 16px;
font-weight: normal;
}
#body div.footer div h2 {
color: #CB3362;
font-family: Tahoma;
font-size: 24px;
font-weight: normal;
line-height: 24px;
margin: 0px 12px;
padding: 0 0 27px;
text-align: left;
text-transform: uppercase;
}
</style>
</head>
<body>
	<jsp:include page="_HoangHien_04_header.jsp"></jsp:include>
	<div id="body">
		<div class="header">
			<div>
				<h1>Thông tin trang</h1>
			</div>
		</div>
		<div class="body">
			<img src="images/map.jpg" alt="" style="width: 1600px; left:30%">
		</div>
		<div class="footer">
			<div class="sidebar">
				<img style="width:180px; height: 180px; border-radius: 50%;object-fit: cover; margin: 0 48px 24px 48px;"
				src="${pageContext.request.contextPath}/images/avatar.jpg">
				<h1 style="padding: 0; line-height: 28px;">Hoàng Hiến</h1>
				<h2>Author</h2>
				<p>Tôi - một sinh viên chuyên ngành Công Nghệ Phần Mềm của trường Đại Học Sư Phạm Kỹ Thuật Tp.HCM
				với niềm đam mê phát triển phần mềm và trở thành một lập trình viên chuyên nghiệp, không ngừng học hỏi, tiếp thu để nâng cao kiến thức.
				Vì thế, nếu bạn có những ý kiến phản hồi về sản phẩm, tôi rất vui khi nhận được các ý kiến phản hồi từ bạn.</p>
				<p>Liên hệ với tôi qua:
				<p style="color: #3b5998;">Facebook: <a style="color: #3b5998;" href="http://www.facebook.com/RizHoang">Hoàng Hiến</a></p>
				<p style="color: #c71610;">E-mail: <a style="color: #c71610;" href="mailto:bluntz.blakz@gmail.com">Hoàng Hiến</a></p>
			</div>
			<div class="article">
				<h1 style="font-size: 28px;font-weight: bold;">Trang web có gì đặc biệt?</h1>
				<h2>Mọi thứ đều miễn phí!</h2>
				<p>Các thông tin hữu ích giúp phát triển các mầm non tương lai của chúng ta dường như vô giá. Quá khó để tôi định giá cho các thông tin của sản phẩm :))</p>
				<p>Bạn hoàn toàn được tự do sử dụng thông tin của trang web, bất kể là sao chép, tải xuống, chụp hình, share,... .Miễn là bạn chấp thuận <a href="">Các điều khoản sử dụng</a>. Thậm chí bạn có thể sao chép mã nguồn của trang nếu bạn muốn.</p>
				<h2>Luôn luôn liên tục cập nhật</h2>
				<p>Bạn muốn có nhiều thông tin hơn? Bạn không hài lòng về các lỗi của trang? Hay bạn muốn có thêm các chức năng hữu ích? Chỉ cần bạn liên hệ với tôi, tôi sẽ cập nhật và đáp ứng những yêu cầu bạn cần một cách nhanh nhất! Tôi luôn luôn thích tìm tòi, thử thách!</p>
			</div>
		</div>
	</div>
	<jsp:include page="_HoangHien_04_footer.jsp"></jsp:include>
</body>
</html>