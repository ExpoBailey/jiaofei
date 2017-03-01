<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>水电煤气后台系统-用户管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
	<link href="${path}/js/jqPaginator/bootstrap.min.css" type="text/css" rel="stylesheet"/>
	<link href="${path}/js/jqeasyui/themes/default/easyui.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqPaginator/jqPaginator.min.js"></script>
	<style type="text/css">
		.nameDiv{
			border : 1px solid black;
			width : 120px;
			height : auto;
			position: absolute;
			background-color : #ffffff;
			display : none;
			line-height: 25px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
		
			/** 为全选绑定点击事件 */
			$("#checkAll").click(function(){
				/** 获取下面数据行checkbox，让它选中 */
				$("input[id^='box_']").attr("checked", this.checked);
				/** 获取下面所有的tr 触发onmouseover与onmouseout */
				$("tr[id^='tr_']").trigger(this.checked ? "mouseover" : "mouseout");
			});
			
			
			/** ##################### 用户姓名文本框(联想) ######################## */
			$(window).resize(function(){
				/** 获取文本框的偏移对象 */
				var offset = $("#userName").offset();
				/** 设置nameDiv偏移左边与上面的距离 */
				$("#nameDiv").css("left", offset.left)
			             .css("top", offset.top + $("#userName").outerHeight() + 1)
			             .css("width", $("#userName").innerWidth() + 1);
			});
			/** 定义变量缓存上一次姓名的查询数据 */
			var cache_name = "";
			/** 为姓名文本框绑定onkeyup事件 */
			$("#userName").keyup(function(){
				/** 获取文本框的偏移对象 */
				var offset = $("#userName").offset();
				/** 设置nameDiv偏移左边与上面的距离 */
				$("#nameDiv").css("left", offset.left)
			             .css("top", offset.top + $("#userName").outerHeight() + 1)
			             .css("width", $("#userName").innerWidth() + 1);
				/** 获取文本框中的值 */
				var name = $.trim(this.value);
				/** 判断输入值 */
				if (name != ""){
					/** 判断是不是与上次查询条件一样 */
					if (cache_name != name){
						/** 异步请求查询用户姓名 */
						$.ajax({
							url : "${path}/admin/identity/loadUserNameAjax.jspx",
							type : "post",
							data : {name : name},
							dataType : "json", // {}, [], [{},{}]
							async : true,
							success : function(data){
								// data: ['','']
								/** 把nameDiv中子元素全部删除 */
								$("#nameDiv").empty();
								if (data.length > 0){
									/** 迭代数组 */
									$.each(data, function(){
										// this: 字符串
										$("<div/>").text(this).appendTo("#nameDiv");
									});
									/** 显示nameDiv */
									$("#nameDiv").slideDown(200);
									/** 获取nameDiv里面所有的子元素 */
									$("#nameDiv").children().hover(
										function(){
											/** 先全部删除 */
											$("#nameDiv > div").removeAttr("style");
											$(this).css({"background-color" : "#FFC0CB", "cursor" : "pointer"});
										}, 
										function(){
											$(this).removeAttr("style");// 删除所有的样式
										}
									).click(function(){
										/** 获取div中的文本，放到name文本框 */
										$("#userName").val($(this).text());
										/** 缓存姓名 */
										cache_name = $(this).text();
										/** 隐藏div(往上滑) */
										$("#nameDiv").slideUp(200);
									});
								}
							},
							error : function(){
								alert("数据加载失败！");
							}
						});
					}
					/** 缓存上一次的姓名 */
					cache_name = name;
				}else{
					/** 还原 */
					cache_name = "";
					/** 把nameDiv中子元素全部删除 */
					$("#nameDiv").empty();
					/** 隐藏div(往上滑) */
					$("#nameDiv").slideUp(200);
				}
			});
			/** 为文档绑定点击事件 */
			$(document).click(function(){
				/** 隐藏div(往上滑) */
				$("#nameDiv").slideUp(200);
			});
			/** 监听按键 */
			$(document).keydown(function(event){
				if (event.keyCode === 38){ // 向上
					/** 获取nameDiv中所有的子元素，再把有style的div找出来 */
					var currentDiv = $("#nameDiv > div[style]");
					/** 删除currentDiv的style */
					currentDiv.removeAttr("style");
					/** 获取当前div的相邻上一个元素 */
					var prevDiv = currentDiv.prev();
					/** 判断上一个div元素是不是不存在 */
					if (prevDiv.length == 0){
						/** 获取最后一个div */
						prevDiv = $("#nameDiv > div:last");
					}
					/** 添加样式 */
					prevDiv.css("background-color", "#FFC0CB");
					/** 设置缓存变量的值 (防止发送异步请求查询数据)*/
					cache_name = prevDiv.text();
					/** 用户姓名文本框添加value */
					$("#userName").val(prevDiv.text());
				}
				if (event.keyCode === 40){ // 向下
					/** 获取nameDiv中所有的子元素，再把有style的div找出来 */
					var currentDiv = $("#nameDiv > div[style]");
					/** 删除currentDiv的style */
					currentDiv.removeAttr("style");
					/** 获取当前div的相邻下一个元素 */
					var nextDiv = currentDiv.next();
					/** 判断下一个div元素是不是不存在 */
					if (nextDiv.length == 0){
						/** 获取第一个div */
						nextDiv = $("#nameDiv > div:first");
					}
					/** 添加样式 */
					nextDiv.css("background-color", "#FFC0CB");
					/** 设置缓存变量的值 (防止发送异步请求查询数据)*/
					cache_name = nextDiv.text();
					/** 用户姓名文本框添加value */
					$("#userName").val(nextDiv.text());
				}
			});
			/** ##################### 用户姓名文本框(联想) ######################## */
			
			/** 为添加按钮绑定点击事件 */
			$("#addUser").click(function(){
				/** 弹出添加用户的窗口 */
				$("#divDialog").dialog({
					title : "添加用户", //标题
					width : 475, // 宽度
					height : 270, // 高度
					modal : true, // 模态窗口
					collapsible : true, // 可伸缩
					minimizable : false, // 最小化
					maximizable : true,  // 最大化
					onClose : function(){ // 关闭窗口
						/** 刷新当前页面 */
						window.location.href = "${path}/admin/identity/selectUser.jspx?pageModel.pageIndex="+ pageIndex +"&user.name=${user.name}&user.phone=${user.phone}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/identity/showAddUser.jspx").show();
			});
			
			
			/** 为修改按钮绑定点击事件 */
			$("#updateUser").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				/** 判断选中的checkbox的个数 */
				if (boxs.size() == 0){
					alert("请选择要修改的用户！");
				}else if (boxs.length == 1){
					/** 显示修改窗口 */
					$("#divDialog").dialog({
						title : "修改用户", //标题
						width : 475, // 宽度
						height : 245, // 高度
						modal : true, // 模态窗口
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true,  // 最大化
						onClose : function(){ // 关闭窗口
							/** 刷新当前页面 */
							fillTable(pageIndex);
						}
					});
					$("#iframe").attr("src", "${path}/admin/identity/showUpdateUser.jspx?user.userId=" + boxs.val()).show();
					
				}else{
					alert("修改用户时，只能选择一个！");
				}
			});
			
			/** 为删除按钮绑定点击事件 */
			$("#deleteUser").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				/** 判断选中的checkbox的个数 */
				if (boxs.size() == 0){
					alert("请选择要删除的用户！");
				}else{
					if (confirm("您确定要删除吗？")){
						/** 定义数组 */
						var userIds = new Array();
						/** 获取删除的userId */
						boxs.each(function(){
							userIds.push(this.value);
						});
						window.location.href = "${path}/admin/identity/deleteUser.jspx?pageModel.pageIndex="+ pageIndex +"&user.name=${user.name}&user.phone=${user.phone}&userIds=" + userIds.join(",");
					}
				}
			});
			
			/** 为审核按钮绑定点击事件 */
			$("#checkUser").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				/** 判断选中的checkbox的个数 */
				if (boxs.size() == 0){
					alert("请选择要审核的用户！");
				}else{
					/** 定义数组 */
					var userIds = new Array();
					/** 获取删除的userId */
					boxs.each(function(){
						userIds.push(this.value);
					});
					$.ajax({
						url : "${path}/admin/identity/checkUser.jspx",
						type : "post",
						dataType : "json",
						data : "userIds=" + userIds.join(",") + "&user.status=" + $("#status").val(),
						async : true,
						success : function(data){
							if (data == 0){
								$("#tip").text("审核成功！");
								/** 异步刷新表格中的数据 */
								fillTable(pageIndex);
							}else{
								$("#tip").text("审核失败！");
							}
						},
						error : function(){
							alert("数据加载失败！");
						}
					});
				}
			});
			
			/** 获取用户在模块对应的权限 */
			var modulePopedoms = "${session_user_module_popedom}";
			var btnArrs = ["addUser", "updateUser", "deleteUser", "checkUser"];
			$.each(btnArrs, function(){
				if (modulePopedoms.indexOf(this) == -1){
					if (this == "checkUser"){
						$("#td_" + this).hide();
					}
					$("#" + this).hide();
				}
			});
			
			/** 发异步请求填充表格 */
			var fillTable = function(num){
				  $.ajax({
	           	   	  url : "${path}/admin/identity/loadUserPageData.jspx",
	           	   	  type : "post",
	           	   	  data : "pageModel.pageIndex="+ num +"&user.name=${user.name}&user.phone=${user.phone}",
	           	   	  dataType : "json",
	           	   	  async : true,
	           	   	  success : function(data){
	           	   	  		// this : {}
	           	   	  	 	$("#tbody").empty();
	           	   	  	 	// [{},{}]
		           	   	  	$.each(data, function(i){
		           	   	  	 	/** 创建行 */
		           	   	  	 	/**
		           	   	  	 	  {"phone":"13888888888","sex":"男","status":1,"email":"441510427@qq.com",
		           	   	  	 	  	"name":"李英","userId":"liying","checkDate":"2016-03-19T09:54:02",
		           	   	  	 	  	"job":"职员","creater":"柳岩","dept":"人事部",
		           	   	  	 	  	"createDate":"2016-03-19T09:54:02","checker":"柳岩"}
		           	   	  	 	 */
		           	   	  	 	var tr = $("<tr/>").attr("id", "tr_" + i).addClass("listTr");
		           	   	  	 	$("<td><input type='checkbox' id='box_"+ i +"' value='"+ this.userId +"'/>"+ (i + 1) +"</td>").appendTo(tr);
		           	   	  	 	$("<td/>").text(this.userId).appendTo(tr);
								$("<td/>").text(this.name).appendTo(tr);
								$("<td/>").text(this.sex).appendTo(tr);
								$("<td/>").text(this.qqNum).appendTo(tr);
								$("<td/>").text(this.phone).appendTo(tr);
								$("<td/>").text(this.email).appendTo(tr);
								$("<td/>").html(this.status).appendTo(tr);
								$("<td/>").html(this.createDate.replace("T","&nbsp;")).appendTo(tr);
								$("<td/>").text(this.creater).appendTo(tr);
								$("<td/>").html(this.checkDate.replace("T","&nbsp;")).appendTo(tr);
								$("<td/>").text(this.checker).appendTo(tr);
								
								$("#tbody").append(tr);
		           	   	  	 });
	           	   	  	 	
							/** 让全选选中 */
							/** 获取下面数据行checkbox */
							var trs = $("tr[id^='tr_']");
							var boxs = $("input[id^='box_']");
							/** 全部绑定点击事件 */
							trs.click(function(){
								/** 获取选中的checkbox (把选中的过滤出来) */
								//var checkBoxs = boxs.filter(":checked");
			
								
								var input = $(this).find("input");
								if (input.prop("checked")) {
									input.prop("checked", false);
								} else {
									input.prop("checked", true);
								}
								$("#checkAll").prop("checked", boxs.filter(":checked").length == boxs.length);
							});
		           	   	  	 
							/** 获取所有的tr绑定onmouseover与onmouseout事件 */
							trs.hover(
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
			
	           	   	  },
	           	   	  error : function(){
	           	   	  	alert("数据加载失败！");
	           	   	  }
	           	   });
			};
			
			/** 显示分页标签 */
			 $.jqPaginator("#pager", {
		        totalCounts: ${pageModel.recordCount}, // 总记录条数
		        pageSize : ${pageModel.pageSize}, // 每页显示的记录数
		        visiblePages: 8, // 显示的页码数
		        currentPage: ${pageModel.pageIndex}, // 当前页码
		        first: '<li class="first"><a href="javascript:;">首页</a></li>',
		        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
		        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
		        last: '<li class="last"><a href="javascript:;">尾页</a></li>',
		        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
		        onPageChange: function (num, type) {
		           	window.pageIndex = num;
	           	 	fillTable(num);
		        }
		    });
			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
	<s:form  action="/admin/identity/selectUser.jspx" method="post" theme="simple">
		<table>
			<tr>
				<td><input type="button" value="添加" id="addUser"/></td>
				<td><input type="button" value="修改" id="updateUser"/></td>
				<td><input type="button" value="删除" id="deleteUser"/></td>
				<td id="td_checkUser">状态：<select id="status">
					    <option value="1">审核</option>
					    <option value="2">不通过</option>
					    <option value="3">冻结</option>
					</select>
				</td>
				<td><input type="button" value="审核" id="checkUser"/></td>
				<td>姓名：<s:textfield name="user.name" autocomplete="off" id="userName"/></td>
				<td>手机号码：<s:textfield name="user.phone" size="12"/></td>
				<td><input type="submit" value="查询"/>&nbsp;<font color="red" id="tip">${tip}</font></td>
			</tr>
		</table>
	</s:form>

	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>qq号码</th>
			<th>手机号码</th>
			<th>邮箱</th>
			<th>状态</th>
			<th>创建日期</th>
			<th>创建人</th>
			<th>审核日期</th>
			<th>审核人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;" id="tbody">
			
		</tbody>
	</table>
	
	<!-- 分页标签区 -->
	<div align="center">
	 	<ul class="pagination" id="pager"></ul>
	</div>
	
	<!-- 定义div显示用户姓名数据 -->
	<div id="nameDiv" class="nameDiv"></div>
	
	<!-- 定义div作为弹出窗口  -->
	<div id="divDialog">
		<iframe width="100%" height="100%" style="display:none;" id="iframe" frameborder="0"></iframe>
	</div>
</body>
</html>