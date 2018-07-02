package com.tuandai.utils;

import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Created by 肖文 on 2018/4/25
 * 加密解密使用
 */
public class CryptographyUtils extends BaseUtils{

    private static final String Algorithm = "DESede"; // 定义 加密算法,可用 DES,DESede,Blowfish
    private static final String SEC_PASSWORD =
            "^(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[@!#$%^&*()_+\\.\\-\\?<>'\"|=]+).{8,15}$";

    /**
     * MD5加密
     * @param pwd
     * @return
     */
    public static String md5(String pwd){
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(pwd.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String password = new BigInteger(1, md.digest()).toString(16);
            return password;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwd;
    }

    @Test
    public void testMD5(){
        String result = md5("123456");
        System.out.println(result);
    }
    /**
     * ECB加密
     * @param original 原文
     * @param key 密钥
     * @return
     */
    public static String tripleDESEncrypt(String original, String key) {
        try {
            byte[] keyByte = key.substring(0, 24).getBytes("UTF-8");
            // 生成密钥
            SecretKey desKey = new SecretKeySpec(keyByte, Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm + "/ECB/PKCS5Padding");
            c1.init(Cipher.ENCRYPT_MODE, desKey);
            return Base64.getEncoder().encodeToString(c1.doFinal(original.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            logger.error("3DES加密出错", e);
        }
        return null;
    }

    /**
     * ECB解密
     * @param cryptograph 密文
     * @param key 密钥
     * @return
     */
    public static String tripleDESDecrypt(String cryptograph, String key) {
        try {
            byte[] keyByte = key.substring(0, 24).getBytes("UTF-8");
            byte[] src = Base64.getDecoder().decode(cryptograph);
            // 生成密钥
            SecretKey desKey = new SecretKeySpec(keyByte, Algorithm);
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm + "/ECB/PKCS5Padding");
            c1.init(Cipher.DECRYPT_MODE, desKey);
            return new String(c1.doFinal(src));
        } catch (Exception e) {
            logger.error("3DES解密出错", e);
        }
        return null;
    }

    @Test
    public void testECB(){
        String name="大家好啊，我是小文";
        String key="b26dcd59-756a-4062-9737-98ce2306e408";
        String result=CryptographyUtils.tripleDESEncrypt(name, key);
        logger.info("密文是{}",result);
        result=CryptographyUtils.tripleDESDecrypt(result, key);
        logger.info("密文是{}",result);
    }

    /**
     * CBC加密
     * @param key 密钥
     * @param keyiv IV
     * @param original 明文
     * @return
     */
    public static String des3EncodeCBC(byte[] key, byte[] keyiv, String original) {
        try{
            byte[] data = original.getBytes("UTF-8");
            Key desKey;
            DESedeKeySpec spec = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            desKey = keyFactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(keyiv, 0, 8);
            cipher.init(Cipher.ENCRYPT_MODE, desKey, ips);
            byte[] bOut = cipher.doFinal(data);
            return Base64.getEncoder().encodeToString(bOut);
        }catch(Exception e){
            logger.error("CBC加密出错", e);
        }
        return null;
    }

    /**
     * CBC解密
     * @param key 密钥
     * @param keyiv IV
     * @param cryptograph 密文
     * @return
     */
    public static String des3DecodeCBC(byte[] key, byte[] keyiv,String cryptograph ) {
        try {
            byte[] data = Base64.getDecoder().decode(cryptograph);
            Key desKey;
            DESedeKeySpec spec = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            desKey = keyFactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(keyiv, 0, 8);
            cipher.init(Cipher.DECRYPT_MODE, desKey, ips);
            byte[] bOut = cipher.doFinal(data);
            return new String(bOut, "UTF-8");
        } catch (Exception e) {
            logger.error("CBC解密出错", e);
        }
        return null;
    }

    @Test
    public void testCBC(){
        String name = "大家好啊，早上好";
        String iv = "团贷网公司";
        String key="b26dcd59-756a-4062-9737-98ce2306e408";
        String result = CryptographyUtils.des3EncodeCBC(key.getBytes(), iv.getBytes(), name);
        logger.info("CBC加密结果是{}",result);

        //解密
        result = CryptographyUtils.des3DecodeCBC(key.getBytes(), iv.getBytes(), result);
        logger.info("CBC解密结果是{}",result);
    }

    /**
     * base63编码方法
     * @param bytes
     * @return
     */
    public static String byte2base64(byte[] bytes) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(bytes);
    }

    public static byte[] base642byte(String base64) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        return base64Decoder.decodeBuffer(base64);
    }

    @Test
    public void testBase64() throws IOException {
        String name = "my name is XiaoWen , come from China";
        byte[] bytes = name.getBytes();
        String data=CryptographyUtils.byte2base64(bytes);
        System.out.println(data);

        String base64 = "bXkgbmFtZSBpcyBYaWFvV2VuICwgY29tZSBmcm9tIENoaW5h";
        bytes=CryptographyUtils.base642byte(base64);
        name = new String(bytes);
        System.out.println(name);
    }
}
