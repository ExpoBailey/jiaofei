package com.scarlett.expenditure.admin.interceptor;

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
                .getSession().get(AdminConstant.SESSION_USER);

        System.out.println("session中用户：" + user);
        /** 定义是否登录标记 (不用登录) */
        boolean isLogin = false;

        if (user == null){
            /** 获取Cookie(是不是记住了用户) */
            Cookie cookie = CookieTools.getCookie(AdminConstant.COOKIE_NAME);
            if (cookie != null){
                /** 获取cookie的值 userId + key */
                String cookieValue = cookie.getValue();
                /** 截取最后32位加密key */
                String md5Suffix = cookieValue.substring(cookieValue.length() - 32);
                /** 截取前面的 */
                String userId = cookieValue.substring(0, cookieValue.length() - 32);
                /** 判断cookie中的值是否修改过 */
                if (MD5.getMD5(userId + CookieTools.SAFE_KEY).equals(md5Suffix)){
                    /** 登录 */
                    user = identityService.getUser(userId);
                    /** 存入Session */
                    invocation.getInvocationContext()
                            .getSession().put(AdminConstant.SESSION_USER, user);
                    /** 获取该用户所有的权限 */
                    Map<String, List<String>> allPopedoms = identityService.getAllPopedomByUserId(user.getUserId());
                    /** 权限存入Session  redis */
                    invocation.getInvocationContext()
                            .getSession().put(AdminConstant.SESSION_USER_POPEDOMS, allPopedoms);
                }else{
                    /** 删除Cookie */
                    CookieTools.removeCookie(AdminConstant.COOKIE_NAME);
                    isLogin = true;
                }
            }else{
                isLogin = true;
            }
        }

        return isLogin ? Action.LOGIN : invocation.invoke();
    }
}
