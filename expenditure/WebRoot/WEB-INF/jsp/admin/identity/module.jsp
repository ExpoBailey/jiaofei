<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-操作管理</title>
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
			
			
			
			/** 为添加按钮绑定点击事件 */
			$("#addModule").click(function(){
				/** 弹出添加操作的窗口 */
				$("#divDialog").dialog({
					title : "添加操作", //标题
					width : 375, // 宽度
					height : 235, // 高度
					modal : true, // 模态窗口
					collapsible : true, // 可伸缩
					minimizable : false, // 最小化
					maximizable : true,  // 最大化
					onClose : function(){ // 关闭窗口
						/** 刷新左边的树 */
						parent.moduleLeftFrame.location.reload();
						/** 刷新当前页面 */
						window.location.href = "${path}/admin/identity/selectModule.jspx?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/identity/showAddModule.jspx?parentCode=${parentCode}").show();
			});
			
			
			/** 为修改按钮绑定点击事件 */
			$("#updateModule").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				/** 判断选中的checkbox的个数 */
				if (boxs.size() == 0){
					alert("请选择要修改的操作！");
				}else if (boxs.length == 1){
					/** 显示修改窗口 */
					$("#divDialog").dialog({
						title : "修改操作", //标题
						width : 375, // 宽度
						height : 235, // 高度
						modal : true, // 模态窗口
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true,  // 最大化
						onClose : function(){ // 关闭窗口
							/** 刷新左边的树 */
							parent.moduleLeftFrame.location.reload();
							/** 刷新当前页面 */
							window.location.href = "${path}/admin/identity/selectModule.jspx?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}";
						}
					});
					$("#iframe").attr("src", "${path}/admin/identity/showUpdateModule.jspx?module.code=" + boxs.val()).show();
					
				}else{
					alert("修改操作时，只能选择一个！");
				}
			});
			
			/** 为删除按钮绑定点击事件 */
			$("#deleteModule").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				/** 判断选中的checkbox的个数 */
				if (boxs.size() == 0){
					alert("请选择要删除的操作！");
				}else{
					if (confirm("您确定要删除吗？")){
						/** 调用map方法返回的jQuery对象里面存放的是回调函数的返回值 */
						var codes = boxs.map(function(){
							return this.value;
						});
						// Array
						// codes.toArray()
						window.location.href = "${path}/admin/identity/deleteModule.jspx?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}&codes=" + codes.toArray();
					}
				}
			});
			
			/** 删除后刷新左边页面  */
			if ("${tip}" != ""){
				/** 刷新左边的树 */
				parent.moduleLeftFrame.location.reload();
			}

			var fillTable = function(pCode, num){
				$.ajax({
					url : "${path}/admin/identity/loadModuleAjax.jspx",
					type : "post",
					data : "pageModel.pageIndex="+ num +"&parentCode="+pCode,
					dataType : "json",
					async : true,
					success : function(data){
						// this : {}
						$("#tbody").empty();
						// [{},{}]
						$.each(data, function(i){
							/** 创建行 */
							var tr = $("<tr/>").attr("id", "tr_" + i).addClass("listTr");
							$("<td><input type='checkbox' id='box_"+ i +"' value='"+ this.code +"'/>"+ (i + 1) +"</td>").appendTo(tr);
							$("<td/>").text(this.code).appendTo(tr);
							$("<td/>").text(this.name).appendTo(tr);
							$("<td/>").text(this.remark).appendTo(tr);
							$("<td/>").text(this.url).appendTo(tr);
							$("<td/>").html("<a href='${path}/admin/identity/selectModule.jspx?parentCode="+this.code+"'>查看下级</a>").appendTo(tr);
							$("<td/>").html(this.createDate.replace("T","&nbsp;")).appendTo(tr);
							$("<td/>").text(this.creater).appendTo(tr);
							$("<td/>").html(this.modifyDate.replace("T","&nbsp;")).appendTo(tr);
							$("<td/>").text(this.modifier).appendTo(tr);

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
						alert("列表数据加载失败！");
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
					window.parentCode = '${parentCode}';
					fillTable(parentCode,num);
				}
			});
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
	<table>
		<tr>
			<td><input type="button" value="添加" id="addModule"/></td>
			<td><input type="button" value="修改" id="updateModule"/></td>
			<td><input type="button" value="删除" id="deleteModule"/>
				<font color="red">${tip}</font></td>
		</tr>
	</table>

	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>名称</th>
			<th>备注</th>
			<th>链接</th>
			<th>操作</th>
			<th>创建日期</th>
			<th>创建人</th>
			<th>修改日期</th>
			<th>修改人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;" id="tbody">
		</tbody>
	</table>
	
	<!-- 分页标签区 -->
	<div align="center">
		<ul class="pagination" id="pager"></ul>
	</div>
	
	<!-- 定义div作为弹出窗口  -->
	<div id="divDialog">
		<iframe width="100%" height="100%" style="display:none;" id="iframe" frameborder="0"></iframe>
	</div>
</body>
</html>