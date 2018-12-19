package com.drugstore.service.impl;

import com.drugstore.po.UserPO;
import com.drugstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl {
    @Resource
    private UserRepository userRepository;

    public void save(UserPO user){
        userRepository.save(user);
    }

    public boolean verifyAccount(String account){
        return userRepository.existsByEmailOrPhone(account,account);
    }

    public String verifyPassword(String account){
        UserPO user = userRepository.findUserPOByEmailOrPhone(account, account);
        return user.getPassword();
    }




}
