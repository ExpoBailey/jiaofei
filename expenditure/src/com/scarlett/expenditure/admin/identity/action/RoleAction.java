package com.scarlett.expenditure.admin.identity.action;

import java.util.List;

import com.scarlett.expenditure.admin.identity.entity.Role;
import org.apache.struts2.json.JSONUtil;

/**
 *RoleAction.java 
 *@intention 
 * <p> 角色管理的控制层 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class RoleAction extends IdentityAction {
    
    private static final long serialVersionUID = -6318552760204345160L;
    private Role role;
    private List<Role> roles;
    private String ids;
    private String jsonStr;

    /** 查询统计角色 */
    public String selectRole(){
            try{
                    System.out.println("################## 统计角色 ###################");
                    identityService.countRole(pageModel);
            }catch(Exception ex){
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 异步加载角色 */
    public String loadRoleAjax(){
        try{
            System.out.println("################## 异步加载角色 ###################");
            listMap = identityService.getRoleByPage(pageModel);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return SUCCESS;
    }
    
    /** 添加角色 */
    public String addRole(){
            try{
                    identityService.saveRole(role);
                    setTip("添加成功！");
            }catch(Exception ex){
                    setTip("添加失败！");
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 跳转到修改角色页面 */
    public String showUpdateRole(){
            try{
                    role = identityService.getRole(role.getId());
            }catch(Exception ex){
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 修改角色 */
    public String updateRole(){
            try{
                    identityService.updateRole(role);
                    setTip("修改成功！");
            }catch(Exception ex){
                    setTip("修改失败！");
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** 批量删除 */
    public String deleteRole(){
            try{
                    identityService.deleteRole(ids.split(","));
                    setTip("删除成功！");
            }catch(Exception ex){
                    setTip("删除失败！");
                    ex.printStackTrace();
            }
            return SUCCESS;
    }
    
    /** setter and getter method */
    public Role getRole() {
            return role;
    }
    public void setRole(Role role) {
            this.role = role;
    }
    public List<Role> getRoles() {
            return roles;
    }
    public void setRoles(List<Role> roles) {
            this.roles = roles;
    }
    public String getIds() {
            return ids;
    }
    public void setIds(String ids) {
            this.ids = ids;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}

