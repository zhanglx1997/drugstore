package com.drugstore.controller;

import com.drugstore.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    public @ResponseBody  Map<String,String> login( String account,String password){
        Map<String,String> map=new HashMap<>(4);

        if(StringUtils.isBlank(account)){
            map.put(account,"账号不能为空");
        }else{
            if(!userService.verifyAccount(account)){
                map.put(account,"账号不存在");
            }else{
                if(!userService.verifyPassword(account).equals(password)){
                    map.put(password,"密码不正确");
                }
            }
        }

        return map;
    }

}
