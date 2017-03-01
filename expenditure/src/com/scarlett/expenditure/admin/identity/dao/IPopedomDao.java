package com.scarlett.expenditure.admin.identity.dao;

import com.scarlett.expenditure.core.dao.HibernateDao;

import java.util.List;


/**
 *IPopedomDao.java
 *@intention
 * <p> 权限数据访问接口 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface IPopedomDao extends HibernateDao {
	/**
	 * 根据角色与模块代号查询操作
	 * @param moduleCode
	 * @param id
	 * @return List
	 */
	List<String> getOperaCodesByModuleCodeAndRoleId(String moduleCode, Long id);
	/**
	 * 删除以前绑定的权限
	 * @param id
	 * @param moduleCode
	 */
	void deletePopedom(Long id, String moduleCode);
	/**
	 * 根据当前Session中的用户userId查询他所有的角色，
	 * 再根据角色查询所有权限(code为八位)
	 * @param userId 用户id
	 * @return List
	 */
	List<String> getModuleCodeByUserId(String userId);
	/**
	 * 根据用户userId查询所有的角色，再根据角色查询所有操作(12位)
	 * @param userId
	 * @return List
	 */
	List<String> getOperaCodeByUserId(String userId);

}
