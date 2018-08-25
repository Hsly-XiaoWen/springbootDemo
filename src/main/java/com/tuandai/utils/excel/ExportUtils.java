package com.tuandai.utils.excel;

import com.tuandai.utils.HttpContextUtils;
import eu.bitwalker.useragentutils.Browser;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 列表导出到excel工具类
 * Created by 肖文 on 2018/8/21
 */
public class ExportUtils {

    /**
     * 导出excel表格设置响应头
     * @param excelName
     * @param request
     * @param response
     */
    public static void setResponseHeader(String excelName,HttpServletRequest request
            , HttpServletResponse response) {
        String userAgent = HttpContextUtils.getBrowerInfo(request);
        if (StringUtils.isBlank(userAgent)) {
            userAgent = "-" + Browser.parseUserAgentString(userAgent).getName();
        }

        if (userAgent != null && userAgent.indexOf("Firefox") > -1) {
            response.setHeader("Content-Disposition", "attachment;filename=" + excelName
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("_yyMMdd_HHmmss")) + ".xls");
        } else {
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName,"utf-8")
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("_yyMMdd_HHmmss")) + ".xls");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
    }
}

