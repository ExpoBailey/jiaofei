package com.scarlett.expenditure.admin.account.service;

import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.pojo.PageModel;

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
public interface IAccountService {
    /**
     * 按条件、分页统计帐号的个数
     * @param user
     * @param pageModel
     */
    void countFFY(User user, PageModel pageModel);

    /**
     * 异步加载付费易列表
     * @param user
     * @param pageModel
     * @return
     */
    List<Map<String,Object>> loadFFYAjax(User user, PageModel pageModel);

    /**
     * 按条件、分页统计交易记录的条数
     * @param user
     * @param startDate
     * @param endDate
     */
    void countRecord(User user, Date startDate, Date endDate,PageModel pageModel);

    /**
     * 异步加载交易记录列表
     * @param user
     * @param startDate
     * @param endDate
     * @param pageModel
     * @return
     */
    List<Map<String,Object>> loadRecordAjax(User user, Date startDate, Date endDate, PageModel pageModel);
}
