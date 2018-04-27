package xyz.ieden.mybatis.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author ThinkPad
 * @date Created by 2018/4/20 18:45
 */
public class Result<T> implements Serializable {


    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // Setter and Getter Method
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
