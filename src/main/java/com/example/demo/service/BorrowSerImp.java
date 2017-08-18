package com.example.demo.service;

import com.example.demo.dao.BorrowBookIn;
import com.example.demo.po.Borrowbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/7/2 0002.
 */
@Service
@Transactional
public class BorrowSerImp {
    @Autowired
    BorrowBookIn dao;
    public Borrowbook save(Borrowbook books){
        return dao.save(books);
    }
    public Page<Borrowbook> findAll(Pageable pageable, Integer id){
        return dao.findAll(pageable,id);
    }
    public  Borrowbook gunhuan(Integer id){
        return dao.gunhuan(id);
    }
}
