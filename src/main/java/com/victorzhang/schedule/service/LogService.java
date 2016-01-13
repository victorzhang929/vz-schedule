package com.victorzhang.schedule.service;

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

}
