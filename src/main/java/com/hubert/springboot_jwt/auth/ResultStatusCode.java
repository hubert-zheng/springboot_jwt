package com.hubert.springboot_jwt.auth;

/**
 * 返回状态 枚举类
 * Created by Hubrt on 2017/9/12.
 */
public enum ResultStatusCode {
    OK(100, "success"),
    ERROR(400, "request error"),   //请求失败
    NOT_USER_PASSWORD(401,"not username or password"),  //缺失账号或密码
    INVALID_LOGIN(402, "User name or password is incorrect"),  //用户不存在
    INVALID_USER(404, "Invalid username"),  //用户不存在
    INVALID_PASSWORD(405, "password is incorrect"),  //密码错误
    INVALID_TOKEN(410, "Invalid token");  //token失效


    private int errcode;
    private String errmsg;

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

    private ResultStatusCode(int Errode, String ErrMsg)
    {
        this.errcode = Errode;
        this.errmsg = ErrMsg;
    }
}
