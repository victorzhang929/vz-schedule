package com.victorzhang.schedule.mapper;

import java.util.List;
import java.util.Map;

/**
 * 数据日志
 * @author victorzhang
 *
 */
public interface LogMapper {

	/**
	 * 加入日志
	 * @param info
	 * @return
	 */
	void addLog(Map<String, Object> info);

	/**
	 * 根据参数查询数据库中日志的总数
	 * @param param
	 * @return
	 */
	int queryCount(Map<String, Object> param);

	/**
	 * 根据参数查询数据库中日志的记录
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> querylogpage(Map<String, Object> param);

}
