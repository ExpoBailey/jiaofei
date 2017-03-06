package com.scarlett.expenditure.admin.account.dao.impl;

import com.scarlett.expenditure.admin.account.dao.IRecordDao;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.account.entity.Record;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.dao.impl.HibernateDaoImpl;
import com.scarlett.expenditure.core.pojo.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *RecordDaoImpl.java
 *@intention
 * <p> 交易记录的数据操作实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class RecordDaoImpl extends HibernateDaoImpl implements IRecordDao{


    @Override
    public int countByPage(User user, Date startDate, Date endDate) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select count(r.id) from Record r inner join r.ffy.user u ")
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
        if (startDate != null || endDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (startDate == null)
                try {
                    startDate = sdf.parse("2016-06-06 06:06:06");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            if (endDate == null) endDate = new Date();
            hql.append(" and r.tranDate between ? and ?");
            params.add(startDate);
            params.add(endDate);
        }
        return this.count(hql.toString(), params.toArray());
    }

    @Override
    public List<Record> loadRecordAjax(User user, Date startDate, Date endDate, PageModel pageModel) {
        StringBuilder hql = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        hql.append("select r from Record r inner join r.ffy.user u ")
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
        if (startDate != null || endDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (startDate == null)
                try {
                    startDate = sdf.parse("2016-06-06 06:06:06");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            if (endDate == null) endDate = new Date();
            hql.append(" and r.tranDate between ? and ?");
            params.add(startDate);
            params.add(endDate);
        }
        return findByPage(hql.toString(), pageModel, params);
    }
}
