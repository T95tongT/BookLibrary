package com.example.demo.control;

import com.example.demo.config.FreeMarkerUtils;
import com.example.demo.po.Admin;
import com.example.demo.po.Books;
import com.example.demo.po.Student;
import com.example.demo.service.BooksSerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
 * Created by Administrator on 2017/6/30 0030.
 */
@Controller
@WebServlet(name = "bookControl" ,urlPatterns = "/book",initParams = {
        @WebInitParam(name="show",value = "show.ftl")
})
public class BookControl extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    @Autowired
    BooksSerImp service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        switch (action){

            case "queryall":
                HashMap vmap=new HashMap();
                int pagesize=0;
                Student student=(Student) req.getSession().getAttribute("suser");
                Admin admin=(Admin) req.getSession().getAttribute("auser");
                int  curpage=Integer.parseInt(req.getParameter("cur"));

                Sort sort=new Sort(Sort.DEFAULT_DIRECTION.DESC,"bookid");
                String  row=req.getParameter("row");
                if(student!=null){
                    if(row!=null){
                        pagesize = Integer.parseInt(row);

                    }else {
                        pagesize=student.getPageNum();
                    }
                }else {
                    pagesize=4;
                }
              /*  if(admin!=null){
                    if(row!=null){
                        pagesize = Integer.parseInt(row);
                    }else {
                        pagesize=admin.getPageNum();
                    }
                }else {
                    pagesize=5;
                }*/
                Pageable pageable=new PageRequest(curpage,pagesize,sort);
                Page<Books> page=service.findAll(pageable,0);

                if(student!=null){
                    vmap.put("info",student.getUsername()+" login successful!");
                    vmap.put("suser",student);
                }

                if(admin!=null){
                    vmap.put("info",admin.getAusername());
                    vmap.put("auser",admin);
                }

                vmap.put("tanle","table");
                vmap.put("page",page);


                System.out.println("suser="+student);
                FreeMarkerUtils.forward(resp,map.get("show"),vmap);
                break;

            case "addbook":

                String bookname=req.getParameter("bookname");
                req.setCharacterEncoding("utf-8");
                String writer=req.getParameter("writer");
                String publish=req.getParameter("publish");
                String sort1=req.getParameter("sort");
                String booknum=req.getParameter("booknum");
                Books book=new Books();
                book.setBookname(bookname);
                book.setWriter(writer);
                book.setPublish(publish);
                book.setId(0);
                book.setSort(sort1);
                book.setBooknum(Integer.parseInt(booknum));
                req.getSession().setAttribute("table","table");
                service.save(book);
                RequestDispatcher dispatcher=req.getRequestDispatcher("/wel");
                dispatcher.forward(req,resp);
                break;




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
