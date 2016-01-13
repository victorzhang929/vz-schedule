package com.victorzhang.schedule.controller;

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
	public Map<String,Object> querylogpage(String _page,String _pageSize,String loglx,String stadate,String enddate,String isuserslog){
		return logService.querylogpage(request,_page,_pageSize,loglx,stadate,enddate,isuserslog);
	}
	
}
