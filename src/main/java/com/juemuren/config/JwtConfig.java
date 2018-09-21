package com.juemuren.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Created by 肖文 on 2018/6/21
 */
@Component
public class JwtConfig {

    private Logger logger = LoggerFactory.getLogger(JwtConfig.class);
    private String key = "28b21c1cfd&Test*";
    private Long ttl = 12000000000L;

    /**
     * 生成签名的Key
     * @return
     */
    private SecretKey generalKey() {
        Base64UrlCodec base64UrlCodec = new Base64UrlCodec();
        byte[] encodedKey = base64UrlCodec.decode(key);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 生成JWT
     * @param id
     * @return
     */
    public String createJWT(String id) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, generalKey());
        if (ttl > 0) {
            long expMillis = nowMillis + ttl;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 检验Token是否
     * @param compactJws
     * @return
     */
    public Claims checkJWT(String compactJws) {
        Claims result = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(compactJws).getBody();
            long expTime = claims.getExpiration().getTime();
            long nowTime = System.currentTimeMillis();
            if (expTime > nowTime) {
                result = claims;
            }
        } catch (Exception e) {
            result = null;
            logger.error("检验Token是否有效异常{}", e);
        }
        return result;
    }
}
