package com.scarlett.expenditure.admin;

import com.opensymphony.xwork2.ActionSupport;
import com.scarlett.expenditure.admin.identity.service.IIdentityService;
import com.scarlett.expenditure.admin.identity.service.impl.IdentityService;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.util.Map;

/**
 *LoginAjax.java
 *@intention
 * <p> 异步登录的控制器 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class LoginAjax extends ActionSupport{
    /** 定义Field接收页面请求参数 */
    private String userId;
    private String password;
    private String vcode;
    private int key;
    /** 封装响应数据 */
    private Map<String, Object> responseData;
    /** 注入业务层 */
    @Resource
    private IIdentityService identityService;

    public String execute() {
        try{
            /** 调用登录方法 */
            responseData = identityService.login(userId, password, vcode, key);
            System.out.println(JSONUtil.serialize(responseData));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return SUCCESS;
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

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Map<String, Object> getResponseData() {
        return responseData;
    }

    public void setResponseData(Map<String, Object> responseData) {
        this.responseData = responseData;
    }
}
