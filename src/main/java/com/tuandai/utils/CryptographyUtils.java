package com.tuandai.utils;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

/**
 * Created by 肖文 on 2018/4/25
 * 加密解密使用
 */
public class CryptographyUtils extends BaseUtils{

    private static final String Algorithm = "DESede"; // 定义 加密算法,可用 DES,DESede,Blowfish

    /**
     * ECB加密
     * @param original 原文
     * @param key 密钥
     * @return
     */
    public static String tripleDESEncrypt(String original, String key) {
        try {
            byte[] keybyte = key.substring(0, 24).getBytes("UTF-8");
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm + "/ECB/PKCS5Padding");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
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
            byte[] keybyte = key.substring(0, 24).getBytes("UTF-8");
            byte[] src = Base64.getDecoder().decode(cryptograph);
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm + "/ECB/PKCS5Padding");
            c1.init(Cipher.DECRYPT_MODE, deskey);
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
}
