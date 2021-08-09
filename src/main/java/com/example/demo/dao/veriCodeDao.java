package com.example.demo.dao;

import com.example.demo.entity.VeriCode;
import org.apache.ibatis.annotations.*;

@Mapper
public interface veriCodeDao {
    @Insert("INSERT INTO usr_veri_code (code, mail) VALUE (#{code}, #{mail})")
    int addVeriCode(@Param("code") String code,
                    @Param("mail") String mail
                           );

    @Select("SELECT * FROM usr_veri_code where mail = #{mail} AND STATE = 1 ORDER BY CREATE_TIME DESC LIMIT 1;")
    VeriCode queryCodeBYMail(@Param("mail") String mail);    // 根据邮箱去查询最新的一条未使用的验证码

    @Update("UPDATE usr_veri_code SET STATE = 2 WHERE ID = #{id}")
    int updateCodeState(@Param("id") int id);
}
