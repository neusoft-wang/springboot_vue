package com.boylegu.springboot_vue.dao;

public class ResultUtil {

    public static Result<Object> success(Object object) {

        Result<Object> result = new Result<>();
        result.setCode(200);
        result.setMsg("成功");
        result.setDate(object);

        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
