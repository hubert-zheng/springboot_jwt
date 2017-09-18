package com.hubert.springboot_jwt.auth;

/**
 * Rest 返回结果集
 * Created by Hubrt on 2017/9/12.
 */
public class ResultMsg {
    private int errcode;
    private String errmsg;
    private Object data;
    public ResultMsg(int ErrCode, String ErrMsg, Object Data)
    {
        this.errcode = ErrCode;
        this.errmsg = ErrMsg;
        this.data = Data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getdata() {
        return data;
    }

    public void setdata(Object data) {
        this.data = data;
    }
}
