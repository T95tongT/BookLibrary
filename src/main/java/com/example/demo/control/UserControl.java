package com.example.demo.control;

import com.example.demo.po.Student;
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
 * Created by Administrator on 2017/6/26 0026.
 */
@Controller
@WebServlet(name = "usercontrol",urlPatterns = "/user",initParams = {
        @WebInitParam(name = "show",value = "show.ftl")
})
public class UserControl extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    @Autowired
    UserSerImp service;
    
    HashMap vmap=null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(req)){
            vmap=new HashMap<>();
            Student student= service.upload(req);
            if( student!=null) {
                try (FileInputStream fis = new FileInputStream(student.getPicpath())) {
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    student.setPic(buffer);
                    student.setPageNum(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                service.save(student);
                System.out.println(student.getUsername());
                System.out.println(student.getPassword());
//                req.getSession().setAttribute("info",student.getUsername());
                req.getSession().setAttribute("suser", student);
                resp.setCharacterEncoding("utf-8");
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

        }else {
            String action=req.getParameter("action");
            switch (action){
                case "login":
                    login(req, resp);
                    break;
                case "pic":
                    String id=req.getParameter("id");
                    Student student=new Student();
                    OutputStream os=os=resp.getOutputStream();
                    student=service.findpicById(Integer.parseInt(id));
                    if(student!=null){

                        os.write(student.getPic());
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
        req.getSession().removeAttribute("suser");
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

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        vmap=new HashMap();
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        Student student=service.slogin(username,password);
        vmap=new HashMap();
        if(student!=null){
            req.getSession().setAttribute("suser", student);
            String sun=req.getParameter("sun");
            if(sun!=null){//勾上了，记住一星期
                Cookie uc=new Cookie("papaoku",username);
                uc.setMaxAge(3600*24*7);
                resp.addCookie(uc);
                Cookie up=new Cookie("papaokp",password);
                up.setMaxAge(3600*24*7);
                resp.addCookie(up);
            }
            vmap.put("info",student.getUsername()+"success");
            req.getSession().setAttribute("table","table");
//            req.getSession().setAttribute("info", "sucess"+student.getUsername());
          /*  FreeMarkerUtils.forward(resp,map.get("show"),vmap);*/
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
