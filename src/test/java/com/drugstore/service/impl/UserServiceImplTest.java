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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DrugstoreApplication.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    @Transactional
    public void operation(){
        int id=2;

        List<UserPO> all = userService.findAll();
        userService.delete(id);
        System.out.println("ahha");
    }

}