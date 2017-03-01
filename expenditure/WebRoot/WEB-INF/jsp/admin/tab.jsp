<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<title>办公管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="gdcct.gov.cn" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${path}/js/jqeasyui/themes/default/easyui.css"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
  	<style type="text/css">
		html, body {
		width : 100%;
		height : 100%;
		padding : 0;
		margin : 0;
		overflow : hidden;
	</style>
	<script type="text/javascript">
		$(function(){
			/** 在div添加选项卡 */
			$("#tab").tabs({
				fit : true, // 填充父容器
				onSelect : function(title,index){ // 当选项卡选中时触发
					var tab = $(this).tabs("getSelected");  // 获取选择的面板
					$(this).tabs("update", {
						tab: tab, // 指定需要更新的面板
						options: { // 指定面板中的参数
							title: title // 标题
						}
					});
				}
			});
			/** 添加面板 */
			$("#tab").tabs("add", {
				title:"用户信息", // 标题 
    			content:"用户信息", // 内容    
    			closable:false // 是否可以关闭 
			});
			
		});
		
		/** 添加面板的函数 */
		var addTab = function(title, url){
			/** 判断面板是否已经存在 */
			var exists = $("#tab").tabs("exists", title);
			if (exists){
				/** 选中面板 */
				$("#tab").tabs("select", title);
			}else{
				/** 添加新的面板 */
				$("#tab").tabs("add", {
					title: title, // 标题 
	    			content: "<iframe width='100%' height='100%' src='"+ url +"' frameborder='0'></iframe>", // 内容    
	    			closable:true // 是否可以关闭 
				});
			}
		};
	</script>
</head>
<body>
	<div id="tab">
	</div>
</body>
</html>