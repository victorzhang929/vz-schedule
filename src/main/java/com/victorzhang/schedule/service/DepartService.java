package com.victorzhang.schedule.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.victorzhang.schedule.pojo.Depart;

public interface DepartService {

	/**
	 * 查看所有部门信息记录(系统管理员权限)
	 * @param request
	 * @param dname 
	 * @param _pageSize 
	 * @param _page 
	 * @return
	 */
	Map<String, Object> queryDepartInfos(HttpServletRequest request, String _page, String _pageSize, String dname);

	/**
	 * 获取当前部门下所有班级(系统管理员权限)
	 * @param request
	 * @param departid
	 * @return
	 */
	Map<String, Object> queryClassesByDepartid(HttpServletRequest request, String departid);

	/**
	 * 获取当前学院信息(系统管理员权限)
	 * @param request
	 * @param departid
	 * @return
	 */
	Depart getDepartInfo(HttpServletRequest request, String departid);

	/**
	 * 更新当前学院信息(系统管理员权限)
	 * @param request
	 * @param departid
	 * @param dname
	 * @param dphone
	 * @param address
	 * @param connperson
	 * @param connphone
	 * @return
	 */
	Map<String,Object> editDepartInfo(HttpServletRequest request, String departid, String dname, String dphone, String address, String connperson, String connphone);

	/**
	 * 删除当前学院，联表删除包括所有下属班级和学生，教师(系统管理员权限)
	 * @param request
	 * @param departid
	 * @return
	 */
	Map<String, Object> deleteDepart(HttpServletRequest request, String departid);

	/**
	 * 添加部门(系统管理员权限)
	 * @param request
	 * @param dname
	 * @param dphone
	 * @param address
	 * @param connperson
	 * @param connphone
	 * @return
	 */
	Map<String, Object> addDepart(HttpServletRequest request, String dname, String dphone, String address, String connperson, String connphone);
	
	/**
	 * 根据学院名称获取学院id
	 * @param dname
	 * @return
	 */
	String getDepartidByDname(String dname);

}
