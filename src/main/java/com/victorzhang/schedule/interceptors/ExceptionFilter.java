package com.victorzhang.schedule.interceptors;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常拦截器
 * @author victorzhang
 *
 */
public class ExceptionFilter implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ex", ex);
		if (ex instanceof FileNotFoundException) {
			return null;
		} else {
			//TODO
			return new ModelAndView("error/demo", map);
		}
	}

}
