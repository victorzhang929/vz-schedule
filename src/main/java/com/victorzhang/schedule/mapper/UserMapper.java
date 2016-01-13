package com.victorzhang.schedule.mapper;

import java.util.Map;

public interface UserMapper {

	/**
	 * 原始密码输入是否正确
	 * @param param
	 * @return
	 */
	boolean queryExistPassword(Map<String,Object> param);

	/**
	 * 更新密码
	 * @param param
	 */
	void doChangePassword(Map<String,Object> param);

	/**
	 * 根据userid获取user信息
	 * @param userid
	 * @return
	 */
	Map<String,Object> getUserInfo(String userid);

}
