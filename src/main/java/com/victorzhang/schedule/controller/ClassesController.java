package com.victorzhang.schedule.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.victorzhang.schedule.service.ClassesService;

/**
 * 班级控制器（系统管理员和学院管理人权限）
 * @author 40808
 *
 */
@Controller
@RequestMapping("/classes")
public class ClassesController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	@Qualifier("classesService")
	private ClassesService classesService;
	
	/**
	 * 班级信息导航
	 * @return
	 */
	@RequestMapping("/classesInfoUI.do")
	public String classesInfoUI(){
		return "jsp/classesInfo";
	}
	
	/**
	 * 班级信息记录
	 * @return
	 */
	@RequestMapping("/queryClassesInfos.do")
	@ResponseBody
	public Map<String,Object> queryClassesInfos(String _page,String _pageSize,String cname,String dname){
		return classesService.queryClassesInfos(request,_page,_pageSize,cname,dname);
	}
	
	/**
	 * 获取当前班级信息
	 * @param classid
	 * @return
	 */
	@RequestMapping("/getClassesInfo.do")
	@ResponseBody
	public Map<String, Object> getClassesInfo(String classid){
		return classesService.getClassesInfo(request,classid);
	}
	
	/**
	 * 更新当前班级信息：注意用户信息中departid的更新
	 * @param classid
	 * @param cname
	 * @param mdname
	 * @return
	 */
	@RequestMapping("/editClassesInfo.do")
	@ResponseBody
	public Map<String,Object> editClassesInfo(String classid,String cname,String dname){
		return classesService.editClassesInfo(request,classid,cname,dname);
	}
	
	/**
	 * 删除当前班级：注意教师，学生信息都被删除
	 * @param classid
	 * @return
	 */
	@RequestMapping("/deleteClass.do")
	@ResponseBody
	public Map<String,Object> deleteClass(String classid){
		return classesService.deleteClass(request,classid);
	}
	
	/**
	 * 添加班级
	 * @param cname
	 * @param dname
	 * @return
	 */
	@RequestMapping("/addClasses.do")
	@ResponseBody
	public Map<String,Object> addClasses(String cname,String dname){
		return classesService.addClasses(request,cname,dname);
	}
	
	/**
	 * 根据部门名称，查询所有班级名称
	 * @return
	 */
	@RequestMapping("/queryAllClassesByDname.do")
	@ResponseBody
	public List<Map<String,Object>> queryAllClassesByDname(String dname){
		return classesService.queryAllClassesByDname(request,dname);
	}
}
