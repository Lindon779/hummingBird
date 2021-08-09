package com.example.demo.entity;

import lombok.Data;

@Data
public class VeriCode {
    private int id;
    private String code;
    private String mail;
    private int state;
    private String create_time;
}
