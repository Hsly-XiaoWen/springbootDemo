package com.juemuren.jdk.jdk8;

import com.juemuren.entiy.User;
import com.juemuren.utils.functions.Supplier;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 肖文 on 2018/6/14
 * 方法引用使用一对冒号 ::
 * 双冒号运用于接口函数作为参数被其他方法调用时，使用双冒号具体实现该接口函数
 */
public class Car {

    //ObjectProcess是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final User user) {
        System.out.println("Collided " + user.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired "+this.toString());
    }

    //构造方法调用
    @Test
    public void test() {
        Car car = Car.create( Car::new );
        List<Car> cars = Arrays.asList(car);
        List<User> users=new ArrayList<User>(){
            {
                add(new User(1, "xiaowen1", 21, "xx"));
                add(new User(2, "xiaowen2", 21, "xx"));
                add(new User(3, "xiaowen3", 21, "xx"));
            }
        };
        //相当于users:forEach(x->System.out.println(x))
        //双冒号相当于实现接口方法的具体实现
        users.forEach(System.out::println);
        users.forEach(Car::collide);
        cars.forEach(Car::repair);
    }

}

