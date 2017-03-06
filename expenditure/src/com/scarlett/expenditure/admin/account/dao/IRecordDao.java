package com.scarlett.expenditure.admin.account.dao;

import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.account.entity.Record;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.HibernateDao;
import com.scarlett.expenditure.core.pojo.PageModel;

import java.util.Date;
import java.util.List;

/**
 *IRecordDao.java
 *@intention
 * <p> 交易记录的数据操作接口 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface IRecordDao extends HibernateDao{
    /**
     * 按条件统计交易记录的条数
     * @param user
     * @param startDate
     * @param endDate
     * @return
     */
    int countByPage(User user, Date startDate, Date endDate);

    /**
     * 按条件查询交易记录列表
     * @param user
     * @param startDate
     * @param endDate
     * @param pageModel
     * @return
     */
    List<Record> loadRecordAjax(User user, Date startDate, Date endDate, PageModel pageModel);
}
