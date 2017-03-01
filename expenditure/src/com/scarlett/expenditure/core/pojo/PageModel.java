package com.scarlett.expenditure.core.pojo;
/**
 *@intention 
 * <p> 分页实体  </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class PageModel {
	/** 定义默认每页显示的记录条数 */
	private static final int PAGE_SIZE = 2;
	/** 定义当前页码 */
	private int pageIndex;
	/** 定义每页显示记录条数 */
	private int pageSize;
	/** 定义总记录条数 */
	private int recordCount;
	
	/** setter and getter method */
	public int getPageIndex() {
		/** 判断当前页码不能小于1 */
		pageIndex = pageIndex <= 1 ? 1 : pageIndex;
		
		if (this.getRecordCount() >= 1){
			/** 计算总页数 */
			int totalPage = ((this.getRecordCount() - 1) / this.getPageSize()) + 1;
			/** 判断当前页码不能大于总页数 */
			pageIndex = pageIndex >= totalPage ? totalPage : pageIndex;
		}
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize <= 0 ? PAGE_SIZE : pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	// limit 第一个问号?
	public Integer getStartRow() {
		return (this.getPageIndex() - 1) * getPageSize();
	}
}