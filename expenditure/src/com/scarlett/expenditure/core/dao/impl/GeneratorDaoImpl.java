package com.scarlett.expenditure.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.scarlett.expenditure.core.dao.GeneratorDao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;


/**
 * GeneratorDaoImpl.java 
 *@intention 
 * <p> 主键生成数据接口实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Repository
public class GeneratorDaoImpl extends HibernateDaoImpl implements GeneratorDao {
	/**
	 * 生成主键列的方法
	 * @param clazz 持久化类
	 * @param field 字段
	 * @param parentCode 父级编号
	 * @param codeLen 位数
	 * @return 主键值
	 */
	public String generatorKey(Class<?> clazz, String field, String parentCode,
			int codeLen){
		// SELECT MAX(CODE)  FROM `oa_id_module` WHERE LENGTH(CODE) = 4   // 没有传parentCode
		// SELECT MAX(CODE)  FROM `oa_id_module` WHERE LENGTH(CODE) = 8 AND CODE LIKE '0001%'    // 传parentCode: 0001
		StringBuilder hql = new StringBuilder();
		hql.append("select max("+ field +") from " + clazz.getSimpleName());
		parentCode = StringUtils.isEmpty(parentCode) ? "" : parentCode;
		List<Object> params = new ArrayList<>();
		params.add(parentCode.length() + codeLen);
		hql.append(" where length("+ field +") = ? ");
		if (!StringUtils.isEmpty(parentCode)){
			hql.append(" and " + field + " like ?");
			params.add(parentCode + "%");
		}
		String maxCode = this.findUniqueEntity(hql.toString(), params.toArray());
		if (StringUtils.isEmpty(maxCode)){
			// 0001
			String prefix = "";
			for (int i = 1; i < codeLen; i++){
				prefix += "0"; // 000
			}
			return parentCode + prefix + 1; // 0001
		}else{
			// 0004   --> 0005
			// 00010003 --> 00010004
			// 截取最后四位 0004 0003
			String tempCode = maxCode.substring(maxCode.length() - codeLen, maxCode.length());
			// 0004 --> 5
			// 0003 --> 4
			String suffix = String.valueOf(Integer.parseInt(tempCode) + 1);
			if (suffix.length() > codeLen){
				throw new RuntimeException("主键生成已越界！");
			}
			return parentCode + tempCode.substring(0, tempCode.length() - suffix.length()) + suffix;
		}
	}
}