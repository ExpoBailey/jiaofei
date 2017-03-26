<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>付费易</title>
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
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script>
		function setURL(id,url){
			if (url == null || url =="") {
				url = "/expenditure/web/index.do";
			}
			$("#"+id).attr("src", url);
		}
		$(function(){
//			setURL("headIframe","/expenditure/web/head.do");
//			setURL("footIframe","/expenditure/web/foot.do");
			setURL("mainIframe","");
		});



	</script>
	<style type="text/css">
		.main {
			margin-top: 70px;
			
			height: 527px;
		}
	</style>
</head>


<body style="margin:0px;">
	<div class="container-fluid">
		<div class="row">
			<%@include file="head.jsp"%>
		</div>
		<div class="main row">
			<iframe id="mainIframe" frameborder="0" width="100%" height="100%" ></iframe>
		</div>
		<div class="row">
			<%@include file="foot.jsp"%>
		</div>
	</div>
</body>

</html>