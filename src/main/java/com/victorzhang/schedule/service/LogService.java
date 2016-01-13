package com.victorzhang.schedule.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据库日志
 * @author victorzhang
 *
 */
public interface LogService {
	
	/**
	 * 加入数据库日志
	 * @param lx
	 * @param ms
	 * @return
	 */
	void addLog(String lx, String ms);

	/**
	 * 根据参数查询日志记录
	 * @param _page
	 * @param _pageSize
	 * @param loglx
	 * @param stadate
	 * @param enddate
	 * @param isuserslog
	 * @return
	 */
	Map<String, Object> querylogpage(HttpServletRequest request, String _page, String _pageSize, String loglx, String stadate, String enddate, String isuserslog);

}
