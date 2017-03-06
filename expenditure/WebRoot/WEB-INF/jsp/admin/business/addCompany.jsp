<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-添加机构</title>
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
				
				/** 为文本框绑定失去焦点事件 */
				var isExist = true;
				$("#name").blur(function(){
					var name = $.trim(this.value);
					if (true){
						/** 异步验证用户名是否重复 */
						$.post("${path}/admin/business/validComAjax.jspx", {name : name}, function(data, status){
							// status : success、error
							if (status == "success"){
								//isExist = eval(data);
								isExist = $.parseJSON(data);// true|[]|{}
								if (isExist){
									alert("机构名已存在！");
								}
							}else{
								alert("数据加载失败！");
							}
							
						}, "text");
					}
				});
				
				/** 添加用户，提交表单函数 */
				$("#btn_submit").click(function(){
					// 对表单中所有字段做校验
					var name = $("#name");
					var type = $("#type");
					var price = $("#price");
					var msg = "";
					if (isExist){
						msg = "机构名重复!";
					}else if ($.trim(name.val()) == ""){
						msg = "机构名不能为空!";
						name.focus();
					}else if ($.trim(type.val()) == ""){
						msg = "机构类型不能为空!";
						type.focus();
					}else if ($.trim(price.val()) == ""){
						msg = "单价不能为空!";
						price.focus();
					}
					// 直接提交
					if (msg != ""){
						alert(msg);
					}else{
						$.post("${path}/admin/business/addCompany.jspx", $("#addCompanyForm").serialize(), function(data, status){
							if (status == "success"){
								$("#errors").empty();
								if (data == 0){
									$("#resetBtn").trigger("click");
									$("#tip").text("添加成功！");
								}else if(data == 1){
									$("#tip").text("添加失败！");
								}else{
									for (var key in data){
										$("<li/>").text(data[key]).appendTo("#errors");
									}
								}
							}
						}, "json").fail(function(){
							$("#tip").text("添加失败！");
						});
					}
				});
				showUnit();
			});
			function showUnit() {
				var p = $("#type").val();
				var u = "";
				if (p == "水费") u = "m³";
				if (p == "电费") u = "度";
				if (p == "煤气费") u = "升";
				$("#unit").text(u);
			}
			function showUnitAndRest(){
				$("#resetBt").trigger("click");
				showUnit();
			}
		</script>
	</head>
<body>
	<ul id="errors">
	</ul>
	<table align="center">
		<s:form id="addCompanyForm" theme="simple">
			
			<tr><td colspan="2"></td></tr>
			<tr>
				<td>机构名：</td>
				<td>
					<s:textfield name="company.name" size="18" id="name"/>
				</td>
			</tr>
			<tr>
				<td>机构类型：</td>
				<td>
					<s:select name="company.type" onchange="showUnit()" list="#{'水费':'水费', '电费':'电费','煤气费':'煤气费'}" id="type"/>
				</td>
			</tr>
			<tr>
				<td>单价：</td>
				<td>
					<s:textfield name="company.price" size="18" maxlength="10" id="price"/>元/<span id="unit">m³</span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input value="提 交" type="button" id="btn_submit" />
					&nbsp;<input type="reset" id="resetBt" hidden="hidden">
					<input value="重 置" onclick="showUnitAndRest()" type="button" id="resetBtn"/>&nbsp;<font color="red" id="tip"></font>
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	