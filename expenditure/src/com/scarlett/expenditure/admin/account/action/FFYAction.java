package com.scarlett.expenditure.admin.account.action;

import com.scarlett.expenditure.admin.identity.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.JSONUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *FFYAction.java
 *@intention
 * <p> 付费易的控制器</p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class FFYAction extends AccountAction{

    private User user;
        private String startDateStr;
    private String endDateStr;

    /** 按条件、分页统计帐号的个数 */
    public String countFFY(){
        try {
            accountService.countFFY(user, pageModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    /** 异步加载付费易列表 */
    public String loadFFYAjax(){
        try {
            listMap = accountService.loadFFYAjax(user, pageModel);
            System.out.println("查询到的帐号列表：" + JSONUtil.serialize(listMap));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    /** 按条件、分页统计交易记录的条数 */
    public String countRecord(){
        try {
            Date startDate = null;
            Date endDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtils.isEmpty(startDateStr) || !StringUtils.isEmpty(endDateStr)) {
                if (StringUtils.isEmpty(startDateStr)) {
                    startDateStr = "2016-06-06 06:06:06";
                }
                startDate = sdf.parse(startDateStr);
                endDate =  StringUtils.isEmpty(endDateStr) ? new Date() : sdf.parse(endDateStr);
                System.out.println("开始：" + startDateStr + "-->" + startDate);
                System.out.println("结束：" + endDateStr + "-->" + endDate);
            }
            accountService.countRecord(user, startDate, endDate, pageModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    /** 异步加载交易记录列表 */
    public String loadRecordAjax(){
        try {
            Date startDate = null;
            Date endDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtils.isEmpty(startDateStr) || !StringUtils.isEmpty(endDateStr)) {
                if (StringUtils.isEmpty(startDateStr)) {
                    startDateStr = "2016-06-06 06:06:06";
                }
                startDate = sdf.parse(startDateStr);
                endDate =  StringUtils.isEmpty(endDateStr) ? new Date() : sdf.parse(endDateStr);
                System.out.println("开始：" + startDateStr + "-->" + startDate);
                System.out.println("结束：" + endDateStr + "-->" + endDate);
            }
            listMap = accountService.loadRecordAjax(user,startDate, endDate, pageModel);
            System.out.println("查询到的交易记录列表：" + JSONUtil.serialize(listMap));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }
}
