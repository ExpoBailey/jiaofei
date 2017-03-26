package com.scarlett.expenditure.web.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.admin.identity.service.IIdentityService;
import com.scarlett.expenditure.core.util.CookieTools;
import com.scarlett.expenditure.core.util.MD5;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;

/**
 *LoginInterceptor.java
 *@intention
 * <p> 登录拦截器 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class LoginInterceptor extends AbstractInterceptor{

    private static final long serialVersionUID = -6595658029427493341L;
    @Resource
    private IIdentityService identityService;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        /** 获取Session中的用户 */
        User user = (User)invocation.getInvocationContext()
                .getSession().get(AdminConstant.WEB_SESSION_USER);

        System.out.println("session中web用户：" + user);
        /** 定义是否登录标记 (不用登录) */
        boolean isLogin = false;

        if (user == null){
            isLogin = true;
        }

        return isLogin ? Action.LOGIN : invocation.invoke();
    }
}
