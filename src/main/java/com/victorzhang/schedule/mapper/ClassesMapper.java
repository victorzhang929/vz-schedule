package com.victorzhang.schedule.mapper;

import java.util.List;
import java.util.Map;

public interface ClassesMapper {

	/**
	 * 计算班级数
	 * @param param
	 * @return
	 */
	int queryCount(Map<String, Object> param);

	/**
	 * 班级详细信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryClassesInfos(Map<String, Object> param);

	/**
	 * 根据classid获取当前班级信息
	 * @param classid
	 * @return
	 */
	Map<String, Object> getClassesInfo(String classid);

	/**
	 * 根据学院名称获取学院id
	 * @param dname
	 * @return
	 */
	String getDepartidByDname(String dname);
	
	/**
	 * 更新班级表信息
	 * @param param
	 */
	void doUpdateClasses(Map<String, Object> param);

	/**
	 * 更新用户表信息
	 * @param param
	 */
	void doUpdateUsers(Map<String, Object> param);

	/**
	 * 删除班级信息及该班级下学生，教师信息
	 * @param classid
	 */
	void deleteClass(String classid);

	/**
	 * 添加班级
	 * @param param
	 */
	void addClasses(Map<String, Object> param);

	/**
	 * 根据部门名称，查询所有班级名称
	 * @param dname
	 * @return
	 */
	List<Map<String, Object>> queryAllClassesByDname(String dname);

}
