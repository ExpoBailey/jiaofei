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
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<!-- jQuery时间的插件 -->
	<script type="text/javascript" src="${path}/js/jquery-timer-1.0.js"></script>
	<script type="text/javascript">
		/** 监听html是不是加载完 */
		$(function(){
		
			/** 显示系统当前运行时间 */
			$("#time").runtime();
			
			/** 按点击次数绑定事件 */
			$("#ShowNav").toggle(
				function(){
					/** 获取父窗口main.jsp页面的frameset */
					var tst = $("#tstMain",parent.document);
					/** 设置属性  rows="82,*" */
					tst.attr("rows", "12,*");
					/** 隐藏Div */
					$("#headtitle").hide();
					/** 设置img的src属性 */
					$(this).prop("src", "${path}/images/system/top_xs.gif");
					/** 更改提示信息 */
					$("#nav_title").attr("title", "显示菜单栏");
				}, 
				function(){
					/** 获取父窗口main.jsp页面的frameset */
					var tst = $("#tstMain",parent.document);
					/** 设置属性  rows="82,*" */
					tst.attr("rows", "82,*");
					/** 显示Div */
					$("#headtitle").slideDown(200);
					/** 设置img的src属性 */
					$(this).prop("src", "${path}/images/system/top_yc.gif");
					/** 更改提示信息 */
					$("#nav_title").attr("title", "隐藏菜单栏");
				}
			);
			
			/** 安全退出 */
			$("#exit").click(function(){
				if (confirm("亲，您确定要退出吗？")){
					parent.window.location.href = "${path}/admin/adminLogout.jspx";
				}
			});
		});
	</script>
  </head>

 <body class="headbody">
	<div class="headtitle" id="headtitle">
    	<div class="headlogo"><img src="${path}/images/system/logo3.jpg" />
    	</div>
        <div class="headmenutop">
        	
        	<a class="headtopout" title="退出系统" href="javascript:void(0);" id="exit">安全退出</a>
        	<a class="headtopout" title="密码修改" href="javascript:void(0);" onclick="password();">密码修改</a>
			<div class="titlexx">
				<span style="font-size:14px;">${session_user.name}&nbsp;您好，</span>&nbsp;&nbsp;
				<span id="time" style="color:#fff;font-size:14px"></span>
				
			</div>
        </div>
    </div>
    
    <div class="yctopdiv">
      	<a href="javascript:void(0);" class="t_link" title="隐藏菜单栏" id="nav_title">
		<img src="${path}/images/system/top_yc.gif" id="ShowNav"/>
		<!--隐藏时反显示的图片<img src="images/top_xs.gif" />--></a>
    </div>
  </body>
</html>