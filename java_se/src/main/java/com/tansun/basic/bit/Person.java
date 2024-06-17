package com.tansun.basic.bit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Person {
    private Long id;
    private String name;//姓名
    private Long phone;//电话
    private BigDecimal salary;//薪水
    private String company;//公司
    private Integer ifSingle;//是否单身
    private Integer sex;//性别
    private String address;//住址
    private LocalDateTime createTime;
    private String createUser;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPhone() {
        return phone;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getCompany() {
        return company;
    }

    public Integer getIfSingle() {
        return ifSingle;
    }

    public Integer getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setIfSingle(Integer ifSingle) {
        this.ifSingle = ifSingle;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }



}