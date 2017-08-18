package com.example.demo.service;

import com.example.demo.dao.BooksdaoiN;
import com.example.demo.po.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
@Service
@Transactional
public class BooksSerImp {
    @Autowired
    BooksdaoiN dao;

    public Page<Books> findAll(Pageable pageable, Integer id){
        return dao.findAll(pageable,id);
    }
    public Books findOne(Integer bookid){
        return dao.findOne(bookid);
    }




    public Books save(Books books){
       return dao.save(books);
    }


}
