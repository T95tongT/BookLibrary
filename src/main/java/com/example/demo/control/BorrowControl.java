package com.example.demo.control;

import com.example.demo.config.FreeMarkerUtils;
import com.example.demo.po.Admin;
import com.example.demo.po.Books;
import com.example.demo.po.Borrowbook;
import com.example.demo.po.Student;
import com.example.demo.service.BooksSerImp;
import com.example.demo.service.BorrowSerImp;
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
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
@Controller
@WebServlet(name = "book",urlPatterns = "/borrow",initParams = {
        @WebInitParam(name = "borrowshow",value = "show.ftl"),
        @WebInitParam(name="bookbook",value = "borrow.ftl")
})
public class BorrowControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Autowired
    BorrowSerImp service;
    @Autowired
    BooksSerImp bookservice;

    Map<String, String> map = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        map.put("borrowshow", config.getInitParameter("borrowshow"));
        map.put("bookbook",config.getInitParameter("bookbook"));
    }

    @Override
    public void destroy() {
        super.destroy();
    }
    HashMap vmap=new HashMap();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "borrow1":

                vmap=new HashMap<>();

                String  bookid=req.getParameter("id");
                System.out.println(bookid);
                Books books=bookservice.findOne(Integer.parseInt(bookid));
                Borrowbook borrowBook=new Borrowbook();
                String username=req.getParameter("borrowbook");
                borrowBook.setBookname(books.getBookname());
                borrowBook.setUsername(username);
                borrowBook.setId(2);
                borrowBook.setBorrowid(0);
                borrowBook.setPublish(books.getPublish());
                borrowBook.setSort(books.getSort());
                borrowBook.setWriter(books.getWriter());
                borrowBook.setTime(new Date(System.currentTimeMillis()));
                borrowBook.setNum(1);
                service.save(borrowBook);

                RequestDispatcher dispatcher=req.getRequestDispatcher("/borrow?action=queryall&cur=0");
//                RequestDispatcher dispatcher=req.getRequestDispatcher("/wel");
                dispatcher.forward(req,resp);

                break;
            case "queryall":
                HashMap vmap = new HashMap();
                int pagesize = 0;
             Student   student = (Student) req.getSession().getAttribute("suser");
                Admin admin = (Admin) req.getSession().getAttribute("auser");
                int curpage = Integer.parseInt(req.getParameter("cur"));

                Sort sort = new Sort(Sort.DEFAULT_DIRECTION.DESC, "id");
                String row = req.getParameter("row");
                if (student != null) {
                    if (row != null) {
                        pagesize = Integer.parseInt(row);

                    } else {
                        pagesize = student.getPageNum();
                    }
                } else {
                    pagesize = 4;
                }

                Pageable pageable = new PageRequest(curpage, pagesize, sort);
                Page<Borrowbook> page = service.findAll(pageable,0);

                if (student != null) {
                    vmap.put("info", student.getUsername() + " borrow successful!");
                    vmap.put("suser", student);
                }

                if (admin != null) {
                    vmap.put("info", admin.getAusername());
                    vmap.put("auser", admin);
                }

                vmap.put("page", page);
                vmap.put("borrow","borrow");
                System.out.print(page.getTotalElements()+"....."+page.getSize());

                System.out.println(999999999);
                FreeMarkerUtils.forward(resp, map.get("bookbook"), vmap);
                break;
            case "huanshu":
                String id=req.getParameter("id");
                Borrowbook borrowbook=service.gunhuan(Integer.parseInt(id));
                books=new Books();
                books.setBookname(borrowbook.getBookname());
                books.setBooknum(borrowbook.getNum());
                books.setId(0);
                books.setWriter(borrowbook.getWriter());
                books.setSort(borrowbook.getSort());
                books.setPublish(borrowbook.getPublish());
                req.getSession().setAttribute("borrow","borrow");
                req.getSession().setAttribute("info","success!");
                bookservice.save(books);
                 dispatcher=req.getRequestDispatcher("/wel");
                dispatcher.forward(req,resp);
                break;
        }


    }
}
