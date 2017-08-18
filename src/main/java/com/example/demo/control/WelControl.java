package com.example.demo.control;

import org.springframework.stereotype.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
@Controller
@WebServlet(name = "welControl",urlPatterns = "/wel",initParams = {
        @WebInitParam(name = "index",value = "book?action=queryall&cur=0")
})
public class WelControl extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    HashMap vmap=new HashMap();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* FreeMarkerUtils.forward(resp,map.get("index"),vmap);*/
        RequestDispatcher dispatcher=req.getRequestDispatcher(map.get("index"));
        dispatcher.forward(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
    Map<String,String> map=new HashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        map.put("index",config.getInitParameter("index"));
    }
}
