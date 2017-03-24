package com.scarlett.expenditure.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 * Created by bailey on 2017/3/24.
 */
public class IndexAction extends ActionSupport {
    private String base = ServletActionContext.getRequest().getContextPath();

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String getBase() {
        return base;
    }
}
