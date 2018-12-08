package com.juemuren.mybatis;

import com.juemuren.dao.PersonDAO;
import com.juemuren.entiy.Customer;
import com.juemuren.entiy.User;
import com.juemuren.service.CacheService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 肖文 on 2018/4/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private PersonDAO personDAO;

    @Test
    public void findUser() throws Exception {
        User user=this.cacheService.findUser(1);
        User user1 = this.cacheService.findUser(1);
        Assert.assertEquals(user.toString(),user1.toString());
    }

    @Test
    public void findUsers() throws Exception {
        this.cacheService.findUsers(2);
        this.cacheService.findUsers(2);
    }

    @Test
    public void deleteUser() throws Exception {
        this.cacheService.insertUser(this.cacheService.createUser(1));
        this.cacheService.deleteUser(1);
        this.cacheService.findUser(1);
    }

    @Test
    public void insertUser() throws Exception {
        this.cacheService.insertUsers(this.cacheService.createUser(1));
        this.cacheService.findUser(1);
    }

    @Test
    public void getCustomer(){
        List<Customer> data = this.personDAO.getCustomer();
        String str = "  UPDATE t_customer_report SET interesting_amount = (SELECT SUM(subscription_amount) FROM t_credit_report WHERE customer_id='%s' AND CURRENT_TIMESTAMP < interest_end_time)\n" +
                " WHERE customer_id='%s';";
        data.forEach(x -> {
            System.out.println(String.format(str,x.getCustomerId().toUpperCase(),x.getCustomerId().toUpperCase()));
        });
        System.out.println(data.size());
    }
}