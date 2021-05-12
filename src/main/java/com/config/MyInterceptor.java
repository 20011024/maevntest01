package com.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //如果是登陆界面则放行
//        System.out.println("uri:"+request.getRequestURI());
        if(request.getRequestURI().contains("/login")){
            return true;
        }
        if(request.getRequestURI().contains("/toLogin")){
            return true;
        }

        HttpSession session = request.getSession();
        //如果用户已登陆,也可放行
        if(session.getAttribute("user")!=null){
            return true;
        }
        request.getRequestDispatcher("/login.jsp").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
