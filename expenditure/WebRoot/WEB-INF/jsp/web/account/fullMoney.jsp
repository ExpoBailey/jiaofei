<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的帐户--帐号充值</title>
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
		width: 95%;
	}


a{
	color: #FFFFFF
}
.form-horizontal {
	position: relative; /*脱离文档流*/
	top: 50%; /*偏移*/
	transform: translateY(-50%);
	display:inline-block; 
	*display:inline; 
	*zoom:1; 
}
</style>

<script type="text/javascript">
	$(function(){
		// 异步提交
		$("#full").click(function(){
			var id = $("#id");
			var regPW = $("#regPW");
			var tranPW = $("#tranPW");
			var num = $("#num");
			var msg = "";
			$("#userId-tip").text(msg);
			$("#password-tip").text(msg);
			if ($.trim(id.val()) == "") {
				id.focus(); // 获取焦点
				msg = "帐号不能为空！";
			} else if ($.trim(regPW.val()) == ""){
				msg = "登录密码不能为空！";
				regPW.focus(); // 获取焦点
			} else if ($.trim(tranPW.val()) == ""){
				msg = "交易密码不能为空！";
				tranPW.focus(); // 获取焦点
			} else if ($.trim(num.val()) == ""){
				msg = "交易金额不能为空！";
				num.focus(); // 获取焦点
			}
			if (msg != "") {
				alert(msg);
			} else {
				$.ajax({
					url:"${path}/web/account/fullMoneyAjax.do",
					type:"post",
					data:$("#form").serialize(),
					dataType:"json",
					success:function(data){
						if (data.status == "1") {
							alert("充值成功");
							localtion.reload();
						} else {
							alert(data.des);
						}
					},
					error:function(){

					}
				});
			}
		});
	});
</script>
</head>

<body style="margin: 0px;">
	<form class="form-horizontal" id="form" method="post">
		<div class="form-group">
			<label for="id" class="col-sm-2 control-label">付费易余额</label>
			<div class="col-sm-3">
				<div class="input-group">
			      <div class="input-group-addon">￥</div>
			      <input type="text" class="form-control" value="${ffy.sum}">
			    </div>
			</div>
		</div>
		<div class="form-group">
			<label for="id" class="col-sm-2 control-label">充值方式</label>
			<div class="col-sm-5">
				<label class="radio-inline">
					<input type="radio" name="from.from" checked="checked" id="zhi" value="支付宝"> 支付宝
				</label>
				<label class="radio-inline">
					<input type="radio" name="from.from" id="weChat" value="微信"> 微信
				</label>
			</div>
		</div>
		<div class="form-group">
			<label for="id" class="col-sm-2 control-label">帐号</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="from.id" id="id" placeholder="手机号">
			</div>
		</div>
		<div class="form-group">
			<label for="regPW" class="col-sm-2 control-label">登录密码</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="regPW" name="from.regPW" placeholder="登录密码">
			</div>
		</div>
		<div class="form-group">
			<label for="tranPW" class="col-sm-2 control-label" >交易密码</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="tranPW" name="from.tranPW" placeholder="交易密码">
			</div>
		</div>
		<div class="form-group">
			<label for="num" class="col-sm-2 control-label">充值金额</label>
			<div class="col-sm-2">
				<select class="form-control" name="from.num" id="num">
					<option value="10.0">10 元</option>
					<option value="20.0">20 元</option>
					<option value="50.0">50 元</option>
					<option value="100.0">100 元</option>
					<option value="200.0">200 元</option>
					<option value="500.0">500 元</option>
					<option value="1000.0">1000 元</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" id="full" class="btn btn-primary">马上充值</button>
			</div>
		</div>
	</form>
</body>

</html>