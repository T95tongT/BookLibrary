package com.example.demo.service;

import com.example.demo.dao.AdminDAOin;
import com.example.demo.po.Admin;
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
 * Created by Administrator on 2017/6/27 0027.
 */
@Service
@Transactional
public class AdminSerImp {
    @Autowired
    private AdminDAOin dao;
    public Admin alogin(String username, String password){
        return dao.alogin(username,password);
    }

    public  Admin save(Admin admin){
        return dao.save(admin);
    }
    private Map<String,String> map=new HashMap<>();
    public AdminSerImp(){
        map.put("image/jpeg",".jpg");
        map.put("image/gif",".gif");
        map.put("image/x-ms-bmp",".bmp");
        map.put("image/png",".png");
    }
    public Admin aupload(HttpServletRequest req){
        CommonsMultipartResolver cmr=new CommonsMultipartResolver(req.getServletContext());
        cmr.setResolveLazily(true);
        cmr.setDefaultEncoding("utf-8");
        cmr.setMaxInMemorySize(1024*1024*10);
        cmr.setMaxUploadSize(1024*1024*10);
        cmr.setMaxUploadSizePerFile(1024*1024*2);
        MultipartHttpServletRequest mhsr=cmr.resolveMultipart(req);
        MultipartFile file=mhsr.getFile("file1");
        String filename=file.getOriginalFilename();
        String path="uploadpic"+ File.separator+filename;
        File file1=new File(path);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Admin admin=new Admin();
        String username=mhsr.getParameter("areusername");
        String password=mhsr.getParameter("arepassword");
        admin.setAusername(username);
        admin.setApassword(password);
        admin.setPicPath(path);
        return admin;
    }

    public Admin afindpicById(@Param(value = "id") Integer id){
        return dao.afindpicById(id);
    }
}
