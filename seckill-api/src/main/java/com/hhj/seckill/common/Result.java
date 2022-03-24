package com.hhj.seckill.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Result<T> {

    
    int code;
    // 失败不需要返回数据，返回失败原因
    T data;
    String msg;

    public Result(T object){
        this.code=200;
        this.data=object;
    }

    public Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    
    public static Result success(Object data){
        return new Result(data);
    }

    public static Result success(Object data,String msg){
        return new Result(200,data,msg);
    }


    
    public static Result error(String msg){
        return new Result(400,null,msg);
    }

    public static Result error(Object data){
        return new Result(400,data,null);
    }

    public static Result exception(String msg){
        return new Result(500,null,msg);
    }
}
