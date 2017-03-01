package com.scarlett.expenditure.admin.identity.action;

import com.scarlett.expenditure.admin.identity.entity.Module;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


/**
 *RoleAction.java
 *@intention
 * <p> 操作管理的控制层 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class ModuleAction extends IdentityAction {
	
	private static final long serialVersionUID = -687377726052135410L;
	/** 定义父级code */
	private String parentCode;
	private Module module;
	private List<Module> modules;
	private String codes;

	public String loadModuleTreeAjax(){
		try {
			listMap = identityService.loadModuleTree();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/** 统计出对应的子操作数量 */
	public String selectModule(){
		try{
			identityService.countModule(parentCode, pageModel);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/** 分页查询操作 */
	public String loadModuleAjax(){
		try{
			listMap = identityService.getModuleByPage(parentCode, pageModel);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 添加操作 */
	public String addModule(){
		try{
			/** 父级code */
			module.setCode(parentCode);
			identityService.saveModule(module);
			setTip("添加成功！");
		}catch(Exception ex){
			setTip("添加失败！");
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 跳转到修改操作页面 */
	public String showUpdateModule(){
		try{
			module = identityService.getModule(module.getCode());
			module.setName(module.getName().replaceAll("-", ""));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 修改操作 */
	public String updateModule(){
		try{
			identityService.updateModule(module);
			setTip("修改成功！");
		}catch(Exception ex){
			setTip("修改失败！");
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 删除操作 */
	public String deleteModule(){
		try{
			identityService.deleteModule(codes.split(","));
			setTip("删除成功！");
		}catch(Exception ex){
			setTip("删除失败！");
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** setter and getter method */
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
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
}