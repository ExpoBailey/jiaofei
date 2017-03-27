package com.scarlett.expenditure.admin.account.dao;

import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.HibernateDao;
import com.scarlett.expenditure.core.pojo.PageModel;

import java.util.List;

/**
 *IFFYDao.java
 *@intention
 * <p> 付费易帐户的数据操作接口 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface IFFYDao extends HibernateDao{

    /**
     * 按条件统计帐户的个数
     * @param user
     * @return
     */
    int countByPage(User user);

    /**
     * 按条件查询帐户列表
     * @param user
     * @param pageModel
     * @return
     */
    List<FuFeiYi> loadFFYByPage(User user, PageModel pageModel);

    FuFeiYi getFFYByUserId(String userId);
}
