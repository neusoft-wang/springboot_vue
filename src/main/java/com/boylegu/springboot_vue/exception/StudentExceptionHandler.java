package com.boylegu.springboot_vue.exception;

import com.boylegu.springboot_vue.dao.Result;
import com.boylegu.springboot_vue.dao.ResultUtil;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(value = StudentException.class)
    @ResponseBody
    public Result studentHandler(Exception e) {
        return ResultUtil.error(1,e.getMessage());
    }
}
