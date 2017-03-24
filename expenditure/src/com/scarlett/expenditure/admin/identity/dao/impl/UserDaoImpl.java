package com.scarlett.expenditure.admin.identity.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.scarlett.expenditure.admin.identity.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.identity.dao.IUserDao;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.impl.HibernateDaoImpl;
import com.scarlett.expenditure.core.pojo.PageModel;

/**
 *UserDao.java 
 *@intention 
 * <p> 用于操作数据库中用户表的实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class UserDaoImpl extends HibernateDaoImpl implements IUserDao {

    /**
     * 按条件统计用户数量
     * @param user
     * @return
     */
    public int countUser(User user) {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from User as u where 1=1 ");
        // 定义List集合封装查询参数
        List<Object> params = new ArrayList<>();
        if (user != null){
                // 姓名
                if (!StringUtils.isEmpty(user.getName())){
                        hql.append(" and u.name like ?");
                        params.add("%"+ user.getName() +"%");
                }
                // 手机号码
                if (!StringUtils.isEmpty(user.getPhone())){
                        hql.append(" and u.phone like ?");
                        params.add("%"+ user.getPhone() +"%");
                }
        }
        // 添加排序
        hql.append(" order by u.createDate asc");
        return this.count(hql.toString(), params.toArray());
    }

    /**
     * 多条件分页查询用户
     * @param user
     * @param pageModel
     * @return
     */
    public List<User> getUserByPage(User user, PageModel pageModel) {
        StringBuilder hql = new StringBuilder();
        hql.append("select u from User as u where 1=1 ");
        // 定义List集合封装查询参数 
        List<Object> params = new ArrayList<>();
        if (user != null){
                // 姓名 
                if (!StringUtils.isEmpty(user.getName())){
                        hql.append(" and u.name like ?");
                        params.add("%"+ user.getName() +"%");
                }
                // 手机号码 
                if (!StringUtils.isEmpty(user.getPhone())){
                        hql.append(" and u.phone like ?");
                        params.add("%"+ user.getPhone() +"%");
                }
        }
        // 添加排序 
        hql.append(" order by u.createDate asc");
        return this.findByPage(hql.toString(), pageModel, params);
    }
    
    /**
     * 批量删除用户
     * @param userIds
     */
    public void deleteUser(String[] userIds){
            // DELETE FROM `oa_id_user` WHERE user_id IN(?, ?)
            StringBuilder hql = new StringBuilder();
            hql.append("delete from User where userId in(");
            for (int i = 0; i < userIds.length; i++){
                    hql.append(i == 0 ? "?" : ",?");
            }
            hql.append(")");
            this.bulkUpdate(hql.toString(), userIds);
    }
    
    /**
     * 批量审核用户
     * @param userIds
     * @param status
     */
    public void checkUser(String[] userIds, Short status){
            // UPDATE `oa_id_user` SET STATUS = ?, CHECK_DATE = ?, checker = ? WHERE user_id IN(?,?)
            StringBuilder hql = new StringBuilder();
            hql.append("update User set status = ?, checkDate = ?, checker = ? where userId in(");
            List<Object> params = new ArrayList<>();
            params.add(status);
            params.add(new Date());
            params.add(AdminConstant.getSessionUser());
            for (int i = 0; i < userIds.length; i++){
                    hql.append(i == 0 ? "?" : ",?");
                    params.add(userIds[i]);
            }
            hql.append(")");
            this.bulkUpdate(hql.toString(), params.toArray());
    }
    
    /**
     * 查询用户姓名
     * @param name 姓名
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<Object> getUserByNames(String name){
            String hql = "select name from User where name like ?";
            return (List<Object>)this.find(hql, new Object[]{"%" + name + "%"});
    }

    /**
     * 把在该角色中的用户查询出来
     * @param role 角色
     * @param pageModel 分页实体
     * @return List
     */
    public List<User> getUserByPageAndRoleId(Role role, PageModel pageModel){
        String hql = "select u from User as u inner join u.roles as r where r.id = ? order by u.createDate asc";
        List<Object> params = new ArrayList<>();
        params.add(role.getId());
        return this.findByPage(hql, pageModel, params);
    }

    /**
     * 把不在该角色中的用户查询出来
     * @param role 角色
     * @param pageModel 分页实体
     * @return List
     */
    public List<User> getUserByPageNotRoleId(Role role, PageModel pageModel){
        StringBuilder hql = new StringBuilder();
        hql.append("select u from User u where u.userId not in");
        hql.append("(select us.userId from User us inner join us.roles as r where r.id = ?)");
        hql.append(" order by u.createDate asc");

        List<Object> params = new ArrayList<>();
        params.add(role.getId());
        return this.findByPage(hql.toString(), pageModel, params);
    }

    /**
     * 把在该角色中的用户个数查询出来
     * @param role
     * @return
     */
    public int countUserByPageAndRoleId(Role role){
        String hql = "select count(u.userId) from User as u inner join u.roles as r where r.id = ? order by u.createDate asc";
        List<Object> params = new ArrayList<>();
        params.add(role.getId());
        return this.count(hql.toString(), params.toArray());
    }

    /**
     * 把不在该角色中的用户的个数查询出来
     * @param role
     * @return
     */
    public int countUserByPageNotRoleId(Role role){
        StringBuilder hql = new StringBuilder();
        hql.append("select count(u.userId) from User u where u.userId not in");
        hql.append("(select us.userId from User us inner join us.roles as r where r.id = ?)");
        hql.append(" order by u.createDate asc");

        List<Object> params = new ArrayList<>();
        params.add(role.getId());
        return this.count(hql.toString(), params.toArray());
    }

    @Override
    public List<User> getAllUser() {
        String hql = "select u from User u";
        return this.find(hql);
    }
}
