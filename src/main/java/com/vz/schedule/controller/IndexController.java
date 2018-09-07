package com.vz.schedule.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vz.schedule.pojo.User;
import com.vz.schedule.service.IndexService;

/**
 * index controller
 *
 * @author victorzhang
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    @Qualifier("indexService")
    private IndexService indexService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login.index")
    @ResponseBody
    public User login(String username, String password) {
        return indexService.doLogin(username, password, request);
    }

    /**
     * 主页面链接
     *
     * @return
     */
    @RequestMapping("/mainPage.do")
    public String mainPage() {
        return "main";
    }

    /**
     * 邮箱：忘记密码，发送邮件
     *
     * @param email
     */
    @RequestMapping("/forgetPassword.index")
    @ResponseBody
    public Map<String, Object> forgetPassword(String email) {
        return indexService.queryUserByEmail(email);
    }

    /**
     * mainPage用户首页(如果用户记住则转到main.jsp)
     *
     * @return
     */
    @RequestMapping("/homePage.index")
    public String homePage() {
        HttpServletRequest req = request;
        HttpSession session = req.getSession(true);

        if (session.getAttribute("userid") != null) {
            return "WEB-INF/jsp/main";
        }
        return "WEB-INF/jsp/login";
    }


    /**
     * 重设密码链接
     *
     * @return
     */
    @RequestMapping("/resetPassword.index")
    public String resetPassword() {
        return "WEB-INF/jsp/resetPassword";
    }

    /**
     * 重设密码
     *
     * @param username
     * @param checkcode
     * @param findPwd
     * @param rfindPwd
     */
    @RequestMapping("/doResetPwd.index")
    @ResponseBody
    public Map<String, Object> doResetPwd(String username, String checkcode, String findPwd, String rfindPwd) {
        return indexService.doResetPwd(username, checkcode, findPwd, rfindPwd);
    }

    /**
     * 退出系统
     */
    @RequestMapping("/quitSystem.do")
    public String quitSystem(String username) {
        indexService.quitSystem(request, username);
        return "WEB-INF/jsp/login";
    }

}
