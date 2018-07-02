package com.tuandai.utils;

import com.tuandai.utils.enums.FileType;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 肖文 on 2018/5/17
 * 文件相关的工具类
 */
public class FileTypeUtils {

    /**
     * 二进制转化为16进制
     */
    private static String bytes2hex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                hex.append("0");
            }
            hex.append(temp.toLowerCase());
        }
        return hex.toString();
    }

    /**
     * 读取文件头
     */
    private static String getFileHeader(String filePath) throws IOException {
        byte[] b = new byte[28];//这里需要注意的是,每个文件的魔数的长度都不相同,因此需要使用startwith
        InputStream inputStream= new FileInputStream(filePath);
        inputStream.read(b, 0, 28);
        inputStream.close();

        return bytes2hex(b);
    }

    /**
     * 判断文件类型
     */
    public static FileType getType(String filePath) throws IOException {

        String fileHead = getFileHeader(filePath);
        if (fileHead == null || fileHead.length() == 0) {
            return null;
        }
        fileHead = fileHead.toUpperCase();
        FileType[] fileTypes = FileType.values();//枚举对象.values()获取所有枚举得到数值
        for (FileType type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }
        return null;
    }

    @Test
    public void test() {
        FileType[] fileTypes = FileType.values();
        System.out.println(fileTypes.length);
        FileType types = FileType.valueOf("JPEG");//枚举对象.valueOf()根据name获取枚举类型
        System.out.println(types.getValue());
//        try {
//            System.out.println(FileUtils.getType("D:\\Download\\doc.rar"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
