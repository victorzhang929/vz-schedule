package com.victorzhang.schedule.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.victorzhang.schedule.service.LogService;

/**
 * logController
 * @author 40808
 *
 */

@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	@Qualifier("logService")
	private LogService logService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/querylogpage.do")
	@ResponseBody
	public Map<String,Object> querylogpage(String _page,String _pageSize,String loglx,String dname,String stadate,String enddate,String roleType){
		return logService.querylogpage(request,_page,_pageSize,loglx,dname,stadate,enddate,roleType);
	}
	
	/**
	 * 查看当前用户所有日志类型
	 * @param roleType日志类型：系统，部门，用户
	 * @return
	 */
	@RequestMapping("/queryAllLogLx.do")
	@ResponseBody
	public List<Map<String,Object>> queryAllLogLx(String roleType){
		return logService.queryAllLogLx(request,roleType);
	}
	
	/**
	 * 系统日志导航
	 * @return
	 */
	@RequestMapping("/syslogUI.do")
	public String syslogUI(){
		return "jsp/syslog";
	}
	
	/**
	 * 部门日志导航
	 * @return
	 */
	@RequestMapping("/departlogUI.do")
	public String departlogUI(){
		return "jsp/departlog";
	}
}
