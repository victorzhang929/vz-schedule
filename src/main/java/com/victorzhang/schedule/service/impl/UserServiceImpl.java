package com.victorzhang.schedule.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorzhang.schedule.mapper.UserMapper;
import com.victorzhang.schedule.service.UserService;
import com.victorzhang.schedule.util.CommonUtils;
import com.victorzhang.schedule.util.MD5Utils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper;
	
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
		if(!pmobile.matcher(usermobile).matches()){
			result.put("msg", "请输入正确的手机号");
			return result;
		}
		if(!pidcard.matcher(useridcard).matches()){
			result.put("msg", "请输入正确的身份证号");
			return result;
		}
		if(!pemail.matcher(usermail).matches()){
			result.put("msg", "请输入正确的邮箱地址");
			return result;
		}
		
		//验证通过
		Map<String,Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("username", username);
		param.put("realname", realname);
		param.put("usermobile", usermobile);
		param.put("useridcard", useridcard);
		param.put("usermail", usermail);
		
		userMapper.doSaveUserInfo(param);
		result.put("msg", "保存成功");
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
	public Map<String, Object> doUpdateStuTeaInfo(String userid, String dname, String cname, String realname) {
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
		param.put("userid", userid);
		param.put("dname", dname);
		param.put("cname", cname);
		param.put("realname", realname);
		
		userMapper.doUpdateStuTeaInfo(param);
		result.put("msg", "保存成功");
		return result;
	}

	@Override
	public Map<String, Object> deleteUserInfo(String userid) {
		Map<String,Object> result = new HashMap<>();
		//后台验证
		if(StringUtils.isEmpty(userid)){
			result.put("msg", "请选择一名用户");
			return result;
		}
		userMapper.deleteUserInfo(userid);
		result.put("msg", "删除成功");
		return result;
	}

	@Override
	public Map<String, Object> addUserInfo(HttpServletRequest request,String roleid, String dname, String cname, String username, String realname, String usermobile,
			String usermail) {
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
		return result;
	}
	
}
