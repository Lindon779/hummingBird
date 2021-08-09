package com.example.demo.tools;

import com.example.demo.service.loginService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Token {

    public static String createToken(int id, int power){
        String token = JwtUtil.createJWT(
                id,
                power,
                getCurrentTime.getTime(),
                null
        );
        return token;
    }

    public static Map<String, String> analysisToken(String token){
        Map<String, String> token_info = new HashMap<>();
        Claims parseToken = JwtUtil.parseJWT(token);
        String id = parseToken.getId();
        String time = (String) parseToken.get("time");
        String power = parseToken.getSubject();
        token_info.put("id", id);
        token_info.put("time", time);
        token_info.put("power", power);
        return token_info;
    }
}
