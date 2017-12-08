package com.boylegu.springboot_vue.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "administrators")
public class Administrators implements Serializable {

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

    @Column(name = "jurisdiction")
    private String Jurisdiction;

    @Column(name = "number")
    private String number;

    public String getNumber() {

        return number;
    }

    public void setNumber(String number) {

        this.number = number;
    }

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

    public String getJurisdiction() {

        return Jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {

        Jurisdiction = jurisdiction;
    }

    public String getPassWord() {

        return passWord;
    }

    public void setPassWord(String passWord) {

        this.passWord = passWord;
    }
}