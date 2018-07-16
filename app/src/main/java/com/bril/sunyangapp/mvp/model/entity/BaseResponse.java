package com.bril.sunyangapp.mvp.model.entity;

import com.bril.sunyangapp.mvp.model.api.Api;

import java.io.Serializable;

/**
 * 项目名：sunyangframe
 * 包名：com.bril.sunyangapp.mvp.entity
 * 时间：2018/7/9 11:54
 * 描述：
 * 姓名： sunyang
 */
public class BaseResponse<T> implements Serializable {
    private T data;
    private String code;
    private String msg;

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (code.equals(Api.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }
}
