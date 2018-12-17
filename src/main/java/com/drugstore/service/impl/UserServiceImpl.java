package com.drugstore.service.impl;

import com.drugstore.po.UserPO;
import com.drugstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl {
    @Resource
    private UserRepository userRepository;

    public List<UserPO> findAll(){
        return userRepository.findAll();
    }

    public UserPO find(int id){
        return userRepository.findById(id);
    }
    public void delete(int id){
        userRepository.deleteById(id);
    }
}
