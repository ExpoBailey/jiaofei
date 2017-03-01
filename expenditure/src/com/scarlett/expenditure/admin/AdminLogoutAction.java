package com.scarlett.expenditure.admin;

import com.scarlett.expenditure.core.util.CookieTools;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;

/**
 *LoginAjax.java
 *@intention
 * <p> 安全退出 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class AdminLogoutAction extends ActionSupport {

	private static final long serialVersionUID = 1768609517552880194L;

	@Override
	public String execute() throws Exception {
		/** 让session失效 */
		ServletActionContext.getRequest().getSession().invalidate();
		/** 删除cookie */
		CookieTools.removeCookie(AdminConstant.COOKIE_NAME);
		return LOGIN;
	}
}
