package com.scarlett.expenditure.admin.identity.dao.impl;

import com.scarlett.expenditure.admin.identity.dao.IPopedomDao;
import com.scarlett.expenditure.core.dao.impl.HibernateDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *PopedomDaoImpl.java
 *@intention
 * <p> 权限数据访问接口实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class PopedomDaoImpl extends HibernateDaoImpl implements IPopedomDao {
	/**
	 * 根据角色与模块代号查询操作
	 * @param moduleCode
	 * @param id
	 * @return List
	 */
	public List<String> getOperaCodesByModuleCodeAndRoleId(String moduleCode, Long id){
		String hql = "select opera.code from Popedom where module.code = ? and role.id = ?";
		return (List<String>)this.find(hql, new Object[]{moduleCode, id});
	}
	/**
	 * 删除以前绑定的权限
	 * @param id
	 * @param moduleCode
	 */
	public void deletePopedom(Long id, String moduleCode){
		String hql = "delete from Popedom where module.code = ? and role.id = ?";
		this.bulkUpdate(hql, new Object[]{moduleCode, id});
	}
	/**
	 * 根据当前Session中的用户userId查询他所有的角色，
	 * 再根据角色查询所有权限(code为八位)
	 * @param userId 用户id
	 * @return List
	 */
	public List<String> getModuleCodeByUserId(String userId){
		StringBuilder hql = new StringBuilder();
		hql.append("select module.code from Popedom where role.id in");
		hql.append("(select r.id from Role r inner join r.users as u where u.userId = ?)");
		hql.append(" group by module.code order by module.code asc");
		return (List<String>)this.find(hql.toString(), new Object[]{userId});
	}
	/**
	 * 根据用户userId查询所有的角色，再根据角色查询所有操作(12位)
	 * @param userId
	 * @return List
	 */
	public List<String> getOperaCodeByUserId(String userId){
		StringBuilder hql = new StringBuilder();
		hql.append("select opera.code from Popedom where role.id in");
		hql.append("(select r.id from Role r inner join r.users as u where u.userId = ?)");
		hql.append(" group by opera.code order by opera.code asc");
		return (List<String>)this.find(hql.toString(), new Object[]{userId});
	}
}
