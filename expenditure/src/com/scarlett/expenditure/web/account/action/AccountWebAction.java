package com.scarlett.expenditure.web.account.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scarlett.expenditure.admin.business.entity.Bill;
import com.scarlett.expenditure.admin.business.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.account.service.IAccountService;
import com.scarlett.expenditure.core.pojo.PageModel;
import com.scarlett.expenditure.web.account.entity.FullFrom;
import com.scarlett.expenditure.web.account.service.IService;

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
    
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IBusinessService businessService;
    
    private FuFeiYi ffy;
    
    private FullFrom from;

    private Bill bill;
    
    private PageModel pageModel = new PageModel();
    
    private Map<String, Object> map = new HashMap<String, Object>();
    
    private List<Map<String, Object>> listMap = new ArrayList<>();
    
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
            map = service.fullMoney(from);
        } catch (Exception e) {
        }
        return SUCCESS;
    }
    
    /** 交易记录统计个数 */
    public String countMyRecord(){
        try {
            pageModel.setPageSize(4);
            accountService.countRecord(AdminConstant.getWebSessionUser(), null, null, pageModel);
        } catch (Exception e) {
        }
        return SUCCESS;
    }
    
    /** 交易记录详细数据 */
    public String recordInfoAjax(){
        try {
            pageModel.setPageSize(4);
            listMap = accountService.loadRecordAjax(AdminConstant.getWebSessionUser(), null, null, pageModel);
        } catch (Exception e) {
        }
        return SUCCESS;
    }

    /** 当前用户帐单数量 */
    public String countBill(){
        try {
            pageModel.setPageSize(4);
            if (bill == null) {
                bill = new Bill();
            }
            bill.setPertain(AdminConstant.getWebSessionUser());
            businessService.countBill(bill, null, null, pageModel);
        } catch (Exception e) {
        }
        return SUCCESS;
    }

    /** 加载当前用户的帐单 */
    public String loadBillAjax(){
        try {
            pageModel.setPageSize(4);
            if (bill == null) {
                bill = new Bill();
            }
            bill.setPertain(AdminConstant.getWebSessionUser());
            listMap = businessService.loadBillAjax(bill, null, null, pageModel);
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
    public PageModel getPageModel() {
        return pageModel;
    }
    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }
    public List<Map<String, Object>> getListMap() {
        return listMap;
    }
    public void setListMap(List<Map<String, Object>> listMap) {
        this.listMap = listMap;
    }
    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
