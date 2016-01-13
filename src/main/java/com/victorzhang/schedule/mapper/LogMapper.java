package com.victorzhang.schedule.mapper;

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

}
