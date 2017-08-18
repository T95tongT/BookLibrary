package com.example.demo.dao;

import com.example.demo.po.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
public interface BooksdaoiN extends CrudRepository<Books,Integer>{
    @Query("select c from Books c where id=:id")
    Page<Books> findAll(Pageable pageable, @Param("id") Integer id);
    @Query("select c from Books c where bookid=:id")
    Books findOne(@Param(value = "id") Integer bookid);



    Books save(Books books);
/*
    @Query("select c from Borrowbook c where id=:id")
    Page<Borrowbook> findBorrowAll(Pageable pageable, @Param("id") Integer id);

    Borrowbook save(Borrowbook  borrowbooks);*/
}
