package com.victorzhang.schedule.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MessageService {

	Map<String, Object> doPublishMsg(HttpServletRequest request,String ms);
	
	Map<String, Object> getNewestMsg(HttpServletRequest request);

	Map<String, Object> getUnreadMsgInfo(HttpServletRequest request);

	void doReadMsg(String msid);

	Map<String, Object> querymsgpage(HttpServletRequest request, String _page, String _pageSize, String isread, String stadate, String enddate);

}
