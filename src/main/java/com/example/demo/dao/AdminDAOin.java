package com.example.demo.dao;

import com.example.demo.po.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public interface AdminDAOin extends CrudRepository<Admin,Integer> {

    @Query("select c from Admin  c where ausername=:u and apassword=:p")
    Admin alogin(@Param(value = "u") String username, @Param(value = "p") String password);

    Admin save(Admin admin);

    @Query("select c from Admin c where id=:id")
    Admin afindpicById(@Param(value = "id") Integer id);
}
