package com.scarlett.expenditure.admin.account.action;

import com.scarlett.expenditure.admin.identity.entity.User;
import org.apache.struts2.json.JSONUtil;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
