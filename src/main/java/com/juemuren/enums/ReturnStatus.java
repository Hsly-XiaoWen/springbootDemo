package com.juemuren.enums;

/**
 * Created by xiaojianqiu on 2018/5/4.
 */
public enum ReturnStatus {

    SUCCESS(0, "成功"),
    SYSTEM_ERROR(1,"接口请求失败");;

    ReturnStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
