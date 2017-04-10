package com.scarlett.expenditure.core.util;

import java.util.Date;

public class DateUtil {
	/**
	 * 计算输入的Date与现在时间相差多少天
	 * @param date
	 * @return 相差天数。若Date为空，则返回-1。
	 */
	public static int getIntervalDay(Date date) {
		if ( date != null ) {
			long oldTime = date.getTime();
			long nowTime = new Date().getTime();
			long time = 0L;
			if (oldTime > nowTime) {
    			        // 缴费日期还没到
				//time = oldTime - nowTime;
			        return 0;
			} else {
				time = nowTime - oldTime;
			}
			int day =(int) time/1000/60/60/24;
			System.out.println("计算出相差的天数：" + day + "天");
			return day;
		} else {
			return -1;
		}
	}
}
