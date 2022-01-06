package com.example.mybatissimple.model;


/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-27
 */
 public class User {
    private Integer id;
    private String name;
    private String checkPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckPhone() {
        return checkPhone;
    }

    public void setCheckPhone(String checkPhone) {
        this.checkPhone = checkPhone;
    }
}
