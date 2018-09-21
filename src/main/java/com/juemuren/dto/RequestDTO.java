package com.juemuren.dto;

/**
 * Created by 肖文 on 2018/6/21
 */
public class RequestDTO<T> {

    private String userId;
    private T data;

    public RequestDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "userId='" + userId + '\'' +
                ", data=" + data +
                '}';
    }
}
