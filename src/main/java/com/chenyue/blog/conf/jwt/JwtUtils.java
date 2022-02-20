package com.chenyue.blog.conf.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Slf4j
public class JwtUtils {
    private static final String SECRET = "!Hs*%@)KTy37&(+1AM";

    public static String createToken(Map<String, Object> claims){
        JWTCreator.Builder builder = JWT.create();
        for (Map.Entry<String, Object> entry:
                claims.entrySet()){
            builder.withClaim(entry.getKey(), entry.getValue().toString());
        }

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 15);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static String createToken(Map<String, Object> claims, Date expireTime){
        JWTCreator.Builder builder = JWT.create();
        for (Map.Entry<String, Object> entry:
                claims.entrySet()){
            builder.withClaim(entry.getKey(), entry.getValue().toString());
        }
        builder.withExpiresAt(expireTime);
        return builder.sign(Algorithm.HMAC256(SECRET));
    }


    public static DecodedJWT verify(String token){
        log.info("-----------------验证token正常---------------");
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
}
