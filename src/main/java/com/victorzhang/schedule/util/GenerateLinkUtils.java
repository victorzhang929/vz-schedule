package com.victorzhang.schedule.util;

import com.victorzhang.schedule.pojo.User;

/**
 * 生成重新设置密码的链接
 * 章伟
 * @author 40808
 *
 */
public class GenerateLinkUtils {
	
	private static final String CHECK_CODE="checkcode";
	
	/**
	 * 生成重设密码的链接
	 * @param user
	 * @return
	 */
	public static String generateResetPwdLink(User user){
		return "http://localhost:8080/Schedule/index/resetPassword.index?username="
				+ user.getUsername()+"&"+ CHECK_CODE +"="+generateCheckcode(user);
	}
	
	public static String generateCheckcode(User user){
		String username = user.getUsername();
		String randomCode = user.getRandomcode();
		return new MD5Utils().getMD5ofStr(username+":"+randomCode);
	}
	
	
}
