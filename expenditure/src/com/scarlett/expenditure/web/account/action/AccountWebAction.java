package com.scarlett.expenditure.web.account.action;

import com.opensymphony.xwork2.ActionSupport;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.account.service.IAccountService;
import com.scarlett.expenditure.core.pojo.PageModel;
import com.scarlett.expenditure.web.account.FullFrom;
import com.scarlett.expenditure.web.account.service.IService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *AccountWebAction.java.java
 *@intention 
 * <p> 付费易的前端基础控制器</p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class AccountWebAction extends ActionSupport{
    
    private static final long serialVersionUID = -2496960156333682058L;

    @Autowired
    private IService service;
    
    private FuFeiYi ffy;
    
    private FullFrom from;
    
    private Map<String, Object> map = new HashMap<String, Object>();
    
    /** 帐户信息 */
    public String accountInfo(){
        try {
            ffy = service.getMyAccount();
        } catch (Exception e) {
        }
        return SUCCESS;
    }
    
    /** 充值 */
    public String fullMoneyAjax(){
        try {
            service.fullMoney(from);
        } catch (Exception e) {
        }
        return SUCCESS;
    }

    /** getter and setter method */
    public FuFeiYi getFfy() {
        return ffy;
    }
    public void setFfy(FuFeiYi ffy) {
        this.ffy = ffy;
    }
    public Map<String, Object> getMap() {
        return map;
    }
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
    public FullFrom getFrom() {
        return from;
    }
    public void setFrom(FullFrom from) {
        this.from = from;
    }
}
