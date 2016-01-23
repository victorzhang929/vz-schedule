package com.victorzhang.schedule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorzhang.schedule.mapper.MessageMapper;
import com.victorzhang.schedule.service.LogService;
import com.victorzhang.schedule.service.MessageService;
import com.victorzhang.schedule.service.UserService;
import com.victorzhang.schedule.util.CommonUtils;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	@Qualifier("messageMapper")
	private MessageMapper messageMapper;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	
	@Override
	public Map<String, Object> doPublishMsg(HttpServletRequest request,String ms) {
		Map<String,Object> result = new HashMap<>();
		if(StringUtils.isEmpty(ms)&&StringUtils.length(ms)>2000){//消息内容不为空，且消息长度不大于2000
			//发布消息人权限为（系统管理员和部门管理员）
			result.put("msg","请重新输入发布信息");
			return result;
		}
		
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "1")||StringUtils.equals(roleid, "2")){
			
			String departid =  CommonUtils.sesAttr(request, "departid");
			List<Map<String,Object>> userids = userService.queryUseridsByDid(departid);
			if(userids.size()>0&&userids!=null){
				Map<String,Object> param = new HashMap<>();
				param.put("ms", ms);
				param.put("seuserid", CommonUtils.sesAttr(request, "userid"));
				param.put("seuserdate", CommonUtils.getDateTime());
				param.put("seuserip", CommonUtils.getIpAddr());
				for(int i=0; i<userids.size(); i++){
					String msid = CommonUtils.newUuid();
					param.put("msid", msid);
					param.put("reuserid", userids.get(i).get("userid"));
					messageMapper.addPulishMsg(param);
					param.remove("msid");
					param.remove("reuserid");
				}
				logService.addLog("发布信息",ms+"信息发布成功");//加入日志
				result.put("msg","发布成功");
				return result;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> getNewestMsg(HttpServletRequest request) {
		String reuserid = CommonUtils.sesAttr(request, "userid");
		return messageMapper.getNewestMsg(reuserid);
	}

	@Override
	public Map<String, Object> getUnreadMsgInfo(HttpServletRequest request) {
		Map<String,Object> result = new HashMap<>();
		String reuserid = CommonUtils.sesAttr(request, "userid");
		result.put("unreadNum", messageMapper.getUnreadMsgNum(reuserid));
		result.put("data", CommonUtils.dataNull(messageMapper.getUnreadMsg(reuserid)));
		return result;
	}

	@Override
	public void doReadMsg( String msid) {
		if(StringUtils.isNotEmpty(msid)){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("msid", msid);
			String readdate = CommonUtils.getDateTime();
			param.put("readdate", readdate);
			String readip = CommonUtils.getIpAddr();
			param.put("readip", readip);
			messageMapper.doReadMsg(param);
		}
	}

	@Override
	public Map<String, Object> querymsgpage(HttpServletRequest request, String _page, String _pageSize, String isread, String stadate, String enddate) {
		int page = CommonUtils.paraPage(_page);
		int pageSize = CommonUtils.paraPageSize(_pageSize);
		
		Map<String,Object> param = new HashMap<>();
		
		if(StringUtils.isNotEmpty(isread)){
			param.put("isread", isread);
		}
		if(StringUtils.isNotEmpty(stadate)){
			stadate += " 00:00:00";
			param.put("stadate", stadate);
		}
		if(StringUtils.isNotEmpty(enddate)){
			enddate += " 23:59:59";
			param.put("enddate", enddate);
		}
		
		String roleid = CommonUtils.sesAttr(request, "roleid");
		if(StringUtils.equals(roleid, "2")){//部门消息，部门管理员权限
			String departid = CommonUtils.sesAttr(request, "departid");
			param.put("departid", departid);
		}else if(StringUtils.equals(roleid, "3")||StringUtils.equals(roleid, "4")){//用户日志
			String seuserid = CommonUtils.sesAttr(request, "userid");
			param.put("reuserid", seuserid);
		}
		
		//查询总数目
		int count = messageMapper.querymsgCount(param);
		Map<String,Object> result = new HashMap<>();
		result = CommonUtils.para4Page(result, page, pageSize, count);
		if(count > 0){
			//sql分页参数
			param.put("begin", result.get("begin"));
			param.put("pageSize", pageSize);
			result.put("data", CommonUtils.dataNull(messageMapper.querymsgpage(param)));
		}else{
			result.put("data", "");
		}
		return result;
	}

}
