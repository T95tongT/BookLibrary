package com.example.demo.dao;

import com.example.demo.po.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public interface UserDAOIn extends CrudRepository<Student,Integer>{
    @Query("select c from Student  c where username=:u and password=:p")
    Student slogin(@Param(value = "u") String username, @Param(value = "p") String password);

    Student save(Student student);

    @Query("select c from Student c where id=:id")
    Student findpicById(@Param(value = "id") Integer id);
}
