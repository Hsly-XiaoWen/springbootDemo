package com.juemuren.entiy;

import com.juemuren.utils.excel.annotation.ExcelField;

/**
 * Created by 肖文 on 2018/8/25
 * 导出excel所需实体
 */
public class PersonDTO {

    @ExcelField(title = "id", align = 2, sort = 10)
    private int id;
    @ExcelField(title = "用户名", align = 2, sort = 10)
    private String userName ;
    @ExcelField(title = "年龄", align = 2, sort = 10)
    private int age ;
    @ExcelField(title = "手机号码", align = 2, sort = 10)
    private String mobilePhone ;

    public PersonDTO() {}

    public PersonDTO(int id, String userName, int age, String mobilePhone) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.mobilePhone = mobilePhone;
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
