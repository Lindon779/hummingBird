package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class JwtUtil {
    private static String key = "598d4c200461b81522a3328565c25f7c";
    private static long ttl = 3 * 60 * 60 * 1000;  //3个小时

    public static String createJWT(int id, int power, String time, Map<String, Object> claims, long ttl){
        JwtUtil.ttl = ttl;
        return createJWT(id, power, time, claims);
    }

    public static String createJWT(int id, int power, String time, Map<String, Object> claims){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(String.valueOf(id))
                .setSubject(String.valueOf(power))
                .setIssuedAt(now)
                .claim("time", time)
                .signWith(SignatureAlgorithm.HS256, key);
        if (claims != null){
            builder.setClaims(claims);
        }
        if (ttl > 0){
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    public static Claims parseJWT(String jwtStr){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}
