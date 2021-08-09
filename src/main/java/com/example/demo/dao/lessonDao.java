package com.example.demo.dao;

import com.example.demo.entity.Lesson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface lessonDao {
    @Select("SELECT * FROM les_lesson WHERE ISDEL = 1")
    List<Lesson> getLesson();

    @Insert("INSERT INTO les_lesson (CLASS_NAME, LEVEL) VALUES (#{class_name}, #{level})")
    int insertLesson(@Param("class_name") String class_name,
                     @Param("level") int level);

    @Update("UPDATE les_lesson SET ISDEL = 2 WHERE ID = #{id}")
    int delLesson(@Param("id") int id);

    @Update("UPDATE les_lesson SET CLASS_NAME = #{class_name}, LEVEL = #{level} WHERE ID = #{id}")
    int updateLesson(@Param("class_name") String class_name,
                     @Param("level") int level,
                     @Param("id") int id);
}
