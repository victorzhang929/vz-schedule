package com.victorzhang.schedule.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.victorzhang.schedule.service.UserService;

/**
 * 用户（教师，学生，管理员，系统管理员共同权限）
 * @author 40808
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	/**
	 * 操作日志导航
	 * @return
	 */
	@RequestMapping("/userslogUI.do")
	public String userslogUI(){
		return "jsp/userslog";
	}
	
	/**
	 * 修改当前用户密码
	 * @param oldpassword原密码
	 * @param password新密码
	 * @param password2重复密码
	 * @return
	 */
	@RequestMapping("/changePassword.do")
	@ResponseBody
	public Map<String,Object> changePassword(String oldpassword,String password,String password2){
		return userService.changePassword(request,oldpassword,password,password2);
	}
	
	/**
	 * 用户信息导航提供数据
	 * @return
	 */
	@RequestMapping("/userInfoUI.do")
	public String userInfoUI(ModelMap modelMap){
		Map<String,Object> userInfo = userService.getUserInfo(request);
		modelMap.addAllAttributes(userInfo);
		return "jsp/userInfo";
	}
	
	/**
	 * 保存用户信息
	 * @return
	 */
	@RequestMapping("/saveUserInfo.do")
	@ResponseBody
	public Map<String,Object> saveUserInfo(String username,String realname,String usermobile,String useridcard,String usermail){
		return userService.doSaveUserInfo(request,username,realname,usermobile,useridcard,usermail);
		
	}
	
	/**
	 * 教师页面导航
	 * @return
	 */
	@RequestMapping("/teacherInfoUI.do")
	public String teacherInfoUI(){
		return "jsp/teacherInfo";
		
	}
	
	/**
	 * 学生页面导航
	 * @return
	 */
	@RequestMapping("/studentInfoUI.do")
	public String studentInfoUI(){
		return "jsp/studentInfo";
	}
	
	/**
	 * 获取学生，教师信息表
	 * @param _page
	 * @param _pageSize
	 * @param dname
	 * @param cname
	 * @return
	 */
	@RequestMapping("/getUserInfos.do")
	@ResponseBody
	public Map<String,Object> getUserInfos(String _page,String _pageSize,String dname,String cname,String roleid){
		return userService.getUserInfos(_page,_pageSize,dname,cname,roleid);
	}
	
	/**
	 * 根据userid获取学生，教师信息
	 * @param userid
	 * @return
	 */
	@RequestMapping("/getUserInfoByUserid.do")
	@ResponseBody
	public Map<String,Object> getUserInfoByUserid(String userid){
		return userService.getUserInfoByUserid(userid);
	}
	
	/**
	 * 保存学生，教师信息
	 * @param userid
	 * @param dname
	 * @param cname
	 * @param realname
	 * @return
	 */
	@RequestMapping("/doUpdateStuTeaInfo.do")
	@ResponseBody
	public Map<String,Object> doUpdateStuTeaInfo(String userid,String dname,String cname,String realname){
		return userService.doUpdateStuTeaInfo(request,userid,dname,cname,realname);
	}
	
	/**
	 * 根据userid删除该用户
	 * @param userid
	 * @return
	 */
	@RequestMapping("/deleteUserInfo.do")
	@ResponseBody
	public Map<String,Object> deleteUserInfo(String userid){
		return userService.deleteUserInfo(request,userid);
	}
	
	/**
	 * 添加用户
	 * @param userid
	 * @return
	 */
	@RequestMapping("/addUserInfo.do")
	@ResponseBody
	public Map<String,Object> addUserInfo(String roleid,String dname,String cname,String username,String realname,String usermobile,String usermail){
		return userService.addUserInfo(request,roleid,dname,cname,username,realname,usermobile,usermail);
	}
	
}
