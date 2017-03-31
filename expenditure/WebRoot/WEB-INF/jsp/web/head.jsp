<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>付费易头</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="gdcct.gov.cn" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${path}/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${path}/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${path}/devAid-v1.1/assets/plugins/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="${path}/devAid-v1.1/assets/plugins/prism/prism.css">
	<link id="theme-style" rel="stylesheet" href="${path}/devAid-v1.1/assets/css/styles.css">
	<script type="text/javascript" src="${path}/devAid-v1.1/assets/plugins/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/devAid-v1.1/assets/plugins/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="${path}/devAid-v1.1/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${path}/devAid-v1.1/assets/plugins/jquery-scrollTo/jquery.scrollTo.min.js"></script>
	<script type="text/javascript" src="${path}/devAid-v1.1/assets/plugins/prism/prism.js"></script>
	<script type="text/javascript" src="${path}/devAid-v1.1/assets/js/main.js"></script>
	<style type="text/css">
		.modal-content{
			width: 400px;
			margin: 0 auto;
		}
		#myModalLabel{
			color: black;
		}
		.header {
			border-bottom: 1px solid #ccc;
		}
	</style>
</head>

<body style="margin:0;">
<div id="fb-root"></div>
<script>;(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) return;
	js = d.createElement(s); js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

	
	window.isLogin = false; // 未登录
	
	$(function(){
		$.get("${path}/web/checkLogin.action", function(data){
			isLogin = data.isLogin;
			var login_exit = $("#login-exit");
			if (data.isLogin) {
				// 已经登录了，改成退出标签
				//<a class="scrollto" href="#" >退出</a>
				login_exit.empty();
				$("<a/>").addClass("scrollto").prop("href", "${path}/web/exit.action").text("退出").appendTo(login_exit);
			} else {
				//<a class="scrollto" href="#" data-toggle="modal" data-target="#myModal">登录</a>
				//$("<a class='scrollto' href='#' data-toggle='modal' data-target='#myModal'>登录</a>").appendTo(login_exit);
			}
		});
	});
	
	function toMain(url){
		if (!isLogin) {
			$('#myModal').modal('show');
			return false;
		}
		var iframe = $("#mainIframe", parent.document);
		iframe.attr("src", url);
	}
</script>

<!-- ******HEADER****** -->
<header id="header" class="header">
	<div class="container">
		<h1 class="logo pull-left">
			<a class="scrollto" href="#promo">
				<span class="logo-title">付费易</span>
			</a>
		</h1><!--//logo-->
		<nav id="main-nav" class="main-nav navbar-right" role="navigation">
			<div class="navbar-header">
				<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button><!--//nav-toggle-->
			</div><!--//navbar-header-->
			<div class="navbar-collapse collapse" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active nav-item sr-only"><a class="scrollto" href="#promo">Home</a></li>
					<li class="nav-item"><a class="scrollto" onclick="toMain('${path}/web/account/myAccount.do')">我的帐户</a></li>
					<li class="nav-item"><a class="scrollto" onclick="toMain('${path}/web/account/bill.do')">帐单查询</a></li>
					<li class="nav-item"><a class="scrollto" onclick="toMain('${path}/web/account/account/newBillSelect.do')">未缴帐单</a></li>
					<li class="nav-item last" id="login-exit"><a class="scrollto" href="#" data-toggle="modal" data-target="#myModal">登录</a></li>
				</ul><!--//nav-->
			</div><!--//navabr-collapse-->
		</nav><!--//main-nav-->
	</div>
</header><!--//header-->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">付费易</h4>
      </div>
      <div class="modal-body">
        <%@include file="login.jsp" %>
      </div>
      <div class="modal-footer">
      	<br/>
      </div>
    </div>
  </div>
</div>
</body>

</html>