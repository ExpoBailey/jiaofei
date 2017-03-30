<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的帐户--收支明细</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta name="Keywords" content="keyword1,keyword2,keyword3" />
<meta name="Description" content="网页信息的描述" />
<meta name="Author" content="gdcct.gov.cn" />
<meta name="Copyright" content="All Rights Reserved." />
<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
<link href="${path}/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${path}/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="${path}/devAid-v1.1/assets/plugins/font-awesome/css/font-awesome.css">
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/jquery.easing.1.3.js"></script>
<script type="text/javascript"
	src="${path}/devAid-v1.1/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${path}/js/jqPaginator/jqPaginator.min.js"></script>
<style type="text/css">
html, body, section {
	height: 100%;
}

.carousel-inner .item img {
	width: 100%;
	height: 100%;
}

a {
	color: #FFFFFF
}
</style>

<script type="text/javascript">
	$(function() {
		var fillTable = function(num){
			$.ajax({
         	   	  url : "${path}/web/account/recordInfoAjax.do",
         	   	  type : "post",
         	   	  data : "pageModel.pageIndex="+ num,
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
	           	   	  	 	var tr = $("<tr/>").attr("id", "tr_" + i).addClass("warning");
	           	   	  	 	$("<td>"+ (i + 1) +"</td>").appendTo(tr);
							$("<td/>").text(this.content).appendTo(tr);
							$("<td/>").html(this.tranDate.replace("T", "&nbsp;")).appendTo(tr);
							$("<td/>").text(this.remark == null ? '':this.remark).appendTo(tr);
							$("#tbody").append(tr);
	           	   	  	 });
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

<body style="margin: 0px;">
	<div class="table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>交易内容</th>
					<th>交易时间</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody id="tbody">

			</tbody>
		</table>
	</div>
	<!-- 分页标签区 -->
	<div align="center">
	 	<ul class="pagination" id="pager"></ul>
	</div>
</body>

</html>