<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>水电煤气后台系统-付费易管理</title>
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
			
			/** 发异步请求填充表格 */
			var fillTable = function(num){
				  $.ajax({
	           	   	  url : "${path}/admin/account/loadFFYAjax.jspx",
	           	   	  type : "post",
	           	   	  data : "pageModel.pageIndex="+ num +"&user.userId=${user.userId}&user.phone=${user.phone}",
	           	   	  dataType : "json",
	           	   	  async : true,
	           	   	  success : function(data){
	           	   	  		// this : {}
	           	   	  	 	$("#tbody").empty();
	           	   	  	 	// [{},{}]
						  	if (data == null) {
								var tr = $("<tr/>").attr("id", "tr_" + i).addClass("listTr");
								$("<td rowspan='7'/>").text("暂无数据").appendTo(tr);
							}
		           	   	  	$.each(data, function(i){
		           	   	  	 	/** 创建行 */
		           	   	  	 	var tr = $("<tr/>").attr("id", "tr_" + i).addClass("listTr");
		           	   	  	 	$("<td><input type='checkbox' id='box_"+ i +"' value='"+ this.userId +"'/>"+ (i + 1) +"</td>").appendTo(tr);
		           	   	  	 	$("<td/>").text(this.userId).appendTo(tr);
								$("<td/>").text(this.userName).appendTo(tr);
								$("<td/>").text(this.phone).appendTo(tr);
								$("<td/>").text(this.sum).appendTo(tr);
								$("<td/>").html(this.status).appendTo(tr);
								$("<td/>").text(this.remark).appendTo(tr);

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
	<s:form  action="/admin/account/countFFY.jspx" method="post" theme="simple">
		<table>
			<tr>
				<td>用户ID：<s:textfield name="user.userId" autocomplete="off" id="userId"/></td>
				<td>手机号码：<s:textfield name="user.phone" size="12"/></td>
				<td><input type="submit" value="查询"/>&nbsp;<font color="red" id="tip">${tip}</font></td>
			</tr>
		</table>
	</s:form>

	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>用户ID</th>
			<th>用户名</th>
			<th>手机</th>
			<th>余额</th>
			<th>状态</th>
			<th>备注</th>
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