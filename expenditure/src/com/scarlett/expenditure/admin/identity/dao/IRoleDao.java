package com.scarlett.expenditure.admin.identity.dao;

import java.util.List;

import com.scarlett.expenditure.admin.identity.entity.Role;
import com.scarlett.expenditure.core.dao.HibernateDao;
import com.scarlett.expenditure.core.pojo.PageModel;

/**
 *IRoleDao.java 
 *@intention 
 * <p>  </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface IRoleDao extends HibernateDao{
    /**
     * 分页查询角色
     * @param pageModel 分页实体
     * @return 角色集合
     */
    List<Role> getRoleByPage(PageModel pageModel);
    /**
     * 批量删除
     * @param ids
     */
    void deleteRole(String[] ids);
    /**
     * 统计所有的角色数量
     * @return
     */
    int countRole();
}
