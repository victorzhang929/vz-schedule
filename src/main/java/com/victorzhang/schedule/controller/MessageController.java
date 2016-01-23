package com.victorzhang.schedule.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.victorzhang.schedule.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;
	
	/**
	 * 发布公告消息导航
	 * @return
	 */
	@RequestMapping("/publishNBUI.do")
	public String publishNBUI(){
		return "jsp/publishNB";
	}
	
	/**
	 * 公告栏导航
	 * @return
	 */
	@RequestMapping("/noticeBoardUI.do")
	public String noticeBoardUI(){
		return "jsp/noticeBoard";
	}
	
	/**
	 * 发布消息
	 * @param ms消息内容
	 * @return
	 */
	@RequestMapping("/doPublishMsg.do")
	@ResponseBody
	public Map<String,Object> doPublishMsg(String ms){
		return messageService.doPublishMsg(request,ms);
	}
	
	/**
	 * 获取最新消息
	 * @return
	 */
	@RequestMapping("/getNewestMsg.do")
	@ResponseBody
	public Map<String,Object> getNewestMsg(){
		return messageService.getNewestMsg(request);
	}
	
	/**
	 * 获取所有未读最新消息
	 * @return
	 */
	@RequestMapping("/getUnreadMsg.do")
	@ResponseBody
	public Map<String, Object> getUnreadMsgInfo(){
		return messageService.getUnreadMsgInfo(request);
	}
	
	/**
	 * 阅读所有未读最新消息
	 * @return
	 */
	@RequestMapping("/doReadMsg.do")
	@ResponseBody
	public void doReadMsg(String msid){
		messageService.doReadMsg(msid);
	}
	
	/**
	 * 所有消息界面
	 * @return
	 */
	@RequestMapping("/getAllMsg.do")
	public String getAllUnreadMsg(){
		return "jsp/allMsg";
	}
	
	/**
	 * 消息页面查询
	 * @param _page
	 * @param _pageSize
	 * @param isread
	 * @param stadate
	 * @param enddate
	 * @return
	 */
	@RequestMapping("/querymsgpage.do")
	@ResponseBody
	public Map<String,Object> querymsgpage(String _page,String _pageSize,String isread,String stadate,String enddate){
		return messageService.querymsgpage(request,_page,_pageSize,isread,stadate,enddate);
	}
}
