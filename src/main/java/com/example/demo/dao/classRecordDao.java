package com.example.demo.dao;

import com.example.demo.entity.AppointStudents;
import com.example.demo.entity.Class_record;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface classRecordDao {
    @Select("SELECT r.ID, r.ROOM_ID, r.USR_ID, r.APPOINTMENT_TIME, r.REPORT_URL, r.STATE, l.CLASS_NAME, m.CLASS_TIME, u.`NAME` AS STUDENT_NAME, t.NAME AS TEACHER_NAME FROM usr_class_record r, les_room m, student_info u, teacher_info t, les_lesson l WHERE r.USR_ID = u.ID AND r.ROOM_ID = m.ID AND m.LESSON_ID = l.ID AND m.TEACHER_ID = t.ID;")
    List<Class_record> getClassRecord();

    @Insert("INSERT INTO usr_class_record (USR_ID, ROOM_ID) VALUES (#{usr_id}, #{room_id})")
    int insertClassRecord(@Param("usr_id") int usr_id,
                          @Param("room_id") int room_id);

    @Update("UPDATE usr_class_record SET STATE = 2 WHERE USR_ID = #{usr_id} AND ROOM_ID = #{room_id} AND STATE = 1")
    int delClassRecord(@Param("usr_id") int id,
                       @Param("room_id") int room_id);

    @Select("SELECT ROOM_ID FROM usr_class_record WHERE ID = #{id}")
    int selectRoomById(@Param("id") int id);

    @Select("SELECT r.ROOM_ID, s.`NAME` AS STUDENT_NAME FROM usr_class_record r, student_info s WHERE r.STATE = 1 AND r.USR_ID = s.ID")
    List<AppointStudents> getAppointStudents();

    @Select("SELECT ROOM_ID FROM usr_class_record WHERE USR_ID = #{id} AND STATE = 1")
    List<Integer> getAppointByUsrId(@Param("id") int id);

}
