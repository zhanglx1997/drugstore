package com.drugstore.controller;

import com.drugstore.po.UserPO;
import com.drugstore.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    private Logger logger=LoggerFactory.getLogger(UserController.class);

    public static final int MAP_CAPACITY=4;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    public @ResponseBody  Map<String,String> login( String account,String password){
        Map<String,String> map=new HashMap<>(MAP_CAPACITY);

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

    public Map<String,String> register(@RequestBody  @Valid UserPO user, BindingResult result){
        Map<String,String> map=new HashMap<>(MAP_CAPACITY);
        if(result.hasErrors()){
            for(ObjectError error:result.getAllErrors()){
                map.put("msg",error.getDefaultMessage());
                return map;
            }
        }
        if(StringUtils.isBlank(user.getId())){
            user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        }
        //todo
        //有个电话号码或邮箱重复的验证，最好还是另外实现验证码功能，可确保唯一性

        userService.save(user);

    }

}
