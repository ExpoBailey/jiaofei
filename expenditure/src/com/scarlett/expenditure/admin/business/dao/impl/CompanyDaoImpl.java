package com.scarlett.expenditure.admin.business.dao.impl;

import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.business.dao.ICompanyDao;
import com.scarlett.expenditure.admin.business.entity.Company;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.impl.HibernateDaoImpl;
import com.scarlett.expenditure.core.pojo.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 *CompanyDaoImpl.java
 *@intention
 * <p> 出帐机构的数据操作实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class CompanyDaoImpl extends HibernateDaoImpl implements ICompanyDao {

    @Override
    public int countByPage(Company company) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select count(c.id) from Company c ")
                .append(" where 1=1 ");
        if (company != null) {
            if (!StringUtils.isEmpty(company.getName())) {
                hql.append(" and c.name like ?");
                params.add("%" + company.getName() + "%");
            }
            if (!StringUtils.isEmpty(company.getType())) {
                hql.append(" and c.type = ?");
                params.add(company.getType());
            }
        }
        return this.count(hql.toString(), params.toArray());
    }

    @Override
    public List<Company> loadCompanyByPage(Company company, PageModel pageModel) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select c from Company c ")
                .append(" where 1=1 ");
        if (company != null) {
            if (!StringUtils.isEmpty(company.getName())) {
                hql.append(" and c.name like ?");
                params.add("%" + company.getName() + "%");
            }
            if (!StringUtils.isEmpty(company.getType())) {
                hql.append(" and c.type = ?");
                params.add(company.getType());
            }
        }
        return this.findByPage(hql.toString(), pageModel, params);
    }

    @Override
    public int countByName(String name, Long id) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select count(c.id) from Company c ")
                .append(" where c.name=? ");
        params.add(name);
        if (id != null || id != 0L) {
            hql.append(" and c.id <> ?");
            params.add(id);
        }
        return this.count(hql.toString(), params.toArray());
    }

    @Override
    public List<Object> getNameList(String name) {
        String hql = "select name from Company where 1=1 and name like ?";
        return (List<Object>) this.find(hql, new Object[]{"%" + name + "%"});
    }

    @Override
    public List<Company> loadAllCompanyAjax() {
        String hql = "select c from Company c where 1=1";
        return this.find(hql.toString());
    }
}
