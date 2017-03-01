package com.scarlett.expenditure.admin.identity.dao;

import com.scarlett.expenditure.admin.identity.entity.Module;
import com.scarlett.expenditure.core.dao.HibernateDao;
import com.scarlett.expenditure.core.pojo.PageModel;

import java.util.List;
import java.util.Map;


/**
 *IModuleDao.java
 *@intention
 * <p> 模块数据访问接口 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public interface IModuleDao extends HibernateDao {
	/**
	 * 查询操作(code与name)
	 * @return List
	 */
	List<Map<String, Object>> getModuleByCodeAndName();
	/**
	 * 分页查询操作
	 * @param parentCode 父级编号
	 * @param pageModel 分页实体
	 * @return List
	 */
	List<Module> getModuleByPage(String parentCode, PageModel pageModel, int codeLen);
	/**
	 * 删除操作
	 * @param codes
	 */
	void deleteModule(String[] codes);
	/**
	 * 查询操作(code与name)
	 * @param codeLen
	 * @return List
	 */
	List<Map<String, Object>> getModuleByCodeAndName(int codeLen);
	/**
	 * 查询操作(权限)
	 * @param moduleCode 八位
	 * @return List
	 */
	List<Module> getModuleByCode8(String moduleCode, int codeLen);

	/**
	 * 统计父操作的个数
	 * @param codeLen
	 * @return
     */
    int countParentModule(int codeLen);

	/**
	 * 统计子操作的个数
	 * @param parentCode
	 * @return
     */
	int countChildModule(String parentCode, int childLength);
}
