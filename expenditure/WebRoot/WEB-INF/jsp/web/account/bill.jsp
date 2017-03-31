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
	html, body, section{
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
a{

}
	.title {
		text-align: center;
	}
</style>

<script type="text/javascript">
	function toBill(type) {
		//alert(type);
		$("#bill").attr("href", "${path}/web/account/billSelect.do?bill.company.type=" + type)
	}
</script>
</head>

<body style="margin: 0px;">
<!-- Columns are always 50% wide, on mobile and desktop -->
<div class="row title">
	<div class="col-xs-6">
		<h2><a href="javascript:toBill('')">全部</a></h2>
	</div>
	<div class="col-xs-6">
		<h2><a href="javascript:toBill('水费')">水</a></h2>
	</div>
</div>

<div class="panel panel-warning">
	<iframe frameborder="0" width="100%" height="100%" id="bill" ></iframe>
</div>

<!-- Columns are always 50% wide, on mobile and desktop -->
<div class="row title">
	<div class="col-xs-6">
		<h2><a href="javascript:toBill('电费')">电</a></h2>
	</div>
	<div class="col-xs-6">
		<h2><a href="javascript:toBill('煤气费')">煤气</a></h2>
	</div>
</div>
</body>

</html>