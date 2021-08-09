package com.example.demo.service;

import com.example.demo.dao.RoomDao;
import com.example.demo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements RoomDao {
    @Autowired
    private RoomDao roomDao;

    @Override
    public List<Room> getRooms() {
        return roomDao.getRooms();
    }

    @Override
    public int insertRoom(int lesson_id, int teacher_id, String class_time, int limit_quantity, String remark) {
        return roomDao.insertRoom(lesson_id, teacher_id, class_time, limit_quantity, remark);
    }

    @Override
    public int updateRoom(int lesson_id, int teacher_id, String class_time, int limit_quantity, String remark, int id) {
        return roomDao.updateRoom(lesson_id, teacher_id, class_time, limit_quantity, remark, id);
    }

    @Override
    public int delRoom(int id) {
        return roomDao.delRoom(id);
    }

    @Override
    public int addCurrentNum(int id) {
        return roomDao.addCurrentNum(id);
    }

    @Override
    public int decCurrentNum(int id) {
        return roomDao.decCurrentNum(id);
    }

    @Override
    public List<Room> getRoomByLevel(int level) {
        return roomDao.getRoomByLevel(level);
    }

    @Override
    public List<Room> getRoomsByTeacherId(int teacher_id) {
        return roomDao.getRoomsByTeacherId(teacher_id);
    }

    @Override
    public List<Room> getRoomsByStudentId(int usr_id) {
        return roomDao.getRoomsByStudentId(usr_id);
    }
}
