package com.scarlett.expenditure.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.scarlett.expenditure.admin.identity.service.IIdentityService;
import org.apache.struts2.json.JSONUtil;


import com.opensymphony.xwork2.ActionSupport;

/**
 *LoadMenuTreeAjax.java
 *@intention
 * <p> 异步加载菜单树 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class LoadMenuTreeAjax extends ActionSupport {
	
	private static final long serialVersionUID = -301730378782429934L;
	@Resource
	private IIdentityService identityService;
	private List<Map<String, Object>> responseData;
	
	@Override
	public String execute() {
		try{
			responseData = identityService.loadMenuTree();
			System.out.println(JSONUtil.serialize(responseData));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/** getter method */
	public List<Map<String, Object>> getResponseData() {
		return responseData;
	}
}