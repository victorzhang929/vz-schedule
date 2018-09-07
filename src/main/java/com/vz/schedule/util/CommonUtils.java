package com.vz.schedule.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 公共工具类
 * @author victorzhang
 *
 */
public class CommonUtils {
	
	/**
	 * 获取request对象
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * 获取Session对象
	 * @param flag
	 * @return
	 */
	public static HttpSession getSession(boolean flag) {
		return getRequest().getSession(flag);
	}
	
	/**
	 * 验证session中内容，如果存在session则显示此id，如果不存在则返回null
	 * @param request
	 * @param userid
	 */
	public static String sesAttr(HttpServletRequest request, String userid){
		if(request.getSession().getAttribute("userid")!=null){
			return request.getSession().getAttribute(userid).toString();
		}
		return null;
	}

	/**
	 * 获取当前ip地址
	 * @return
	 */
	public static String getIpAddr() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		
		return ip; 
	}

	/**
	 * 获取格式为"yyyy-MM-dd HH:mm:ss"的当前时间
	 * @return
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 获取新的UUID
	 * @return
	 */
	public static String newUuid() {
		return UUID.randomUUID().toString().toUpperCase().replace("-", "");
	}

}
