package com.boylegu.springboot_vue.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "activity")
public class Activity implements Serializable {

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

    @Column(name = "keywords_group")
    private String keywordsGroup;

    @Column(name = "is_use")
    private String isUse;

    @Column(name = "add_time")
    private String addTime;

    @Column(name = "frequency")
    private Integer frequency;

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

    public String getKeywordsGroup() {

        return keywordsGroup;
    }

    public void setKeywordsGroup(String keywordsGroup) {

        this.keywordsGroup = keywordsGroup;
    }

    public String getIsUse() {

        return isUse;
    }

    public void setIsUse(String isUse) {

        this.isUse = isUse;
    }

    public String getAddTime() {

        return addTime;
    }

    public void setAddTime(String addTime) {

        this.addTime = addTime;
    }

    public Integer getFrequency() {

        return frequency;
    }

    public void setFrequency(Integer frequency) {

        this.frequency = frequency;
    }
}