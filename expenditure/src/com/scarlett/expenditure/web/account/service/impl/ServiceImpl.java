package com.scarlett.expenditure.web.account.service.impl;

import com.opensymphony.xwork2.ActionContext;
import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.account.dao.IFFYDao;
import com.scarlett.expenditure.admin.account.dao.IRecordDao;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.account.entity.Record;
import com.scarlett.expenditure.admin.business.dao.IBillDao;
import com.scarlett.expenditure.admin.business.entity.Bill;
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
    
    @Resource
    private IBillDao billDao;

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

    /**
     * 单张帐单，通过当前用户付费易缴费
     * @param from 
     * @param bill 帐单
     * @return
     */
    public Map<String, Object> transation(FullFrom from, Bill bill) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            // 获取当前用户
            User user = AdminConstant.getWebSessionUser();
            
            if (user == null || "".equals(user.getUserId())) {
                map.put("status", 0);
                map.put("des", "用户未登录！");
                return map;
            }
            
            // 付费易帐户
            FuFeiYi ffy = ffyDao.getFFYByUserId(user.getUserId());
            
            // 校验交易密码
            if (from == null || "".equals(from.getTranPW())) {
                map.put("status", 0);
                map.put("des", "交易密码不能为空！");
                return map;
            }
            
            if (!MD5.getMD5(from.getTranPW()).equals(ffy.getPassword())) {
                map.put("status", 0);
                map.put("des", "交易密码错误！");
                return map;
            }
            
            // 帐单
            bill = billDao.get(Bill.class, bill.getId());
            
            if (bill == null || bill.getType() == 1) {
                map.put("status", 0);
                map.put("des", bill == null ? "帐单不存在！" : "帐单已被缴纳过！");
                return map;
            }
            
            if (ffy.getSum() < bill.getSumPrice()) {
                map.put("status", 0);
                map.put("des", "帐户金额不足，请充值后再缴费。");
                return map;
            }
            
            // 系统的付费易，所有付费的钱都转到这
            FuFeiYi admin = ffyDao.getFFYByUserId("admin");
            
            from.setSum(ffy.getSum());
            
            // 余额
            double now = NumUtil.sub(ffy.getSum(), bill.getSumPrice());
            ffy.setSum(now);
            
            // 转钱
            FullFrom f = new FullFrom();
            f.setSum(admin.getSum());
            admin.setSum(NumUtil.add(admin.getSum(), bill.getSumPrice()));
            
            // 变换帐单的状态
            bill.setType(1);
            bill.setHandleDate(new Date());
            bill.setCostStyle("付费易");
            
            // 设置记录数据
            from.setFrom(bill.getCompany().getType());
            from.setNow(ffy.getSum());
            from.setNum(bill.getSumPrice());
            from.setType("支出");
            
            // 当前用户的记录
            addRecordInfo(ffy, from);
            
            // 系统用户记录
            f.setFrom(ffy.getUser().getUserId() + " -- " + ffy.getUser().getName());
            f.setNow(admin.getSum());
            f.setNum(bill.getSumPrice());
            f.setType("收入 -- " + bill.getCompany().getType() + " -- " + bill.getCompany().getName());
            addRecordInfo(admin, f);
        } catch (Exception ex) {
            throw new OAException("单张帐单，通过当前用户付费易缴费时出现异常", ex);
        }
        map.put("status", 1);
        map.put("des", "缴费成功！");
        return map;
    }
}
