<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <link rel="stylesheet" type="text/css" href="${path}/css/common/global.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/js/dtree/dtree.css"/>
    <script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/dtree/dtree.js"></script>
	
	<style type="text/css">
		html,body{ height:100%;}
		a{color:#6a6f71; text-decoration:none;}
	</style>
	<script type="text/javascript">
		/** 监听html是不是加载完 */
		$(function(){
			/** 按点击次数绑定事件 */
			$("#ShowNav").toggle(
				function(){
					/** 获取父窗口main.jsp页面的frameset */
					var fst = $("#fstMain",parent.document);
					/** 设置属性  cols="148,*" */
					fst.attr("cols", "14,*");
					/** 隐藏Div */
					$("#shumenu").hide();
					/** 设置img的src属性 */
					$(this).prop("src", "${path}/images/system/left_xs.gif");
					/** 更改提示信息 */
					$("#nav_title").attr("title", "显示菜单栏");
				}, 
				function(){
					/** 获取父窗口main.jsp页面的frameset */
					var fst = $("#fstMain",parent.document);
					/** 设置属性  rows="82,*" */
					fst.attr("cols", "148,*");
					/** 显示Div */
					$("#shumenu").show();
					/** 设置img的src属性 */
					$(this).prop("src", "${path}/images/system/left_yc.gif");
					/** 更改提示信息 */
					$("#nav_title").attr("title", "隐藏菜单栏");
				}
			);
		});
	</script>	
	
  </head>
 <body class="leftmenubody">
 	<div class="topdivyc">
    	<a href="javascript:void(0);" class="a_link" title="隐藏菜单栏" id="nav_title">
    	<img src="${path}/images/system/left_yc.gif" id="ShowNav"/>
    	<!--隐藏时反显示的图片<img src="images/left_xs.gif" />--></a>
    </div>
    <div class="bodytextmenu" id="shumenu">
	    <div class="shumenu" >
	    	<script type="text/javascript">
				/** 创建树对象 */
				var d = new dTree("d", "${path}/js/dtree/");
				
			    /** 添加根节点 */
			    /** 
			     * id : 树节点的id
			     * pid : 父节点的id
			     * name : 显示名称
			     */
			    d.add(0, -1, "办公管理系统");
			    /** 异步请求加载菜单树 */
			     $.ajax({
			    	url : "${path}/admin/loadMenuTreeAjax.jspx",
			    	type : "post",
			    	dataType : "json",
			    	async : false,// true 异步  false 同步
			    	success : function(data){
			    		// data : [{id : "1", pid: "", name : "", url : ""}]
			    		$.each(data, function(){
			    			// this : {id : "1", pid: "", name : "", url : ""}
			    			var url = "javascript:parent.mainframe.addTab('"+ this.name +"','${path}"+ this.url +"');";
			    			url = this.pid == 0 ? "" : url;
			    			d.add(this.id, this.pid, this.name, url, this.name);
			    		});
			    	},
			    	error : function(){ 
			    		alert("数据加载失败！");
			    	}
			    });
				/*d.add(1, 0, "系统管理");
				d.add(2,1,'用户管理',"javascript:parent.mainframe.addTab('用户管理','${path}/admin/identity/selectUser.jspx')", "用户管理");
				d.add(3,1,'角色管理',"javascript:parent.mainframe.addTab('角色管理','${path}/admin/identity/selectRole.jspx')", "角色管理");
				d.add(4,1,'操作管理',"javascript:parent.mainframe.addTab('操作管理','${path}/admin/identity/mgrModule.jspx')", "操作管理");
				*/
				document.write(d);
				
				/** 展开所有树节点 */
				d.openAll();
		
			</script>
	    </div>
    </div>
  </body>
</html>
