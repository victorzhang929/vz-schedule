package com.victorzhang.schedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ClassesService {

	/**
	 * 班级信息记录
	 * @param request
	 * @param _page
	 * @param _pageSize
	 * @param cname
	 * @return
	 */
	Map<String, Object> queryClassesInfos(HttpServletRequest request, String _page, String _pageSize, String cname, String dname);

	/**
	 * 获取当前班级信息
	 * @param request
	 * @param classid
	 * @return
	 */
	Map<String, Object> getClassesInfo(HttpServletRequest request, String classid);

	/**
	 * 更新当前班级信息
	 * @param request
	 * @param classid
	 * @param cname
	 * @param mdname
	 * @return
	 */
	Map<String, Object> editClassesInfo(HttpServletRequest request, String classid, String cname, String dname);

	/**
	 * 删除当前班级：注意教师，学生信息都被删除
	 * @param request
	 * @param classid
	 * @return
	 */
	Map<String, Object> deleteClass(HttpServletRequest request, String classid);

	/**
	 * 添加班级
	 * @param request
	 * @param cname
	 * @param dname
	 * @return
	 */
	Map<String, Object> addClasses(HttpServletRequest request, String cname, String dname);

	/**
	 * 根据部门名称，查询所有班级名称
	 * @param request
	 * @param dname
	 * @return
	 */
	List<Map<String, Object>> queryAllClassesByDname(HttpServletRequest request, String dname);

}
