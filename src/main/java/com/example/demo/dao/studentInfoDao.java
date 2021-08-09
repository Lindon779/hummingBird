package com.example.demo.dao;

import com.example.demo.entity.Student_info;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface studentInfoDao {
    @Select("SELECT t.ID, t.`NAME`, t.SEX, t.BIRTHDAY, t.`LEVEL`, t.MOBILE, t.login_id, t.class_hour, t.image, k.MAIL, k.POWER FROM student_info t, usr_login k WHERE k.id = t.LOGIN_ID AND k.ISDEL = 1")
    List<Student_info> getStudentInfo();

    @Insert("INSERT INTO student_info (NAME, SEX, BIRTHDAY, LEVEL, MOBILE, LOGIN_ID, CLASS_HOUR) VALUES (#{name}, #{sex}, #{birthday}, #{level}, #{mobile}, #{login_id}, #{class_hour})")
    int insertStudentInfo(@Param("name") String name,
                          @Param("sex") int sex,
                          @Param("birthday") String birthday,
                          @Param("level") int level,
                          @Param("mobile") String mobile,
                          @Param("login_id") int login_id,
                          @Param("class_hour") int class_hour);

    @Update("UPDATE student_info SET NAME = #{name}, SEX = #{sex}, BIRTHDAY = #{birthday}, LEVEL = #{level}, MOBILE = #{mobile}, CLASS_HOUR = #{class_hour} WHERE ID = #{id}")
    int updateStudentInfo(@Param("name") String name,
                          @Param("sex") int sex,
                          @Param("birthday") String birthday,
                          @Param("level") int level,
                          @Param("mobile") String mobile,
                          @Param("class_hour") int class_hour,
                          @Param("id") int id);

    @Select("SELECT t.ID, t.`NAME`, t.SEX, t.BIRTHDAY, t.`LEVEL`, t.MOBILE, t.login_id, t.class_hour, t.image, k.MAIL, k.POWER FROM student_info t, usr_login k WHERE k.id = t.LOGIN_ID AND k.ISDEL = 1 AND k.ID = #{student_id}")
    Student_info getStudentInfoById(@Param("student_id") int student_id);

    @Update("UPDATE student_info SET NAME = #{name}, SEX = #{sex}, BIRTHDAY = #{birthday}, MOBILE = #{mobile} WHERE ID = #{id}")
    int updateStudentPersonInfo(@Param("name") String name,
                          @Param("sex") int sex,
                          @Param("birthday") String birthday,
                          @Param("mobile") String mobile,
                          @Param("id") int id);

    @Update("UPDATE student_info SET IMAGE = #{image} WHERE LOGIN_ID = #{login_id}")
    int updateStduentImage(@Param("login_id") int login_id,
                           @Param("image") String image);
}
