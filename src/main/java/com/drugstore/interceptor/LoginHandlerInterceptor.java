package com.drugstore.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle");

        String basePath=request.getContextPath();
        String path=request.getRequestURI();
        if(!doLoginInterceptor(path,basePath)){
            return true;
        }

        Cookie cookie=null;
        Cookie[] cookies = request.getCookies();
        //// TODO: 2018/12/20
        //对没有cookie应该做进入登录界面
        if(cookies==null){
            return true;
        }
        for(Cookie co:cookies){
            if("id".equals(co.getName())){
                cookie=co;
            }
        }
        if(cookie==null || cookie.getValue()==null){
            request.setAttribute("msg","无权限请先登录");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }
        return true;
    }

    private boolean doLoginInterceptor(String path,String basePath){
        path = path.substring(basePath.length());
        Set<String> notLoginPaths = new HashSet<>();
        //设置不进行登录拦截的路径：登录注册和验证码
        notLoginPaths.add("/login");
        notLoginPaths.add("/register");
       // notLoginPaths.add("/kaptcha.jpg");
        //notLoginPaths.add("/kaptcha");
        //notLoginPaths.add("/sys/logout");
        //notLoginPaths.add("/loginTimeout");

        if(notLoginPaths.contains(path)) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion");
    }
}
