<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>付费易</title>
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
</style>
</head>

<body style="margin: 0px;">
	<!-- ******PROMO****** -->
	<section id="promo" class="promo section offset-header"> <!-- <div class="container text-center">
		<h2 class="title">dev<span class="highlight">Aid</span></h2>
		<p class="intro">A free mobile-friendly Bootstrap theme designed to help developers
			promote their personal projects</p>
		<div class="btns">
			<a class="btn btn-cta-secondary" href="http://themes.3rdwavemedia.com/" target="_blank">Demo</a>
			<a class="btn btn-cta-primary" href="http://themes.3rdwavemedia.com/website-templates/devaid-free-bootstrap-theme-developers/" target="_blank">Download</a>
		</div>
		<ul class="meta list-inline">
			<li><a href="https://github.com/xriley/devAid-Theme" target="_blank">View on GitHub</a></li>
			<li><a href="http://themes.3rdwavemedia.com/website-templates/devaid-free-bootstrap-theme-developers/" target="_blank">Full Documentation</a></li>
			<li>Created by: <a href="http://themes.3rdwavemedia.com/" target="_blank">Xiaoying Riley</a> at 3rd Wave Media</li>
		</ul>
	</div> --> 
	<div class="container">
	
		<!-- 轮播图 strat-->
		<div class="col-xs-12 col-md-9">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				</ol>
	
				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="${path}/html/images/1.jpg" alt="">
					</div>
					<div class="item">
						<img src="${path}/html/images/2.jpg" alt="">
					</div>
				</div>
	
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<!-- 轮播图 end-->

		<div class="col-md-3">
          <h2 style="color: #FFFFFF">生活之家</h2>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;付费易是新时的产物，旨在为居民提供水电煤气等基本生活用费的查询和支付服务。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;我们，正努力从水电煤拓展成为承包您所有生活账单的贴心管家。</p>
			<p>付费易公司郑重承诺:</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;对用户每笔交易的安全性负责:依法严格管理用户资料</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;——只为让您能更放心、更方便。</p>

        </div>

		
	</div>
	



	</section>
	<!--//promo-->
</body>

</html>