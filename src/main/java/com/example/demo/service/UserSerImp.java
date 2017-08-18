package com.example.demo.service;

import com.example.demo.dao.UserDAOIn;
import com.example.demo.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
@Service
@Transactional
public class UserSerImp  {
    @Autowired
    private UserDAOIn userDAOIn;
    public Student slogin(String username, String password){
        return userDAOIn.slogin(username,password);
    }

    public Student save(Student student){
        return userDAOIn.save(student);
    }
    private Map<String,String> map=new HashMap<>();
    public UserSerImp(){
        map.put("image/jpeg",".jpg");
        map.put("image/gif",".gif");
        map.put("image/x-ms-bmp",".bmp");
        map.put("image/png",".png");
    }
    public Student upload(HttpServletRequest req){
        CommonsMultipartResolver cmr=new CommonsMultipartResolver(req.getServletContext());
        cmr.setResolveLazily(true);
        cmr.setDefaultEncoding("utf-8");
        cmr.setMaxInMemorySize(1024*1024*10);
        cmr.setMaxUploadSize(1024*1024*10);
        cmr.setMaxUploadSizePerFile(1024*1024*2);
        MultipartHttpServletRequest mhsr=cmr.resolveMultipart(req);
        MultipartFile file=mhsr.getFile("file0");
        String filename=file.getOriginalFilename();
        String path="uploadpic"+ File.separator+filename;
        File file1=new File(path);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Student student=new Student();
        String username=mhsr.getParameter("reusername");
        String password=mhsr.getParameter("repassword");
        student.setUsername(username);
        student.setPassword(password);
        student.setPicpath(path);
        return student;
    }

    public Student findpicById(@Param(value = "id") Integer id){
     return userDAOIn.findpicById(id);
    }
}
