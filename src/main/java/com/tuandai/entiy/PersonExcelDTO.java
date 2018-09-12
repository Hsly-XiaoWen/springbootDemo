package com.tuandai.entiy;

import com.tuandai.utils.excel.annotation.ExcelField;

/**
 * Created by 肖文 on 2018/9/12
 */
public class PersonExcelDTO {

    @ExcelField(title = "id", align = 2, sort = 10)
    private int id;
    @ExcelField(title = "用户姓名", align = 2, sort = 10)
    private String userName ;
    @ExcelField(title = "年龄", align = 2, sort = 10)
    private int age ;
    @ExcelField(title = "手机号码", align = 2, sort = 10)
    private String mobilePhone ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
