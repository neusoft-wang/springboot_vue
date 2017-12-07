package com.boylegu.springboot_vue.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "create_datetime")
    /*
       @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
       @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "UTC")
       @Type(type="datetime")
    */
    private String create_datetime;

    @Column(name = "username")
    private String username;

    @Column(name ="passWord")
    private String passWord;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sex")
    private String sex;

    @Column(name = "number")
    private String number;

    @Column(name = "classInfo")
    private String classInfo;

    @Column(name = "is_use")
    private boolean isUse;

    @Column(name = "use_time")
    private String useTime;

    @Column(name = "pay_state")
    private boolean payState;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreate_datetime() {
        return create_datetime;

    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumber() {

        return number;
    }

    public void setNumber(String number) {

        this.number = number;
    }

    public String getClassInfo() {

        return classInfo;
    }

    public void setClassInfo(String classInfo) {

        this.classInfo = classInfo;
    }

    public boolean isUse() {

        return isUse;
    }

    public void setUse(boolean use) {

        isUse = use;
    }

    public String getUseTime() {

        return useTime;
    }

    public void setUseTime(String useTime) {

        this.useTime = useTime;
    }

    public boolean isPayState() {

        return payState;
    }

    public void setPayState(boolean payState) {

        this.payState = payState;
    }

    public String getPassWord() {

        return passWord;
    }

    public void setPassWord(String passWord) {

        this.passWord = passWord;
    }
}