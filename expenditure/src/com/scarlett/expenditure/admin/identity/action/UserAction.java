package com.scarlett.expenditure.admin.identity.action;

import com.scarlett.expenditure.admin.identity.entity.User;

/**
 *UserAction.java 
 *@intention 
 * <p> 用户管理的控制层 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class UserAction extends IdentityAction {
    private static final long serialVersionUID = -3796143670201333390L;
    
    private User user;
    private boolean isExist;
    private String userId;
    private String userIds;
    private String name;
    private int indexProxy;


    public String loadAllUserAjax(){
        try {
            listMap = identityService.loadAllUserAjax();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 返回用户页面，初始化用户分页实体
     * @return
     */
    public String countUser(){
        try {
            System.out.println("################## 统计用户 ###################");
            identityService.countUser(user, pageModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    /**
     * 按条件异步加载用户列表
     * @return
     */
    public String loadUserPageData(){
        try {
            System.out.println("################## 异步加载用户 ###################");
            listMap = identityService.loadUserPageData(user, pageModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
        
    }

    /** 异步加载用户姓名 */
    public String loadUserNameAjax(){
            try{
                    //  ["",""]
                    // []: List
                    list = identityService.loadUserName(name);
            }catch(Exception ex){
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 异步验证用户名是否重复 */
    public String validUserIdAjax(){
            try{
                   isExist = identityService.validUserId(userId);
            }catch(Exception ex){
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 添加用户 */
    public String addUser(){
            try{
                    identityService.saveUser(user);
                    setTipStatus(0);
            }catch(Exception ex){
                    setTipStatus(1);
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 跳转到修改用户的页面 */
    public String showUpdateUser(){
            try{
                    user = identityService.getUser(user.getUserId());
            }catch(Exception ex){
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 修改用户 */
    public String updateUser(){
            try{
                    identityService.updateUser(user);
                    setTip("修改成功！");
            }catch(Exception ex){
                    setTip("修改失败！");
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 批量删除用户 */
    public String deleteUser(){
            try{
                    identityService.deleteUser(userIds.split(","));
                    setTip("删除成功！");
            }catch(Exception ex){
                    setTip("删除失败！");
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 批量审核用户 */
    public String checkUser(){
            try{
                    identityService.checkUser(userIds.split(","), user.getStatus());
                    setTipStatus(0);
            }catch(Exception ex){
                    setTipStatus(1);
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /* getter and setter method */
    public User getUser() {
        return user;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getIsExist() {
        return isExist;
    }

    public void setExist(boolean isExist) {
        this.isExist = isExist;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getIndexProxy() {
        return indexProxy;
    }

    public void setIndexProxy(int indexProxy) {
        this.indexProxy = indexProxy;
    }

}
