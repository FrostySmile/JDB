package com.yihen.jdb.bean.ViewModel;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:09
 */
public class Result<T> implements Serializable {
    @Expose
    private String  code;
    @Expose
    private boolean isSuccess = Boolean.FALSE;
    @Expose
    private T       data;
    @Expose
    private String  desc;

    public T getData() {
        return data;
    }
    public String getCode() {
        return code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getDesc() {
        return desc;
    }

}
