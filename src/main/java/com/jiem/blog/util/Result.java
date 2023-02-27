package com.jiem.blog.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    int code;

    String msg;

    T data;

    public static <T> Result success(T obj) {
        Result result = new Result();
        result.setCode(200);
        result.setData(obj);
        result.setMsg("111");
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.code = 201;
        result.msg = msg;
        return result;
    }


    public static Result ok(String msg) {
        return new Result(200, msg, null);
    }
}
