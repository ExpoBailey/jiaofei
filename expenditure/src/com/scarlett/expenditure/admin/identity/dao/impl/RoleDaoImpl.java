package com.scarlett.expenditure.admin.identity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.scarlett.expenditure.admin.identity.dao.IRoleDao;
import com.scarlett.expenditure.admin.identity.entity.Role;
import com.scarlett.expenditure.core.dao.impl.HibernateDaoImpl;
import com.scarlett.expenditure.core.pojo.PageModel;

/**
 *RoleDaoImpl.java 
 *@intention 
 * <p> 用于操作数据库中角色表的实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class RoleDaoImpl extends HibernateDaoImpl implements IRoleDao {

    /**
     * 分页查询角色
     * @param pageModel 分页实体
     * @return 角色集合
     */
    public List<Role> getRoleByPage(PageModel pageModel){
            String hql = "select r from Role r order by r.id asc";
            return this.findByPage(hql, pageModel, null);
    }
    /**
     * 批量删除
     * @param ids
     */
    public void deleteRole(String[] ids){
            StringBuilder hql = new StringBuilder();
            hql.append("delete from Role where id in(");
            Long[] idArr = new Long[ids.length];
            for (int i = 0; i < ids.length; i++){
                    hql.append(i == 0 ? "?" : ",?");
                    idArr[i] = Long.valueOf(ids[i]);
            }
            hql.append(")");
            this.bulkUpdate(hql.toString(), idArr);
    }
    /* (non-Javadoc)
     * @see com.scarlett.expenditure.admin.identity.dao.IRoleDao#countRole()
     */
    @Override
    public int countRole() {
        StringBuilder hql = new StringBuilder();
        hql
        .append("select count(r.id) from Role as r ");
        return this.count(hql.toString());
    }
}
