<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>帐单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta name="Keywords" content="keyword1,keyword2,keyword3" />
<meta name="Description" content="网页信息的描述" />
<meta name="Author" content="gdcct.gov.cn" />
<meta name="Copyright" content="All Rights Reserved." />
<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
<link href="${path}/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${path}/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="${path}/devAid-v1.1/assets/plugins/font-awesome/css/font-awesome.css">
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/jquery.easing.1.3.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
html, body, section {
	height: 100%;
}

.promo {
	background: #17baef;
	color: #fff;
	padding-top: 50px;
}

.carousel-inner .item img {
	width: 100%;
	height: 100%;
}

.carousel slide {
	height: 300px;
	width: 400px;
}

.container {
	margin: 0 auto;
}

a {
	
}

.title {
	text-align: center;
	margin: 0 auto;
}

.panel-warning {
	margin-bottom: 0px;
	height: 470px;
}

.circle {
	background-color: #d9edf7;
	border-radius: 81px;
	width: 42px;
	cursor: pointer;
	margin: 0 auto;
}

.circle img {
	width: 40px;
	height: 40px;
}
.col-xs-4 {
    margin: 9px 0px;
}
</style>

<script type="text/javascript">
	function toBill(type) {
		$("#bill").prop(
				"src",
				"${path}/web/account/billSelect.do?type=" + type
						+ "&bill.company.type=");
	}
	$(function() {
		toBill(0);
	});
</script>
</head>

<body style="margin: 0px;">
	<!-- Columns are always 50% wide, on mobile and desktop -->

	<div class="panel panel-warning">
		<iframe frameborder="0" width="100%" height="100%" id="bill"></iframe>
	</div>

	<!-- Columns are always 50% wide, on mobile and desktop -->
	<div class="row title">
		<div class="col-xs-4">
			<div class="circle button" onclick="toBill(1)">
				<img src="${path}/images/water.png" alt="水费">
			</div>
		</div>
		<div class="col-xs-4">
			<div class="circle button" onclick="toBill(2)">
				<img src="${path}/images/ligh.png" alt="电费">
			</div>
		</div>
		<div class="col-xs-4">
			<div class="circle button" onclick="toBill(3)">
				<img src="${path}/images/hot.png" alt="燃气费">
			</div>
		</div>
	</div>
</body>

</html>