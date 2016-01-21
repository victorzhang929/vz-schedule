package com.victorzhang.schedule.mapper;

import java.util.List;
import java.util.Map;

import com.victorzhang.schedule.pojo.Depart;

public interface DepartMapper {

	/**
	 * 部门总数
	 * @param param
	 * @return
	 */
	int queryCount(Map<String, Object> param);

	/**
	 * 部门总记录
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryDepartInfos(Map<String, Object> param);

	/**
	 * 获取当前部门下所有班级(系统管理员权限)
	 * @param departid
	 * @return
	 */
	List<Map<String, Object>> queryClassesByDepartid(String departid);

	/**
	 * 获取当前学院信息(系统管理员权限)
	 * @param departid
	 * @return
	 */
	Depart getDepartInfo(String departid);

	/**
	 * 更新部门信息(系统管理员权限)
	 * @param param
	 */
	void doUpdateDepart(Map<String, Object> param);

	/**
	 * 删除当前学院，联表删除包括所有下属班级和学生，教师(系统管理员权限)
	 * @param departid
	 */
	void deleteDepart(String departid);

	/**
	 * 添加部门(系统管理员权限)
	 * @param param
	 */
	void addDepart(Map<String, Object> param);

}
