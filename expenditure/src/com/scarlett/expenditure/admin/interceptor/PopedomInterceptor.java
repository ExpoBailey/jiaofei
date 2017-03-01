package com.scarlett.expenditure.admin.interceptor;

import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.scarlett.expenditure.admin.AdminConstant;

/**
 *PopedomInterceptor.java
 *@intention
 * <p> 权限拦截器 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class PopedomInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -2049479344707453667L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		/**############ 获取当前用户请求的URL ########### */
		/** 获取ActionProxy代理对象 */
		ActionProxy actionProxy = invocation.getProxy();
		/** 获取命名空间 */
		String namespace = actionProxy.getNamespace();
		/** 获取action的name */
		String actionName = actionProxy.getActionName();
		/** 定义请求URL */
		String requestUrl = namespace + "/" + actionName;
		System.out.println("请求URL: " + requestUrl);
		
		
		/** 从Session中获取用户所有的权限 */
		@SuppressWarnings("unchecked")
		Map<String, List<String>> allPopedoms = (Map<String, List<String>>)invocation
				.getInvocationContext()
				.getSession().get(AdminConstant.SESSION_USER_POPEDOMS);
		
		/** 定义是否有权限的标识符 */
		boolean isPopedom = false;
		/** 循环用户所有得权限 */
		outer:
		for(Map.Entry<String, List<String>> entry : allPopedoms.entrySet()){
			/** 迭代Value */
			for (String operaUrl : entry.getValue()){
				// /admin/identity/checkUser
				// /admin/identity/checkUser.jspx
				/** 做权限验证 */
				if (operaUrl.substring(0, operaUrl.lastIndexOf(".")).equals(requestUrl)){
					/** 把该模块所有的权限存入Session */
					invocation.getInvocationContext().getSession().put(AdminConstant.SESSION_USER_MODULE_POPEDOM, 
							entry.getValue());
					isPopedom = true;
					break outer;
				}
			}
		}
		
		if (isPopedom){ // 有权限
			return invocation.invoke();
		}else{ // 没有权限，就提示
			invocation.getInvocationContext().put("tip", "亲，您的权限正在审核中，请稍候再试！");
			return Action.ERROR;
		}
	}
}