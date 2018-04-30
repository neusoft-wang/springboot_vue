package com.boylegu.springboot_vue.exception;

public class StudentException extends RuntimeException {

    private Integer code;

    StudentException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
