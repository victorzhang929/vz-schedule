package com.victorzhang.schedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

	/**
	 * 修改密码，返回回馈信息
	 * @param oldpassword
	 * @param password
	 * @param password2
	 * @return
	 */
	Map<String, Object> changePassword(HttpServletRequest request, String oldpassword, String password, String password2);

	/**
	 * 获取当前用户信息
	 * @return
	 */
	Map<String,Object> getUserInfo(HttpServletRequest request);

	/**
	 * 保存当前用户信息
	 * @param request
	 * @param username
	 * @param realname
	 * @param usermobile
	 * @param useridcard
	 * @param usermail
	 * @return
	 */
	Map<String, Object> doSaveUserInfo(HttpServletRequest request, String username, String realname, String usermobile, String useridcard, String usermail);

	/**
	 * 学生，教师信息表
	 * @param _page
	 * @param _pageSize
	 * @param dname
	 * @param cname
	 * @return
	 */
	Map<String, Object> getUserInfos(String _page, String _pageSize, String dname, String cname,String roleid);

	/**
	 * 根据userid获取学生，教师信息
	 * @param userid
	 * @return
	 */
	Map<String, Object> getUserInfoByUserid(String userid);

	/**
	 * 保存学生，教师信息
	 * @param userid
	 * @param dname
	 * @param cname
	 * @param realname
	 * @return
	 */
	Map<String, Object> doUpdateStuTeaInfo(HttpServletRequest request,String userid, String dname, String cname, String realname);

	/**
	 * 根据userid删除该用户
	 * @param userid
	 * @return
	 */
	Map<String, Object> deleteUserInfo(HttpServletRequest request,String userid);

	/**
	 * 添加用户
	 * @param request
	 * @param dname
	 * @param cname
	 * @param username
	 * @param realname
	 * @param usermobile
	 * @param usermail
	 * @return
	 */
	Map<String, Object> addUserInfo(HttpServletRequest request,String roleid, String dname, String cname, String username, String realname, String usermobile, String usermail);

	List<Map<String, Object>> queryUseridsByDid(String departid);

}
