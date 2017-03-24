<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>水电煤气后台系统-帐单管理</title>
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
	<link href="${path}/js/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqPaginator/jqPaginator.min.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">

		.empty {
			row-span: 7;
			collapse: 7;
		}
	</style>
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


			/** 为添加按钮绑定点击事件 */
			$("#addBill").click(function(){
				/** 弹出添加的窗口 */
				$("#divDialog").dialog({
					title : "添加帐单", //标题
					width : 400, // 宽度
					height : 220, // 高度
					modal : true, // 模态窗口
					collapsible : true, // 可伸缩
					minimizable : false, // 最小化
					maximizable : true,  // 最大化
					onClose : function(){ // 关闭窗口
						/** 刷新当前页面 */
						window.location.href = "${path}/admin/business/countBill.jspx?pageModel.pageIndex="+ pageIndex +"&bill.pertain.userId=${bill.pertain.userId}&bill.company.name=${bill.company.name}&startDateStr=${startDateStr}&endDateStr=${endDateStr}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/business/showAddBill.jspx").show();
			});


			/** 为修改按钮绑定点击事件 */
			$("#updateBill").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']:checked");
				/** 判断选中的checkbox的个数 */
				if (boxs.size() == 0){
					alert("请选择要修改的帐单！");
				}else if (boxs.length == 1){
					/** 显示修改窗口 */
					$("#divDialog").dialog({
						title : "修改帐单", //标题
						width : 400, // 宽度
						height : 220, // 高度
						modal : true, // 模态窗口
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true,  // 最大化
						onClose : function(){ // 关闭窗口
							/** 刷新当前页面 */
							fillTable(pageIndex);
						}
					});
					$("#iframe").attr("src", "${path}/admin/business/showUpdateBill.jspx?bill.id=" + boxs.val()).show();

				}else{
					alert("修改帐单时，只能选择一个！");
				}
			});


			/** 发异步请求填充表格 */
			var fillTable = function(num){
				  $.ajax({
	           	   	  url : "${path}/admin/business/loadBillAjax.jspx",
	           	   	  type : "post",
	           	   	  data : "pageModel.pageIndex="+ num +"&bill.pertain.userId=${bill.pertain.userId}&bill.company.name=${bill.company.name}&startDateStr=${startDateStr}&endDateStr=${endDateStr}",
	           	   	  dataType : "json",
	           	   	  async : true,
	           	   	  success : function(data){
	           	   	  		// this : {}
	           	   	  	 	$("#tbody").empty();
	           	   	  	 	// [{},{}]
						  	if (data == null || data.length < 1) {
						  		alert("暂无数据！");
								return;
							}
		           	   	  	$.each(data, function(i){
		           	   	  	 	/** 创建行 */
		           	   	  	 	var tr = $("<tr/>").attr("id", "tr_" + i).addClass("listTr");
		           	   	  	 	$("<td><input type='checkbox' id='box_"+ i +"' value='"+ this.id +"'/>"+ (i + 1) +"</td>").appendTo(tr);
		           	   	  	 	$("<td/>").text(this.comId).appendTo(tr);
								$("<td/>").text(this.comName).appendTo(tr);
								$("<td/>").text(this.price).appendTo(tr);
								$("<td/>").text(this.use).appendTo(tr);
								$("<td/>").text("￥ "+this.sum).appendTo(tr);
								$("<td/>").html(this.type).appendTo(tr);
								$("<td/>").text(this.pertain).appendTo(tr);
								$("<td/>").html(this.appearDate.replace("T", "&nbsp;")).appendTo(tr);
								$("<td/>").html(this.handleDate.replace("T", "&nbsp;")).appendTo(tr);
//								$("<td/>").text(this.checker).appendTo(tr);
								$("<td/>").text(this.remark == null ? '':this.remark).appendTo(tr);

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
	<s:form  action="/admin/business/countBill.jspx" method="post" theme="simple">
		<table>
			<tr>
				<td><input type="button" value="添加" id="addBill"/></td>
				<td><input type="button" value="修改" id="updateBill"/></td>
				<td>用户ID：<s:textfield name="bill.pertain.userId" id="bill.pertain.userId"/></td>
				<td>出帐机构：<s:textfield name="bill.company.name" />
				</td>
				<td>帐单时间段：<s:textfield name="startDateStr" value="%{startDateStr}" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					--&nbsp;<s:textfield name="endDateStr" value="%{endDateStr}" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
				<td></td>
				<td><input type="submit" value="查询"/>&nbsp;<font color="red" id="tip">${tip}</font></td>
			</tr>
		</table>
	</s:form>

	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<thead id="thead">
			<tr class="listHeaderTr">
				<th><input type="checkbox" id="checkAll"/>全部</th>
				<th>机构编号</th>
				<th>机构名称</th>
				<th>单价</th>
				<th>使用量</th>
				<th>支付总额</th>
				<th>帐单状态</th>
				<th>帐单所属人</th>
				<th>出帐时间</th>
				<th>缴费时间</th>
				<th>帐单备注</th>
			</tr>
		</thead>
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