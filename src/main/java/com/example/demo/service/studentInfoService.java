package com.example.demo.service;

import com.example.demo.dao.studentInfoDao;
import com.example.demo.entity.Student_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentInfoService implements studentInfoDao {
    @Autowired
    private studentInfoDao studentInfoDao;

    @Override
    public List<Student_info> getStudentInfo() {
        return studentInfoDao.getStudentInfo();
    }

    @Override
    public int insertStudentInfo(String name, int sex, String birthday, int level, String mobile, int login_id, int class_hour) {
        return studentInfoDao.insertStudentInfo(name, sex, birthday, level, mobile, login_id, class_hour);
    }

    @Override
    public int updateStudentInfo(String name, int sex, String birthday, int level, String mobile, int class_hour, int id) {
        return studentInfoDao.updateStudentInfo(name, sex, birthday, level, mobile, class_hour, id);
    }

    @Override
    public Student_info getStudentInfoById(int student_id) {
        return studentInfoDao.getStudentInfoById(student_id);
    }

    @Override
    public int updateStudentPersonInfo(String name, int sex, String birthday, String mobile, int id) {
        return studentInfoDao.updateStudentPersonInfo(name, sex, birthday, mobile, id);
    }

    @Override
    public int updateStduentImage(int login_id, String image) {
        return studentInfoDao.updateStduentImage(login_id, image);
    }
}
