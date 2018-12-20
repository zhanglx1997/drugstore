package com.drugstore.service.impl;

import com.drugstore.DrugstoreApplication;
import com.drugstore.po.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DrugstoreApplication.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void verifyPasswordTest(){
        String account="123456";
        String password = userService.verifyPassword(account);
        System.out.println(password);
    }

    @Test
    public void verifyAccountTest(){
        userService.verifyAccount("123456");
    }

}