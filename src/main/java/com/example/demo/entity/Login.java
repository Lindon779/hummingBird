package com.example.demo.entity;

import lombok.Data;

@Data
public class Login {
    private int id;
    private String mail;
    private int isDel;
    private int power;
    private String token;
}
