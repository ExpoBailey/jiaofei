<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的帐号页面</title>
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
<link rel="stylesheet"
	href="${path}/devAid-v1.1/assets/plugins/prism/prism.css">
<link id="theme-style" rel="stylesheet"
	href="${path}/devAid-v1.1/assets/css/styles.css">
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/jquery.easing.1.3.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/jquery-scrollTo/jquery.scrollTo.min.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/prism/prism.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/js/main.js"></script>
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
	color: #FFFFFF
}
.left-list {
    width: 220px;
    text-align: center;
    padding-right: 0px;
    height: 100%
}
.two {
	margin: 0 auto;
	*display:inline; 
	*zoom:1; 
}
.panel-body {
	height: 450px;
}
.list-group-item .active {
	background-color: #ccc;
}
</style>

<script type="text/javascript">
	function toPanel(url,obj){
		alert(obj);
		// 切换列表
		$(".active").removeClass("active");
		$(obj).addClass("active");
		// 跳到页面
		$("#panel").attr("src", url);
	}
</script>
</head>

<body style="margin: 0px;">
	<!-- ******PROMO****** -->
	<section id="promo" class="promo section offset-header"> 
	
		<div class="two clearfix">
		  <div class="col-xs-6 col-md-2 left-list">
			  	<div class="list-group">
				  <a href="javascript:toPanel('${path}/web/account/accountInfo.do',this)" class="list-group-item active">帐户信息</a>
				  <a href="javascript:toPanel('${path}/web/account/record.do',this)" class="list-group-item">收支明细</a>
				  <a href="javascript:toPanel('${path}/web/account/fullMoney.do',this)" class="list-group-item">帐户充值</a>
				</div>
		  </div>
		  
		  <div class="col-xs-12 col-md-10"> 
		  		<div class="panel panel-default">
				  <div class="panel-body">
				    <iframe id="panel" frameborder="0" width="100%" height="100%" src="${path}/web/account/accountInfo.do"></iframe>
				  </div>
				</div>
		  </div>
		  
		</div>

	</section>
	<!--//promo-->
</body>

</html>