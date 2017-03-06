package com.scarlett.expenditure.admin.business.action;

import com.scarlett.expenditure.admin.business.entity.Company;
import org.apache.struts2.json.JSONUtil;

/**
 *FFYAction.java
 *@intention
 * <p> 出帐机构的控制器</p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class CompanyAction extends BusinessAction {

    private Company company;
    private boolean isExist;
    private String name;
    private Long id;

    /** 按条件、分页统计帐号的个数 */
    public String countCompany(){
        try {
            businessService.countCompany(company, pageModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    /** 异步加载帐号列表 */
    public String loadCompanyAjax(){
        try {
            listMap = businessService.loadCompanyAjax(company, pageModel);
            System.out.println("查询到的帐号列表：" + JSONUtil.serialize(listMap));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public String loadCompanyNameAjax(){
        try {
            list = businessService.loadCompanyNameAjax(name);
            System.out.println("查询到的帐号列表：" + JSONUtil.serialize(list));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    /** 校验 */
    public String validComAjax(){
        try {
            isExist = businessService.validComAjax(name, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public String addCompany() {
        try {
            businessService.addCompany(company);
            setTipStatus(0);
        } catch (Exception ex) {
            setTipStatus(1);
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public String showUpdateCompany(){
        try {
            company = businessService.getCompanyById(company.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    /** 修改机构 */
    public String updateCompany(){
        try{
            businessService.updateCompany(company);
            setTip("修改成功！");
        }catch(Exception ex){
            setTip("修改失败！");
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public String loadAllCompanyAjax() {
        try {
            listMap = businessService.loadAllCompanyAjax();
            System.out.println("所有机构：" + JSONUtil.serialize(listMap));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public boolean getIsExist() {
        return isExist;
    }

    public void setIsExist(boolean exist) {
        isExist = exist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
