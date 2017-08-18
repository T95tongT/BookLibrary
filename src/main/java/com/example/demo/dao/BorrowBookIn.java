package com.example.demo.dao;

import com.example.demo.po.Borrowbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/7/2 0002.
 */
public interface BorrowBookIn  extends CrudRepository<Borrowbook ,Integer>{

    @Query("select c from Borrowbook c where borrowid=:id")
    Page<Borrowbook> findAll(Pageable pageable, @Param("id") Integer id);

    Borrowbook save(Borrowbook borrowbooks);
    @Query("select  c from Borrowbook  c where id=:id")
    Borrowbook gunhuan(@Param(value = "id") Integer id);
}
