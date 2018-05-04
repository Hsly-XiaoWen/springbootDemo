package com.tuandai.service;

import com.tuandai.entiy.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 肖文 on 2018/5/4
 */
@Service
public class RoutesService {

    @Autowired
    private Routes routes;

    public void printRoutes(){
        routes.getRoutes().forEach(x->System.out.println(x.getInterfaces()));
    }
}
