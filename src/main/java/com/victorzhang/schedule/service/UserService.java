package com.victorzhang.schedule.service;

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

}
