package com.scarlett.expenditure.admin.business.dao.impl;

import com.scarlett.expenditure.admin.account.entity.Record;
import com.scarlett.expenditure.admin.business.dao.IBillDao;
import com.scarlett.expenditure.admin.business.entity.Bill;
import com.scarlett.expenditure.core.dao.impl.HibernateDaoImpl;
import com.scarlett.expenditure.core.pojo.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *BillDaoImpl.java
 *@intention
 * <p> 交易记录的数据操作实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class BillDaoImpl extends HibernateDaoImpl implements IBillDao {

    @Override
    public int countByPage(Bill bill, Date startDate, Date endDate) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select count(b.id) from Bill b inner join b.pertain u inner join b.company c")
                .append(" where 1=1 ");
        if (bill != null) {
            if (bill.getPertain() != null && !StringUtils.isEmpty(bill.getPertain().getUserId())) {
                hql.append(" and u.userId = ?");
                params.add(bill.getPertain().getUserId());
            }
            if (bill.getCompany() != null && !StringUtils.isEmpty(bill.getCompany().getName())) {
                hql.append(" and c.name like ?");
                params.add("%" + bill.getCompany().getName() + "%");
            }
            if (bill.getCompany() != null && !StringUtils.isEmpty(bill.getCompany().getType())) {
                hql.append(" and c.type = ?");
                params.add(bill.getCompany().getType());
            }
            if (bill.getType() != 0) {
                hql.append(" and b.type = ?");
                params.add(bill.getType());
            }
        }
        if (startDate != null && endDate != null) {
            hql.append(" and b.appearDate between ? and ?");
            params.add(startDate);
            params.add(endDate);
        }
        
        hql.append(" order by b.appearDate desc ");
        return this.count(hql.toString(), params.toArray());
    }

    @Override
    public List<Bill> loadBillAjax(Bill bill, Date startDate, Date endDate, PageModel pageModel) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select b from Bill b inner join b.pertain u inner join b.company c")
                .append(" where 1=1 ");
        if (bill != null) {
            if (bill.getPertain() != null && !StringUtils.isEmpty(bill.getPertain().getUserId())) {
                hql.append(" and u.userId = ?");
                params.add(bill.getPertain().getUserId());
            }
            if (bill.getCompany() != null && !StringUtils.isEmpty(bill.getCompany().getName())) {
                hql.append(" and c.name like ?");
                params.add("%" + bill.getCompany().getName() + "%");
            }
            if (bill.getCompany() != null && !StringUtils.isEmpty(bill.getCompany().getType())) {
                hql.append(" and c.type = ?");
                params.add(bill.getCompany().getType());
            }
            if (bill.getType() != 0) {
                hql.append(" and b.type = ?");
                params.add(bill.getType());
            }
        }
        if (startDate != null && endDate != null) {
            hql.append(" and b.appearDate between ? and ?");
            params.add(startDate);
            params.add(endDate);
        }
        
        hql.append(" order by b.appearDate desc ");
        return this.findByPage(hql.toString(), pageModel, params);
    }
}
