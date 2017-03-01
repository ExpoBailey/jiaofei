<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-角色管理</title>
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
			$("#addRole").click(function(){
				/** 弹出添加角色的窗口 */
				$("#divDialog").dialog({
					title : "添加角色", //标题
					width : 375, // 宽度
					height : 215, // 高度
					modal : true, // 模态窗口
					collapsible : true, // 可伸缩
					minimizable : false, // 最小化
					maximizable : true,  // 最大化
					onClose : function(){ // 关闭窗口
						/** 刷新当前页面 */
						window.location.href = "${path}/admin/identity/selectRole.jspx?pageModel.pageIndex="+pageIndex;
					}
				});
				$("#iframe").attr("src", "${path}/admin/identity/showAddRole.jspx").show();
			});
			
			
			/** 为修改按钮绑定点击事件 */
			$("#updateRole").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				/** 判断选中的checkbox的个数 */
				if (boxs.size() == 0){
					alert("请选择要修改的角色！");
				}else if (boxs.length == 1){
					/** 显示修改窗口 */
					$("#divDialog").dialog({
						title : "修改角色", //标题
						width : 375, // 宽度
						height : 215, // 高度
						modal : true, // 模态窗口
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true,  // 最大化
						onClose : function(){ // 关闭窗口
							/** 刷新当前页面 */
							fillTable(pageIndex);
						}
					});
					$("#iframe").attr("src", "${path}/admin/identity/showUpdateRole.jspx?role.id=" + boxs.val()).show();
					
				}else{
					alert("修改角色时，只能选择一个！");
				}
			});
			
			/** 为删除按钮绑定点击事件 */
			$("#deleteRole").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				/** 判断选中的checkbox的个数 */
				if (boxs.size() == 0){
					alert("请选择要删除的角色！");
				}else{
					if (confirm("您确定要删除吗？")){
						/** 定义数组 */
						var ids = new Array();
						/** 获取删除的ids */
						boxs.each(function(){
							ids.push(this.value);
						});
						window.location.href = "${path}/admin/identity/deleteRole.jspx?pageModel.pageIndex=${pageModel.pageIndex}&ids=" + ids.join(",");
					}
				}
			});

			/** 发送异步请求填充列表 */
			var fillTable = function(num){
				$.ajax({
					url : "${path}/admin/identity/loadRoleAjax.jspx",
					type : "post",
					data : "pageModel.pageIndex=" + num,
					dataType : "json",
					async : true,
					success : function(data){
                        var tbody = $("#tbody");
						tbody.empty();
                        var obj = eval(data);
						$.each(obj, function(i){
							var tr = $("<tr/>").prop("id", "tr_" + i).addClass("listTr");
							$("<td><input type='checkbox' id='box_"+ i +"' value='"+ this.id +"' />"+ (i + 1) +"</td>").appendTo(tr);
							$("<td/>").text(this.name).appendTo(tr);
							$("<td/>").text(this.remark).appendTo(tr);
							$("<td/>").html("<a href='${path}/admin/identity/selectRoleUser.jspx?role.id="+this.id+"'>绑定用户</a>" +
									"|<a href='${path}/admin/identity/mgrPopedom.jspx?role.id="+this.id+"'>绑定操作</a>").appendTo(tr);
							$("<td/>").text(this.createDate.replace("T", " ")).appendTo(tr);
							$("<td/>").text(this.creater).appendTo(tr);
							$("<td/>").text(this.modifyDate.replace("T", " ")).appendTo(tr);
							$("<td/>").text(this.modifier).appendTo(tr);
                            tr.appendTo(tbody);
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
					error : function(data){
						alert("获取角色列表异常！");
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
	<table>
		<tr>
			<td><input type="button" value="添加" id="addRole"/></td>
			<td><input type="button" value="修改" id="updateRole"/></td>
			<td><input type="button" value="删除" id="deleteRole"/>
				<font color="red">${tip}</font></td>
		</tr>
	</table>

	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>名称</th>
			<th>备注</th>
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