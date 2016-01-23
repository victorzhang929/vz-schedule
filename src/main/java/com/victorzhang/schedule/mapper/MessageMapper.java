package com.victorzhang.schedule.mapper;

import java.util.List;
import java.util.Map;

public interface MessageMapper {

	/**
	 * 发布信息（系统管理员，部门管理员不同权限，判断departid是否存在）
	 * @param param
	 */
	void addPulishMsg(Map<String, Object> param);

	Map<String, Object> getNewestMsg(String reuserid);

	/**
	 * 获取当前用户前五条未读消息 
	 * @param reuserid
	 * @return
	 */
	List<Map<String, Object>> getUnreadMsg(String reuserid);
	
	
	int getUnreadMsgNum(String reuserid);

	void doReadMsg(Map<String, Object> param);

	int querymsgCount(Map<String, Object> param);

	List<Map<String, Object>> querymsgpage(Map<String, Object> param);

}
