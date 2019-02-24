package com.juemuren.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;

/**
 * Created by 肖文 on 2019/2/24.
 * 生成二维码工具类
 */
public class CreateImageUtils {

    private static Logger logger = LoggerFactory.getLogger(CreateImageUtils.class);
    /**
     * 生成二维码 二进制流
     * @param url
     * @return
     */
    public static String createImageBase64Str(String url,int width,int height){
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错级别(L--7%,M--15%,Q--25%,H--30%)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        ByteArrayOutputStream out = null;
        String binary = null;
        try{
            // 编码内容,编码类型(这里指定为二维码),生成图片宽度,生成图片高度,设置参数
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            out = new ByteArrayOutputStream();
            BufferedImage image = toBufferedImage(bitMatrix);
            ImageIO.write(image, "png", out);

            byte[] bytes = out.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            binary = "data:image/png;base64," + encoder.encodeBuffer(bytes).trim();
        }catch (Exception e){
            logger.error("生成二维码发生异常:{}",e.getMessage());
        }finally {
            IOUtils.closeQuietly(out);
        }
        return binary;
    }
}
