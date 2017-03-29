package com.scarlett.expenditure.web.account.service;

import java.util.Map;

import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.business.entity.Bill;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.web.account.entity.FullFrom;

/**
 * IService
 * @date 2017年3月26日 上午12:25:46
 * @version 1.0
 */
public interface IService {

    /**
     * 前端界面登录
     * @return
     */
    Map<String, Object> login(String userId, String password);

    FuFeiYi getMyAccount();

    Map<String, Object> fullMoney(FullFrom from);

    /**
     * 添加一条帐户记录信息
     * @param ffy
     * @param from
     */
    void addRecordInfo(FuFeiYi ffy, FullFrom from);
    
    /**
     * 单张帐单，通过当前用户付费易缴费
     * @param from 
     * @param bill 帐单
     * @return
     */
    Map<String, Object> transation(FullFrom from, Bill bill);
}
