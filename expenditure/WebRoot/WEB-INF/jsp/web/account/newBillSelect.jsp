<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>未缴帐单</title>
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
	background: #17baef;
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
.row {
	margin-right: 0px;
	margin-left: 0px;
}
.mybill {
	margin: 0 auto;
	width: 650px;
}
.jumbotron {
	margin-bottom: 0px;
}
</style>

<script type="text/javascript">
	
</script>
</head>

<body>
	<div class="row">
		<div class="mybill">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox" id="mybill">
					<div class="item active">
						<div class="jumbotron">
							<h1>Jumbotron heading</h1>
							<p class="lead">Cras justo odio, dapibus ac facilisis in,
								egestas eget quam. Fusce dapibus, tellus ac cursus commodo,
								tortor mauris condimentum nibh, ut fermentum massa justo sit
								amet risus.</p>
							<p>
								<a class="btn btn-lg btn-success" href="#" role="button">Sign
									up today</a>
							</p>
						</div>
					</div>
					<div class="item">
						<div class="jumbotron">
							<h1>Jumbotron heading</h1>
							<p class="lead">Cras justo odio, dapibus ac facilisis in,
								egestas eget quam. Fusce dapibus, tellus ac cursus commodo,
								tortor mauris condimentum nibh, ut fermentum massa justo sit
								amet risus.</p>
							<p>
								<a class="btn btn-lg btn-success" href="#" role="button">Sign
									up today</a>
							</p>
						</div>
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
	</div>

</body>

</html>