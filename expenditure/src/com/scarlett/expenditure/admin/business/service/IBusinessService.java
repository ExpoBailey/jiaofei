package com.scarlett.expenditure.admin.business.service;

import com.scarlett.expenditure.admin.business.entity.Bill;
import com.scarlett.expenditure.admin.business.entity.Company;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.pojo.PageModel;
import com.scarlett.expenditure.web.account.entity.FullFrom;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *IAccountService.java
 *@intention
 * <p> 付费易帐户的业务接口 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface IBusinessService {
    /**
     * 按条件、分页统计机构的个数
     * @param company
     * @param pageModel
     */
    void countCompany(Company company, PageModel pageModel);

    /**
     * 异步加载机构列表
     * @param company
     * @param pageModel
     * @return
     */
    List<Map<String,Object>> loadCompanyAjax(Company company, PageModel pageModel);

    /**
     * 按条件、分页统计帐单的条数
     * @param bill
     * @param startDate
     * @param endDate
     */
    void countBill(Bill bill, Date startDate, Date endDate, PageModel pageModel);

    /**
     * 异步加载帐单列表
     * @param bill
     * @param startDate
     * @param endDate
     * @param pageModel
     * @return
     */
    List<Map<String,Object>> loadBillAjax(Bill bill, Date startDate, Date endDate, PageModel pageModel);

    /**
     * 校验名字是否存在
     * @param name
     * @return
     */
    boolean validComAjax(String name, Long id);

    /**
     * 增加机构
     * @param company
     */
    void addCompany(Company company);

    /**
     * 根据id查询机构
     * @param id
     * @return
     */
    Company getCompanyById(Long id);

    /**
     * 修改机构
     * @param company
     */
    void updateCompany(Company company);

    /**
     * 加载名字
     * @param name
     * @return
     */
    List<Object> loadCompanyNameAjax(String name);

    /**
     * 加载所有的机构
     * @return
     */
    List<Map<String,Object>> loadAllCompanyAjax();

    /**
     * 增加帐单
     * @param bill
     */
    void addBill(Bill bill);

    /**
     * 根据id取帐单
     * @param id
     * @return
     */
    Bill getBillById(Long id);

    /**
     * 修改帐单
     * @param bill
     */
    void updateBill(Bill bill);


}
