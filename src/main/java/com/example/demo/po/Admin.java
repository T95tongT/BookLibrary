package com.example.demo.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
@Entity
public class Admin {
    private Integer id;
    private String apassword;
    private String ausername;
    private byte[] pic;
    private String picPath;
    private Integer pageNum;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "apassword", nullable = false, length = 80)
    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    @Basic
    @Column(name = "ausername", nullable = false, length = 50)
    public String getAusername() {
        return ausername;
    }

    public void setAusername(String ausername) {
        this.ausername = ausername;
    }

    @Basic
    @Column(name = "pic", nullable = true)
    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "picPath", nullable = true, length = 50)
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Basic
    @Column(name = "pageNum", nullable = true)
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (id != null ? !id.equals(admin.id) : admin.id != null) return false;
        if (apassword != null ? !apassword.equals(admin.apassword) : admin.apassword != null) return false;
        if (ausername != null ? !ausername.equals(admin.ausername) : admin.ausername != null) return false;
        if (!Arrays.equals(pic, admin.pic)) return false;
        if (picPath != null ? !picPath.equals(admin.picPath) : admin.picPath != null) return false;
        if (pageNum != null ? !pageNum.equals(admin.pageNum) : admin.pageNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (apassword != null ? apassword.hashCode() : 0);
        result = 31 * result + (ausername != null ? ausername.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(pic);
        result = 31 * result + (picPath != null ? picPath.hashCode() : 0);
        result = 31 * result + (pageNum != null ? pageNum.hashCode() : 0);
        return result;
    }
}
