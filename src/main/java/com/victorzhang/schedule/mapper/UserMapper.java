package com.victorzhang.schedule.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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

	/**
	 * 保存用户信息
	 * @param param
	 */
	void doSaveUserInfo(Map<String, Object> param);

	/**
	 * 获取学生或教师总数
	 * @param param
	 * @return
	 */
	int queryUserCount(Map<String, Object> param);

	/**
	 * 获取学生或教师记录
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryUserInfos(Map<String, Object> param);

	/**
	 * 根据userid获取学生，教师信息
	 * @param userid
	 * @return
	 */
	Map<String, Object> getUserInfoByUserid(String userid);

	/**
	 * 保存学生，教师信息
	 * @param param
	 */
	void doUpdateStuTeaInfo(Map<String, Object> param);

	/**
	 * 根据userid删除该用户
	 * @param userid
	 */
	void deleteUserInfo(String userid);

	/**
	 * 添加用户
	 * @param param
	 */
	void addUserInfo(Map<String, Object> param);

	/**
	 * 根据departid查询所有用户
	 * @return
	 */
	List<Map<String, Object>> queryUseridsByDid(@Param(value="departid") String departid);

}
