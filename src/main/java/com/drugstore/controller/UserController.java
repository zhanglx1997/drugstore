package com.drugstore.controller;

import com.drugstore.po.UserPO;
import com.drugstore.service.impl.UserServiceImpl;
import com.drugstore.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public static final int MAP_CAPACITY = 4;

    private static final String MESSAGE = "msg";

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    public @ResponseBody
    Map<String, String> login(String account, String password, HttpServletResponse response) {
        logger.info("login");
        Map<String, String> map = new HashMap<>(MAP_CAPACITY);
        if (StringUtils.isBlank(account)) {
            map.put(MESSAGE, "账号不能为空！");
            return map;
        }
        UserPO user = userService.getUserByAccount(account);
        if (null == user) {
            map.put(MESSAGE, "该账号不存在！");
            return map;
        }
        if (!password.equals(user.getPassword())) {
            map.put(MESSAGE, "密码错误！");
            return map;
        }
        //设置cookie
        CookieUtils.setCookie("id", user.getId(), response);

        return map;
    }

    @RequestMapping("/register")
    public @ResponseBody
    Map<String, String> register(@RequestBody @Valid UserPO user, BindingResult result, HttpServletResponse response) {
        logger.info("register");
        Map<String, String> map = new HashMap<>(MAP_CAPACITY);
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                map.put(MESSAGE, error.getDefaultMessage());
                return map;
            }
        }
        if (StringUtils.isBlank(user.getId())) {
            user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        //todo
        //有个电话号码或邮箱重复的验证，最好还是另外实现验证码功能，可确保唯一性

        userService.save(user);

        CookieUtils.setCookie("id", user.getId(), response);
        map.put("msg", "注册成功");
        return map;
    }

    @RequestMapping("/logout")
    public @ResponseBody
    Map<String, String> logout(HttpServletResponse response, HttpServletRequest request) {
        CookieUtils.removeCookie(response, request);
        Map<String, String> map = new HashMap<>();
        map.put(MESSAGE, "注销成功");
        return map;
    }

    @RequestMapping("/hello")
    public @ResponseBody
    String hello() {
        return "Hello boy";
    }
}
