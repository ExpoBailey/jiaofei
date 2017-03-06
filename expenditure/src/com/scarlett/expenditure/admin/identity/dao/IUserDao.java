package com.scarlett.expenditure.admin.identity.dao;

import java.util.List;

import com.scarlett.expenditure.admin.identity.entity.Role;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.HibernateDao;
import com.scarlett.expenditure.core.pojo.PageModel;

/**
 *IUserDao.java 
 *@intention 
 * <p> 操作用户的接口类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface IUserDao extends HibernateDao{
    /**
     * 按条件统计用户数量
     * @param user
     * @return
     */
    int countUser(User user);
    
    /**
     * 多条件分页查询用户
     * @param user
     * @param pageModel
     * @return
     */
    List<User> getUserByPage(User user, PageModel pageModel);
    
    /**
     * 批量删除用户
     * @param userIds
     */
    void deleteUser(String[] userIds);

    /**
     * 批量审核用户
     * @param userIds
     * @param status
     */
    void checkUser(String[] userIds, Short status);
    
    /**
     * 加载用户姓名
     * @param name
     * @return
     */
    List<Object> getUserByNames(String name);

    /**
     * 把在该角色中的用户查询出来
     * @param role 角色
     * @param pageModel 分页实体
     * @return List
     */
    List<User> getUserByPageAndRoleId(Role role, PageModel pageModel);

    /**
     * 把不在该角色中的用户查询出来
     * @param role 角色
     * @param pageModel 分页实体
     * @return List
     */
    List<User> getUserByPageNotRoleId(Role role, PageModel pageModel);

    /**
     * 把在该角色中的用户个数查询出来
     * @param role
     * @return
     */
    int countUserByPageAndRoleId(Role role);

    /**
     * 把不在该角色中的用户的个数查询出来
     * @param role
     * @return
     */
    int countUserByPageNotRoleId(Role role);

    /**
     * 加载所有用户
     * @return
     */
    List<User> getAllUser();
}
