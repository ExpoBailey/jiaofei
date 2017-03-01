package com.scarlett.expenditure.admin.identity.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.scarlett.expenditure.admin.identity.dao.IModuleDao;
import com.scarlett.expenditure.admin.identity.entity.Module;
import com.scarlett.expenditure.core.dao.impl.HibernateDaoImpl;
import com.scarlett.expenditure.core.pojo.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 *ModuleDaoImpl.java
 *@intention
 * <p> 模块数据访问接口实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class ModuleDaoImpl extends HibernateDaoImpl implements IModuleDao {
	/**
	 * 查询操作(code与name)
	 * @return List
	 */
	public List<Map<String, Object>> getModuleByCodeAndName(){
		String hql = "select new map(code as id, name as name) from Module order by code asc";
		return this.find(hql);
	}
	/**
	 * 分页查询操作
	 * @param parentCode 父级编号
	 * @param pageModel 分页实体
	 * @return List
	 */
	public List<Module> getModuleByPage(String parentCode, PageModel pageModel, int codeLen){
		// SELECT *  FROM `oa_id_module` WHERE LENGTH(CODE) = 4   // 没有传parentCode
		// SELECT *  FROM `oa_id_module` WHERE LENGTH(CODE) = 12 AND CODE LIKE '00010001%'    // 传parentCode: 00010001
		StringBuilder hql = new StringBuilder();
		hql.append("select m from Module m where length(m.code) = ? ");
		parentCode = StringUtils.isEmpty(parentCode) ? "" : parentCode;
		List<Object> params = new ArrayList<>();
		params.add(parentCode.length() + codeLen);
		if (!StringUtils.isEmpty(parentCode)){
			hql.append(" and m.code like ?");
			params.add(parentCode + "%");
		}
		hql.append(" order by m.code asc");
		return this.findByPage(hql.toString(), pageModel, params);
	}
	/**
	 * 删除操作
	 * @param codes
	 */
	public void deleteModule(String[] codes){
		// DELETE  FROM `oa_id_module` WHERE CODE LIKE '0007%' OR CODE LIKE '0006%'
		StringBuilder hql = new StringBuilder();
		hql.append("delete from Module where ");
		List<Object> params = new ArrayList<>();
		for (int i = 0; i < codes.length; i++){
			hql.append( i == 0 ? " code like ?" : " or code like ?");
			params.add(codes[i] + "%");
		}
		this.bulkUpdate(hql.toString(), params.toArray());
	}
	/**
	 * 查询操作(code与name)
	 * @param codeLen
	 * @return List
	 */
	public List<Map<String, Object>> getModuleByCodeAndName(int codeLen){
		String hql = "select new map(code as id, name as name) from Module where length(code) <= ? order by code asc";
		return (List<Map<String, Object>>)this.find(hql, new Object[]{codeLen});
	}
	/**
	 * 查询操作(权限)
	 * @param moduleCode 八位
	 * @return List
	 */
	public List<Module> getModuleByCode8(String moduleCode, int codeLen){
		String hql = "select m from Module m where m.code like ? and length(m.code) = ? order by m.code asc";
		return (List<Module>)this.find(hql, new Object[]{moduleCode + "%", moduleCode.length() + codeLen});
	}

	/**
	 * 统计父操作的个数
	 * @param codeLen
	 * @return
	 */
	public int countParentModule(int codeLen) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(code) from Module where length(code)<=? ");
		return this.count(hql.toString(), new Object[]{codeLen});
	}

	/**
	 * 统计子操作的个数
	 * @param parentCode
	 * @return
	 */
	public int countChildModule(String parentCode, int childLength) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(code) from Module where code like ? and length(code) = ?");
		return this.count(hql.toString(), new Object[]{parentCode+"%", childLength});
	}
}
