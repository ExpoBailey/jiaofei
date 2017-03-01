package com.scarlett.expenditure.admin;

import com.opensymphony.xwork2.ActionContext;
import com.scarlett.expenditure.admin.identity.entity.User;

/**
 * AdminConstant.java 
 *@intention 
 * <p> 常量类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public final class AdminConstant {
	/** 定义存放在Session中的用户 */
	public static final String SESSION_USER = "session_user";
	/** 定义Cookie的有效时间(7天) 按秒算 */
	public static final int MAX_AGE = 60 * 60 * 24 * 7;
	/** 定义Cookie的名称 */
	public static final String COOKIE_NAME = "admin_cookie";
	/** 定义存入在Session用户权限 */
	public static final String SESSION_USER_POPEDOMS = "session_user_popedoms";
	/** 定义存入在Session用户模块对应的权限 */
	public static final String SESSION_USER_MODULE_POPEDOM = "session_user_module_popedom";
	
	/** 获取Session中的用户 */
	public static User getSessionUser() {
		return (User)ActionContext.getContext().getSession().get(SESSION_USER);
	}
	
}
