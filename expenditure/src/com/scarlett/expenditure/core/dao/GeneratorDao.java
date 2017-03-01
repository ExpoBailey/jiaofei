package com.scarlett.expenditure.core.dao;


/**
 * 主键生成数据接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-9-19 上午11:44:33
 * @version 1.0
 */
public interface GeneratorDao extends HibernateDao {
	/**
	 * 生成主键列的方法
	 * @param clazz 持久化类
	 * @param field 字段
	 * @param parentCode 父级编号
	 * @param codeLen 位数
	 * @return 主键值
	 */
	String generatorKey(Class<?> clazz, String field, String parentCode,
			int codeLen);
	
}
