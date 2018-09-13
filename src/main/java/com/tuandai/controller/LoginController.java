package com.tuandai.controller;

import com.tuandai.annotations.SysLog;
import com.tuandai.config.JwtConfig;
import com.tuandai.config.spring.ApiVersion;
import com.tuandai.config.spring.Version;
import com.tuandai.dao.PersonDAO;
import com.tuandai.entiy.PersonDTO;
import com.tuandai.entiy.PersonExcelDTO;
import com.tuandai.utils.HttpContextUtils;
import com.tuandai.utils.excel.ExcelUtils;
import com.tuandai.utils.excel.ExportExcel;
import com.tuandai.utils.excel.ExportUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.I0Itec.zkclient.ZkClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 肖文 on 2018/6/21
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private ZkClient zkClient;
    @Autowired
    private PersonDAO personDAO;

    @ApiOperation("生成token")
    @GetMapping("/check")
    public String checkUser(@RequestParam("userName") String username,
                            @RequestParam("password") String password) {
        String token = jwtConfig.createJWT(username);
        System.out.println(token+"=============");
        return "登录成功 "+token;
    }

    @ApiOperation("校验jwt token")
    @GetMapping("/checks")
    public String checkToken(@RequestParam("token") String token) {
        Claims result = jwtConfig.checkJWT(token);
        if (result != null) {
            return "校验成功";
        } else {
            return "token校验失败";
        }
    }

    @GetMapping("/test")
    @RequiresPermissions("update")
    public String testPermission(){
        return "测试权限控制";
    }

    @ApiOperation("测试获取数据")
    @GetMapping("/testZk")
    @ApiVersion(version = Version.V_1_1_0)
    public String testZk() {
        return this.zkClient.readData("/test/name");
    }

    @GetMapping("/export")
    @SysLog("导出用户信息表")
    public void downExcel(HttpServletRequest request
            , HttpServletResponse response){
        ExportUtils.setResponseHeader("excel",request,response);
        List<PersonExcelDTO> result = this.personDAO.queryPersonExcelDTO();
        ExportExcel exportExcel=new ExportExcel("用户信息表", PersonDTO.class)
                .setDataList(result);
        try {
            exportExcel.write(response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
