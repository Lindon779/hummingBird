package com.example.demo.dao;

import com.example.demo.entity.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomDao {
    @Select("SELECT r.ID, r.LESSON_ID, r.TEACHER_ID, r.CREATE_TIME, r.CLASS_TIME AS START, r.LIMIT_QUANTITY, r.CURRENT_NUM, r.REMARK, l.CLASS_NAME AS NAME, l.`LEVEL`, t.`NAME` AS TEACHER_NAME, t.COLOR FROM les_room r, les_lesson l, teacher_info t WHERE r.LESSON_ID = l.ID AND r.TEACHER_ID = t.ID AND r.ISDEL = 1")
    List<Room> getRooms();

    @Insert("INSERT INTO les_room (LESSON_ID, TEACHER_ID, CLASS_TIME, LIMIT_QUANTITY, REMARK) VALUE (#{lesson_id}, #{teacher_id}, #{class_time}, #{limit_quantity}, #{remark})")
    int insertRoom(@Param("lesson_id") int lesson_id,
                   @Param("teacher_id") int teacher_id,
                   @Param("class_time") String class_time,
                   @Param("limit_quantity") int limit_quantity,
                   @Param("remark") String remark);

    @Update("UPDATE les_room SET LESSON_ID = #{lesson_id}, TEACHER_ID = #{teacher_id}, CLASS_TIME = #{class_time}, LIMIT_QUANTITY = #{limit_quantity}, REMARK = #{remark} WHERE ID = #{id}")
    int updateRoom(@Param("lesson_id") int lesson_id,
                   @Param("teacher_id") int teacher_id,
                   @Param("class_time") String class_time,
                   @Param("limit_quantity") int limit_quantity,
                   @Param("remark") String remark,
                   @Param("id") int id);

    @Update("UPDATE les_room SET ISDEL = 2 WHERE ID = #{id}")
    int delRoom(@Param("id") int id);

    @Update("UPDATE les_room SET CURRENT_NUM = CURRENT_NUM + 1 WHERE ID = #{id}")
    int addCurrentNum(@Param("id") int id);

    @Update("UPDATE les_room SET CURRENT_NUM = CURRENT_NUM - 1 WHERE ID = #{id}")
    int decCurrentNum(@Param("id") int id);

    @Select("SELECT r.ID, r.LESSON_ID, r.TEACHER_ID, r.CREATE_TIME, r.CLASS_TIME AS START, r.LIMIT_QUANTITY, r.CURRENT_NUM, r.REMARK, l.CLASS_NAME AS NAME, l.`LEVEL`, t.`NAME` AS TEACHER_NAME, t.COLOR FROM les_room r, les_lesson l, teacher_info t WHERE r.LESSON_ID = l.ID AND r.TEACHER_ID = t.ID AND r.ISDEL = 1 AND l.`LEVEL` <= #{level}")
    List<Room> getRoomByLevel(@Param("level") int level);

    @Select("SELECT r.ID, r.LESSON_ID, r.TEACHER_ID, r.CREATE_TIME, r.CLASS_TIME AS START, r.LIMIT_QUANTITY, r.CURRENT_NUM, r.REMARK, l.CLASS_NAME AS NAME, l.`LEVEL`, t.`NAME` AS TEACHER_NAME, t.COLOR FROM les_room r, les_lesson l, teacher_info t WHERE r.LESSON_ID = l.ID AND r.TEACHER_ID = t.ID AND r.ISDEL = 1 AND r.TEACHER_ID = #{teacher_id}")
    List<Room> getRoomsByTeacherId(@Param("teacher_id") int teacher_id);

    @Select("SELECT r.ID, r.LESSON_ID, r.TEACHER_ID, r.CREATE_TIME, r.CLASS_TIME AS START, r.LIMIT_QUANTITY, r.CURRENT_NUM, r.REMARK, l.CLASS_NAME AS NAME, l.`LEVEL`, t.`NAME` AS TEACHER_NAME, t.COLOR FROM les_room r, les_lesson l, teacher_info t, usr_class_record c WHERE r.LESSON_ID = l.ID AND r.TEACHER_ID = t.ID AND r.ISDEL = 1 AND c.ROOM_ID = r.ID AND c.USR_ID = #{usr_id} AND c.STATE = 1")
    List<Room> getRoomsByStudentId(@Param("usr_id") int usr_id);
}
