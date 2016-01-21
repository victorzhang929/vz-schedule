package com.victorzhang.schedule.service;

import java.util.List;
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
	 * @param roleType
	 * @return
	 */
	Map<String, Object> querylogpage(HttpServletRequest request, String _page, String _pageSize, String loglx,String dname, String stadate, String enddate, String roleType);

	/**
	 * 查看当前用户所有日志类型
	 * @param request
	 * @return
	 */
	List<Map<String, Object>> queryAllLogLx(HttpServletRequest request, String roleType);

}
