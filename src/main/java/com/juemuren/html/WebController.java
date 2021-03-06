package com.juemuren.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by 肖文 on 2018/5/14
 */
@Controller
@RequestMapping(WebUrl.WEB_DIR)
public class WebController {

    private static String UPLOADED_FOLDER = "D://temp//";
    private static String UPLOAD_SUCCESS = "%s文件上传成功";
    private static String UPLOAD_ERROR = "%s文件上传失败，error:%s";

    @GetMapping(WebUrl.WEB_HELLO)
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("host", "SprintBoot Test");
        int i = 5 / 0;
        return "hello";
    }

    @GetMapping(WebUrl.WEB_LOGIN)
    public String login(ModelMap modelAndView) {
        modelAndView.addAttribute("username", "xiaowen");
        modelAndView.addAttribute("password", "123456");
        return "login";
    }

    @GetMapping(WebUrl.WEB_INDEX)
    public String index() {
        return "upload";
    }

    /**
     * 文件上传
     *
     * @param file
     * @param modelMap
     * @return
     */
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   ModelMap modelMap) {
        if (file.isEmpty()) {
            modelMap.addAttribute("message", "Please select a file to upload");
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "result";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            modelMap.addAttribute("message",String.format(UPLOAD_SUCCESS,file.getOriginalFilename()));
//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            modelMap.addAttribute("message", String.format(UPLOAD_ERROR, file.getOriginalFilename()
                    , e.getMessage()));
        }

        return "result";
    }


    @PostMapping("/uploads")
    public String uploads(HttpServletRequest request,ModelMap modelMap){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        byte[] bytes;
        Path path;
        if (files == null || files.isEmpty()) {
            modelMap.addAttribute("message", "Please select a file to upload");
            return "result";
        }
        for (MultipartFile file : files) {
            try {
                bytes = file.getBytes();
                path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modelMap.addAttribute("message", String.format(UPLOAD_SUCCESS, "多文件上传成功"));
        return "result";
    }
    /**
     * 文件下载实现
     * @param resp
     */
    @PostMapping(WebUrl.WEB_DOWNLOAD)
    public void downLoad(HttpServletResponse resp) {
        File file = new File("D:\\temp\\肖文：试用期考核评估表.docx");
        String fileName = "test.doc";
        resp.setHeader("content-type", "application/octet-stream");
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os ;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
