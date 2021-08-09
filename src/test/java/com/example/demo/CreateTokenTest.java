package com.example.demo;

import io.jsonwebtoken.Claims;

import java.util.Map;

public class CreateTokenTest {
    public static void main(String[] args) {
//        String token = JwtUtil.createJWT(
//                1,
//                1,
//                "2021-03-27 12:49:11",
//                null,
//                1000*120
//        );
//        System.out.println(token);
        try {
            Claims parseToken = JwtUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiMSIsImlhdCI6MTYxNjgzMTg4NSwidGltZSI6IjIwMjEtMDMtMjcgMTU6NTg6MDUiLCJleHAiOjE2MTY4MzU0ODV9.IKMA3OU0OnZIACUcD9URf-LPROPYw-SqDf7oKb5yYPk");
            System.out.println(parseToken.getId());
            System.out.println(parseToken.get("time"));
            System.out.println(parseToken.getSubject());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
