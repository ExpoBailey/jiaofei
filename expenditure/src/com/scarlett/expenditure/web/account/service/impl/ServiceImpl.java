package com.scarlett.expenditure.web.account.service.impl;

import com.opensymphony.xwork2.ActionContext;
import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.account.dao.IFFYDao;
import com.scarlett.expenditure.admin.account.dao.IRecordDao;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.account.entity.Record;
import com.scarlett.expenditure.admin.identity.dao.IUserDao;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.exception.OAException;
import com.scarlett.expenditure.core.util.MD5;
import com.scarlett.expenditure.core.util.NumUtil;
import com.scarlett.expenditure.web.account.entity.FullFrom;
import com.scarlett.expenditure.web.account.service.IService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ServiceImpl
 * @date 2017年3月26日 上午12:27:17
 * @version 1.0
 */
@Service
public class ServiceImpl implements IService {
    
    @Resource
    private IUserDao userDao;
    
    @Resource
    private IFFYDao ffyDao;
    
    @Resource
    private IRecordDao recordDao;

    @Override
    public Map<String, Object> login(String userId, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User u = userDao.get(User.class, userId);
            if (u != null) {
                if (u.getPassword().equals(MD5.getMD5(password))) {
                    map.put("status", 1);
                    ActionContext.getContext().getSession().put(AdminConstant.WEB_SESSION_USER, u);
                } else {
                    map.put("status", 0);
                    map.put("des", "密码错误！");
                }
            } else {
                map.put("status", 0);
                map.put("des", "用户名不存在！");
            }
        } catch (Exception e) {
            throw new OAException("登录方法出现异常！", e);
        }
        return map;
    }

    @Override
    public FuFeiYi getMyAccount() {
        FuFeiYi ffy = null;
        try {
            User user = AdminConstant.getWebSessionUser();
            if (user == null || user.getUserId() == null || "".equals(user.getUserId())) {
                return ffy;
            }
            ffy = ffyDao.getFFYByUserId(user.getUserId());
            if (ffy != null) {
                if (ffy.getUser() != null) ffy.getUser().getName();
            }
        } catch (Exception e) {
            throw new OAException("获取我的帐户出现异常！", e);
        }
        return ffy;
    }

    @Override
    public Map<String, Object> fullMoney(FullFrom from) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 引入第三方充值支付平台（支付宝、微信等）,假设已经对接成功。
            boolean success = true;
            if (success) {
                // 1.充值
                //  先拿此用户帐号
                FuFeiYi ffy = getMyAccount();
                Double old = ffy.getSum();
                Double num = from.getNum();
                if (num != 0.00) {
                    double now = NumUtil.add(old, num);
                    System.out.println("计算公式：" + old + " + " + num + " = " + now);
                    from.setSum(old);
                    from.setNow(now);
                    from.setType("充值");
                    ffy.setSum(now);
                    // 2.帐单记录
                    addRecordInfo(ffy, from);
                }
                map.put("status", 1);
                map.put("des", "充值成功！余额为￥" + ffy.getSum());
            } else {
                map.put("status", 0);
                map.put("des", "支付方式验证失败！");
            }
        } catch (Exception e) {
            map.put("status", 0);
            map.put("des", "充值失败，服务器异常！");
            throw new OAException("充值方法出现异常！", e);
        }
        return map;
    }

    @Override
    public void addRecordInfo(FuFeiYi ffy, FullFrom from) {
    //    如：总额 70 元    电费     支出   50.0 元   余额 20.0 元
    //    如：总额  0 元   支付宝    充值   30.0 元   余额 30.0 元
        try {
            StringBuilder sb = new StringBuilder();
            if (from != null) {
                sb.append("总额：￥").append(from.getSum())
                .append(" ").append(from.getFrom())
                .append(" ").append(from.getType())
                .append("：￥" + from.getNum())
                .append(" 余额：￥").append(from.getNow());
            }
            if (ffy != null && ffy.getUser() != null) {
                Record record = new Record();
                record.setFfy(ffy);
                record.setContent(sb.toString());
                record.setTranDate(new Date());
                record.setRemark("");
                recordDao.save(record);
            }
        } catch (Exception e) {
            throw new OAException("添加帐单记录出现异常！", e);
        }
    }


}
