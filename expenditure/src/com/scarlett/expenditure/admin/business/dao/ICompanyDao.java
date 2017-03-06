package com.scarlett.expenditure.admin.business.dao;

import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.business.entity.Company;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.HibernateDao;
import com.scarlett.expenditure.core.pojo.PageModel;

import java.util.List;

/**
 *ICompanyDao.java
 *@intention
 * <p> 出帐机构的数据操作接口 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface ICompanyDao extends HibernateDao{

    /**
     * 按条件统计出帐机构的个数
     * @param company
     * @return
     */
    int countByPage(Company company);

    /**
     * 按条件查询出帐机构列表
     * @param company
     * @param pageModel
     * @return
     */
    List<Company> loadCompanyByPage(Company company, PageModel pageModel);

    /**
     * 根据名字统计个数
     * @param name
     * @return
     */
    int countByName(String name, Long id);

    /**
     * 模糊查找名字集
     * @param name
     * @return
     */
    List<Object> getNameList(String name);

    /**
     * 加载所有机构
     * @return
     */
    List<Company> loadAllCompanyAjax();
}
