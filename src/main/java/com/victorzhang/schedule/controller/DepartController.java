package com.victorzhang.schedule.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.victorzhang.schedule.pojo.Depart;
import com.victorzhang.schedule.service.DepartService;

/**
 * 部门控制器
 * @author 40808
 *
 */

@Controller
@RequestMapping("/depart")
public class DepartController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	@Qualifier("departService")
	private DepartService departService;
	
	/**
	 * 学院信息导航
	 * @return
	 */
	@RequestMapping("/departInfoUI.do")
	public String userInfoUI(){
		return "jsp/departInfo";
	}
	
	/**
	 * 学院信息记录(系统管理员权限)
	 * @return
	 */
	@RequestMapping("/queryDepartInfos.do")
	@ResponseBody
	public Map<String,Object> queryDepartInfos(String _page,String _pageSize,String dname){
		return departService.queryDepartInfos(request,_page,_pageSize,dname);
	}
	
	/**
	 * 获取当前学院下所有班级(系统管理员权限)
	 * @param dname
	 * @return
	 */
	@RequestMapping("/queryClassesByDepartid.do")
	@ResponseBody
	public Map<String,Object> queryClassesByDepartid(String departid){
		return departService.queryClassesByDepartid(request,departid);
	}
	
	/**
	 * 获取当前学院信息(系统管理员权限)
	 * @param departid
	 * @return
	 */
	@RequestMapping("/getDepartInfo.do")
	@ResponseBody
	public Depart getDepartInfo(String departid){
		return departService.getDepartInfo(request,departid);
	}
	
	/**
	 * 更新当前学院信息(系统管理员权限)
	 * @param departid
	 * @param dname
	 * @param dphone
	 * @param address
	 * @param connperson
	 * @param connphone
	 * @return
	 */
	@RequestMapping("/editDepartInfo.do")
	@ResponseBody
	public Map<String,Object> editDepartInfo(String departid,String dname,String dphone,
			String address,String connperson,String connphone){
		return departService.editDepartInfo(request,departid,dname,dphone,address,connperson,connphone);
	}
	
	/**
	 * 删除当前学院，联表删除包括所有下属班级和学生，教师(系统管理员权限)
	 * @param departid
	 * @return
	 */
	@RequestMapping("/deleteDepart.do")
	@ResponseBody
	public Map<String,Object> deleteDepart(String departid){
		return departService.deleteDepart(request,departid);
	}
	
	/**
	 * 添加部门(系统管理员权限)
	 * @param dname
	 * @param dphone
	 * @param address
	 * @param connperson
	 * @param connphone
	 * @return
	 */
	@RequestMapping("/addDepart.do")
	@ResponseBody
	public Map<String,Object> addDepart(String dname,String dphone,
			String address,String connperson,String connphone){
		return departService.addDepart(request,dname,dphone,address,connperson,connphone);
	}
}
