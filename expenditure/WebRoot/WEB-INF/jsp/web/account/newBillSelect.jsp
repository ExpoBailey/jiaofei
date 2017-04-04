<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>未缴帐单</title>
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
<style type="text/css">
html, body, section {
	height: 100%;
	background: #17baef;
}

.promo {
	background: #17baef;
	color: #fff;
	padding-top: 50px;
}

.carousel-inner .item img {
	width: 100%;
	height: 100%;
}

.carousel slide {
	height: 300px;
	width: 400px;
}

.container {
	margin: 0 auto;
}

a {
	
}

.title {
	text-align: center;
	margin: 0 auto;
}

.panel-warning {
	margin-bottom: 0px;
	height: 470px;
}
.row {
	margin-right: 0px;
	margin-left: 0px;
}
.mybill {
	margin: 0 auto;
	width: 650px;
}
.jumbotron {
	padding-top: 0px;
	padding-bottom: 3px;
	margin-bottom: 0px;
	width: 65%;
	margin: 0 auto;
}
.bill-table {
	font-size: 16px;
}
.bill-title {
	font-size: 25px;
	align-content: center;
}
.single {
	background-color: #2b55af;
}
.double {
	background-color: #f0ad4e;
}
.table {
	margin-bottom: 0px;
}
.modal-dialog {
	width: 400px;
}
.jum-div{
	position: relative; /*脱离文档流*/
	top: 50%; /*偏移*/
	transform: translateY(-50%);
}
.nobill{
	padding-top: 100px;
	padding-bottom: 100px;
}
.bg-center{
	align-content: center;
}

</style>

<script type="text/javascript">
	$(function(){

	   $.ajax({
	       url : "${path}/web/account/loadBillAjax.do",
           type : "get",
           data : {"bill.type" : 2},
           dataType : "json",
           success : function (data) {
               var myBill = $("#mybill");
               //mybill.empty();
			   //alert(data);
               if (data == null || data.length < 0) {
				   var html = "<div class=\"item active  nobill \"><div class=\"jumbotron\"><table class=\"table bill-table\">"
						   + "<tr class=\"warning\"><th class=\"bill-title\">您暂无未缴费帐单</th></tr>"
						   + "</table></div></div>";
				   myBill.append(html);
               } else {
					$.each(data, function(i){
						var html = "<div class=\"item " + (i == 0 ? "active" : "") + "\"><div class=\"jumbotron\"><table class=\"table bill-table\">"
									+ "<tr class=\"warning\"><td class=\"bill-title\">" + this.comType + "单</td></tr>"
								    + "<tr class=\"danger\"><td>" + this.comName + "</td></tr>"
									+ "<tr class=\"warning\"><td>单价：" + this.price + "&nbsp;&nbsp;|&nbsp;&nbsp;使用量：" + this.use + "</td></tr>"
								    + "<tr class=\"danger\"><td>总价：" + this.sum + "元</td></tr>"
									+ "<tr class=\"warning\"><td>账单日期：" + this.appearDate.replace("T", " ") + "</td></tr>"
								    + "<tr class=\"danger\"><td>逾期：" + this.overTime + "天</td></tr>"
									+ "<tr class=\"warning bg-center\"><td><button type=\"button\" onclick=\"showForm('" + this.id + "')\" class=\"btn btn-warning\">马上缴费</button></td></tr>"
									+ "</table></div></div>";
						myBill.append(html);
					})
               }
           },
           error : function () {
               alert("获取帐单失败！");
           }
       });



		/** 交易界面隐藏后触发*/
		$('#myModal').on('hidden.bs.modal', function (e) {
			var iframe = $("#mainIframe", parent.parent.document);
			var url = "${path}/web/account/account/newBillSelect.do";
			iframe.attr("src", url);
		})
    });

	function tran(){
		$.ajax({
			url : "${path}/web/account/transaction.do",
			type : "post",
			data : $("form").serialize(),
			dataType : "json",
			success : function (data) {
				$("#aria").text(data.des);
			},
			error : function () {
				alert("缴费系统异常！");
			}
		});
	}
	
	function showForm(id) {
		$("#id").val(id);
		$('#myModal').modal('show');
	}

</script>
</head>

<body>
<div class="clearfix jum-div">
	<div class="col-xs-3 col-md-3"></div>
	<div class="col-xs-6 col-md-6">
		<div class="mybill">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox" id="mybill">

					<%--<div class="item active">
						<div class="jumbotron">
							<table class="table bill-table">
								<tr class="warning">
									<td class="bill-title">电费单</td>
								</tr>
								<tr class="danger">
									<td>广州市越秀区供电局</td>
								</tr>
								<tr class="warning">
									<td>单价：xx ｜ 使用量：xx</td>
								</tr>
								<tr class="danger">
									<td>总价：xxx</td>
								</tr>
								<tr class="warning">
									<td>帐单日期：xx vv b  b  b  b</td>
								</tr>
									<tr class="danger">
									<td>逾期：xxx天</td>
								</tr>
								<tr class="warning bg-center">
									<td><button type="button" onclick="showForm('00')" class="btn btn-warning">马上缴费</button></td>
								</tr>
							</table>
						</div>
					</div>--%>

				</div>
			</div>
			</div>
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
	<div class="col-xs-3 col-md-3"></div>
		</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">缴费</h4>
				</div>
				<div class="modal-body">
					<form class="form-signin" id="form">
						<h3 class="form-signin-heading">付费易缴费</h3>
						<input type="text" name="bill.id" id="id" hidden="hidden">
						<div class="row form-group">
							<label for="tranPW" class="col-sm-3 control-label" >交易密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" id="tranPW" name="from.tranPW" placeholder="交易密码">
							</div>
						</div>
						<div class="row">
							<button type="button" onclick="tran()" class="btn btn-warning" style="margin-left: 150px;" id="submit">缴费</button>
							<span aria-hidden="true" id="aria"></span>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<br/>
				</div>
			</div>
		</div>
	</div>

</body>

</html>