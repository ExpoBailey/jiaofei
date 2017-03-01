<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>办公管理系统-权限管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="itcast.cn" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link rel="stylesheet" href="${path}/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/ztree/jquery.ztree.all.min.js"></script>
	<style type="text/css">
		body{
			margin: 0px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			
			/** 定义构建ztree需要配置信息 */
			var setting = {
				async : { // 异步请求
				   enable : true, // 开启
				   url : "${path}/admin/identity/loadPopedomTreeAjax.jspx" // 请求URL
				},
				data : { // 指定树的数据格式
					simpleData : {
						enable : true // 开启简单的格式构建树
					}
				},
				callback : { // 指定树的事件
					onNodeCreated: function(event, treeId, treeNode){
						if (treeNode.id.length == 8){
							/** 获取对应的树节点里面的a标签 */
							$("#" + treeNode.tId + "_a").attr("href", "${path}/admin/identity/selectPopedom.jspx?role.id=${role.id}&moduleCode=" + treeNode.id)
						               .attr("target", "popedomRightFrame");
						}
					},
					onClick : function(event, treeId, treeNode){ // 点击树的节点
						/** 获取树对象 */
						var treeObj = $.fn.zTree.getZTreeObj(treeId);
						/** 展开树节点 */
						treeObj.expandNode(treeNode);
					}
				}
			};
			/** 定义树节点 */
			var nodes = [{id: 0, pId : -1, name : "权限管理", isParent : true}];
			/** 
			 * 初始化树 
			 * 第一个参数：jQuery对象(树对象)
			 * 第二个参数：全局设置信息
			 * 第三个参数：构建需要的数据
			 */
			$.fn.zTree.init($("#popedomTree"), setting, nodes);
		});
		
	</script>
</head>
<body>
	<ul id="popedomTree" class="ztree"></ul>
</body>
</html>