package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Room {
    private int id;
    private int lesson_id;
    private int teacher_id;
    private String create_time;
    private String start;
    private int limit_quantity;
    private int current_num;
    private String remark;
    private String name;
    private int level;
    private String teacher_name;
    private String color;
    private List<String> students;
    private int percentage;
}
