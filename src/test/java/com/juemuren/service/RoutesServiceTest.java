package com.juemuren.service;

import com.juemuren.AppTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 肖文 on 2018/5/4
 */
public class RoutesServiceTest extends AppTest{

    @Autowired
    private RoutesService routesService;

    @Test
    public void printRoutes() throws Exception {
        this.routesService.printRoutes();
    }

}