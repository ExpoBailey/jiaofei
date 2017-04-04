<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>登录用户</title>
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
		function login(){
			var userId = $("#userId");
				var password = $("#password");
				var msg = "";
				$("#userId-tip").text(msg);
				$("#password-tip").text(msg);
				if ($.trim(userId.val()) == "") {
					//userId.focus(); // 获取焦点
				} else if (!/^\w{5,20}$/.test($.trim(userId.val()))){
					msg = "用户名格式不正确！";
					$("#userId-tip").text(msg);
					//userId.focus(); // 获取焦点
				} else if ($.trim(password.val()) == ""){
					//password.focus(); // 获取焦点
				} else if (!/^\w{6,20}$/.test($.trim(password.val()))){
					msg = "密码格式不正确！";
					$("#password-tip").text(msg);
					//password.focus(); // 获取焦点
				}
				if (msg != "") {

				} else {
				$.ajax({
					url : "/expenditure/web/loginAjax.do",
					type : "POST",
					dataType : "json",
					data : {"userId" : $.trim(userId.val()), "password" : $.trim(password.val())},
					async : true,
					success : function(data) {
						if (data.status == 1) {
							// 登录成功 
							window.location.reload(true);
						} else {
							alert(data.des);
						}
					},
					error : function() {
						
					}
				});
				//alert("22222222222");
			}
		}
	
		$(function(){
			$("#submit").on("click",login);
		});

	</script>
	<style type="text/css">
		.form-signin {
			width: 250px;
			margin: 0 auto;
		}
		.reset-password {
			float: right;
		}
		.form-signin-heading {
			color: #444;
		}
		.tip {
			color:red;
		}
	</style>
</head>


<body style="margin:0px;">
      <form class="form-signin" id="from">
        <h3 class="form-signin-heading">登录</h3>
        <div class="form-group">
	        <label for="inputEmail" >用户名：</label><span class="tip" id="userId-tip"></span>
	        <input type="text" name="userId" id="userId" class="form-control" placeholder="用户名" required autofocus>
        </div>
        <div class="form-group">
	        <label for="inputPassword" >密码：</label><span class="tip" id="password-tip"></span>
	        <input type="password" name="password" id="password" class="form-control" placeholder="密码" required>
        </div>
        <p></p>
        <div class="reset-password">
          <label>
            <a href="${path}/admin/">忘记密码?</a>
          </label>
        </div>
        <p></p>
        <button type="button" class="btn btn-primary btn-block" id="submit">登录</button>
      </form>

</body>

</html>