package com.example.demo.control;

import com.example.demo.po.Admin;
import com.example.demo.service.AdminSerImp;
import com.example.demo.service.UserSerImp;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29 0029.
 */
@Controller
@WebServlet(name = "admincontrol",urlPatterns = "/admin",initParams = {
        @WebInitParam(name = "show",value = "show.ftl")
})
public class AdminControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    @Autowired
    UserSerImp service;

    @Autowired
    AdminSerImp aservice;
    HashMap vmap=null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(req)){
            vmap=new HashMap<>();
            Admin admin=aservice.aupload(req);
            if( admin!=null){

                try(FileInputStream fis=new FileInputStream(admin.getPicPath())){
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    admin.setPic(buffer);
                    admin.setPageNum(5);
                }catch (Exception e){
                    e.printStackTrace();
                }

                aservice.save(admin);
                req.getSession().setAttribute("table","table");
                req.getSession().setAttribute("info",admin.getAusername());
                req.getSession().setAttribute("auser",admin);
                resp.setCharacterEncoding("utf-8");
                RequestDispatcher dispatcher=req.getRequestDispatcher("/wel");
                try {
                    dispatcher.forward(req,resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

          /*  req.getSession().setAttribute("user",student);*/

        }else {
            String action=req.getParameter("action");
            switch (action){
                case "alogin":
                    alogin(req, resp);
                    break;
                case "pic":
                    String id=req.getParameter("id");
                    Admin admin=new Admin();
                    OutputStream os=os=resp.getOutputStream();
                    admin=aservice.afindpicById(Integer.parseInt(id));
                  if(admin!=null){

                        os.write(admin.getPic());
                    }

                    os.flush();
                    os.close();
                    break;
                case "out": out(req,resp);
                    break;
            }

        }

    }
    private void out(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("auser");
        vmap.remove("auser");
        req.getSession().setAttribute("table","table");
        RequestDispatcher d=req.getRequestDispatcher("/wel");
        try {
            d.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void alogin(HttpServletRequest req, HttpServletResponse resp) {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        Admin admin=aservice.alogin(username,password);
        vmap=new HashMap();
        if(admin!=null){
            req.getSession().setAttribute("auser",admin);
            String sun=req.getParameter("sun");
            if(sun!=null){//勾上了，记住一星期
                Cookie uc=new Cookie("papaoku",username);
                uc.setMaxAge(3600*24*7);
                resp.addCookie(uc);
                Cookie up=new Cookie("papaokp",password);
                up.setMaxAge(3600*24*7);
                resp.addCookie(up);
            }
            req.getSession().setAttribute("table","table");
            req.getSession().setAttribute("info","sucess"+username);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/wel");
            try {
                dispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            req.getSession().setAttribute("info", "fail");
            req.getSession().setAttribute("table","table");
            RequestDispatcher dispatcher=req.getRequestDispatcher("/wel");
            try {
                dispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
    Map<String,String> map=new HashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        map.put("show",config.getInitParameter("show"));
    }
}
