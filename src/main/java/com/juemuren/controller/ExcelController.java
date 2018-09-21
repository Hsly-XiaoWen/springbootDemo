package com.juemuren.controller;

import com.juemuren.dao.PersonDAO;
import com.juemuren.entiy.PersonDTO;
import com.juemuren.utils.excel.ExportExcel;
import com.juemuren.utils.excel.ExportUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 肖文 on 2018/8/25
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private PersonDAO personDAO;

    @RequestMapping("/person")
    public void export(HttpServletRequest request
            , HttpServletResponse response) {
        ExportUtils.setResponseHeader("产品明细表",request, response);

        List<PersonDTO> dataList = this.personDAO.queryPersonDTO();

        ExportExcel exportExcel = new ExportExcel("产品明细表", PersonDTO.class).setDataList(dataList);
        try {
            exportExcel.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        PersonDTO personDTO = new PersonDTO(1, "test1", 21, "sadsadsa");
        PersonDTO personDTO1 = new PersonDTO(1, "test1", 21, "sadsadsa");
        PersonDTO personDTO2 = new PersonDTO(1, "test1", 21, "sadsadsa");
        PersonDTO personDTO3 = new PersonDTO(1, "test1", 21, "sadsadsa");
        PersonDTO personDTO4= new PersonDTO(1, "test1", 21, "sadsadsa");
        List<PersonDTO> dataList = new ArrayList<>();
        dataList.add(personDTO);
        dataList.add(personDTO1);
        dataList.add(personDTO2);
        dataList.add(personDTO3);
        dataList.add(personDTO4);
        ExportExcel exportExcel = new ExportExcel("产品明细表", PersonDTO.class).setDataList(dataList);

          /*生成临时文件*/
        FileOutputStream out = null;
        String localPath = null;
        File tempFile = null;
        String fileName = String.valueOf(new Date().getTime()/1000);
        try {
            tempFile = File.createTempFile(fileName, ".xlsx");
            localPath = tempFile.getAbsolutePath();
            out = new FileOutputStream(localPath);
            exportExcel.write(out);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                out.flush();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println(localPath);
    }
}
