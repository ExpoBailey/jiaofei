package com.scarlett.expenditure.admin.identity.action;

import java.util.List;
import java.util.Map;

import com.scarlett.expenditure.admin.identity.entity.Module;
import com.scarlett.expenditure.admin.identity.entity.Role;
import com.scarlett.expenditure.admin.identity.entity.User;
import org.apache.struts2.json.JSONUtil;

/**
 *RoleBindAction.java
 *@intention
 * <p> 角色绑定的控制层 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class RoleBindAction extends IdentityAction {
	
	private static final long serialVersionUID = -4323715876746449910L;
	private Role role;
	private List<User> users;
	private String userIds;
	private String moduleCode;
	private List<Module> modules;
	private String codes;
	private String operaCodes;

	/** 把在该角色中的用户个数查询出来 */
	public String selectRoleUser(){
		try{
			identityService.countUserByPageAndRoleId(role, pageModel);
			role = identityService.getRole(role.getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/** 把在该角色中的用户查询出来 */
	public String loadRoleUserAjax(){
		try{
			listMap = identityService.getUserByPageAndRoleId(role, pageModel);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 把不在该角色中的用户的个数查询出来 */
	public String showBindUser(){
		try{
			pageModel.setPageSize(6);
			identityService.countUserByPageNotRoleId(role, pageModel);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/** 把不在该角色中的用户查询出来 */
	public String loadBindUserAjax(){
		try{
			pageModel.setPageSize(6);
			listMap = identityService.getUserByPageNotRoleId(role, pageModel);
			System.out.println(JSONUtil.serialize(listMap));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 角色绑定用户 */
	public String bindUser(){
		try{
			identityService.bindUser(role, userIds.split(","));
			setTip("绑定成功！");
		}catch(Exception ex){
			setTip("绑定失败！");
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 角色解除用户 */
	public String unbindUser(){
		try{
			identityService.unbindUser(role, userIds.split(","));
			setTip("解除成功！");
		}catch(Exception ex){
			setTip("解除失败！");
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 查询操作(权限) */
	public String selectPopedom(){
		try{
			modules = identityService.getModuleByCode8(moduleCode);
			role = identityService.getRole(role.getId());
			/** 根据角色与模块代号查询操作 */ 
			operaCodes = JSONUtil.serialize(identityService.getOperaCodesByModuleCodeAndRoleId(moduleCode, role.getId()));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 角色绑定操作 */
	public String bindModule(){
		try{
			identityService.bindModule(role, moduleCode, codes);
			setTip("绑定成功！");
		}catch(Exception ex){
			setTip("绑定失败！");
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/** 异步加载权限树 */
    public String loadPopedomTreeAjax(){
        try {
            listMap = identityService.loadPopedomTreeAjax();
            System.err.println(JSONUtil.serialize(listMap));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  SUCCESS;
    }


    /** setter and getter method */
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public String getOperaCodes() {
		return operaCodes;
	}

	public void setOperaCodes(String operaCodes) {
		this.operaCodes = operaCodes;
	}
}
