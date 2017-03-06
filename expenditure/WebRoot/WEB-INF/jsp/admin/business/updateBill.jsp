<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-修改帐单</title>
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
						$("<option/>").val(this.userId).html(this.userId+"\t"+this.name).appendTo("#pertain");
					});
					$("#pertain").val("${bill.pertain.userId}");
				}, "json");
				
				/** 修改帐单，提交表单函数 */
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
						$("#updateBillForm").submit();
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
							if (this.id == "${bill.company.id}") {
								$("#comSelect").val(this.id);
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
<!-- 输出后台校验出错的提示信息 -->
<s:fielderror cssStyle="font-size:12px;color:red;"/>
<!-- 输出防表单重复提交的提示信息 -->
<s:actionerror cssStyle="font-size:12px;color:red;"/>
	<table align="center">
		<s:form id="updateBillForm" action="/admin/business/updateBill.jspx" method="post" theme="simple">
			<tr><td colspan="2"></td></tr>
			<!-- 防表单重复提交需要的参数 -->
			<s:token></s:token>
			<tr>
				<td>出帐机构：</td>
				<td>
					<s:hidden name="bill.id"/>
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
					<input value="重 置" onclick="showUnitAndRest()" type="button" id="resetBtn"/>&nbsp;<font color="red" id="tip">${tip}</font>
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	