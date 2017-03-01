<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-角色绑定操作管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			/** 为全选绑定点击事件 */
			$("#checkAll").click(function(){
				/** 获取下面数据行checkbox，让它选中 */
				$("input[id^='box_']").attr("checked", this.checked);
				/** 获取下面所有的tr 触发onmouseover与onmouseout */
				$("tr[id^='tr_']").trigger(this.checked ? "mouseover" : "mouseout");
			});
			/** 获取所有的tr绑定onmouseover与onmouseout事件 */
			$("tr[id^='tr_']").hover(
				function(){ // onmouseover
					$(this).css({"background-color" : "#FFFFBF", "cursor" : "pointer"});
				}, 
				function(){ // onmouseout
					/** 获取当前行中选中的checkbox */
					if ($(this).find("input[id^='box_']:checked").length == 0){
						$(this).css("background-color", "#FFFFFF");
					}
				}
			);
			/** 让全选选中 */
			/** 获取下面数据行checkbox */
			var boxs = $("input[id^='box_']");
			/** 全部绑定点击事件 */
			boxs.click(function(){
				/** 获取选中的checkbox (把选中的过滤出来) */
				$("#checkAll").attr("checked", boxs.filter(":checked").length == boxs.length);
			});
			
			
			/** 获取该角色在模块中已经有的权限，把它转化成Array */
			var operaCodes = $.parseJSON('${operaCodes}');
			boxs.each(function(){
				if (operaCodes.indexOf(this.value) != -1){
					/** 让checkbox选中 */
					$(this).attr("checked", true);
					/** 获取tr */
					$(this).parent().parent().trigger("mouseover");
				}
			});
			/** 让全选选中 */
			$("#checkAll").attr("checked", boxs.length == operaCodes.length);
			
			
			/** 为绑定操作按钮绑定点击事件 */
			$("#bindModule").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				

				/** 调用map方法返回的jQuery对象里面存放的是回调函数的返回值 */
				var codes = boxs.map(function(){
					return this.value;
				});
				window.location.href = "${path}/admin/identity/bindModule.jspx?moduleCode=${moduleCode}&role.id=${role.id}&codes=" + codes.toArray();
			});
			
			/** 返回按钮 */
			$("#backBtn").click(function(){
				parent.window.location.href = "${path}/admin/identity/selectRole.jspx";
			});
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
	<table>
		<tr>
			<td><input type="button" value="绑定操作" id="bindModule"/></td>
			<td><input type="button" value="返回" id="backBtn"/>
				&nbsp;当前角色：【<font color="red">${role.name}</font>】
				&nbsp;<font color="red">${tip}</font></td>
		</tr>
	</table>

	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>名称</th>
			<th>链接</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="modules" status="stat">
				<tr id="tr_${stat.index}" class="listTr">
					<td width="5%"><input type="checkbox" id="box_${stat.index}" value="${code}"/>${stat.count}</td>
					<td><s:property value="code"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="url"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>