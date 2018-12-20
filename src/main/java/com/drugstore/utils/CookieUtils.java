package com.drugstore.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CookieUtils {
    private static Logger logger=LoggerFactory.getLogger(CookieUtils.class);

    public static final int COOKIE_HALF_HOUR=2*60;


    public static Cookie getCookie(String name,HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(cookies==null ||cookies.length<1){
            return null;
        }
        Cookie cookie=null;
        for(Cookie co:cookies){
            if(co.getName().equals(name)){
                cookie=co;
                break;
            }
        }
        return cookie;
    }

    public static String getCookieValue(String name,HttpServletRequest request){
        Cookie cookie = getCookie(name,request);
        if(cookie==null){
            return null;
        }
        return cookie.getValue();
    }

    public static void setCookie(String name,String value,int maxAge,HttpServletResponse response){
        if(StringUtils.isBlank(name)){
            return;
        }
        if(value==null){
            value="";
        }
        Cookie cookie=new Cookie(name,value);
        cookie.setPath("/");
        if(maxAge>0){
            cookie.setMaxAge(maxAge);
        }else{
            cookie.setMaxAge(COOKIE_HALF_HOUR);
        }
        response.addCookie(cookie);
        try{
            response.flushBuffer();
        }catch (IOException e){
            logger.info("http响应刷新缓存失败");
            e.printStackTrace();
        }
    }

    public static void setCookie(String name,String value,HttpServletResponse response){
        setCookie(name,value,COOKIE_HALF_HOUR,response);
    }

    public static void removeCookie(HttpServletResponse response,HttpServletRequest request){
        logger.info("*************/removeCookie");
        Cookie cookie=new Cookie("id",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
