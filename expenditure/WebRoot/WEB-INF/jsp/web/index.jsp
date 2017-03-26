<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>付费易脚</title>
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
          <h2>Heading</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
        
		<div class="row">
			<div class="col-xs-2">&nbsp;</div>
			<div class="col-xs-8">
				<ul class="nav nav-pills" role="tablist">
					<li role="presentation" class="active">
						<div>
					  		<a href="#">水费</a>
						</div>
					</li>
					<li role="presentation" class="active">
						<div>
					  		<a href="#">电费</a>
						</div>
					</li>
					<li role="presentation" class="active">
						<div>
					  		<a href="#">煤气费</a>
						</div>
					</li>
				</ul>
			</div>
			<div class="col-xs-2">&nbsp;</div>
		</div>
		
	</div>
	



	</section>
	<!--//promo-->
</body>

</html>