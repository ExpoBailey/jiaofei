package com.scarlett.expenditure.web.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.account.dao.IFFYDao;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.identity.dao.IUserDao;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.exception.OAException;
import com.scarlett.expenditure.core.util.MD5;
import com.scarlett.expenditure.web.account.FullFrom;
import com.scarlett.expenditure.web.account.service.IService;

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
                
                // 2.帐单记录
            } else {
                map.put("status", 0);
                map.put("des", "支付方式验证失败！");
            }
        } catch (Exception e) {
            throw new OAException("充值方法出现异常！", e);
        }
        return map;
    }
    
    
}
