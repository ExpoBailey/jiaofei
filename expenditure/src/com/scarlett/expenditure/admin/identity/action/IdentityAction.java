package com.scarlett.expenditure.admin.identity.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.scarlett.expenditure.admin.identity.service.IIdentityService;
import com.scarlett.expenditure.core.pojo.PageModel;

/**
 *IdentityAction.java 
 *@intention 
 * <p> 权限管理模块的基础控制器</p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class IdentityAction extends ActionSupport{
    
    private static final long serialVersionUID = -2496960156333682058L;

    @Autowired
    protected IIdentityService identityService;
    
    protected PageModel pageModel = new PageModel();
    protected String tip;
    protected int tipStatus;
    protected List<Object> list;
    protected Map<String, Object> map;
    protected List<Map<String, Object>> listMap;
    protected Map<String,Map<String,Object>> mapMap;
    
    /* getter and setter method */
    public PageModel getPageModel() {
        return pageModel;
    }
    public List<Object> getList() {
        return list;
    }
    public void setList(List<Object> list) {
        this.list = list;
    }
    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }
    public String getTip() {
        return tip;
    }
    public void setTip(String tip) {
        this.tip = tip;
    }
    public int getTipStatus() {
        return tipStatus;
    }
    public void setTipStatus(int tipStatus) {
        this.tipStatus = tipStatus;
    }
    public Map<String, Object> getMap() {
        return map;
    }
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
    public List<Map<String, Object>> getListMap() {
        return listMap;
    }
    public void setListMap(List<Map<String, Object>> listMap) {
        this.listMap = listMap;
    }
    public Map<String, Map<String, Object>> getMapMap() {
        return mapMap;
    }
    public void setMapMap(Map<String, Map<String, Object>> mapMap) {
        this.mapMap = mapMap;
    }
}
