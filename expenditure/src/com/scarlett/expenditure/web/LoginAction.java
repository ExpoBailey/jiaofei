package com.scarlett.expenditure.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.web.account.service.IService;

/**
 * LoginAction
 * @date 2017年3月26日 上午12:21:51
 * @version 1.0
 */
public class LoginAction extends ActionSupport {
    
    private static final long serialVersionUID = 4562161935124712985L;

    @Autowired
    private IService service;
    
    private String userId;
    private String password;
    
    private Map<String, Object> map = new HashMap<String, Object>();
    
    /** 前端界面登录 */
    public String login(){
        try {
            System.out.println("前端登录--userId:" + userId + ",password:" + password);
            map = service.login(userId, password);
        } catch (Exception e) {
            map.put("status", 0);
            map.put("des", "登录出错！");
        }
        return SUCCESS;
    }
    
    /** 检查是否登录 */
    public String checkLogin(){
        try {
            User user = AdminConstant.getWebSessionUser();
            System.out.println("检查是否登录--user--" + user);
            map.put("isLogin", user != null ? true : false);
        } catch (Exception e) {
        }
        return SUCCESS;
    }
    
    /** 退出登录 */
    public String exit(){
        try {
            System.out.println("退出登录");
            ServletActionContext.getRequest().getSession().invalidate();
        } catch (Exception e) {
        }
        return SUCCESS;
    }

    /** getter and setter method */
    

    public Map<String, Object> getMap() {
        return map;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    
}
