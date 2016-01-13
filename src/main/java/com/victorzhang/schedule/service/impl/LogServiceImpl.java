package com.victorzhang.schedule.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

}
