package com.victorzhang.schedule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorzhang.schedule.mapper.UserMapper;
import com.victorzhang.schedule.service.LogService;
import com.victorzhang.schedule.service.UserService;
import com.victorzhang.schedule.util.CommonUtils;
import com.victorzhang.schedule.util.MD5Utils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper;
	
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	
	@Override
	public Map<String, Object> changePassword(HttpServletRequest request,String oldpassword, String password, String password2) {
		Map<String, Object> result = new HashMap<>();
		
		if(StringUtils.isEmpty(oldpassword)){
			result.put("msg", "原密码不能为空");
		}else if(StringUtils.isEmpty(password)){
			result.put("msg", "新密码不能为空");
		}else if(StringUtils.isEmpty(password2)){
			result.put("msg", "重复密码不能为空");
		}else if(!StringUtils.equals(password, password2)){
			result.put("msg", "两次密码不一致");
		}else {
			result.put("msg", doValChaPassword(request,oldpassword,password));
		}
		logService.addLog("更新", "更新密码："+result.get("msg").toString());//加入日志
		return result;
	}
	
	/**
	 * 验证和修改密码
	 * @param oldpassword
	 * @param password
	 * @param password2
	 * @return
	 */
	private String doValChaPassword(HttpServletRequest request,String oldpassword,String password){
		Map<String,Object> param = new HashMap<>();
		String msg ;
		String userid = CommonUtils.sesAttr(request, "userid");
		param.put("userid", userid);
		param.put("oldpassword",  new MD5Utils().getMD5ofStr(oldpassword));
		if(!userMapper.queryExistPassword(param)){
			msg = "原密码输入不正确";
		}else{
			param.remove("oldpassword");
			param.put("password", new MD5Utils().getMD5ofStr(password));
			userMapper.doChangePassword(param);
			msg = "修改密码成功";
		}
		return msg;
	}

	@Override
	public Map<String,Object> getUserInfo(HttpServletRequest request) {
		String userid = CommonUtils.sesAttr(request, "userid");
		return userMapper.getUserInfo(userid);
	}

	@Override
	public Map<String, Object> doSaveUserInfo(HttpServletRequest request, String username, String realname, String usermobile, String useridcard, String usermail) {
		String userid = CommonUtils.sesAttr(request, "userid");
		Pattern pmobile = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
		Pattern pidcard = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])"); 
		Pattern pemail= Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"); 
		Map<String,Object> result = new HashMap<>();
		//后台验证
		if(StringUtils.isEmpty(username)){
			result.put("msg", "用户名不能为空");
			return result;
		}
		if(StringUtils.isEmpty(realname)){
			result.put("msg", "真实姓名不能为空");
			return result;
			
		}
		if(StringUtils.isNotEmpty(usermobile)&&!pmobile.matcher(usermobile).matches()){
			result.put("msg", "请输入正确的手机号");
			return result;
		}
		if(StringUtils.isNotEmpty(useridcard)&&!pidcard.matcher(useridcard).matches()){
			result.put("msg", "请输入正确的身份证号");
			return result;
		}
		if(StringUtils.isNotEmpty(usermail)&&!pemail.matcher(usermail).matches()){
			result.put("msg", "请输入正确的邮箱地址");
			return result;
		}
		
		//验证通过
		String updatedate = CommonUtils.getDateTime();
		String updateip = CommonUtils.getIpAddr();
		Map<String,Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("username", username);
		param.put("realname", realname);
		param.put("usermobile", usermobile);
		param.put("useridcard", useridcard);
		param.put("usermail", usermail);
		param.put("updateuserid", userid);
		param.put("updatedate", updatedate);
		param.put("updateip", updateip);
		
		userMapper.doSaveUserInfo(param);
		result.put("msg", "保存成功");
		logService.addLog("更新","更新用户信息，"+realname+"信息更新成功");//加入日志
		return result;
	}

	@Override
	public Map<String, Object> getUserInfos(String _page, String _pageSize, String dname, String cname,String roleid) {
		int page = CommonUtils.paraPage(_page);
		int pageSize = CommonUtils.paraPageSize(_pageSize);
		
		Map<String,Object> param = new HashMap<>();
		if(StringUtils.isNotEmpty(dname)&&!StringUtils.equals(dname, "所属学院")){
			param.put("dname", dname);
		}
		if(StringUtils.isNotEmpty(cname)&&!StringUtils.equals(cname, "所属班级")){
			param.put("cname", cname);
		}
		if(StringUtils.isNotEmpty(roleid)){
			param.put("roleid", roleid);
		}
		
		int count = userMapper.queryUserCount(param);
		
		Map<String,Object> result = new HashMap<>();
		result = CommonUtils.para4Page(result, page, pageSize, count);
		if(count > 0){
			//sql分页参数
			param.put("begin", result.get("begin"));
			param.put("pageSize", pageSize);
			result.put("data", CommonUtils.dataNull(userMapper.queryUserInfos(param)));
		}else{
			result.put("data", "");
		}
		return result;
	}

	@Override
	public Map<String, Object> getUserInfoByUserid(String userid) {
		return userMapper.getUserInfoByUserid(userid);
	}

	@Override
	public Map<String, Object> doUpdateStuTeaInfo(HttpServletRequest request,String userid, String dname, String cname, String realname) {
		//用户信息系统管理员和班级管理员权限
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")||StringUtils.equals(roleid, "2")){
			Map<String,Object> result = new HashMap<>();
			//后台验证
			if(StringUtils.isEmpty(userid)){
				result.put("msg", "请选择一名用户");
				return result;
			}
			if(StringUtils.equals(dname, "所属学院")){
				result.put("msg", "请选择学院");
				return result;
			}
			if(StringUtils.equals(cname, "所属班级")){
				result.put("msg", "请选择班级");
				return result;
			}
			if(StringUtils.isEmpty(realname)){
				result.put("msg", "真实姓名不能为空");
				return result;
			}
			
			//验证通过
			Map<String,Object> param = new HashMap<>();
			param.put("udateuserid", CommonUtils.sesAttr(request, "userid"));
			param.put("updatedate", CommonUtils.getDateTime());
			param.put("updateip", CommonUtils.getIpAddr());
			param.put("userid", userid);
			param.put("dname", dname);
			param.put("cname", cname);
			param.put("realname", realname);
			
			userMapper.doUpdateStuTeaInfo(param);
			result.put("msg", "保存成功");
			if(StringUtils.isNotEmpty(cname)){//教师从属学院，区分教师和学生
				logService.addLog("更新", "更新学生信息，学院："+dname+",班级："+cname+",真实姓名"+realname+",最后更新时间:"+CommonUtils.getDateTime());
			}else{
				logService.addLog("更新", "更新教师信息，学院："+dname+",真实姓名"+realname+",最后更新时间:"+CommonUtils.getDateTime());
			}
			return result;
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteUserInfo(HttpServletRequest request,String userid) {
		//用户信息系统管理员和班级管理员权限
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")||StringUtils.equals(roleid, "2")){
			Map<String,Object> result = new HashMap<>();
			//后台验证
			if(StringUtils.isEmpty(userid)){
				result.put("msg", "请选择一名用户");
				return result;
			}
			userMapper.deleteUserInfo(userid);
			result.put("msg", "删除成功");
			logService.addLog("删除", "删除用户信息，用户编号为："+userid+"被删除,删除时间:"+CommonUtils.getDateTime());
			return result;
		}
		return null;
	}

	@Override
	public Map<String, Object> addUserInfo(HttpServletRequest request,String roleid, String dname, String cname, String username, String realname, String usermobile,
			String usermail) {
		//用户信息系统管理员和班级管理员权限
		String userroleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(userroleid, "1")||StringUtils.equals(userroleid, "2")){
			//获取创建人信息
			String inputuserid = CommonUtils.sesAttr(request, "userid");
			String inputdate = CommonUtils.getDateTime();
			String inputip = CommonUtils.getIpAddr();
			//后台验证
			Pattern pmobile = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
			Pattern pemail= Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"); 
			Map<String,Object> result = new HashMap<>();
			if(StringUtils.equals(dname, "所属学院")){
				result.put("msg", "请选择所属学院");
				return result;
			}
			if(StringUtils.equals(cname, "所属班级")){
				result.put("msg", "请选择所属班级");
				return result;
			}
			if(StringUtils.isEmpty(username)){
				result.put("msg", "用户名不能为空");
				return result;
			}
			if(StringUtils.isEmpty(realname)){
				result.put("msg", "真实姓名不能为空");
				return result;
				
			}
			if(StringUtils.isNotEmpty(usermobile)&&!pmobile.matcher(usermobile).matches()){
				result.put("msg", "请输入正确的手机号");
				return result;
			}
			if(StringUtils.isNotEmpty(usermail)&&!pemail.matcher(usermail).matches()){
				result.put("msg", "请输入正确的邮箱地址");
				return result;
			}
			String userid = CommonUtils.newUuid();
			//默认密码设置111111
			String password = new MD5Utils().getMD5ofStr("111111");
			//找回密码激活码
			String randomcode = CommonUtils.newUuid();
			//验证通过
			Map<String,Object> param = new HashMap<>();
			param.put("userid", userid);
			param.put("roleid", roleid);
			param.put("dname", dname);
			param.put("cname", cname);
			param.put("username", username);
			param.put("password", password);
			param.put("realname", realname);
			param.put("usermobile", usermobile);
			param.put("usermail", usermail);
			param.put("randomcode", randomcode);
			param.put("inputuserid", inputuserid);
			param.put("inputdate", inputdate);
			param.put("inputip", inputip);
			
			userMapper.addUserInfo(param);
			result.put("msg", "添加成功");
			if(StringUtils.isNotEmpty(cname)){//教师从属学院，区分教师和学生
				logService.addLog("添加", "添加学生信息，学院："+dname+",班级："+cname+",真实姓名"+realname+",添加时间:"+CommonUtils.getDateTime());
			}else{
				logService.addLog("添加", "添加教师信息，学院："+dname+",真实姓名"+realname+",添加时间:"+CommonUtils.getDateTime());
			}
			return result;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryUseridsByDid(String departid) {
		List<Map<String,Object>> result = userMapper.queryUseridsByDid(departid);
		return result;
	}
	
}
