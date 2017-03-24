package com.scarlett.expenditure.admin.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by bailey on 2017/3/24.
 */
public class PathInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation inv) throws Exception {
        String path = ServletActionContext.getRequest().getContextPath();
        ServletActionContext.getRequest().setAttribute("base", path);
        System.out.println("base:"+path);
        return inv.invoke();
    }
}
