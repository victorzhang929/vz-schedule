package com.vz.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户（教师，学生，管理员，系统管理员共同权限）
 *
 * @author 40808
 */

@Controller
@RequestMapping("user")
public class UserController {

    /**
     * 首页欢迎子页面
     *
     * @return
     */
    @RequestMapping("/welcome.do")
    public String welcome() {
        return "WEB-INF/jsp/user/welcome";
    }

    /**
     * 信息中心导航
     *
     * @return
     */
    @RequestMapping("/infocenter.do")
    public String infocenter() {
        return "WEB-INF/jsp/user/infocenter";
    }

    /**
     * 修改密码导航
     *
     * @return
     */
    @RequestMapping("/changePassword.do")
    public String changePassword() {
        return "WEB-INF/jsp/user/changePassword";
    }

    /**
     * 操作日志
     *
     * @return
     */
    @RequestMapping("/userlog.do")
    public String userlog() {
        return "WEB-INF/jsp/user/userlog";
    }

}
