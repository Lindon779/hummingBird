package com.example.demo.dao;

import com.example.demo.entity.Student_info;
import com.example.demo.entity.Teacher_info;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface teacherInfoDao {
    @Select("SELECT t.ID, t.`NAME`, t.SEX, t.BIRTHDAY, t.`LEVEL`, t.MOBILE, t.LOGIN_ID, t.COLOR, t.IMAGE k.MAIL, k.POWER FROM teacher_info t, usr_login k WHERE k.id = t.LOGIN_ID AND k.ISDEL = 1")
    List<Teacher_info> getTeacherInfo();

    @Insert("INSERT INTO teacher_info (NAME, SEX, BIRTHDAY, LEVEL, MOBILE, LOGIN_ID, COLOR) VALUES (#{name}, #{sex}, #{birthday}, #{level}, #{mobile}, #{login_id}, #{color})")
    int insertTeacherInfo(@Param("name") String name,
                          @Param("sex") int sex,
                          @Param("birthday") String birthday,
                          @Param("level") int level,
                          @Param("mobile") String mobile,
                          @Param("login_id") int login_id,
                          @Param("color") String color);

    @Update("UPDATE teacher_info SET NAME = #{name}, SEX = #{sex}, BIRTHDAY = #{birthday}, LEVEL = #{level}, MOBILE = #{mobile}, COLOR = #{color} WHERE ID = #{id}")
    int updateTeacherInfo(@Param("name") String name,
                          @Param("sex") int sex,
                          @Param("birthday") String birthday,
                          @Param("level") int level,
                          @Param("mobile") String mobile,
                          @Param("color") String color,
                          @Param("id") int id);

    @Select("SELECT t.ID, t.`NAME`, t.SEX, t.BIRTHDAY, t.`LEVEL`, t.MOBILE, t.LOGIN_ID, t.COLOR, t.IMAGE, k.MAIL, k.POWER FROM teacher_info t, usr_login k WHERE k.id = t.LOGIN_ID AND k.ISDEL = 1 AND k.ID = #{teacher_id}")
    Teacher_info getTeacherInfoById(@Param("teacher_id") int teacher_id);

    @Update("UPDATE teacher_info SET NAME = #{name}, SEX = #{sex}, BIRTHDAY = #{birthday}, MOBILE = #{mobile} WHERE ID = #{id}")
    int updateTeacherPersonInfo(@Param("name") String name,
                          @Param("sex") int sex,
                          @Param("birthday") String birthday,
                          @Param("mobile") String mobile,
                          @Param("id") int id);

    @Update("UPDATE teacher_info SET IMAGE = #{image} WHERE LOGIN_ID = #{login_id}")
    int updateTeacherImage(@Param("login_id") int login_id,
                           @Param("image") String image);
}
