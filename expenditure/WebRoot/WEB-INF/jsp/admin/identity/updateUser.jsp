<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-修改用户</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
		<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
		<meta name="Description" content="网页信息的描述" />
		<meta name="Author" content="fkjava.org" />
		<meta name="Copyright" content="All Rights Reserved." />
		<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript">
			$(function(){


				/** 修改用户，提交表单函数 */
				$("#btn_submit").click(function(){
					// 对表单中所有字段做校验
					var name = $("#name");
					var email = $("#email");
					var tel = $("#tel");
					var phone = $("#phone");
					var qqNum = $("#qqNum");
					var answer = $("#answer");
					var msg = "";
					if ($.trim(name.val()) == ""){
						msg = "姓名不能为空!";
						name.focus();
					}else if ($.trim(email.val()) == ""){
						msg = "邮箱不能为空!";
						email.focus();
					}else if (!/^\w+@\w{2,}\.\w{2,}$/.test(email.val())){
						msg = "邮箱格式不正确!";
						email.focus();
					}else if ($.trim(tel.val()) == ""){
						msg = "电话号码不能为空!";
						tel.focus();
					}else if (!/^0\d{2,3}-?\d{7,8}$/.test(tel.val())){
						msg = "电话号码格式不正确!";
						tel.focus();
					}else if ($.trim(phone.val()) == ""){
						msg = "手机号码不能为空!";
						phone.focus();
					}else if (!/^1[3|4|5|7|8]\d{9}$/.test(phone.val())){
						msg = "手机号码格式不正确!";
						phone.focus();
					}else if ($.trim(qqNum.val()) == ""){
						msg = "QQ号码不能为空!";
						qqNum.focus();
					}else if (!/^\d{5,12}$/.test(qqNum.val())){
						msg = "QQ号码长度必须在5-12之间!";
						qqNum.focus();
					}else if ($.trim(answer.val()) == ""){
						msg = "密保问题不能为空!";
						answer.focus();
					}
					// 直接提交
					if (msg != ""){
						alert(msg);
					}else{
						$("#updateUserForm").submit();
					}
				});
				
			});
		</script>
	</head>
<body>
	<!-- 输出后台校验出错的提示信息 -->
	<s:fielderror cssStyle="font-size:12px;color:red;"/>
	<!-- 输出防表单重复提交的提示信息 -->
	<s:actionerror cssStyle="font-size:12px;color:red;"/>
	<table align="center">
		<s:form id="updateUserForm" action="/admin/identity/updateUser.jspx" method="post" theme="simple">
			<s:hidden name="user.userId"/>
			<!-- 防表单重复提交需要的参数 -->
			<s:token></s:token>
			<tr><td colspan="4"></td></tr>
			<tr>
				<td>登&nbsp;录&nbsp;名：</td>
				<td>
					<s:textfield name="user.userId" disabled="true" size="18" id="userId"/>
				</td>
				<td>用户姓名：</td>
				<td>
					<s:textfield name="user.name" size="18" maxlength="20" id="name"/>
				</td>
			</tr>
			<tr>
				<td>用户密码：</td>
				<td>
					<s:password name="user.password" size="18" id="passWord"/>
				</td>
				<td>重输密码：</td>
				<td>
					<s:password name="repwd" size="18" id="repwd"/>
				</td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<s:select name="user.sex" list="#{1:'男', 2 :'女'}"/>
				</td>
				<td>邮&nbsp;&nbsp;箱：</td>
				<td>
					<s:textfield name="user.email" size="18" maxlength="50" id="email"/>
				</td>
			</tr>
			<tr>
				<td>电&nbsp;&nbsp;话：</td>
				<td>
					<s:textfield name="user.tel" size="18" id="tel"/>
				</td>
				<td>手&nbsp;&nbsp;机：</td>
				<td>
					<s:textfield name="user.phone" size="18" maxlength="11" id="phone"/>
				</td>
			</tr>
			<tr>
				<td>QQ号码：&nbsp;</td>
				<td>
					<s:textfield name="user.qqNum" size="18" maxlength="20" id="qqNum"/>
				</td>
				<td>问&nbsp;&nbsp;题：</td>
				<td>
					<s:select name="user.question"
							  list="#{0:'您的生日？',1:'您父亲的姓名？',2:'您的出生地？',3:'您母亲的名字？'}"/>
				</td>
			</tr>
			<tr>
				<td>结&nbsp;&nbsp;果：</td>
				<td colspan="3">
					<s:textfield name="user.answer" size="48" maxlength="50" id="answer"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input value="提 交" type="button" id="btn_submit" />
					&nbsp;
					<input value="重 置" type="reset" />&nbsp;<font color="red">${tip}</font>
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	