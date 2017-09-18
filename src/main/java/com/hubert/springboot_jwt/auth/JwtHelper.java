package com.hubert.springboot_jwt.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * Created by Hubrt on 2017/9/12.
 */
public class JwtHelper {

    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    /**
     * 创建JWT
     * @param loginName  用户名
     * @param issuer     签发者
     * @param TTLMillis  过期时间
     * @param base64Security   密钥
     * @return
     */
    public static String createJWT(String loginName,
                                   String issuer, long TTLMillis, String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        SecretKey secretKey = generalKey();

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                //.setId(id)                // JWT_ID
                //.setSubject("")           // 主题
                //.claim("unique_name", name)
                .claim("loginName", loginName)   // 自定义属性
                .setIssuer(issuer)    //签发者
                .setIssuedAt(now)        // 签发时间
                .setNotBefore(now)       // 失效时间
                //.setExpiration(long)          // 过期时间
                //.setAudience(audience)     //接受者
                .signWith(signatureAlgorithm, secretKey);  //签名算法以及密匙

        //添加Token过期时间
        if (TTLMillis >= 0) {
            //过期时间
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            //系统时间之前的token都是不可以被承认的
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64("123");
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

}
