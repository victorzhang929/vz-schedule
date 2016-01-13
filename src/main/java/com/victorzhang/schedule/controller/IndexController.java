package com.victorzhang.schedule.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.victorzhang.schedule.pojo.User;
import com.victorzhang.schedule.service.IndexService;

/**
 * index controller
 * @author victorzhang
 *
 */

@Controller
@RequestMapping("index")
public class IndexController {
	
	@Autowired
	@Qualifier("indexService")
	private IndexService indexService;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login.index")
	@ResponseBody
	public User login(String username,String password) {
		return indexService.doLogin(username,password,request);
	}
	
	/**
	 * 主页面链接
	 * @return
	 */
	@RequestMapping("/mainPage.do")
	public String mainPage(){
		return "jsp/main";
	}
	
	/**
	 * 邮箱：忘记密码，发送邮件
	 * @param email
	 */
	@RequestMapping("/sendMail.index")
	@ResponseBody
	public Map<String,Object> sendMail(String email){
		return indexService.queryUserByEmail(email);
	}
	
	
	/**
	 * 重设密码链接
	 * @return
	 */
	@RequestMapping("/resetPassword.index")
	public String resetPassword(){
		return "jsp/resetPassword";
	}
	
	/**
	 * 重设密码
	 * @param username用户名
	 * @param checkcode验证码
	 * @param findPwd
	 * @param rfindPwd
	 * @return
	 */
	@RequestMapping("/doResetPassword.index")
	@ResponseBody
	public Map<String,Object> doResetPassword(String username,String checkcode,String password,String repassword){
		return indexService.doResetPassword(username,checkcode,password,repassword);
	}
	
	/**
	 * 退出系统
	 */
	@RequestMapping("/exit.do")
	public String exit(){
		indexService.exit(request);
		return "index";
	}
	
	/**
	 * 图形界面快速链接
	 */
	@RequestMapping("/graphicLinkUI.do")
	public String graphicLinkUI(){
		return "jsp/graphicLink";
	}
	
}
