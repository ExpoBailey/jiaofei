package com.scarlett.expenditure.admin.business.dao;

import com.scarlett.expenditure.admin.account.entity.Record;
import com.scarlett.expenditure.admin.business.entity.Bill;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.HibernateDao;
import com.scarlett.expenditure.core.pojo.PageModel;

import java.util.Date;
import java.util.List;

/**
 *IBillDao.java
 *@intention
 * <p> 帐单数据操作接口 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface IBillDao extends HibernateDao{
    /**
     * 按条件统计帐单的条数
     * @param bill
     * @param startDate
     * @param endDate
     * @return
     */
    int countByPage(Bill bill, Date startDate, Date endDate);

    /**
     * 按条件查询帐单列表
     * @param bill
     * @param startDate
     * @param endDate
     * @param pageModel
     * @return
     */
    List<Bill> loadBillAjax(Bill bill, Date startDate, Date endDate, PageModel pageModel);
}
