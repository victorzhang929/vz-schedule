package com.victorzhang.schedule.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorzhang.schedule.mapper.LogMapper;
import com.victorzhang.schedule.service.LogService;
import com.victorzhang.schedule.util.CommonUtils;

/**
 * 数据库日志
 * @author victorzhang
 *
 */
@Service("logService")
public class LogServiceImpl implements LogService {

	@Autowired
	@Qualifier("logMapper")
	private LogMapper logMapper;
	
	@Override
	public void addLog(String lx, String ms) {
		
		Map<String,Object> info = new HashMap<String,Object>();
		//获取ip地址
		String ip = CommonUtils.getIpAddr();
		//获取当前时间
		String dt = CommonUtils.getDateTime();
		//获取id
		String id = CommonUtils.newUuid();
		//获取操作人
		HttpSession session = CommonUtils.getSession(false);
		String userid = (String) session.getAttribute("userid");
		
		//放入集合
		info.put("logid", id);
		info.put("loglx", lx);
		info.put("logms", ms);
		info.put("userid", userid);
		info.put("userdate", dt);
		info.put("userip", ip);
		
		logMapper.addLog(info);
	}

	@Override
	public Map<String, Object> querylogpage(HttpServletRequest request, String _page, String _pageSize, String loglx, String stadate, String enddate, String isuserslog) {
		int page = CommonUtils.paraPage(_page);
		int pageSize = CommonUtils.paraPageSize(_pageSize);
		
		Map<String,Object> param = new HashMap<>();
		
		if(StringUtils.equals(loglx, "操作类型")){
			loglx = null;
		}
		param.put("loglx", loglx);
		
		if(StringUtils.equals(isuserslog, "1")){
			//当前用户日志
			String userid = CommonUtils.sesAttr(request, "userid");
			param.put("userid", userid);
		}else{
			//TODO
			//系统管理员，系统全部日志(按部门查看)
		}
		
		if(StringUtils.isNotEmpty(stadate)){
			stadate += " 00:00:00";
			param.put("stadate", stadate);
		}
		if(StringUtils.isNotEmpty(enddate)){
			enddate += " 23:59:59";
			param.put("enddate", enddate);
		}
		
		//查询总数目
		int count = logMapper.queryCount(param);
		Map<String,Object> result = new HashMap<>();
		result = CommonUtils.para4Page(result, page, pageSize, count);
		if(count > 0){
			//sql分页参数
			param.put("begin", result.get("begin"));
			param.put("pageSize", pageSize);
			result.put("data", CommonUtils.dataNull(logMapper.querylogpage(param)));
		}else{
			result.put("data", "");
		}
		return result;
	}

}
