package com.example.demo.config;

import com.example.demo.service.loginService;
import com.example.demo.tools.Token;
import com.example.demo.tools.compareTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CheckHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private loginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("execute LoginCheckHandlerInterceptor's preHandle method");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Request-Headers", "*");
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(200);
            return false;
        }
        String token = request.getHeader("Authorization");
        if(token == null || token.equals("null")) {    // token为空 => 返回302重定向
            response.setStatus(302);
            response.getWriter().write("No token！");
            return false;
        }
        return true;
//        try {
//            Map<String, String> token_info = Token.analysisToken(token);   // 解析接收到的token
//            System.out.println("解析成功");
//            int id = Integer.parseInt(token_info.get("id"));    // 提取接收到的id
//            System.out.println("提取id成功");
//            System.out.println(id);
//            String login_token = loginService.getLoginToken(id);    // 用提取到的id去数据库登录表拿对应的token
//            System.out.println("获取token成功");
//            if (token.equals(login_token)){
//                return true;
//            }
//        else {
//            response.setStatus(302);
//            response.getWriter().write("登录失效！");
//            System.out.println("登录失效。");
//            return false;
//        }
//        }
//        catch (Exception e){
//            response.setStatus(302);
//            response.getWriter().write("token失效！");
//            System.out.println(e);
//            return false;
//        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("execute LoginCheckHandlerInterceptor's postHandle method");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("execute LoginCheckHandlerInterceptor's afterCompletion method");
    }
}

