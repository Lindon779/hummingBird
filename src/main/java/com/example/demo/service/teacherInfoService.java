package com.example.demo.service;

import com.example.demo.dao.teacherInfoDao;
import com.example.demo.entity.Student_info;
import com.example.demo.entity.Teacher_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class teacherInfoService implements teacherInfoDao {
    @Autowired
    private teacherInfoDao teacherInfoDao;

    @Override
    public List<Teacher_info> getTeacherInfo() {
        return teacherInfoDao.getTeacherInfo();
    }

    @Override
    public int insertTeacherInfo(String name, int sex, String birthday, int level, String mobile, int login_id, String color) {
        return teacherInfoDao.insertTeacherInfo(name, sex, birthday, level, mobile, login_id, color);
    }

    @Override
    public int updateTeacherInfo(String name, int sex, String birthday, int level, String mobile, String color, int id) {
        return teacherInfoDao.updateTeacherInfo(name, sex, birthday, level, mobile, color, id);
    }

    @Override
    public Teacher_info getTeacherInfoById(int teacher_id) {
        return teacherInfoDao.getTeacherInfoById(teacher_id);
    }

    @Override
    public int updateTeacherPersonInfo(String name, int sex, String birthday, String mobile, int id) {
        return teacherInfoDao.updateTeacherPersonInfo(name, sex, birthday, mobile, id);
    }

    @Override
    public int updateTeacherImage(int login_id, String image) {
        return teacherInfoDao.updateTeacherImage(login_id, image);
    }
}
