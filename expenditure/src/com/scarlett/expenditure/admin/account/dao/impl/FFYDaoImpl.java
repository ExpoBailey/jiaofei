package com.scarlett.expenditure.admin.account.dao.impl;

import com.scarlett.expenditure.admin.account.dao.IFFYDao;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.impl.HibernateDaoImpl;
import com.scarlett.expenditure.core.pojo.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 *FFYDaoImpl.java
 *@intention
 * <p> 付费易帐户的数据操作实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class FFYDaoImpl extends HibernateDaoImpl implements IFFYDao {
    @Override
    public int countByPage(User user) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select count(f.id) from FuFeiYi f inner join f.user u ")
        .append(" where 1=1 ");
        if (user != null) {
            if (!StringUtils.isEmpty(user.getUserId())) {
                hql.append(" and u.userId like ?");
                params.add("%" + user.getUserId() + "%");
            }
            if (!StringUtils.isEmpty(user.getPhone())) {
                hql.append(" and u.phone like ?");
                params.add(user.getPhone() + "%");
            }
        }
        return this.count(hql.toString(), params.toArray());
    }

    @Override
    public List<FuFeiYi> loadFFYByPage(User user, PageModel pageModel) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select f from FuFeiYi f inner join f.user u ")
                .append(" where 1=1");
        if (user != null) {
            if (!StringUtils.isEmpty(user.getUserId())) {
                hql.append(" and u.userId like ?");
                params.add("%" + user.getUserId() + "%");
            }
            if (!StringUtils.isEmpty(user.getPhone())) {
                hql.append(" and u.phone like ?");
                params.add(user.getPhone() + "%");
            }
        }
        return this.findByPage(hql.toString(), pageModel, params);
    }
}
