package com.ln.springboot_shiro_jsp.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping({"/", "index"})
    public String index(){
        return "index";
    }
    @RequestMapping("403")
    public String no403(){
        return "403";
    }

    @RequestMapping("/err")
    public String err(){
        return "err";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("session================>>>>"+session.toString());

        Enumeration<String> attrs = session.getAttributeNames();

        if(null != session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY")){
            return "redirect:/logout";
        }
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map){
        System.out.println("跳入HomeController.login()");
        // 登录失败从request中获取shiro信息；
        // shiroLoginFailure: 就是shro异常类的全类名
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception="+exception);
        String msg = "";

        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }

            map.put("msg", msg);
            // 此方法不处理登录成功,由shiro进行处理.

            return "/login";
        }else{
            return "/index";
        }

    }

}
