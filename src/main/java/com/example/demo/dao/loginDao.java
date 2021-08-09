package com.example.demo.dao;

import com.example.demo.entity.Login;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface loginDao {
    @Select("SELECT * FROM usr_login WHERE MAIL = #{mail} AND ISDEL = 1")
    Login checkMail(@Param("mail") String mail);        // 查看usr_login中是否存在该用户

    @Update("UPDATE usr_login SET TOKEN = #{token} WHERE ID = #{id}")
    int updateToken(@Param("token") String token,
                    @Param("id") int id);

    @Select("SELECT TOKEN FROM usr_login WHERE ID = #{id}")
    String getLoginToken(@Param("id") int id);

    @Insert("INSERT INTO usr_login (MAIL) VALUES (#{mail})")
    int insertLogin(@Param("mail") String mail);

    @Select("SELECT ID FROM usr_login WHERE ISDEL = 1 AND MAIL = #{mail}")
    int selectIdByMail(@Param("mail") String mail);

    @Update("UPDATE usr_login SET ISDEL = 2 WHERE ID = #{id}")
    int delUserById(@Param("id") int id);

    @Update("UPDATE usr_login SET MAIL = #{mail} WHERE ID = #{id} AND ISDEL = 1")
    int updateMail(@Param("mail") String mail,
                   @Param("id") int id);

}
