package com.atguigu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
    private Integer id;

    private String filenumber;

    private String name;

    private String sex;

    private String idcard;

    private String phonenumber;

    private Date regtime;

    private String password;

    private String email;

    private Date birthday;

    private String remark;

    private static final long serialVersionUID = 1L;


    public Member() {
    }

    public Member(String name, String sex, String idcard, String phonenumber, Date regtime) {
        this.name = name;
        this.sex = sex;
        this.idcard = idcard;
        this.phonenumber = phonenumber;
        this.regtime = regtime;
    }

    public Member(Integer id, String filenumber, String name, String sex, String idcard, String phonenumber, Date regtime, String password, String email, Date birthday, String remark) {
        this.id = id;
        this.filenumber = filenumber;
        this.name = name;
        this.sex = sex;
        this.idcard = idcard;
        this.phonenumber = phonenumber;
        this.regtime = regtime;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilenumber() {
        return filenumber;
    }

    public void setFilenumber(String filenumber) {
        this.filenumber = filenumber == null ? null : filenumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }



}