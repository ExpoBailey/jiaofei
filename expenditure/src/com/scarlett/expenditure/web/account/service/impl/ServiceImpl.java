package com.scarlett.expenditure.web.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.identity.dao.IUserDao;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.exception.OAException;
import com.scarlett.expenditure.core.util.MD5;
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
    
    
}
