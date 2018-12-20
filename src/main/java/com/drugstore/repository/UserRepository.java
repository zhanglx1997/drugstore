package com.drugstore.repository;

import com.drugstore.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPO,Integer> {

    boolean existsByEmailOrPhone(String email,String phone);


    UserPO findUserPOByEmailOrPhone(String email,String phone);
}
