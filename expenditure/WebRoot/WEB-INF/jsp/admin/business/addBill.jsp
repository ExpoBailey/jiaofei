<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-添加帐单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
		<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
		<meta name="Description" content="网页信息的描述" />
		<meta name="Author" content="fkjava.org" />
		<meta name="Copyright" content="All Rights Reserved." />
		<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
		<link href="${path}/js/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			var allCom;
			$(function(){
				
				$.get("${path}/admin/identity/loadAllUserAjax.jspx", {}, function(data){
					$.each(data, function(){
						$("<option/>").val(this.userId).html(this.userId + "\t" + this.name).appendTo("#pertain");
					});
				}, "json");
				
				/** 添加用户，提交表单函数 */
				$("#btn_submit").click(function(){
					// 对表单中所有字段做校验
					var comSelect = $("#comSelect");
					var usageAmount = $("#usageAmount");
					var endDateStr = $("#endDateStr");
					var pertain = $("#pertain");
					var msg = "";
					if ($.trim(comSelect.val()) == ""){
						msg = "机构不能为空!";
						comSelect.focus();
					}else if ($.trim(usageAmount.val()) == ""){
						msg = "使用量不能为空!";
						usageAmount.focus();
					}else if ($.trim(endDateStr.val()) == ""){
						msg = "出帐时间不能为空!";
						endDateStr.focus();
					}else if ($.trim(pertain.val()) == ""){
						msg = "所属人不能为空!";
						pertain.focus();
					}
					// 直接提交
					if (msg != ""){
						alert(msg);
					}else{
						$.post("${path}/admin/business/addBill.jspx", $("#addBillForm").serialize(), function(data, status){
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
//				showUnit();

				$.ajax({
					url : "${path}/admin/business/loadAllCompanyAjax.jspx",
					data : "",
					type : "post",
					dataType : "json",
					async : true,
					success : function(data){
						allCom = eval(data);
						$.each(data, function(i){
							$("<option/>").val(this.id).text(this.name).appendTo("#comSelect");
							if (i == 0) {
								$("#priceTip").text(this.price);
								var p = this.type;
								var u = "";
								if (p == "水费") u = "m³";
								if (p == "电费") u = "度";
								if (p == "煤气费") u = "升";
								$("#unitTip").text(u);
                                $("#unitTip2").text(u);
							}
						});

					},
					error : function(data){
						alert("加载机构下拉列表出错！");
					}
				});

			});
//			function showUnit() {
//				var p = $("#type").val();
//				var u = "";
//				if (p == "水费") u = "m³";
//				if (p == "电费") u = "度";
//				if (p == "煤气费") u = "升";
//				$("#unit").text(u);
//			}
			function showUnitAndRest(){
				$("#resetBt").trigger("click");
				changePrice();
			}

			function changePrice(){
				$.each(allCom, function(){
					if (this.id == $("#comSelect").val()) {
						$("#priceTip").text(this.price);
						var p = this.type;
						var u = "";
						if (p == "水费") u = "m³";
						if (p == "电费") u = "度";
						if (p == "煤气费") u = "升";
						$("#unitTip").text(u);
						$("#unitTip2").text(u);
					}
				});
			}
		</script>
	</head>
<body>
	<ul id="errors">
	</ul>
	<table align="center">
		<s:form id="addBillForm" theme="simple">
			
			<tr><td colspan="2"></td></tr>
			<tr>
				<td>出帐机构：</td>
				<td>
					<select name="bill.company.id" id="comSelect" onchange="changePrice()">

					</select>
				</td>
			</tr>
			<tr>
				<td>单价：</td>
				<td>
					￥&nbsp;<span id="priceTip"></span>元/<span id="unitTip"></span>
				</td>
			</tr>
			<tr>
				<td>使用量：</td>
				<td>
					<s:textfield name="bill.usageAmount" size="18" maxlength="50" id="usageAmount"/>&nbsp;<span id="unitTip2"></span>
				</td>
			</tr>
			<tr>
				<td>出帐时间：</td>
				<td>
					<s:textfield name="endDateStr" id="endDateStr" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
				</td>
			</tr>
			<tr>
				<td>所属人：</td>
				<td>
					<select name="bill.pertain.userId" id="pertain">

					</select>
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