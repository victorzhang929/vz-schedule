package com.victorzhang.schedule.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;

import com.victorzhang.schedule.service.StudentService;

/**
 * 教师控制器
 * @author 40808
 *
 */
public class StudentController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	/**
	 * 教师信息导航
	 * @return
	 */
	@RequestMapping("/studentInfoUI.do")
	public String studentInfoUI(){
		return "jsp/studentInfo";
	}
}
