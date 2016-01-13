package com.victorzhang.schedule.mapper;

import java.util.Map;

import com.victorzhang.schedule.pojo.User;

/**
 * IndexMapper借口
 * @author victorzhang
 *
 */
public interface IndexMapper {
	
	/**
	 * 根据map查找数据库users表是否存在该数据
	 * @param map
	 * @return
	 */
	User querryUser(Map<String, Object> map);

	/**
	 * 查找是否存在邮箱地址为email的用户
	 * @param email
	 * @return
	 */
	User querryUserByEamil(String email);
	
	/**
	 * 查找是否存在邮箱地址为email的用户
	 * @param username
	 * @return
	 */
	User querryUserByUsername(String username);

	/**
	 * 根据userid更新password
	 * @param param
	 */
	void doUpdatePwd(Map<String, Object> param);

}
