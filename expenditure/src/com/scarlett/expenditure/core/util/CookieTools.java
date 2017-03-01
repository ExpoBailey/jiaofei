package com.scarlett.expenditure.core.util;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;


/**
 *LoginInterceptor.java
 *@intention
 * <p> 操作Cookie的工具类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public final class CookieTools {
	
	/** 定义加密Cookie的value的key */
	public static final String SAFE_KEY = "G9DkEkdy629088";
	/**
	 * 根据Cookie名称获取Cookie
	 * @param cookieName 名称
	 * @return Cookie
	 */
	public static Cookie getCookie(String cookieName){
		/** 获取当前用户浏览器中所有的Cookie */
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (cookies != null && cookies.length > 0){
			/** 迭代所有的Cookie */
			for (Cookie cookie : cookies){
				if (cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
		}
		return null;
	}
	/**
	 * 添加Cookie
	 * @param cookieName 名称
	 * @param cookieValue 值
	 * @param maxAge 有效时间(按秒)
	 */
	public static void addCookie(String cookieName, String cookieValue, int maxAge)
			throws Exception{
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (cookie == null){
			/** 生成加密字符串 */
			String key = MD5.getMD5(cookieValue + SAFE_KEY);
			/** 创建Cookie */
			cookie = new Cookie(cookieName, cookieValue + key);
		}
		/** 设置过期时间 */
		cookie.setMaxAge(maxAge);
		/** 设置访问路径 http://localhost:8080/ */
		cookie.setPath("/");
		/** 设置Cookie可以跨域访问  http://www.it.com http://a.it.com */
		//cookie.setDomain(".it.com");
		/** 最后把Cookie添加到用户的浏览器 */
		ServletActionContext.getResponse().addCookie(cookie);
	}
	/**
	 * 删除Cookie
	 * @param cookieName 名称
	 */
	public static void removeCookie(String cookieName){
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (cookie != null){
			/** 设置过期时间 (立即失效) */
			cookie.setMaxAge(0);
			/** 设置访问路径 http://localhost:8080/*/
			cookie.setPath("/");
			/** 最后把Cookie添加到用户的浏览器 */
			ServletActionContext.getResponse().addCookie(cookie);
		}
	}
}
