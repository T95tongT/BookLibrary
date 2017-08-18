package com.example.demo.po;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/3 0003.
 */
@Entity
public class Books {
    private Integer bookid;
    private Integer id;
    private String bookname;
    private String writer;
    private String publish;
    private String sort;
    private Integer booknum;

    @Id
    @Column(name = "bookid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookname", nullable = false, length = 50)
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Basic
    @Column(name = "writer", nullable = true, length = 10)
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Basic
    @Column(name = "publish", nullable = true, length = 10)
    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    @Basic
    @Column(name = "sort", nullable = true, length = 10)
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "booknum", nullable = false)
    public Integer getBooknum() {
        return booknum;
    }

    public void setBooknum(Integer booknum) {
        this.booknum = booknum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Books books = (Books) o;

        if (bookid != null ? !bookid.equals(books.bookid) : books.bookid != null) return false;
        if (id != null ? !id.equals(books.id) : books.id != null) return false;
        if (bookname != null ? !bookname.equals(books.bookname) : books.bookname != null) return false;
        if (writer != null ? !writer.equals(books.writer) : books.writer != null) return false;
        if (publish != null ? !publish.equals(books.publish) : books.publish != null) return false;
        if (sort != null ? !sort.equals(books.sort) : books.sort != null) return false;
        if (booknum != null ? !booknum.equals(books.booknum) : books.booknum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookid != null ? bookid.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        result = 31 * result + (writer != null ? writer.hashCode() : 0);
        result = 31 * result + (publish != null ? publish.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (booknum != null ? booknum.hashCode() : 0);
        return result;
    }
}
