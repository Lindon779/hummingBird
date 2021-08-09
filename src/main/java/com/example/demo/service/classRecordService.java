package com.example.demo.service;

import com.example.demo.dao.RoomDao;
import com.example.demo.dao.classRecordDao;
import com.example.demo.entity.AppointStudents;
import com.example.demo.entity.Class_record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class classRecordService implements classRecordDao {
    @Autowired
    private classRecordDao classRecordDao;
    @Autowired
    private RoomDao roomDao;

    @Override
    public List<Class_record> getClassRecord() {
        return classRecordDao.getClassRecord();
    }

    @Override
    public int insertClassRecord(int usr_id, int room_id) {
        int i = classRecordDao.insertClassRecord(usr_id, room_id);
        if (i == 1){
            return roomDao.addCurrentNum(room_id);
        }
        else {
            return 0;
        }

    }

    @Override
    public int delClassRecord(int usr_id, int room_id) {
        int i = classRecordDao.delClassRecord(usr_id, room_id);
        if (i == 1){
            return roomDao.decCurrentNum(room_id);
        }
        else {
            return 0;
        }
    }

    @Override
    public int selectRoomById(int id) {
        return classRecordDao.selectRoomById(id);
    }

    @Override
    public List<AppointStudents> getAppointStudents() {
        return classRecordDao.getAppointStudents();
    }

    @Override
    public List<Integer> getAppointByUsrId(int id) {
        return classRecordDao.getAppointByUsrId(id);
    }

}
