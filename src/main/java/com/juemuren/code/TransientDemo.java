package com.juemuren.code;

import java.io.*;

/**
 * Created by 肖文 on 2018/7/7
 * 测试使用transient关键字
 * 一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问
 * transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。
 * 变量如果是用户自定义类变量，则该类需要实现Serializable接口
 * 被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化
 */
public class TransientDemo {

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setName("xiaowen");
        cat.setPwd("12345");
        Cat.setSchool("jxlgdx");

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D:/cat.txt"))) {
            outputStream.writeObject(cat);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try(ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("D:/cat.txt"))) {
            cat= (Cat) inputStream.readObject();
            System.out.println(cat.getName()+":"+cat.getPwd()+":");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Cat implements Serializable{

    private String name;
    private transient  String pwd;
    private static String school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static String getSchool() {
        return school;
    }

    public static void setSchool(String school) {
        Cat.school = school;
    }
}

