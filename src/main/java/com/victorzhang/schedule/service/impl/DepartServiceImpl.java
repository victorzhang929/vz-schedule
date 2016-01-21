package com.victorzhang.schedule.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorzhang.schedule.mapper.DepartMapper;
import com.victorzhang.schedule.pojo.Depart;
import com.victorzhang.schedule.service.DepartService;
import com.victorzhang.schedule.util.CommonUtils;

@Service("departService")
public class DepartServiceImpl implements DepartService {

	@Autowired
	@Qualifier("departMapper")
	private DepartMapper departMapper;

	@Override
	public Map<String, Object> queryDepartInfos(HttpServletRequest request, String _page, String _pageSize, String dname) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			int page = CommonUtils.paraPage(_page);
			int pageSize = CommonUtils.paraPageSize(_pageSize);
			
			Map<String,Object> param = new HashMap<>();
			if(StringUtils.isNotEmpty(dname)){
				param.put("dname", dname);
			}
			
			int count = departMapper.queryCount(param);
			
			Map<String,Object> result = new HashMap<>();
			result = CommonUtils.para4Page(result, page, pageSize, count);
			if(count > 0){
				//sql分页参数
				param.put("begin", result.get("begin"));
				param.put("pageSize", pageSize);
				result.put("data", CommonUtils.dataNull(departMapper.queryDepartInfos(param)));
			}else{
				result.put("data", "");
			}
			
			return result;
		}
		return null;
	}

	@Override
	public Map<String, Object> queryClassesByDepartid(HttpServletRequest request, String departid) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			Map<String,Object> result = new HashMap<>();
			result.put("data", CommonUtils.dataNull(departMapper.queryClassesByDepartid(departid)));
			return result;
		}
		return null;
	}

	@Override
	public Depart getDepartInfo(HttpServletRequest request, String departid) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			return departMapper.getDepartInfo(departid);
		}
		return null;
	}

	@Override
	public Map<String,Object> editDepartInfo(HttpServletRequest request, String departid, String dname, String dphone, String address, String connperson, String connphone) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		Pattern pphone = Pattern.compile("^([0-9]{3,4}-)?[0-9]{7,8}$"); 
		Pattern pmobile = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
		if(StringUtils.equals(roleid, "1")){
			Map<String,Object> result = new HashMap<>();
			//后台验证
			if(StringUtils.isEmpty(departid)){
				result.put("msg", "请选择部门");
				return result;
			}
			if(StringUtils.isEmpty(dname)){
				result.put("msg", "学院名称不能为空");
				return result;
			}
			if(StringUtils.isNotEmpty(dphone)&&!pphone.matcher(dphone).matches()){
				result.put("msg", "请输入正确的电话号码");
				return result;
			}
			if(StringUtils.isNotEmpty(connphone)&&!pmobile.matcher(connphone).matches()){
				result.put("msg", "请输入正确的手机号码");
				return result;
			}
			
			//验证通过
			Map<String,Object> param = new HashMap<>();
			param.put("departid", departid);
			param.put("dname", dname);
			param.put("dphone", dphone);
			param.put("address", address);
			param.put("connperson", connperson);
			param.put("connphone", connphone);
			departMapper.doUpdateDepart(param);
			result.put("msg", "保存成功");
			return result;
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteDepart(HttpServletRequest request, String departid) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			Map<String,Object> result = new HashMap<>();
			//后台验证
			if(StringUtils.isEmpty(departid)){
				result.put("msg", "请选择学院");
				return result;
			}
			departMapper.deleteDepart(departid);
			result.put("msg", "删除成功");
			return result;
		}
		return null;
	}

	@Override
	public Map<String, Object> addDepart(HttpServletRequest request, String dname, String dphone, String address, String connperson, String connphone) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		Pattern pphone = Pattern.compile("^([0-9]{3,4}-)?[0-9]{7,8}$"); 
		Pattern pmobile = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
		if(StringUtils.equals(roleid, "1")){
			Map<String,Object> result = new HashMap<>();
			//后台验证
			if(StringUtils.isEmpty(dname)){
				result.put("msg", "学院名称不能为空");
				return result;
			}
			if(StringUtils.isNotEmpty(dphone)&&!pphone.matcher(dphone).matches()){
				result.put("msg", "请输入正确的电话号码");
				return result;
			}
			if(StringUtils.isNotEmpty(connphone)&&!pmobile.matcher(connphone).matches()){
				result.put("msg", "请输入正确的手机号码");
				return result;
			}
			String departid = CommonUtils.newUuid();
			String ddate = CommonUtils.getDateTime();
			//验证通过
			Map<String,Object> param = new HashMap<>();
			param.put("departid", departid);
			param.put("dname", dname);
			param.put("dphone", dphone);
			param.put("address", address);
			param.put("connperson", connperson);
			param.put("connphone", connphone);
			param.put("ddate", ddate);
			departMapper.addDepart(param);
			result.put("msg", "添加成功");
			return result;
		}
		return null;
	}

	
}
