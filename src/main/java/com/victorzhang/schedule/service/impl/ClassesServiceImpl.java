package com.victorzhang.schedule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorzhang.schedule.mapper.ClassesMapper;
import com.victorzhang.schedule.service.ClassesService;
import com.victorzhang.schedule.util.CommonUtils;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {

	@Autowired
	@Qualifier("classesMapper")
	private ClassesMapper classesMapper;
	
	@Override
	public Map<String, Object> queryClassesInfos(HttpServletRequest request, String _page, String _pageSize, String cname, String dname) {
		//验证系统管理员权限(部门管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			int page = CommonUtils.paraPage(_page);
			int pageSize = CommonUtils.paraPageSize(_pageSize);
			
			Map<String,Object> param = new HashMap<>();
			if(StringUtils.isNotEmpty(cname)){
				param.put("cname", cname);
			}
			if(!StringUtils.equals(dname, "所属学院")){
				param.put("dname", dname);
			}
			
			int count = classesMapper.queryCount(param);
			
			Map<String,Object> result = new HashMap<>();
			result = CommonUtils.para4Page(result, page, pageSize, count);
			if(count > 0){
				//sql分页参数
				param.put("begin", result.get("begin"));
				param.put("pageSize", pageSize);
				result.put("data", CommonUtils.dataNull(classesMapper.queryClassesInfos(param)));
			}else{
				result.put("data", "");
			}
			
			return result;
		}
		return null;
	}

	@Override
	public Map<String, Object> getClassesInfo(HttpServletRequest request, String classid) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			return classesMapper.getClassesInfo(classid);
		}
		return null;
	}

	@Override
	public Map<String, Object> editClassesInfo(HttpServletRequest request, String classid, String cname, String dname) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			Map<String,Object> result = new HashMap<>();
			//后台验证
			if(StringUtils.isEmpty(classid)){
				result.put("msg", "请选择班级");
				return result;
			}
			if(StringUtils.isEmpty(cname)){
				result.put("msg", "班级名称不能为空");
				return result;
			}
			if(StringUtils.isEmpty(dname)){
				result.put("msg", "请选择学院");
				return result;
			}
			String departid = classesMapper.getDepartidByDname(dname);
			//验证通过
			Map<String,Object> param = new HashMap<>();
			param.put("classid", classid);
			param.put("cname", cname);
			param.put("departid", departid);
			doUpdateClassesUsers(param);
			result.put("msg", "保存成功");
			return result;
		}
		return null;
	}

	/**
	 * 更新班级信息和用户信息（事务处理，异常发生回滚）
	 * @param param
	 */
	private void doUpdateClassesUsers(Map<String, Object> param) {
		classesMapper.doUpdateClasses(param);
		classesMapper.doUpdateUsers(param);
	}

	@Override
	public Map<String, Object> deleteClass(HttpServletRequest request, String classid) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			Map<String,Object> result = new HashMap<>();
			//后台验证
			if(StringUtils.isEmpty(classid)){
				result.put("msg", "请选择班级");
				return result;
			}
			classesMapper.deleteClass(classid);
			result.put("msg", "删除成功");
			return result;
		}
		return null;
	}

	@Override
	public Map<String, Object> addClasses(HttpServletRequest request, String cname, String dname) {
		//验证管理员权限(系统管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			Map<String,Object> result = new HashMap<>();
			//后台验证
			if(StringUtils.isEmpty(cname)){
				result.put("msg", "班级名称不能为空");
				return result;
			}
			if(StringUtils.isEmpty(dname)){
				result.put("msg", "请选择学院");
				return result;
			}
			String classid = CommonUtils.newUuid();
			//验证通过
			Map<String,Object> param = new HashMap<>();
			param.put("classid", classid);
			param.put("cname", cname);
			param.put("dname", dname);
			classesMapper.addClasses(param);
			result.put("msg", "添加成功");
			return result;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryAllClassesByDname(HttpServletRequest request, String dname) {
		//验证系统管理员权限(部门管理员roleid=1)
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")){
			if(StringUtils.isNoneEmpty(dname)){
				return classesMapper.queryAllClassesByDname(dname);
			}
		}
		return null;
	}

}
