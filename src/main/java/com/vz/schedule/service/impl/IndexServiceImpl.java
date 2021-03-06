package com.vz.schedule.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.vz.schedule.controller.IndexController;
import com.vz.schedule.mapper.IndexMapper;
import com.vz.schedule.service.IndexService;
import com.vz.schedule.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vz.schedule.pojo.User;
import com.vz.schedule.util.CommonUtils;
import com.vz.schedule.util.EmailUtils;
import com.vz.schedule.util.MD5Utils;

/**
 * indexServiceImpl实现类
 *
 * @author victorzhang
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService {

    private static final Logger log = Logger.getLogger(IndexController.class);

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    @Autowired
    @Qualifier("indexMapper")
    private IndexMapper indexMapper;

    @Override
    public User doLogin(String username, String password, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            Map<String, Object> map = new HashMap<>(4);
            map.put("username", username);
            map.put("password", new MD5Utils().getMD5ofStr(password));
            //log4j记录日志
            log.info(username + ":尝试登陆");
            //从数据user表中进行查找
            User user = indexMapper.querryUser(map);
            if (user != null) {
                request.getSession().setAttribute("userid", user.getUserid());
                request.getSession().setAttribute("roleid", user.getRoleid());
                request.getSession().setAttribute("departid", user.getDepartid());
                //添加登录日志到数据库中
                addLoginLog(request, "登录系统");
                //log4j记录日志
                log.info(username + ":登陆成功");
                return user;
            }
            //log4j记录日志
            log.error(username + ":登陆失败");
        }
        return null;
    }

    /**
     * 记录用户登录日志到数据库中
     *
     * @param request
     */
    private void addLoginLog(HttpServletRequest request, String login_outMsg) {
        String userid = CommonUtils.sesAttr(request, "userid");
        if (StringUtils.isNotEmpty(userid)) {
            String agent = request.getHeader("user-agent");
            logService.addLog(login_outMsg, "浏览器：" + agent);
        }
    }

    @Override
    public Map<String, Object> queryUserByEmail(String email) {
        if (StringUtils.isNotEmpty(email)) {
            log.info(email + ":尝试重设密码");
            User user = indexMapper.querryUserByEamil(email);
            if (user != null) {
                Map<String, Object> result = new HashMap<>();
                EmailUtils.sendResetPasswordEmail(user);
                result.put("sendMailMsg", "您的申请已提交，请查收您的" + user.getUsermail() + "邮箱");
                log.info(email + ":重设密码链接已发送至邮箱");
                return result;
            }
            log.warn(email + "不存在");
        }
        return null;
    }

    @Override
    public Map<String, Object> doResetPwd(String username, String checkcode,
                                          String findPwd, String rfindPwd) {
        User user = indexMapper.querryUserByUsername(username);
        Map<String, Object> result = new HashMap<>();
        String _checkcode = new MD5Utils().getMD5ofStr(username + ":" + user.getRandomcode());
        //验证激活码是否正确（url是否正确）
        if (StringUtils.equals(_checkcode, checkcode)) {
            //后台验证两次输入的密码是否一致
            if (StringUtils.equals(findPwd, rfindPwd)) {
                Map<String, Object> param = new HashMap<>();
                param.put("userid", user.getUserid());
                param.put("password", new MD5Utils().getMD5ofStr(findPwd));
                //更新密码
                indexMapper.doUpdatePwd(param);
                log.info(username + "修改密码");
                result.put("successMsg", "修改密码成功");
            } else {
                result.put("errorMsg", "请确认两次密码是否一致");
            }
        } else {
            result.put("errorMsg", "请确认是否为合法的URL");
        }
        return result;
    }

    @Override
    public void quitSystem(HttpServletRequest request, String username) {
        log.info(username + "退出系统");
        addLoginLog(request, "退出系统");
        request.getSession().removeAttribute("userid");
        request.getSession().removeAttribute("roleid");
        request.getSession().removeAttribute("departid");
    }

}
