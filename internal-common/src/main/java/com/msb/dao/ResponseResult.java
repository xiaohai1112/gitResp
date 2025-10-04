package com.msb.dao;

import com.msb.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;

    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMsg(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    /**
     * 失败：同一的错误
     * @param data
     * @return
     */
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }

    /**
     * 失败：自定义失败 错误码和提示信息
     * @param code
     * @param msg
     * @return
     */
    public static ResponseResult fail(int code,String msg){
        return new ResponseResult().setCode(code).setMsg(msg);
    }

    /**
     * 失败：自定义失败 错误码、提示信息、具体错误
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResponseResult fail(int code,String msg,String data){
        return new ResponseResult().setCode(code).setMsg(msg).setData(data);
    }
}
