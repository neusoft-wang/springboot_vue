package com.boylegu.springboot_vue.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "dormitory")
public class Dormitory implements Serializable {

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

    @Column(name = "dor")
    private String dor;

    @Column(name ="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_use")
    private boolean is_use;

    @Column(name = "state")
    private String state;

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

    public String getDor() {

        return dor;
    }

    public void setDor(String dor) {

        this.dor = dor;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public boolean isIs_use() {

        return is_use;
    }

    public void setIs_use(boolean is_use) {

        this.is_use = is_use;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }
}